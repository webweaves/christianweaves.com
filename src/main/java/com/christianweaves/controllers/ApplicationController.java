package com.christianweaves.controllers;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.christianweaves.entities.Article;

@Named
@SessionScoped
public class ApplicationController implements Serializable {

	private static final long serialVersionUID = -5349887495234389626L;
	private Article newArticle;

	public void initNewArticle() {
		newArticle = new Article();
	}
	
	public Article getNewArticle() {
		return newArticle;
	}

	public void setNewArticle(Article newArticle) {
		this.newArticle = newArticle;
	}
}
