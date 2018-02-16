package servlet;

import bean.Partecipazione;
import ejb.PartecipazioneEJB;

import java.io.IOException;

import java.util.List;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ConfermaPartecipazioneServlet extends HttpServlet {

    @Inject
    private PartecipazioneEJB partecipazioneEJB;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Boolean isAdminLogged = (Boolean) session.getAttribute("loginAdmin");

        if (isAdminLogged) {
            List<Partecipazione> lista = partecipazioneEJB.cercaNonRegistrati();

            session.removeAttribute("partecipazioneNonRegistrati");
            session.setAttribute("partecipazioneNonRegistrati", lista);

            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/confermaPartecipazione.jsp");
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

            String id = request.getParameter("idPartecipazione");

            if (id != null && !id.equals("")) {
                partecipazioneEJB.confermaPartecipazione(id);
            }
            List<Partecipazione> lista = partecipazioneEJB.cercaNonRegistrati();

            session.removeAttribute("partecipazioneNonRegistrati");
            session.setAttribute("partecipazioneNonRegistrati", lista);

            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/confermaPartecipazione.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/dashboardAdmin.jsp");
            dispatcher.forward(request, response);
        }
    }

}
