package servlet;

import bean.Criterio;
import bean.Evento;
import ejb.EventoEJB;
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
    private EventoEJB eventoEJB;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Boolean isAdminLogged = (Boolean) session.getAttribute("loginAdmin");

        if (isAdminLogged) {
            List<Evento> lista = eventoEJB.cercaEventi();

            session.removeAttribute("eventi");
            session.setAttribute("eventi", lista);

            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/sceltaEvento.jsp");
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

            String idEvento = request.getParameter("idEvento");

            List<Criterio> lista = eventoEJB.cercaTuttiCriteri(idEvento);

            session.removeAttribute("criteri");
            session.setAttribute("criteri", lista);

            session.removeAttribute("idEvento");
            session.setAttribute("idEvento", idEvento);

            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/sceltaCriterio.jsp");
            dispatcher.forward(request, response);

        } else {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/dashboardAdmin.jsp");
            dispatcher.forward(request, response);
        }
    }

}
