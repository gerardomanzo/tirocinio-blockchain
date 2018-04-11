package servlet;

import bean.Partecipazione;
import bean.Utente;
import bean.Voto;
import ejb.PartecipazioneEJB;
import ejb.UtenteEJB;
import ejb.VotoEJB;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RisultatiTotali extends HttpServlet {

    @Inject
    private PartecipazioneEJB partecipazioneEJb;
    @Inject
    private VotoEJB votoEJB;
    @Inject
    private UtenteEJB utenteEJB;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");

        if (utente != null) {
            String idVotazione = (String) session.getAttribute("idVotazione");
            List<Partecipazione> partecipazioni = partecipazioneEJb.cercaPartecipazioniByIdVotazione(idVotazione);

            List<Voto> voti = votoEJB.cercaVotoByIdPartecipation(partecipazioni);
            List<Utente> utenti = utenteEJB.cercaTuttiGliUtenti();
            List<Utente> votanti = new ArrayList<>();

            Boolean trovato;

            for (Utente e : utenti) {
                trovato = false;

                for (int i = 0; i < voti.size() && !trovato; i++) {
                    if (voti.get(i).getUtente().contains(e.getIdUtente())) {
                        votanti.add(e);
                        trovato = true;
                    }
                }
            }

            session.removeAttribute("voti");
            session.setAttribute("voti", voti);

            session.removeAttribute("parteciapazioni");
            session.setAttribute("partecipazioni", partecipazioni);

            session.removeAttribute("utenti");
            session.setAttribute("utenti", votanti);
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/totaliVoti.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/dashboardUtente.jsp");
            dispatcher.forward(request, response);
        }
    }
}
