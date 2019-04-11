package com.christianweaves.entities;

import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.ejb.Stateless;

@Named
@Stateless
public class ArticleDao extends AbstractDao<Article> {

	public ArticleDao() {
		super(Article.class);
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7542128296933934495L;

	public Article getArticleById(Long id) {
		TypedQuery<Article> query = getEm().createNamedQuery("Article.findById", Article.class);
		query.setParameter("Id", id);	
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}