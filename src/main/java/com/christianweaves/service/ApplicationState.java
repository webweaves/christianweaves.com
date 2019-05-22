package com.christianweaves.service;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import com.christianweaves.entities.Article;
import com.christianweaves.entities.ArticleDao;

@SessionScoped
public class ApplicationState implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8664246990209755611L;

}
