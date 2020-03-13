package fr.imie.productmanager.servlet;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.imie.productmanager.dao.DaoFactory;
import fr.imie.productmanager.entity.Category;
import fr.imie.productmanager.entity.Product;

@WebServlet(urlPatterns = "/auth/addProduct")
@SuppressWarnings("serial")
public class AddProductServlet extends HttpServlet {
		
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("categories", DaoFactory.getCategoryDao().findAll());
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
				final Long categoryId = Long.valueOf(rawCategory);
				final Optional<Category> category = DaoFactory.getCategoryDao().findById(categoryId);

				if (category.isPresent()) {
					final Product product = new Product();
					product.setName(name);
					product.setDescription(description);
					product.setPrice(Float.valueOf(rawPrice));
					product.setCategory(category.get());
					
					DaoFactory.getProductDao().persist(product);

					resp.sendRedirect(req.getContextPath() + "/showProduct?id=" + product.getId()); 	
				}
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
	}

}
