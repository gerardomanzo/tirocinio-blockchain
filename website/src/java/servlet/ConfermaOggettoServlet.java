package servlet;

import bean.Oggetto;
import ejb.OggettoEJB;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ConfermaOggettoServlet extends HttpServlet {

    @Inject
    private OggettoEJB oggettoEJB;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        Boolean isAdminLogged = (Boolean) session.getAttribute("loginAdmin");

        if (isAdminLogged) {
            List<Oggetto> lista = oggettoEJB.cercaOggettiNonRegistrati();

            session.removeAttribute("oggettiNonRegistrati");
            session.setAttribute("oggettiNonRegistrati", lista);

            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/confermaOggetto.jsp");
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
            String idOggetto = request.getParameter("idOggetto");

            if (idOggetto != null && !idOggetto.equals("")) {
                oggettoEJB.confermaOggetto(idOggetto);
            }

            List<Oggetto> lista = oggettoEJB.cercaOggettiNonRegistrati();

            session.removeAttribute("oggettiNonRegistrati");
            session.setAttribute("oggettiNonRegistrati", lista);

            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/confermaOggetto.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/dashboardAdmin.jsp");
            dispatcher.forward(request, response);
        }
    }

}
