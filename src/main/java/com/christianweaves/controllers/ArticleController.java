package com.christianweaves.controllers;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
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
	
	public Article showArticle(Long id) {
		return getArticleById(id);
	}
	
	public List<Article> getLatestArticles() {
		return articleDao.getLatestArticles();
	}
	

	public Article getFeaturedArticle() {
		return appState.getFeaturedArticle();
	}

	public String editArticle() { 
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap(); 
		String articleId = params.get("articleId"); 
		editArticle(new Long(articleId)); 
		return "/editArticle.xhtml?faces-redirect=true"; 
	}
	
	public void editArticle(Long articleId) { 
		Map<String,Object> sessionMapObj =
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		Article article = getArticleById(articleId);
		sessionMapObj.put("editArticleObject", article); 
	 }
}
