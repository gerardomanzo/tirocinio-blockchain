package servlet;

import bean.Utente;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        Boolean isAdminLogged = (Boolean) session.getAttribute("loginAdmin");

        Utente utente = (Utente) session.getAttribute("utente");

        session.invalidate();

        if (isAdminLogged != null && isAdminLogged) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/dashboardAdmin.jsp");
            dispatcher.forward(request, response);
        } else if (utente != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.html");
            dispatcher.forward(request, response);
        }

    }
}
