package servlet;

import bean.Partecipazione;
import bean.Utente;
import bean.Voto;
import ejb.PartecipazioneEJB;
import ejb.VotoEJB;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ConfermaVotiServlet extends HttpServlet {

    @Inject
    private PartecipazioneEJB partecipazioneEJB;

    @Inject
    private VotoEJB votoEJB;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Utente utente = (Utente) session.getAttribute("utente");

        if (utente != null) {
            String idEvento = (String) session.getAttribute("idEvento");

            Integer numOggetti = Integer.parseInt(request.getParameter("numOggetti"));
            Integer numCriteri = Integer.parseInt(request.getParameter("numCriteri"));

            for (int i = 0; i < numOggetti; i++) {
                String idOggetto = request.getParameter("idOggetto" + i);

                Partecipazione p = partecipazioneEJB.cercaPartecipazione(idEvento, idOggetto);

                System.out.println("idEvento " + idEvento);
                System.out.println("idOggetto " + idOggetto);

                System.out.println("idPartecipazione " + p.getIdPartecipazione());
                System.out.println("idEvento " + p.getIdEvento());
                System.out.println("idOggetto " + p.getIdOggetto());

                for (int j = 0; j < numCriteri; j++) {
                    String idCriterio = request.getParameter("idCriterio" + i + "" + j);

                    System.out.println("idCriterio " + idCriterio);
                    
                    Voto v = new Voto(p.getIdPartecipazione(), idCriterio, utente.getIdUtente());

                    Integer punteggio = Integer.parseInt(request.getParameter("criterio" + i + "" + j));
                    
                    System.out.println("punteggio " + punteggio);
                    
                    v.setPunteggio(punteggio);

                    votoEJB.creaVoto(v);
                }
                
            }
             RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/dashboardUtente.jsp");
             dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/index.html");
            dispatcher.forward(request, response);
        }
    }
}
