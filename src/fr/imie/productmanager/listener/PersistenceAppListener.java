package fr.imie.productmanager.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import fr.imie.productmanager.utils.PersistenceManager;

@WebListener
public class PersistenceAppListener implements ServletContextListener {

	public void contextInitialized(final ServletContextEvent event){
		PersistenceManager.getEntityManagerFactory();
	}

	public void contextDestroyed(final ServletContextEvent evt) {
		PersistenceManager.closeEntityManagerFactory();
	}
	
}
