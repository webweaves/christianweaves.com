package com.christianweaves.view;

import java.util.Collection;

import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.christianweaves.entities.Article;
import com.christianweaves.service.AppService;

@Named
@RequestScoped
public class ArticleView {

	private Article currentArticle;
	
    @Inject
    private AppService service;

	public String save() {
		int i = 0;
		i++;
		return null;
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
		currentArticle = service.getArticleById(id);
	}
	
	public Collection<Article> getLatestArticles() {
		return service.getLatestArticles();
	}

	public AppService getAppService() {
		return service;
	}

	public void setService(AppService service) {
		this.service = service;
	}

	public Article getFeaturedArticle() {
		return service.getFeaturedArticle();
	}

	public Article getCurrentArticle() {
		return currentArticle;
	}

	public void setCurrentArticle(Article currentArticle) {
		this.currentArticle = currentArticle;
	}
}
