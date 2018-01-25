package servlet;

import bean.Oggetto;
import bean.Utente;
import ejb.OggettoEJB;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegistrazioneOggettoServlet extends HttpServlet {

    @Inject
    private OggettoEJB oggettoEJB;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Utente utente = (Utente) session.getAttribute("utente");

        if (utente != null) {
            String nomeOggetto = request.getParameter("nomeOggetto");
            String descrizioneOggetto = request.getParameter("descrizioneOggetto");

            Oggetto oggetto = new Oggetto(nomeOggetto, descrizioneOggetto);
            oggetto.setProprietario(utente.getIdUtente());

            oggetto = oggettoEJB.creaOggetto(oggetto);

            if (oggetto != null) {
                RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/dashboardUtente.jsp");
                dispatcher.forward(request, response);
            } else {
                RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/registrazioneOggetto.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/index.html");
            dispatcher.forward(request, response);
        }
    }

}
