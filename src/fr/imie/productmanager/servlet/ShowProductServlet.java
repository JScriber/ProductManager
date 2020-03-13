package fr.imie.productmanager.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.imie.productmanager.entity.Product;

@WebServlet(urlPatterns = "/showProduct")
@SuppressWarnings("serial")
public class ShowProductServlet extends HttpServlet {
	
	private EntityManagerFactory emf;
	
	@Override
	public void init() throws ServletException {
		this.emf = Persistence.createEntityManagerFactory("My-PU");
	}
	
	@Override
	public void destroy() {
		if (this.emf != null && this.emf.isOpen()) {
			this.emf.close();			
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String rawId = req.getParameter("id");
		final PrintWriter writer = resp.getWriter();
		
		if (rawId != null) {
			try {
				final Long id = Long.valueOf(rawId);
				
				final EntityManager manager = this.emf.createEntityManager();
				Product product;

				try {
					final Query query = manager.createQuery("SELECT product FROM Product as product WHERE product.id = " + id);
					product = (Product) query.getSingleResult();
				} catch(Exception e) {
					product = null;
				}

				manager.close();
				
				if (product == null) {
					writer.print(String.format("There's no product with the ID %d", id));
				} else {
					req.setAttribute("product", product); 
			        req.getRequestDispatcher("showProduct.jsp").forward(req, resp); 
				}
			} catch (NumberFormatException e) {
				writer.print("Your ID sucks.");
			}
		} else {
			writer.print("You must pass an ID.");
		}
		
		writer.close();
	}
}
