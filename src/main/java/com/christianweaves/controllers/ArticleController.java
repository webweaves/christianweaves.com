package com.christianweaves.controllers;

import com.christianweaves.entities.Article;
import com.christianweaves.entities.ArticleArchive;
import com.christianweaves.entities.ArticleDao;
import com.christianweaves.entities.GenericDao;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.UserTransaction;

@Named
@RequestScoped
public class ArticleController {

	@Inject
	ArticleDao articleDao;
	
	@Inject
	GenericDao genericDao;
	
	@Resource 
	private UserTransaction userTransaction; 

	private Article newArticle = new Article();
	
	private List<String> filters = Arrays.asList(new String[] {"^<p>&nbsp;</p>", "\\r\\n\\r\\n"});
	/**
	 * return the article based on id
	 * 
	 * @return
	 */
	public Article getArticleById(Long id) {
		return articleDao.getArticleById(id);
	}

	public Article showArticle(Long id) {
		return getArticleById(id);
	}

	/*
	 * ckeditor add all sorts of unwanted markup, remove all unwanted markup in this filter
	 */
	public Article showFilteredArticle(Long id) {
		Article article = showArticle(id);
		for (String filter: filters) {
			article.setBody(article.getBody().replaceAll(filter, ""));	
		}
		return article;
	}

	public List<Article> getLatestArticles() {
		return articleDao.getLatestArticles();
	}

	public Article getFeaturedArticle() {
		return articleDao.getFeaturedArticle();
	}

	public String editArticle() {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String articleId = params.get("articleId");
		editArticle(new Long(articleId));
		return "editArticle.xhtml?faces-redirect=true";
	}

	public void editArticle(Long articleId) {
		Map<String, Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		Article article = getArticleById(articleId);
		sessionMapObj.put("editArticleObject", article);
	}

	public String save() {
		Map<String, Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		Article article = (Article) sessionMapObj.get("editArticleObject");

		archiveExistingArticle(article);

		// do some formatting? //article.getBody().replaceAll("\\r|\\n", "");

		Article dbArticle = getArticleById(article.getId());
		dbArticle.setTitle(article.getTitle());
		dbArticle.setBody(article.getBody());
		dbArticle.setFeatured(article.getFeatured());
		dbArticle.setArchived(article.getArchived());
		dbArticle.setSubtitle(article.getSubtitle());
		dbArticle.setDateAdded(article.getDateAdded());
		dbArticle = articleDao.merge(dbArticle);
		
		return "/showArticle.xhtml?article=" + dbArticle.getId() + "&faces-redirect=true";
	}
	
	private void archiveExistingArticle(Article article) {
		ArticleArchive dbArticleArchive = new ArticleArchive();
		dbArticleArchive.setTitle(article.getTitle());
		dbArticleArchive.setBody(article.getBody());
		dbArticleArchive.setFeatured(article.getFeatured());
		dbArticleArchive.setArchived(article.getArchived());
		dbArticleArchive.setSubtitle(article.getSubtitle());
		dbArticleArchive.setDateAdded(article.getDateAdded());
		genericDao.persist(dbArticleArchive);
	}
	
	public void archiveArticle() { 
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap(); 
		String articleId = params.get("articleId"); 
		archiveExistingArticle(getArticleById(new Long(articleId))); 
		//return "/editArticle.xhtml?faces-redirect=true"; 
	}

	public void addNewArticle() {
		newArticle.setArchived(false);
		newArticle.setDateAdded(new Date());
		articleDao.persist(newArticle);
		newArticle = new Article();
	}
	
	public Article getNewArticle() {
		return newArticle;
	}

	public void setNewArticle(Article newArticle) {
		this.newArticle = newArticle;
	}			 
}
