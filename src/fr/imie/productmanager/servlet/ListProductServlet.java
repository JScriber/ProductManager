package fr.imie.productmanager.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

@WebServlet(urlPatterns = { "/", "/listProduct" })
@SuppressWarnings("serial")
public class ListProductServlet extends HttpServlet {
	
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

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final EntityManager manager = this.emf.createEntityManager();
		List<Product> products;

		try {
			final Query query = manager.createQuery("SELECT product FROM Product as product");
			products = query.getResultList();
		} catch(Exception e) {
			products = new ArrayList<Product>();
		}

		manager.close();

		req.setAttribute("products", products); 
        req.getRequestDispatcher("listProduct.jsp").forward(req, resp); 
	}

}
