package com.christianweaves.controllers;


import com.christianweaves.entities.Article;
import com.christianweaves.entities.ArticleDao;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;


public class ArticleControllerTest {

	private Article dummyArticle = new Article();
	
	private ArticleDao articleDao;
	
	@InjectMocks ArticleController articleController;
	
	@Before
	public void init() {
		dummyArticle.setBody("<p>&nbsp;</p>This is a test");
		articleDao = Mockito.mock(ArticleDao.class);
		doReturn(dummyArticle).when(articleDao).getArticleById(1L);
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testShowFilteredArticle() {
		Article article = articleController.showFilteredArticle(1L);
		assertEquals("This is a test", article.getBody());
	}
}
