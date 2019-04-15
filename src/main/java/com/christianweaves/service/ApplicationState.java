package com.christianweaves.service;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import com.christianweaves.entities.Article;
import com.christianweaves.entities.ArticleDao;

@SessionScoped
public class ApplicationState implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8664246990209755611L;

	private Article featuredArticle;
	
	private List<Article> latestArticles;
	
	@Inject ArticleDao articleDao;
	
	@PostConstruct
	public void init() {
		System.out.println("Im alive");
		latestArticles = articleDao.getLatestArticles();
		
		featuredArticle = articleDao.getFeaturedArticle();
	}

	public Article getFeaturedArticle() {
		return featuredArticle;
	}

	public void setFeaturedArticle(Article featuredArticle) {
		this.featuredArticle = featuredArticle;
	}
}
