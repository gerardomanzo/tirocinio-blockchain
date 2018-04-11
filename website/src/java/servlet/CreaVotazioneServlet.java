package servlet;

import bean.Votazione;
import ejb.VotazioneEJB;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreaVotazioneServlet extends HttpServlet {

    @Inject
    private VotazioneEJB votazioneEJB;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Boolean isAdminLogged = (Boolean) session.getAttribute("loginAdmin");

        if (isAdminLogged) {
            String nomeVotazione = request.getParameter("nomeVotazione");
            String descrizione = request.getParameter("descrizione");

            String dataInizio = request.getParameter("dataInizio");
            String dataFine = request.getParameter("dataFine");

            Votazione votazione = new Votazione(nomeVotazione, descrizione, dataInizio, dataFine);

            votazione = votazioneEJB.creaVotazione(votazione);

            if (votazione != null) {
                RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/dashboardAdmin.jsp");
                dispatcher.forward(request, response);
            } else {
                RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/creaVotazione.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/dashboardAdmin.jsp");
            dispatcher.forward(request, response);
        }
    }
}
