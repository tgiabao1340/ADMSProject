package application.daos;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class MyEntityManagerFactory {
	private static MyEntityManagerFactory instance;
	private EntityManager entityManager;

	private MyEntityManagerFactory() {
		entityManager = Persistence.createEntityManagerFactory("ADMSProject").createEntityManager();
	}

	public synchronized static MyEntityManagerFactory getInstance() {
		if (instance == null)
			instance = new MyEntityManagerFactory();
		return instance;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
}
