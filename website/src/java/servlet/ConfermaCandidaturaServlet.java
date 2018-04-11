package servlet;

import bean.Candidatura;
import ejb.CandidaturaEJB;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ConfermaCandidaturaServlet extends HttpServlet {

    @Inject
    private CandidaturaEJB candidaturaEJB;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        Boolean isAdminLogged = (Boolean) session.getAttribute("loginAdmin");

        if (isAdminLogged) {
            List<Candidatura> lista = candidaturaEJB.cercaCandidatureNonRegistrati();

            session.removeAttribute("candidatureNonRegistrati");
            session.setAttribute("candidatureNonRegistrati", lista);

            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/confermaCandidatura.jsp");
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
            String idCandidatura = request.getParameter("idCandidatura");

            if (idCandidatura != null && !idCandidatura.equals("")) {
                candidaturaEJB.confermaCandidatura(idCandidatura);
            }

            List<Candidatura> lista = candidaturaEJB.cercaCandidatureNonRegistrati();

            session.removeAttribute("candidatureNonRegistrati");
            session.setAttribute("candidatureNonRegistrati", lista);

            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/confermaCandidatura.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/dashboardAdmin.jsp");
            dispatcher.forward(request, response);
        }
    }

}
