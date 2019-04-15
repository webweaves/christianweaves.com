package com.christianweaves.controllers;

import java.util.Collection;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;                           

import com.christianweaves.entities.Article;
import com.christianweaves.entities.ArticleDao;
import com.christianweaves.service.ApplicationState;

@Named
@RequestScoped
public class ArticleController {

	@Inject ApplicationState appState;
	
	@Inject ArticleDao articleDao;
		
	/**
	 * return the article based on id
	 * @return
	 */
	public Article getArticleById(Long id) {
		return articleDao.getArticleById(id);
	}
	
	public List<Article> getLatestArticles() {
		return articleDao.getLatestArticles();
	}
	

	public Article getFeaturedArticle() {
		return appState.getFeaturedArticle();
	}

}
