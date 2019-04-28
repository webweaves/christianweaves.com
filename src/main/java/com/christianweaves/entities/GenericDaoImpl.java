package com.christianweaves.entities;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import java.util.List;

@Stateless
public class GenericDaoImpl implements GenericDao{
    private static final long serialVersionUID = -1519856312403016459L;

    @PersistenceContext
    protected EntityManager em;

    @Override
    public <T> T find(Object id, Class<T> clazz) {
        return getEm().find(clazz, id);
    }

    @Override
    public <T> T merge(T entity) {
        T mergedEntity = getEm().merge(entity);
        return mergedEntity;
    }

    @Override
    public void detach(Object entity) {
        getEm().detach(entity);
    }

    @Override
    public <T> void persist(final T entity) {
        getEm().persist(entity);
    }

    @Override
    public <T> List<T> findAll(Class<T> clazz) {
        final CriteriaQuery<T> criteriaQuery = getEm().getCriteriaBuilder().createQuery(clazz);
        criteriaQuery.select(criteriaQuery.from(clazz));
        return getEm().createQuery(criteriaQuery).getResultList();
    }

    @Override
    public <T> void deleteObject(Object id, Class<T> clazz) {
        getEm().remove(find(id, clazz));
    }

    @Override
    public void remove(Object entity) {
        getEm().remove(getEm().contains(entity) ? entity : getEm().merge(entity));
    }
    
    @Override
    public <T> List<T> getUserPreferences(String username , String queryStr, Class<T> clazz) {
		TypedQuery<T> query = getEm().createNamedQuery(queryStr, clazz);
		query.setParameter("user", username);
		return query.getResultList();
	}

    @Override
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

}
