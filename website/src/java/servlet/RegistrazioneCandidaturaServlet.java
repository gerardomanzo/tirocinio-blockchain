package servlet;

import bean.Candidatura;
import bean.Utente;
import ejb.CandidaturaEJB;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegistrazioneCandidaturaServlet extends HttpServlet {

    @Inject
    private CandidaturaEJB candidaturaEJB;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Utente utente = (Utente) session.getAttribute("utente");

        if (utente != null) {
            String nomeCandidatura = request.getParameter("nomeCandidatura");
            String descrizioneCandidatura = request.getParameter("descrizioneCandidatura");

            Candidatura candidatura = new Candidatura(nomeCandidatura, descrizioneCandidatura);
            candidatura.setProprietario(utente.getIdUtente());

            candidatura = candidaturaEJB.creaCandidatura(candidatura);

            if (candidatura != null) {
                RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/dashboardUtente.jsp");
                dispatcher.forward(request, response);
            } else {
                RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/registrazioneCandidatura.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/index.html");
            dispatcher.forward(request, response);
        }
    }

}
