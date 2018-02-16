package servlet;

import bean.Evento;
import ejb.EventoEJB;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreaEventoServlet extends HttpServlet {

    @Inject
    private EventoEJB eventoEJB;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Boolean isAdminLogged = (Boolean) session.getAttribute("loginAdmin");

        if (isAdminLogged) {
            String nomeEvento = request.getParameter("nomeEvento");
            String descrizione = request.getParameter("descrizione");

            String dataInizio = request.getParameter("dataInizio");
            String dataFine = request.getParameter("dataFine");

            Evento evento = new Evento(nomeEvento, descrizione, dataInizio, dataFine);

            evento = eventoEJB.creaEvento(evento);

            if (evento != null) {
                RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/dashboardAdmin.jsp");
                dispatcher.forward(request, response);
            } else {
                RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/creaEvento.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/dashboardAdmin.jsp");
            dispatcher.forward(request, response);
        }
    }
}
