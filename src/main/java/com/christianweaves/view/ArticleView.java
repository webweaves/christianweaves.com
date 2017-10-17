package com.christianweaves.view;

import java.util.Collection;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.christianweaves.entities.Article;
import com.christianweaves.service.AppService;

@Named
@RequestScoped
public class ArticleView {

    @Inject
    private AppService service;

	public void save() {
		service.saveCurrentArticle();
	}
	
	/**
	 * return the article body based on article id
	 * @param id
	 * @return
	 */
	public String showBody(Long id) {
		try {
			return service.getArticleById(id).getBody();	
		} catch (java.lang.NullPointerException e) {
			return "";
		}	
	}
	
	/**
	 * sets the current active article using passed in id
	 * @param id
	 */
	public void setCurrentArticle(Long id) {
		service.getArticleById(id);
	}
	
	public Collection<Article> getLatestArticles() {
		return service.getLatestArticles();
	}

	public Article getFeaturedArticle() {
		return service.getFeaturedArticle();
	}

	public String getCurrentArticleBody() {
		return service.getCurrentArticle().getBody();
	}
	
	public void setCurrentArticleBody(String data) {
		service.getCurrentArticle().setBody(data);
	}
}
