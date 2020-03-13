package fr.imie.productmanager.servlet;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.imie.productmanager.entity.Category;

@WebServlet(urlPatterns = "/auth/addCategory")
@SuppressWarnings("serial")
public class AddCategoryServlet extends HttpServlet {
	
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
        req.getRequestDispatcher("/addCategory.jsp").forward(req, resp); 
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String name = req.getParameter("name");
		
		if (name != null) {
			final EntityManager manager = this.emf.createEntityManager();
			final EntityTransaction transaction = manager.getTransaction();
			
			try {
				transaction.begin();
				
				Category category = new Category(name);

				manager.persist(category);
				
				System.out.println(category.toString());

				transaction.commit();
			} finally {
				if (transaction.isActive()) {
					transaction.rollback();
				}
				
				manager.close();
			}

			resp.sendRedirect(req.getContextPath() + "/listProduct"); 
		}
	}

}
