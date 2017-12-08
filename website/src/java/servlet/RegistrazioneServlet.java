package servlet;

import bean.Utente;
import ejb.PasswordHash;
import ejb.UtenteEJB;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrazioneServlet extends HttpServlet {

	@Inject
	private UtenteEJB utenteEJB;
	
	@Inject
	private PasswordHash pwHash;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomeUtente = request.getParameter("nomeUtente");
		String email = request.getParameter("email");
		String password = pwHash.hash(request.getParameter("password"));

		utenteEJB.creaUtente(new Utente(nomeUtente, email, password));
	}

}
