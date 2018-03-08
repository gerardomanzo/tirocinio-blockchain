package servlet;

import bean.Evento;
import bean.Oggetto;
import bean.Partecipazione;
import bean.Utente;
import ejb.EventoEJB;
import ejb.OggettoEJB;
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

public class PartecipazioneServlet extends HttpServlet {

    @Inject
    private EventoEJB eventoEJB;
    @Inject
    private OggettoEJB oggettoEJB;
    @Inject
    private PartecipazioneEJB partecipazioneEJB;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Utente utente = (Utente) session.getAttribute("utente");

        if (utente != null) {
            List<Evento> lista = eventoEJB.cercaEventi();

            session.removeAttribute("eventi");
            session.setAttribute("eventi", lista);

            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/selezionaEvento.jsp");
            dispatcher.forward(request, response);

        } else {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/dashboardUtente.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Utente utente = (Utente) session.getAttribute("utente");
        String idEvento;

        String action = request.getParameter("action");
        if (utente != null) {
            if (action.equalsIgnoreCase("visualizzaOggetti")) {
                idEvento = request.getParameter("idEvento");

                List<Oggetto> lista = oggettoEJB.cercaTuttiGliOggetti(idEvento, utente.getIdUtente());

                session.removeAttribute("oggetti");
                session.setAttribute("oggetti", lista);

                session.removeAttribute("idEvento");
                session.setAttribute("idEvento", idEvento);

                RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/selezionaOggetto.jsp");
                dispatcher.forward(request, response);

            } else if (action.equalsIgnoreCase("InserisciPartecipazione")) {
                System.out.println("InserisciPartecipazione");
                idEvento = (String) session.getAttribute("idEvento");

                String idOggetto = request.getParameter("idOggetto");

                Partecipazione part = new Partecipazione(idEvento, idOggetto);
                part = partecipazioneEJB.creaPartecipazione(part);

                if (part != null) {
                    RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/dashboardUtente.jsp");
                    dispatcher.forward(request, response);
                } else {
                    RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/selezionaOggetto.jsp");
                    dispatcher.forward(request, response);
                }
            }

        } else {
            System.out.println("ELSE");
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/dashboardUtente.jsp");
            dispatcher.forward(request, response);
        }

    }
}
