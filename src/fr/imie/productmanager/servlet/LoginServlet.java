package fr.imie.productmanager.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/login")
@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {
	
	private static final List<String> AUTHORIZED_LANGUAGES = List.of("en", "fr");
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/login.jsp").forward(req, resp); 
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String username = req.getParameter("username");
		final String language = req.getParameter("language");
		final String redirectTo;

		if (username != null && !username.isEmpty() && language != null && AUTHORIZED_LANGUAGES.contains(language)) {
			req.getSession().setAttribute("username", username);
			req.getSession().setAttribute("language", language);

			redirectTo = "listProduct";
		} else {
			redirectTo = "login";
		}
		
		resp.sendRedirect(redirectTo);		
	}

}
