package fr.imie.productmanager.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/rest")
public class RestService extends Application {
	
	public RestService() {}
 
	@Override
	public Set<Class<?>> getClasses( )
	{
		final Set<Class<?>> classes = new HashSet<Class<?>>();

		classes.add(ProductResource.class);
		classes.add(CategoryResource.class);

		return classes;
	}
}
