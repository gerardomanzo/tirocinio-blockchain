package servlet;

import ejb.VotazioneEJB;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ConfermaCriterioServlet extends HttpServlet {

    @Inject
    private VotazioneEJB votazioneEJB;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Boolean isAdminLogged = (Boolean) session.getAttribute("loginAdmin");

        if (isAdminLogged) {

            String idVotazione = (String) session.getAttribute("idVotazione");

            String idCriterio = request.getParameter("idCriterio");

            votazioneEJB.aggiungiCriterio(idVotazione, idCriterio);

            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/dashboardAdmin.jsp");
            dispatcher.forward(request, response);

        } else {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/dashboardAdmin.jsp");
            dispatcher.forward(request, response);
        }
    }

}
