package fr.imie.productmanager.rest;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import fr.imie.productmanager.dao.DaoFactory;
import fr.imie.productmanager.dao.ProductDao;
import fr.imie.productmanager.entity.Product;
import fr.imie.productmanager.model.RestError;

@Path("/product")
public class ProductResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getAllProducts() {
		return DaoFactory.getProductDao().findAll();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findProductById(@PathParam("id") final Long id) throws Exception {
		
		final Optional<Product> product = DaoFactory.getProductDao().findById(id);
		final Response response;

		if (product.isPresent()) {
			response = Response.ok(product.get()).build();
		} else {
			final RestError error = new RestError(String.format("Product with ID %d doesn't exist.", id));
			response = Response.status(Status.BAD_REQUEST).entity(error).build();
		}
		
		return response;
	}
	
	@DELETE
	@Path("/{id}")
	public Response removeProduct(@PathParam("id") final Long id) {

		final ProductDao dao = DaoFactory.getProductDao();
		final Optional<Product> product = dao.findById(id);
		final Response response;

		if (product.isPresent()) {
			dao.deleteById(id);
			response = Response.ok().build();
		} else {
			final RestError error = new RestError(String.format("Product with ID %d doesn't exist.", id));
			response = Response.status(Status.BAD_REQUEST).entity(error).build();
		}
		
		return response;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addProduct(final Product product) {
		final Response response;
		product.setId(null);
		DaoFactory.getProductDao().persist(product);
		
		if (product.getId() != null) {
			response = Response.ok().build();
		} else {
			response = Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		
		return response;
	}

}
