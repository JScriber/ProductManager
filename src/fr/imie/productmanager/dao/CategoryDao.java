package fr.imie.productmanager.dao;

import java.util.List;
import java.util.Optional;

import fr.imie.productmanager.entity.Category;

public interface CategoryDao {

	Optional<Category> findById(Long id);

	List<Category> findAll();

	void persist(Category category);
	
}
