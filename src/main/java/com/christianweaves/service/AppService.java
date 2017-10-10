package com.christianweaves.service;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import com.christianweaves.entities.Article;

@ApplicationScoped
public class AppService {
	
    // Injected database connection:
	@PersistenceContext(unitName = "christianweavesDS", type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;
    
	/**
	 * retrieve the latest count of articles from the database
	 * 
	 * @return
	 */
	public List<Article> getArticles(int count) {
		Query query = entityManager.createQuery("from Article article");
		query.setFirstResult(0);
		query.setMaxResults(count);
		return query.getResultList();
	}

	/**
	 * return the featured article from the database, featured article indicated by featured attribute
	 * in the article attribute equalling true
	 * @return
	 */
	public Article getFeaturedArticle() {
		Query query = entityManager.createQuery("from Article where featured = :boolType");
		query.setParameter("boolType", Boolean.TRUE);
		List<Article> list = query.getResultList();
		return list.get(0);
	}
	
	public void createArticle(String title, String subtitle, String body) {
	
		Article article = new Article();
		article.setTitle(title);
		article.setSubtitle(subtitle);
		article.setBody(body);
		
		entityManager.persist(article);
		
	}
}
