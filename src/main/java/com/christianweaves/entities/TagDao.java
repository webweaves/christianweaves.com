package com.christianweaves.entities;

import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import javax.ejb.Stateless;

@Named
@Stateless
public class TagDao extends AbstractDao<Tag> {

	private static final long serialVersionUID = 7542128296933934495L;

	public TagDao() {
		super(Tag.class);
	}

	public Tag getTag(String tag) {
		TypedQuery<Tag> query = getEm().createNamedQuery("tag", Tag.class);
		query.setParameter("tag", tag);	
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}