package com.christianweaves.view;

import java.util.Collection;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.christianweaves.entities.Article;
import com.christianweaves.service.AppService;

@Named
@RequestScoped
public class ArticleView {

 
    private AppService service;

	public String save() {
		Article article = service.saveCurrentArticle();
		return "/showArticle.xhtml?article="+article.getId()+"&faces-redirect=true";
	}
	
	/**
	 * return the article based on article id
	 * @param id
	 * @return
	 */
	public Article showArticle(Long id) {
		return service.getArticleById(id);	
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
	
	public String editArticle() {
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String articleId = params.get("articleId");
		service.editArticle(new Long(articleId));
		return "/editArticle.xhtml?faces-redirect=true";
	}
	
	public void archiveArticle() {
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String articleId = params.get("articleId");
		service.archiveArticle(new Long(articleId));
		//return "/editArticle.xhtml?faces-redirect=true";
	}
}
