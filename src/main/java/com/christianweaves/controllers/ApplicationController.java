package com.christianweaves.controllers;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.christianweaves.entities.Article;
import com.christianweaves.entities.ArticleDao;

@Named
@SessionScoped
public class ApplicationController implements Serializable {

	private static final long serialVersionUID = -5349887495234389626L;
	
	@Inject
	private ArticleDao articleDao;
	
	private Article newArticle = new Article();
	
	private List<Article> articles = null;
	
	public Article getNewArticle() {
		return newArticle;
	}

	public void setNewArticle(Article newArticle) {
		this.newArticle = newArticle;
	}

	public void resetarticles() {
		articles.clear();
		articles = null;
	}
	
	public List<Article> getAllArticles() {
		if (articles == null) {
			articles = articleDao.getAllArticles();
		}
		return articles;
	}
}