package com.christianweaves.controllers;

import com.christianweaves.entities.Article;
import com.christianweaves.entities.ArticleArchive;
import com.christianweaves.entities.ArticleDao;
import com.christianweaves.entities.GenericDao;
import com.christianweaves.entities.PageContents;
import com.christianweaves.entities.Tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.UserTransaction;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@Named
@RequestScoped
public class ArticleController {

	@Inject
	private ArticleDao articleDao;
	
	@Inject
	private GenericDao genericDao;
	
	@Inject 
	private ApplicationController applicationController;
	
	@Inject 
	private TagController tagController;
	
	@Resource 
	private UserTransaction userTransaction; 

	private List<String> formTags;
	
	@SuppressWarnings("unused")
	private Article newArticle = new Article();
	
	private List<String> filters = Arrays.asList(new String[] {"^<p>&nbsp;</p>", "\\r\\n\\r\\n"});
	
	@Inject
    private Event<String> articlesEvent;
	
	/**
	 * get all articles
	 * @return
	 */
	public List<Article> getAllArticles() {
		return applicationController.getAllArticles();
	}
	
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
	
	public Article showArticle(String id) {
		try {
			new Long(id);
			return showArticle(new Long(id));
		} catch (java.lang.NumberFormatException e) {
			return articleDao.getArticleByTitle(id);
		}
	}

	/*
	 * ckeditor adds (ignore) all sorts of unwanted markup, remove all unwanted markup in this filter
	 */
	public Article showFilteredArticle(String id) {
		if (id == null) {
			return new Article();
		}
		Article article = showArticle(id);
		if (article == null) {
			return new Article();
		}
		for (String filter: filters) {
			if (article.getBody() == null) {
				continue;
			}
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
		
	/**
	 * called from the edit button at the bottom of the showArticle page 
	 * @return
	 */
	public String editArticleButtonClick() {
		String articleId = extractArticleId();
		Map<String, Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		Article article = showArticle(articleId);
		
		formTags = new ArrayList<>();
		for (Tag t: article.getTags()) {
			formTags.add(t.getTag());
		}
		
		applicationController.getNewArticle().setPageContents(article.getPageContents());
		
		sessionMapObj.put("editArticleObject", article);
		sessionMapObj.put("formTags", formTags);
		return "/admin/editArticle.xhtml?faces-redirect=true";
	}

	/**
	 * extract the article from the parameter passed in
	 * @return
	 */
	private String extractArticleId() {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String articleId = params.get("articleId");
		return articleId;
	}

	
	/**
	 * mark the incoming article as deleted
	 */
	public String deleteArticleButtonClick() {
		String articleId = extractArticleId();
		if (articleId == null) return "";
		Article article = articleDao.getArticleById(Long.parseLong(articleId));
		article.setDeleted(true);
		articleDao.merge(article);
		growlMessage("Article deleted");
        return "";
	}
	
	/**
	 * mark the incoming article as deleted
	 */
	public String hideArticleButtonClick() {
		String articleId = extractArticleId();
		if (articleId == null) return "";
		Article article = articleDao.getArticleById(Long.parseLong(articleId));
		article.setHidden(true);
		articleDao.merge(article);
		growlMessage("Article hidden");
        return "";
	}
	
	/**
	 * mark the incoming article as a draft
	 */
	public String draftArticleButtonClick() {
		String articleId = extractArticleId();
		if (articleId == null) return "";
		Article article = articleDao.getArticleById(Long.parseLong(articleId));
		article.setDraft(true);
		articleDao.merge(article);
		growlMessage("Article drafted");
        return "";
	}
	
	/**
	 * mark the incoming article as a draft
	 */
	public String archiveArticleButtonClick() {
		String articleId = extractArticleId();
		if (articleId == null) return "";
		Article article = articleDao.getArticleById(Long.parseLong(articleId));
		article.setArchived(true);
		articleDao.merge(article);
		growlMessage("Article archived");
        return "";
	}

	private void growlMessage(String msg) {
		FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Successful",  msg));
	}

	/**
	 * called when the user saves edits made on the edit article page
	 * 
	 * @return
	 */
	public String editArticle() {
		Map<String, Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		Article article = (Article) sessionMapObj.get("editArticleObject");
		List<String> frmTags = (List<String>) sessionMapObj.get("formTags");
		
		if (article.getFeatured()) {
			articleDao.resetFeatured();
		}

		// do some formatting? //article.getBody().replaceAll("\\r|\\n", "");

		Article dbArticle = getArticleById(article.getId());
		archiveExistingArticle(dbArticle);
		
		article.orderPageContentItems();
		
		dbArticle.setIcon(applicationController.getNewArticle().getIcon());
		dbArticle.setTitle(article.getTitle());
		dbArticle.setBody(article.getBody());
		dbArticle.setFeatured(article.getFeatured());
		dbArticle.setDeleted(article.getDeleted());
		dbArticle.setHidden(article.getHidden());
		dbArticle.setDraft(article.getDraft());
		dbArticle.setArchived(article.getArchived());
		dbArticle.setSubtitle(article.getSubtitle());
		dbArticle.setDateAdded(article.getDateAdded());
		dbArticle.setPageContents(article.getPageContents());

		for (PageContents p : applicationController.getNewArticle().getPageContents()) {
			p.setArticle(dbArticle);
		}
		dbArticle.setPageContents(applicationController.getNewArticle().getPageContents());
		
		List<Tag> tags = tagController.persistTags(frmTags);
		dbArticle.setTags(tags);

		dbArticle = articleDao.merge(dbArticle);
		
		articlesEvent.fire("Reload list (edit article)");
		
		return "/showArticle.xhtml?article=" + article.getId() + "&faces-redirect=true";
	}
	
	private void archiveExistingArticle(Article article) {
		ArticleArchive dbArticleArchive = new ArticleArchive();
		dbArticleArchive.setTitle(article.getTitle());
		dbArticleArchive.setBody(article.getBody());
		dbArticleArchive.setFeatured(article.getFeatured());
		dbArticleArchive.setDeleted(article.getDeleted());
		dbArticleArchive.setArchived(article.getArchived());
		dbArticleArchive.setSubtitle(article.getSubtitle());
		dbArticleArchive.setDateAdded(article.getDateAdded());
		genericDao.persist(dbArticleArchive);
	}
	
	public void archiveArticle() { 
		String articleId = extractArticleId(); 
		archiveExistingArticle(getArticleById(new Long(articleId))); 
		//return "/editArticle.xhtml?faces-redirect=true"; 
	}

	public String addNewArticle() {
		if (applicationController.getNewArticle().getFeatured()) {
			articleDao.resetFeatured();
		}
		
		//reset the associated tags
		applicationController.getNewArticle().setTags(new ArrayList<>());
		List<Tag> tags = tagController.persistTags(formTags);
		applicationController.getNewArticle().setTags(tags);
		
		applicationController.getNewArticle().setArchived(false);
		applicationController.getNewArticle().setDateAdded(new Date());

		applicationController.getNewArticle().orderPageContentItems();
		
		
		for (PageContents p : applicationController.getNewArticle().getPageContents()) {
			p.setArticle(applicationController.getNewArticle());
		}
		
		articleDao.persist(applicationController.getNewArticle());
		Long articleId = applicationController.getNewArticle().getId();

		applicationController.setNewArticle(new Article());
		
		formTags = new ArrayList<>();
		
		FacesMessage message = new FacesMessage("Succesful", "New article created!");
        FacesContext.getCurrentInstance().addMessage(null, message);
        
        articlesEvent.fire("Reload list (new article)");
		
        return "/showArticle.xhtml?article=" + articleId + "&faces-redirect=true";
	}
	
	public void handleFileUpload(FileUploadEvent event) {
        UploadedFile uploadedFile = event.getFile();
        String fileName = uploadedFile.getFileName();
        byte[] contents = uploadedFile.getContents();
        applicationController.getNewArticle().setIcon(Base64.getEncoder().encodeToString(contents));
        FacesMessage msg = new FacesMessage("Succesful", fileName + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    
	public Article getNewArticle() {
		return applicationController.getNewArticle();
	}

	public void setNewArticle(Article newArticle) {
		this.applicationController.setNewArticle(newArticle);
	}

	public ApplicationController getApplicationController() {
		return applicationController;
	}

	public void setApplicationController(ApplicationController applicationController) {
		this.applicationController = applicationController;
	}

	public List<String> getFormTags() {
		return formTags;
	}

	public void setFormTags(List<String> formTags) {
		this.formTags = formTags;
	}
}