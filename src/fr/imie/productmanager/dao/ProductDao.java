package fr.imie.productmanager.dao;

import java.util.List;
import java.util.Optional;

import fr.imie.productmanager.entity.Product;

public interface ProductDao {
	
	Optional<Product> findById(Long id);
	
	List<Product> findAll();

	List<Product> findAllWithPriceUnder(float price);
	
	void persist(Product product);
	
	void deleteById(Long id);

}
