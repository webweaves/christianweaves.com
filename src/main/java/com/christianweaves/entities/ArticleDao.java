package com.christianweaves.entities;

import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.util.List;

import javax.ejb.Stateless;

@Named
@Stateless
public class ArticleDao extends AbstractDao<Article> {

	private static final long serialVersionUID = 7542128296933934495L;

	public ArticleDao() {
		super(Article.class);
	}

	/**
	 * grab all the articles 
	 * 
	 * @return
	 */
	public List<Article> getAllArticles() {
		TypedQuery<Article> query = getEm().createNamedQuery("allArticles", Article.class);
		return query.getResultList();
	}

	/**
	 * grab an article based on its id
	 * 
	 * @param id
	 * @return
	 */
	public Article getArticleById(Long id) {
		TypedQuery<Article> query = getEm().createNamedQuery("Article.findById", Article.class);
		query.setParameter("id", id);	
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
		Query query = getEm().createQuery("from Article article where article.archived = :boolType and article.hidden = :boolType and article.deleted = :boolType ORDER BY article.id DESC");
		query.setParameter("boolType", Boolean.FALSE);
		query.setFirstResult(0);
		query.setMaxResults(count);
		return query.getResultList();
	}
	
	public List<Article> getLatestArticles() {
		return getArticles(51);
	}

	/**
	 * return the featured article from the database, featured article indicated by featured attribute
	 * in the article attribute equalling true
	 * @return top article or null if no articles
	 */
	public Article getFeaturedArticle() {
		Query query = getEm().createQuery("from Article where featured = :boolType");
		query.setParameter("boolType", Boolean.TRUE);
		List<Article> list = query.getResultList();
		return list.size() == 0 ? null : list.get(0);
	}

	/**
	 * grab all the featured articles and reset them to false (not featured)
	 */
	public void resetFeatured() {
		Query query = getEm().createQuery("from Article where featured = :boolType");
		query.setParameter("boolType", Boolean.TRUE);
		List<Article> list = query.getResultList();
		for (Article a: list) {
			a.setFeatured(false);
		}
	}

	/**
	 * grab an article based on its title
	 * 
	 * @param title
	 * @return
	 */
	public Article getArticleByTitle(String title) {
		TypedQuery<Article> query = getEm().createNamedQuery("Article.findByTitle", Article.class);
		query.setParameter("title", title);	
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}