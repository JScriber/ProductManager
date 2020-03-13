package fr.imie.productmanager.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.imie.productmanager.dao.DaoFactory;
import fr.imie.productmanager.entity.Product;

@WebServlet(urlPatterns = "/showProduct")
@SuppressWarnings("serial")
public class ShowProductServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String rawId = req.getParameter("id");
		final PrintWriter writer = resp.getWriter();
		
		if (rawId != null) {
			try {
				final Long id = Long.valueOf(rawId);
				final Optional<Product> product = DaoFactory.getProductDao().findById(id);
				
				if (product.isPresent()) {
					req.setAttribute("product", product.get()); 
			        req.getRequestDispatcher("showProduct.jsp").forward(req, resp); 
				} else {
					this.showError(req, resp, String.format("There's no product with the ID %d", id));
				}
			} catch (NumberFormatException e) {
				this.showError(req, resp, "Your ID must be a number.");
			}
		} else {
			this.showError(req, resp, "You must pass an ID.");
		}
		
		writer.close();
	}
	
	private void showError(HttpServletRequest req, HttpServletResponse resp, String message) throws ServletException, IOException {
		req.setAttribute("errorMessage", message);
        req.getRequestDispatcher("error").forward(req, resp); 
	}
	
}
