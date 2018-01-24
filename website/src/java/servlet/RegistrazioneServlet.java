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

public class RegistrazioneServlet extends HttpServlet {

    @Inject
    private UtenteEJB utenteEJB;

    @Inject
    private PasswordHash pwHash;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nomeUtente = request.getParameter("nomeUtente");
        String email = request.getParameter("email");
        String password = pwHash.hash(request.getParameter("password"));

        Utente utente = new Utente(nomeUtente, email, password);

        utente = utenteEJB.creaUtente(utente);

        if (utente != null) {
            HttpSession session = request.getSession();
            session.removeAttribute("utente");
            session.setAttribute("utente", utente);

            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/dashboardUtente.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/registrazione.html");
            dispatcher.forward(request, response);
        }
    }

}
