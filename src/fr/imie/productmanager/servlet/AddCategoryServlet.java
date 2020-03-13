package fr.imie.productmanager.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.imie.productmanager.dao.DaoFactory;
import fr.imie.productmanager.entity.Category;

@WebServlet(urlPatterns = "/auth/addCategory")
@SuppressWarnings("serial")
public class AddCategoryServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/addCategory.jsp").forward(req, resp); 
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String name = req.getParameter("name");
		
		if (name != null) {
			final Category category = new Category(name);
			DaoFactory.getCategoryDao().persist(category);

			resp.sendRedirect(req.getContextPath() + "/listProduct"); 
		}
	}

}
