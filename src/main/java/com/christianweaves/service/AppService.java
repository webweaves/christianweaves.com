package com.christianweaves.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.christianweaves.entities.Article;

@Singleton
public class AppService {
	
    // Injected database connection:
	@PersistenceContext(unitName="christianweavesDS", type=PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	@Resource
    private UserTransaction userTransaction;

	private Article currentArticle;
	private Article featuredArticle;
	private Collection<Article> latestArticles;

	/**
	 * called only once to initialise latest and featured articles
	 */
	@PostConstruct
    public void init() {
        setLatestArticles(getArticles(10));
        setFeaturedArticle(getTheFeaturedArticle());
    }
	
	/**
	 * retrieve the latest count of articles from the database
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
	public Article getTheFeaturedArticle() {
		Query query = entityManager.createQuery("from Article where featured = :boolType");
		query.setParameter("boolType", Boolean.TRUE);
		List<Article> list = query.getResultList();
		return list.get(0); 
	}

	/**
	 * return the article based on id
	 * @return
	 */
	public Article getArticleById(Long id) {
		if (id == null) return null;
		Query query = entityManager.createQuery("from Article where id = :id");
		query.setParameter("id", id);
		currentArticle = (Article)query.getSingleResult();
		return currentArticle;
	}
	
	public void createArticle(String title, String subtitle, String body) {
	
		Article article = new Article();
		article.setTitle(title);
		article.setSubtitle(subtitle);
		article.setBody(body);
		
		entityManager.persist(article);
	}

	public Collection<Article> getLatestArticles() {
		return latestArticles;
	}

	public void setLatestArticles(Collection<Article> latestArticles) {
		this.latestArticles = latestArticles;
	}

	public Article getFeaturedArticle() {
		return featuredArticle;
	}

	public void setFeaturedArticle(Article featuredArticle) {
		this.featuredArticle = featuredArticle;
	}

	public Article getCurrentArticle() {
		return currentArticle;
	}

	public void setCurrentArticle(Article currentArticle) {
		this.currentArticle = currentArticle;
	}

	
	/**
	 * used to save the currently active article
	 */
	public Article saveCurrentArticle() {
		Map<String,Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		Article article = (Article)sessionMapObj.get("editArticleObject");
		
		
		try {
			userTransaction.begin();
			currentArticle = entityManager.merge(currentArticle);
			userTransaction.commit();
		} catch (SecurityException | IllegalStateException | NotSupportedException | SystemException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			e.printStackTrace();
		}
	
		return article;
	}
	
	
	/* Method Used To Edit Student Record In Database */
	public String editArticle(Long articleId) {
		
		/* Setting The Particular Student Details In Session */
		Map<String,Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		//get the article if it existsL
		Article article = getArticleById(articleId);
		sessionMapObj.put("editArticleObject", article);
	
		return "/editArticle.xhtml?faces-redirect=true";
	}
	
	public List<Article> getAll() {
		CriteriaQuery<Article> criteria = this.entityManager.getCriteriaBuilder().createQuery(Article.class);
		return this.entityManager.createQuery(criteria.select(criteria.from(Article.class))).getResultList();
	}
}
