package com.christianweaves.view;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.christianweaves.entities.Article;
import com.christianweaves.service.ArticleService;

@ManagedBean(name="articleView")
@RequestScoped
public class ArticleView {

	private Article featuredArticle;
	private Collection<Article> latestArticles;
	
    @ManagedProperty("#{articleService}")
    private ArticleService articleService;

	@PostConstruct
    public void init() {
        setLatestArticles(articleService.getArticles(10));
        setFeaturedArticle(articleService.getFeaturedArticle());
    }

	public void setLatestArticles(Collection<Article> latestArticles) {
		this.latestArticles = latestArticles;
	}

	public Collection<Article> getLatestArticles() {
		return latestArticles;
	}
	
    public ArticleService getArticleService() {
		return articleService;
	}

	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}

	public Article getFeaturedArticle() {
		return featuredArticle;
	}

	public void setFeaturedArticle(Article featuredArticle) {
		this.featuredArticle = featuredArticle;
	}
}
