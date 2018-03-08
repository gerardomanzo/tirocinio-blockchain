package servlet;

import bean.Criterio;
import bean.Evento;
import bean.Oggetto;
import bean.Partecipazione;
import bean.Utente;
import bean.Voto;
import ejb.CriterioEJB;
import ejb.EventoEJB;
import ejb.OggettoEJB;
import ejb.PartecipazioneEJB;
import ejb.VotoEJB;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RisultatiServlet extends HttpServlet {

    @Inject
    private PartecipazioneEJB partecipazioneEJB;
    @Inject
    private VotoEJB votoEJB;
    @Inject
    private EventoEJB eventoEJB;
    @Inject
    private CriterioEJB criterioEJB;
    @Inject 
    private OggettoEJB oggettoEJB;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");

        if (utente != null) {
            List<Evento> lista = eventoEJB.cercaEventi();
            session.removeAttribute("eventi");
            session.setAttribute("eventi", lista);

            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/visualizzaEventi.jsp");
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

        if (utente != null) {
            String idEvento = request.getParameter("idEvento");

            List<Partecipazione> partecipazioni = partecipazioneEJB.cercaPartecipazioniByIdEvento(idEvento);
            List<Voto> voti = votoEJB.cercaVotoByIdPartecipation(partecipazioni);
            List<Voto> votiUtente = new ArrayList<>();

            String idUtente = utente.getIdUtente();

            for (Voto v : voti) {
                if (v.getUtente().contains(idUtente)) {
                    votiUtente.add(v);
                }
            }

            List<Criterio> criteri = eventoEJB.cercaCriteriEvento(idEvento);
            List<Oggetto> oggetti = oggettoEJB.cercaGliOggetti();

            session.removeAttribute("idEvento");
            session.setAttribute("idEvento", idEvento);

            session.removeAttribute("voti");
            session.setAttribute("voti", votiUtente);
            
            session.removeAttribute("criteri");
            session.setAttribute("criteri", criteri);
            
            session.removeAttribute("partecipazioni");
            session.setAttribute("partecipazioni", partecipazioni);
            
            session.removeAttribute("oggetti");
            session.setAttribute("oggetti", oggetti);

            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/visualizzaRisultati.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/dashboardUtente.jsp");
            dispatcher.forward(request, response);
        }

    }
}
