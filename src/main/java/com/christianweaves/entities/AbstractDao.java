package com.christianweaves.entities;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

/**
 * Abstract Dao for generic Entity functionality
 */
public abstract class AbstractDao<E extends Serializable> implements Serializable {

	private static final long serialVersionUID = -1519856312403016459L;

	private final Class<E> clazz;
	
	@Inject
	GenericDao genericDao;

	public AbstractDao(Class<E> clazz) {
		this.clazz = clazz;
	}
	
	public E find(Object id) {
		return genericDao.find(id, clazz);
	}
	
	public E merge(E entity) {
		return genericDao.merge(entity);
	}
	
	public void detach(Object entity) {
		genericDao.detach(entity);
    }

	public void persist(final E entity) {
		genericDao.persist(entity);
	}
	
	public List<E> findAll() {
		return genericDao.findAll(clazz);
	}
	
	public void deleteObject(Object id) {
		genericDao.deleteObject(id, clazz);
	}
	
	public void remove(Object entity) {
		genericDao.remove(entity);
	}
	
	public EntityManager getEm() {
		return genericDao.getEm();
	}

}