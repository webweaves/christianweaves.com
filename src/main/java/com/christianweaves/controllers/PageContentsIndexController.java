package com.christianweaves.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.christianweaves.entities.Article;
import com.christianweaves.entities.ArticleDao;
import com.christianweaves.entities.GenericDao;
import com.christianweaves.entities.PageContents;

@Named
@RequestScoped
public class PageContentsIndexController {

	@Inject 
	private ApplicationController applicationController;
	
	private String addPageContentItem;
	private List<PageContents> pageContents;
	
	public void addPageContentIndex() {
		PageContents p1 = new PageContents(addPageContentItem);
		p1.setContentReference(addPageContentItem.toLowerCase().replaceAll(" ", ""));
		if (getApplicationController().getNewArticle().getPageContents() == null) {
			getApplicationController().getNewArticle().setPageContents(new ArrayList<>());
		}
		
		//add to the session scoped storage
		getApplicationController().getNewArticle().getPageContents().add(p1);
		addPageContentItem = "";
	}
	
	public String getAddPageContentItem() {
		return addPageContentItem;
	}
	public void setAddPageContentItem(String addPageContentItem) {
		this.addPageContentItem = addPageContentItem;
	}
	public List<PageContents> getPageContents() {
		return pageContents;
	}
	public void setPageContents(List<PageContents> pageContents) {
		this.pageContents = pageContents;
	}

	public ApplicationController getApplicationController() {
		return applicationController;
	}

	public void setApplicationController(ApplicationController applicationController) {
		this.applicationController = applicationController;
	}
}
