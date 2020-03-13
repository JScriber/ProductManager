package fr.imie.productmanager.dao.jpa;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import fr.imie.productmanager.dao.ProductDao;
import fr.imie.productmanager.entity.Product;

public class JpaProductDao implements ProductDao {
	
	private EntityManagerFactory emf;
	
	public JpaProductDao(final EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	public Optional<Product> findById(final Long id) {
		final EntityManager manager = this.emf.createEntityManager();
		Product product;

		try {
			final Query query = manager.createQuery("SELECT product FROM Product as product WHERE product.id = :id");
			query.setParameter("id", id);

			product = (Product) query.getSingleResult();
		} catch(Exception e) {
			product = null;
		}
		
		manager.close();

		return Optional.ofNullable(product);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findAll() {
		final EntityManager manager = this.emf.createEntityManager();
		List<Product> products;

		try {
			final Query query = manager.createQuery("SELECT product FROM Product as product");

			products = query.getResultList();
		} catch(Exception e) {
			e.printStackTrace();
			products = null;
		}
		
		manager.close();

		return products;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findAllWithPriceUnder(final float price) {
		final EntityManager manager = this.emf.createEntityManager();
		List<Product> products;

		try {
			final Query query = manager.createQuery("SELECT product FROM Product as product WHERE product.price < :price");
			query.setParameter("price", price);

			products = query.getResultList();
		} catch(Exception e) {
			e.printStackTrace();
			products = null;
		}
		
		manager.close();

		return products;
	}

	@Override
	public void persist(final Product product) {
		final EntityManager manager = this.emf.createEntityManager();
		final EntityTransaction transaction = manager.getTransaction();

		try {
			transaction.begin();
			manager.persist(product);
			transaction.commit();
		} finally {
			if (transaction.isActive()) {
				transaction.rollback();
			}

			manager.close();
		}
	}

	@Override
	public void deleteById(final Long id) {
		final EntityManager manager = this.emf.createEntityManager();
		final EntityTransaction transaction = manager.getTransaction();

		try {
			transaction.begin();
			final Query query = manager.createQuery("DELETE FROM Product as product WHERE product.id = :id");
			query.setParameter("id", id);

			query.executeUpdate();

			transaction.commit();
		} finally {
			if (transaction.isActive()) {
				transaction.rollback();
			}

			manager.close();
		}
	}

}
