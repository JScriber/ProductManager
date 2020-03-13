package fr.imie.productmanager.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.imie.productmanager.entity.Category;
import fr.imie.productmanager.entity.Product;

@WebServlet(urlPatterns = "/auth/addProduct")
@SuppressWarnings("serial")
public class AddProductServlet extends HttpServlet {
	
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
		List<Category> categories = new ArrayList<>();

		try {
			final Query query = manager.createQuery("SELECT category FROM Category as category");
			categories = query.getResultList();
		} catch(Exception e) {
			categories = new ArrayList<>();
		}

		manager.close();
		
		req.setAttribute("categories", categories);
        req.getRequestDispatcher("/addProduct.jsp").forward(req, resp); 
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String name = req.getParameter("name");
		final String description = req.getParameter("description");
		final String rawPrice = req.getParameter("price");
		final String rawCategory = req.getParameter("category");

		if (name != null && description != null && rawPrice != null && rawCategory != null) {
			
			try {
				final float price = Float.valueOf(rawPrice);
				final int categoryId = Integer.valueOf(rawCategory);
				
				final EntityManager manager = this.emf.createEntityManager();
				final EntityTransaction transaction = manager.getTransaction();
				Product product;
				Category category;

				try {
					final Query query = manager.createQuery("SELECT category FROM Category as category WHERE category.id = " + categoryId);
					category = (Category) query.getSingleResult();
				} catch(Exception e) {
					category = null;
				}
				
				if (category != null) {
					try {
						transaction.begin();

						product = new Product();
						product.setName(name);
						product.setDescription(description);
						product.setPrice(price);
						product.setCategory(category);

						manager.persist(product);
						
						System.out.println(product.toString());

						transaction.commit();
					} finally {
						if (transaction.isActive()) {
							transaction.rollback();
						}
						
						manager.close();
					}
					
					if (product != null) {
						resp.sendRedirect(req.getContextPath() + "/showProduct?id=" + product.getId()); 					
					}
				}
			} catch (Exception e) {
				
			}			
		}
	}

}
