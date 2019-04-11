package com.christianweaves.controllers;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;                           

import com.christianweaves.entities.Article;
import com.christianweaves.entities.ArticleDao;

@Named
@RequestScoped
public class ArticleController {

	@Inject ArticleDao articleDao;
	
	@Inject TestController tc;
	
	/**
	 * return the article based on idtkx gold
	 * @return
	 */
	public Article getArticleById(Long id) {
		return articleDao.getArticleById(id);
	}
}
