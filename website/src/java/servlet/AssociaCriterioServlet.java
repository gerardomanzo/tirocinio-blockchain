package servlet;

import bean.Criterio;
import bean.Votazione;
import ejb.VotazioneEJB;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AssociaCriterioServlet extends HttpServlet {

    @Inject
    private VotazioneEJB votazioneEJB;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Boolean isAdminLogged = (Boolean) session.getAttribute("loginAdmin");

        if (isAdminLogged) {
            List<Votazione> lista = votazioneEJB.cercaVotazioni();

            session.removeAttribute("votazioni");
            session.setAttribute("votazioni", lista);

            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/sceltaVotazione.jsp");
            dispatcher.forward(request, response);

        } else {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/dashboardAdmin.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Boolean isAdminLogged = (Boolean) session.getAttribute("loginAdmin");

        if (isAdminLogged) {

            String idVotazione = request.getParameter("idVotazione");

            List<Criterio> lista = votazioneEJB.cercaTuttiCriteri(idVotazione);

            session.removeAttribute("criteri");
            session.setAttribute("criteri", lista);

            session.removeAttribute("idVotazione");
            session.setAttribute("idVotazione", idVotazione);

            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/sceltaCriterio.jsp");
            dispatcher.forward(request, response);

        } else {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/dashboardAdmin.jsp");
            dispatcher.forward(request, response);
        }
    }

}
