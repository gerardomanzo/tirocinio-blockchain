package servlet;

import bean.Votazione;
import bean.Candidatura;
import bean.Partecipazione;
import bean.Utente;
import ejb.VotazioneEJB;
import ejb.CandidaturaEJB;
import ejb.PartecipazioneEJB;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PartecipazioneServlet extends HttpServlet {

    @Inject
    private VotazioneEJB votazioneEJB;
    @Inject
    private CandidaturaEJB candidaturaEJB;
    @Inject
    private PartecipazioneEJB partecipazioneEJB;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Utente utente = (Utente) session.getAttribute("utente");

        if (utente != null) {
            List<Votazione> lista = votazioneEJB.cercaVotazioni();

            session.removeAttribute("votazioni");
            session.setAttribute("votazioni", lista);

            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/selezionaVotazione.jsp");
            dispatcher.forward(request, response);

        } else {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/dashboardUtente.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Utente utente = (Utente) session.getAttribute("utente");
        String idVotazione;

        String action = request.getParameter("action");
        if (utente != null) {
            if (action.equalsIgnoreCase("visualizzaCandidature")) {
                idVotazione = request.getParameter("idVotazione");

                List<Candidatura> lista = candidaturaEJB.cercaTutteLeCandidature(idVotazione, utente.getIdUtente());

                session.removeAttribute("candidature");
                session.setAttribute("candidature", lista);

                session.removeAttribute("idVotazione");
                session.setAttribute("idVotazione", idVotazione);

                RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/selezionaCandidatura.jsp");
                dispatcher.forward(request, response);

            } else if (action.equalsIgnoreCase("InserisciPartecipazione")) {
                idVotazione = (String) session.getAttribute("idVotazione");

                String idCandidatura = request.getParameter("idCandidatura");

                Partecipazione part = new Partecipazione(idVotazione, idCandidatura);
                part = partecipazioneEJB.creaPartecipazione(part);

                if (part != null) {
                    RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/dashboardUtente.jsp");
                    dispatcher.forward(request, response);
                } else {
                    RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/selezionaCandidatura.jsp");
                    dispatcher.forward(request, response);
                }
            }

        } else {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/dashboardUtente.jsp");
            dispatcher.forward(request, response);
        }

    }
}
