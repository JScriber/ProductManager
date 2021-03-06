package fr.imie.productmanager.dao.jpa;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import fr.imie.productmanager.dao.CategoryDao;
import fr.imie.productmanager.entity.Category;
import fr.imie.productmanager.utils.PersistenceManager;

public class JpaCategoryDao implements CategoryDao {

	private EntityManagerFactory emf;
	
	public JpaCategoryDao() {
		this.emf = PersistenceManager.getEntityManagerFactory();
	}

	@Override
	public Optional<Category> findById(final Long id) {
		final EntityManager manager = this.emf.createEntityManager();
		Category category;

		try {
			category = manager.find(Category.class, id);
		} catch(Exception e) {
			category = null;
		}
		
		manager.close();

		return Optional.ofNullable(category);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> findAll() {
		final EntityManager manager = this.emf.createEntityManager();
		List<Category> categories;

		try {
			final Query query = manager.createQuery("SELECT category FROM Category as category");

			categories = query.getResultList();
		} catch(Exception e) {
			e.printStackTrace();
			categories = null;
		}
		
		manager.close();

		return categories;
	}

	@Override
	public void persist(final Category category) {
		final EntityManager manager = this.emf.createEntityManager();
		final EntityTransaction transaction = manager.getTransaction();

		try {
			transaction.begin();
			manager.persist(category);
			transaction.commit();
		} finally {
			if (transaction.isActive()) {
				transaction.rollback();
			}

			manager.close();
		}
	}
	
	@Override
	public void merge(final Category category) {
		final EntityManager manager = this.emf.createEntityManager();
		final EntityTransaction transaction = manager.getTransaction();

		try {
			transaction.begin();
			manager.merge(category);
			transaction.commit();
		} finally {
			if (transaction.isActive()) {
				transaction.rollback();
			}

			manager.close();
		}
	}

}
