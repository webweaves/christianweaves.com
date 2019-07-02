package com.christianweaves.controllers;

import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import com.christianweaves.entities.Article;
import com.christianweaves.entities.ArticleDao;

public class ArticleControllerTest {

	
	@Mock 
	private ArticleDao articleDao;
	
	@Before
	public void init() {
		articleDao = Mockito.mock(ArticleDao.class);
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testShowFilteredArticle() {
		ArticleController articleController = new ArticleController();
		
		when(articleController.showArticle(1L)).thenReturn(new Article());
		
		Article article = articleController.showFilteredArticle(1L);
	}
}
