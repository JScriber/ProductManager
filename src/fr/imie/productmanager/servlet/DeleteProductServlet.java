package fr.imie.productmanager.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.imie.productmanager.dao.DaoFactory;

@WebServlet(urlPatterns = "/deleteProduct")
@SuppressWarnings("serial")
public class DeleteProductServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String rawId = req.getParameter("id");
		
		if (rawId != null) {
			try {
				final Long id = Long.valueOf(rawId);
				
				DaoFactory.getProductDao().deleteById(id);
				
			} catch (NumberFormatException e) {}
		}

        resp.sendRedirect(req.getContextPath() + "/listProduct");
	}
}
