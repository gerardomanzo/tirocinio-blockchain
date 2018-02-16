package servlet;

import bean.Utente;
import ejb.UtenteEJB;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ConfermaUtenteServlet extends HttpServlet {

    @Inject
    private UtenteEJB utenteEJB;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Boolean isAdminLogged = (Boolean) session.getAttribute("loginAdmin");

        if (isAdminLogged) {
            List<Utente> lista = utenteEJB.cercaNonRegistrati();

            session.removeAttribute("utentiNonRegistrati");
            session.setAttribute("utentiNonRegistrati", lista);

            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/confermaUtente.jsp");
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

            String idUtente = request.getParameter("idUtente");

            if (idUtente != null && !idUtente.equals("")) {
                utenteEJB.confermaUtente(idUtente);
            }
            List<Utente> lista = utenteEJB.cercaNonRegistrati();

            session.removeAttribute("utentiNonRegistrati");
            session.setAttribute("utentiNonRegistrati", lista);

            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/confermaUtente.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/dashboardAdmin.jsp");
            dispatcher.forward(request, response);
        }
    }

}
