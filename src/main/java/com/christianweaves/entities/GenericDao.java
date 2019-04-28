package com.christianweaves.entities;

import javax.persistence.EntityManager;

import java.io.Serializable;
import java.util.List;

public interface GenericDao extends Serializable {
    <T> T find(Object id, Class<T> clazz);
    <T> T merge(T entity);
    void detach(Object entity);
    <T> void persist(T entity);
    <T> List<T> findAll(Class<T> clazz);
    <T> void deleteObject(Object id, Class<T> clazz);
    void remove(Object entity);
    EntityManager getEm();
	<T> List<T> getUserPreferences(String username, String queryStr, Class<T> clazz);
}
