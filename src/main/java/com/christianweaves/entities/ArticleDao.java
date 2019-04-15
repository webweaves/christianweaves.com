package com.christianweaves.entities;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.christianweaves.service.ApplicationState;

import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;

@Named
@Stateless
public class ArticleDao extends AbstractDao<Article> {

	@Inject 
	ApplicationState appState;
	
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
	
	/**
	 * retrieve the latest count of articles from the database
	 * @return
	 */
	public List<Article> getArticles(int count) {
		Query query = getEm().createQuery("from Article article where article.archived = :boolType");
		query.setParameter("boolType", Boolean.FALSE);
		query.setFirstResult(0);
		query.setMaxResults(count);
		return query.getResultList();
	}
	
	public List<Article> getLatestArticles() {
		return getArticles(10);
	}
	

	/**
	 * return the featured article from the database, featured article indicated by featured attribute
	 * in the article attribute equalling true
	 * @return
	 */
	public Article getFeaturedArticle() {
		Query query = getEm().createQuery("from Article where featured = :boolType");
		query.setParameter("boolType", Boolean.TRUE);
		List<Article> list = query.getResultList();
		return list.get(0);
	}

}