package fr.imie.productmanager.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Filter that applies authentication check.
 */
@WebFilter(urlPatterns = "/auth/*")
@SuppressWarnings("serial")
public class AuthenticateFilter extends HttpFilter {
	
	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		final String username = (String) req.getSession().getAttribute("username");
		
		if (username == null) {
			res.sendRedirect(req.getContextPath() + "/login");
		} else {
			chain.doFilter(req, res);
		}
	}

}
