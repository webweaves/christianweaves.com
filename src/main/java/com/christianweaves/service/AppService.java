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
import com.christianweaves.entities.ArticleArchive;


public class AppService {
	
    // Injected database connection:
	@PersistenceContext(unitName="christianweavesDS", type=PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	@Resource
    private UserTransaction userTransaction;

	private Collection<Article> latestArticles;

	/**
	 * called only once to initialise latest and featured articles
	 */
	@PostConstruct
    public void init() {
        setLatestArticles(getArticles(10));
    }
	
	/**
	 * retrieve the latest count of articles from the database
	 * @return
	 */
	public List<Article> getArticles(int count) {
		Query query = entityManager.createQuery("from Article article where article.archived = :boolType");
		query.setParameter("boolType", Boolean.FALSE);
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

	/**
	 * return the article based on id
	 * @return
	 */
	public Article getArticleById(Long id) {
		if (id == null) return null;
		Query query = entityManager.createQuery("from Article where id = :id");
		query.setParameter("id", id);
		return (Article)query.getSingleResult();
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
	
	/**
	 * used to save the currently active article
	 */
	public Article saveCurrentArticle() {
		Map<String,Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		Article article = (Article)sessionMapObj.get("editArticleObject");
		
		archiveExistingArticle(article);
		
		//do some formatting?
		//article.getBody().replaceAll("\\r|\\n", ""); 
		
		Article dbArticle = getArticleById(article.getId());
		dbArticle.setTitle(article.getTitle());
		dbArticle.setBody(article.getBody());
		dbArticle.setFeatured(article.getFeatured());
		dbArticle.setArchived(article.getArchived());
		dbArticle.setSubtitle(article.getSubtitle());
		dbArticle.setDateAdded(article.getDateAdded());
		
		try {
			userTransaction.begin();
			dbArticle = entityManager.merge(dbArticle);
			userTransaction.commit();
		} catch (SecurityException | IllegalStateException | NotSupportedException | SystemException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			e.printStackTrace();
		}

		return dbArticle;
	}
	
	private void archiveExistingArticle(Article article) {
		try {
			ArticleArchive dbArticleArchive = new ArticleArchive();
			dbArticleArchive.setTitle(article.getTitle());
			dbArticleArchive.setBody(article.getBody());
			dbArticleArchive.setFeatured(article.getFeatured());
			dbArticleArchive.setArchived(article.getArchived());
			dbArticleArchive.setSubtitle(article.getSubtitle());
			dbArticleArchive.setDateAdded(article.getDateAdded());
			userTransaction.begin();
			entityManager.persist(dbArticleArchive);
			userTransaction.commit();
		} catch (SecurityException | IllegalStateException | NotSupportedException | SystemException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			e.printStackTrace();
		}
	}

	/**
	 * populates the session with the currently selected article
	 * @param articleId
	 * @return
	 */
	public void editArticle(Long articleId) {		
		/* Setting The Particular Student Details In Session */
		Map<String,Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		//get the article if it existsL
		Article article = getArticleById(articleId);
		sessionMapObj.put("editArticleObject", article);
	}

	/**
	 * deletes the article based on articleId
	 * @param articleId
	 * @return
	 */
	public void deleteArticle(Long articleId) {
		entityManager.remove(getArticleById(articleId));
	}

	/**
	 * deletes the article based on articleId
	 * @param articleId
	 * @return
	 */
	public void archiveArticle(Long articleId) {
		Article article = entityManager.find(Article.class, articleId);
		try {
			userTransaction.begin();
			article.setArchived(true);
			
			userTransaction.commit();
		} catch (SecurityException | IllegalStateException | NotSupportedException | SystemException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * returns a list of all articles
	 * @return
	 */
	public List<Article> getAll() {
		return entityManager.createNamedQuery("allArticles", Article.class).getResultList();
	}
}
