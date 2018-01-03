package servlet;

import ejb.PasswordHash;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginAdminServlet extends HttpServlet {

	@Inject
	private PasswordHash pwHash;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = pwHash.hash(request.getParameter("password"));

		if (email.equals("admin@tirocinio-blockchain.it") && password.equals(pwHash.hash("20171225"))) {
			HttpSession session = request.getSession();
			session.removeAttribute("loginAdmin");
			session.setAttribute("loginAdmin", Boolean.TRUE);
		}
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/dashboard.jsp");
		dispatcher.forward(request, response);
	}
}
