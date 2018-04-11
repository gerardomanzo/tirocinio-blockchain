package servlet;

import bean.Criterio;
import bean.Votazione;
import bean.Candidatura;
import bean.Partecipazione;
import bean.Utente;
import bean.Voto;
import ejb.CriterioEJB;
import ejb.VotazioneEJB;
import ejb.CandidaturaEJB;
import ejb.PartecipazioneEJB;
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

public class RisultatiServlet extends HttpServlet {

    @Inject
    private PartecipazioneEJB partecipazioneEJB;
    @Inject
    private VotoEJB votoEJB;
    @Inject
    private VotazioneEJB votazioneEJB;
    @Inject
    private CriterioEJB criterioEJB;
    @Inject 
    private CandidaturaEJB candidaturaEJB;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");

        if (utente != null) {
            List<Votazione> lista = votazioneEJB.cercaVotazioni();
            session.removeAttribute("votazioni");
            session.setAttribute("votazioni", lista);

            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/visualizzaVotazioni.jsp");
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

        if (utente != null) {
            String idVotazione = request.getParameter("idVotazione");

            List<Partecipazione> partecipazioni = partecipazioneEJB.cercaPartecipazioniByIdVotazione(idVotazione);
            List<Voto> voti = votoEJB.cercaVotoByIdPartecipation(partecipazioni);
            List<Voto> votiUtente = new ArrayList<>();

            String idUtente = utente.getIdUtente();

            for (Voto v : voti) {
                if (v.getUtente().contains(idUtente)) {
                    votiUtente.add(v);
                }
            }

            List<Criterio> criteri = votazioneEJB.cercaCriteriVotazione(idVotazione);
            List<Candidatura> candidature = candidaturaEJB.cercaGliCandidature();

            session.removeAttribute("idVotazione");
            session.setAttribute("idVotazione", idVotazione);

            session.removeAttribute("voti");
            session.setAttribute("voti", votiUtente);
            
            session.removeAttribute("criteri");
            session.setAttribute("criteri", criteri);
            
            session.removeAttribute("partecipazioni");
            session.setAttribute("partecipazioni", partecipazioni);
            
            session.removeAttribute("candidature");
            session.setAttribute("candidature", candidature);

            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/visualizzaRisultati.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/dashboardUtente.jsp");
            dispatcher.forward(request, response);
        }

    }
}
