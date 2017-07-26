package com.christianweaves.service;

import java.util.Collection;

import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import com.christianweaves.entities.Article;

@Named
@ConversationScoped
@Stateful
public class ArticleService {
	
    // Injected database connection:
	@PersistenceContext(unitName = "christianweavesDS", type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;
    
	/**
	 * retrieve the latest articles
	 * 
	 * @return
	 */
	public Collection<Article> getArticles(int count) {
		Query query = entityManager.createQuery("from Article article");
		query.setFirstResult(0);
		query.setMaxResults(count);
		return query.getResultList();
	}

	public Article getFeaturedArticle() {
		Query query = entityManager.createQuery("from Article article");
		//here constructing query
	}

}
