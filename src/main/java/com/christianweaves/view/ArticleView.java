package com.christianweaves.view;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.christianweaves.entities.Article;
import com.christianweaves.service.AppService;

@Named
@ViewScoped
public class ArticleView {

	private Article featuredArticle;
	private Collection<Article> latestArticles;
	
    @Inject
    private AppService service;

	@PostConstruct
    public void init() {
        setLatestArticles(service.getArticles(10));
        setFeaturedArticle(service.getFeaturedArticle());
    }

	/**
	 * return the article body based on article id
	 * @param id
	 * @return
	 */
	public String showBody(Long id) {
		return service.getArticleById(id).getBody();	
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
}
