package fr.imie.productmanager.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import fr.imie.productmanager.dao.DaoFactory;
import fr.imie.productmanager.entity.Category;
import fr.imie.productmanager.model.RestError;

@Path("/category")
public class CategoryResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Category> getAllCategories() {
		return DaoFactory.getCategoryDao().findAll();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCategory(@PathParam("id") final Long id) throws Exception {
		
		final Optional<Category> category = DaoFactory.getCategoryDao().findById(id);
		final Response response;

		if (category.isPresent()) {
			response = Response.ok(category.get()).build();
		} else {
			final RestError error = new RestError(String.format("Category with ID %d doesn't exist.", id));
			response = Response.status(Status.BAD_REQUEST).entity(error).build();
		}
		
		return response;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addCategory(final Category category) {
		final Response response;
		category.setId(null);
		category.setProducts(new ArrayList<>());
		
		DaoFactory.getCategoryDao().persist(category);
		
		if (category.getId() != null) {
			response = Response.ok().build();
		} else {
			response = Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		
		return response;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateCategory(final Category category) {
		DaoFactory.getCategoryDao().merge(category);
		
		return Response.ok().build();
	}
}
