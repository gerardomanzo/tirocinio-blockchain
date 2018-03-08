package servlet;

import bean.Criterio;
import bean.Evento;
import bean.Oggetto;
import bean.Utente;
import ejb.EventoEJB;
import ejb.PartecipazioneEJB;
import ejb.VotoEJB;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class VotoServlet extends HttpServlet {

    @Inject
    private VotoEJB votoEJB;

    @Inject
    private EventoEJB eventoEJB;

    @Inject
    private PartecipazioneEJB partecipazioneEJB;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Utente utente = (Utente) session.getAttribute("utente");

        if (utente != null) {
            List<Evento> lista = votoEJB.cercaEventiVoto(utente.getIdUtente());

            session.removeAttribute("eventi");
            session.setAttribute("eventi", lista);

            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/votaEvento.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/index.html");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Utente utente = (Utente) session.getAttribute("utente");

        if (utente != null) {

            String idEvento = request.getParameter("idEvento");

            List<Criterio> listaCriteri = eventoEJB.cercaCriteriEvento(idEvento);

            List<Oggetto> listaOggetti = partecipazioneEJB.cercaOggettiEvento(idEvento);

            session.removeAttribute("idEvento");
            session.setAttribute("idEvento", idEvento);

            session.removeAttribute("criteri");
            session.setAttribute("criteri", listaCriteri);

            session.removeAttribute("oggetti");
            session.setAttribute("oggetti", listaOggetti);

            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/votaOggetto.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/index.html");
            dispatcher.forward(request, response);
        }
    }

}
