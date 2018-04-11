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
            String idVotazione = (String) session.getAttribute("idVotazione");

            Integer numcandidature = Integer.parseInt(request.getParameter("numCandidature"));
            Integer numCriteri = Integer.parseInt(request.getParameter("numCriteri"));

            for (int i = 0; i < numcandidature; i++) {
                String idCandidatura = request.getParameter("idCandidatura" + i);

                Partecipazione p = partecipazioneEJB.cercaPartecipazione(idVotazione, idCandidatura);

                System.out.println("idVotazione " + idVotazione);
                System.out.println("idCandidatura " + idCandidatura);

                System.out.println("idPartecipazione " + p.getIdPartecipazione());
                System.out.println("idVotazione " + p.getIdVotazione());
                System.out.println("idCandidatura " + p.getIdCandidatura());

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
