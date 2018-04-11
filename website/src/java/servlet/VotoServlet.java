package servlet;

import bean.Criterio;
import bean.Votazione;
import bean.Candidatura;
import bean.Utente;
import ejb.VotazioneEJB;
import ejb.PartecipazioneEJB;
import ejb.VotoEJB;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class VotoServlet extends HttpServlet {

    @Inject
    private VotoEJB votoEJB;

    @Inject
    private VotazioneEJB votazioneEJB;

    @Inject
    private PartecipazioneEJB partecipazioneEJB;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Utente utente = (Utente) session.getAttribute("utente");

        if (utente != null) {
            List<Votazione> lista = votoEJB.cercaVotazioniVoto(utente.getIdUtente());

            session.removeAttribute("votazioni");
            session.setAttribute("votazioni", lista);

            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/votaVotazione.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/index.html");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Utente utente = (Utente) session.getAttribute("utente");

        if (utente != null) {

            String idVotazione = request.getParameter("idVotazione");

            List<Criterio> listaCriteri = votazioneEJB.cercaCriteriVotazione(idVotazione);

            List<Candidatura> listaCandidature = partecipazioneEJB.cercaCandidatureVotazione(idVotazione);

            session.removeAttribute("idVotazione");
            session.setAttribute("idVotazione", idVotazione);

            session.removeAttribute("criteri");
            session.setAttribute("criteri", listaCriteri);

            session.removeAttribute("candidature");
            session.setAttribute("candidature", listaCandidature);

            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/votaCandidatura.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/index.html");
            dispatcher.forward(request, response);
        }
    }

}
