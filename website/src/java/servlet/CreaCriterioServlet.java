package servlet;

import bean.Criterio;
import ejb.CriterioEJB;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreaCriterioServlet extends HttpServlet {

    @Inject
    private CriterioEJB criterioEJB;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Boolean isAdminLogged = (Boolean) session.getAttribute("loginAdmin");

        if (isAdminLogged) {
            String nomeCriterio = request.getParameter("nomeCriterio");
            Integer maxPunteggio = Integer.parseInt(request.getParameter("maxPunteggio"));

            Criterio criterio = new Criterio(nomeCriterio, maxPunteggio);

            criterio = criterioEJB.creaCriterio(criterio);

            if (criterio != null) {
                RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/dashboardAdmin.jsp");
                dispatcher.forward(request, response);
            } else {
                RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/creaCriterio.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/dashboardAdmin.jsp");
            dispatcher.forward(request, response);
        }
    }

}
