package com.christianweaves.controllers;


import com.christianweaves.entities.Article;
import com.christianweaves.entities.ArticleDao;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doReturn;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;


public class ArticleControllerTest {

	
	
	private ArticleDao articleDao;
	
	@InjectMocks ArticleController articleController;
	
	@Before
	public void init() {
		articleDao = Mockito.mock(ArticleDao.class);

		doReturn(new Article()).when(articleDao).getArticleById(1L);
		
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testShowFilteredArticle() {
		
		
		Article article = articleController.showFilteredArticle(1L);
		
		int i =0;
		i++;
	}
}
