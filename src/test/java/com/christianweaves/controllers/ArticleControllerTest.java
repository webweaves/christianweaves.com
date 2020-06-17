package com.christianweaves.controllers;


import com.christianweaves.entities.Article;
import com.christianweaves.entities.ArticleDao;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doReturn;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;


public class ArticleControllerTest {

	private Article dummyArticle = new Article();
	
	private ArticleDao articleDao;
	
	@Mock ExternalContext externalContext;
	@Mock HttpServletRequest request;
	
	@InjectMocks ArticleController articleController;
	
	FacesContext facesContext;
	
	@Before
	public void initMocks() throws Exception {
		MockitoAnnotations.initMocks(this);
		//facesContext = ContextMocker.mockFacesContext();		
		dummyArticle.setBody("<p>&nbsp;</p>This is a test");

		articleDao = Mockito.mock(ArticleDao.class);
		doReturn(dummyArticle).when(articleDao).getArticleById(1L);
		
	}
	
	@Test
	public void testShowFilteredArticle() {
		Article article = articleController.showFilteredArticle(1L);
		assertEquals("This is a test", article.getBody());
	}
	
	@Test public void testSave() {
/*		articleController.save();
		verify(articleController).save();*/
	}
}