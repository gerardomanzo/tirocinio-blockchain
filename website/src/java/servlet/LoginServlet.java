package servlet;

import bean.Utente;
import ejb.PasswordHash;
import ejb.UtenteEJB;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

    @Inject
    private UtenteEJB utenteEJB;

    @Inject
    private PasswordHash pwHash;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = pwHash.hash(request.getParameter("password"));

        Utente utente = new Utente();
        utente.setEmail(email);
        utente.setPassword(password);

        utente = utenteEJB.cercaUtente(utente);

        if (utente != null) {
            HttpSession session = request.getSession();
            session.removeAttribute("utente");
            session.setAttribute("utente", utente);

            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/dashboardUtente.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/index.html");
            dispatcher.forward(request, response);
        }
    }
}
