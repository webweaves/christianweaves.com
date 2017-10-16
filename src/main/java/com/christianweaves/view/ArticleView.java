package com.christianweaves.view;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.christianweaves.entities.Article;
import com.christianweaves.service.AppService;

@Named
@SessionScoped
public class ArticleView {

	private Article currentArticle;
	private Article featuredArticle;
	private Collection<Article> latestArticles;
	
    @Inject
    private AppService service;

	@PostConstruct
    public void init() {
        setLatestArticles(service.getArticles(10));
        setFeaturedArticle(service.getFeaturedArticle());
    }

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
	
	public void setLatestArticles(Collection<Article> latestArticles) {
		this.latestArticles = latestArticles;
	}

	public Collection<Article> getLatestArticles() {
		return latestArticles;
	}

	public AppService getAppService() {
		return service;
	}

	public void setService(AppService service) {
		this.service = service;
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
}
