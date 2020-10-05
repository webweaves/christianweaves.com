package com.christianweaves.controllers;


import com.christianweaves.entities.Article;
import com.christianweaves.entities.ArticleDao;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doReturn;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;


public class ArticleControllerTest {

	private Article dummyArticle = new Article();
	
	private ArticleDao articleDao;
	
	List<Article> articles = new ArrayList<>();
		
	@Mock ExternalContext externalContext;
	@Mock HttpServletRequest request;
	
	@InjectMocks @Spy
	ArticleController articleController;
	
	FacesContext facesContext;
	
	@Before
	public void initMocks() throws Exception {
		MockitoAnnotations.initMocks(this);
		//facesContext = ContextMocker.mockFacesContext();		
		dummyArticle.setBody("<p>&nbsp;</p>This is a test");

		articleDao = Mockito.mock(ArticleDao.class);
		doReturn(dummyArticle).when(articleDao).getArticleById(1L);
		
		seedDummyData();
		doReturn(articles).when(articleController).getAllArticles();
	}
	
	/**
	 * seed the articles collection with 7 articles and 3 snippets 
	 */
	private void seedDummyData() {
		articles = new ArrayList<>();
		for (int idx=0; idx<=10; idx++) {
			Article a1 = new Article();
			a1.setTitle("Title " + idx);
			a1.setSubtitle("Sub Title " + idx);
			a1.setSnippet(false);
			if (idx==3 || idx==5 || idx==7) {
				a1.setSnippet(true);
			}
			articles.add(a1);
		}
	}

	@Test
	public void testShowFilteredArticle() {
//		Article article = articleController.showFilteredArticle("1");
//		assertEquals("This is a test", article.getBody());
	}
	
	@Test public void testSave() {
/*		articleController.save();
		verify(articleController).save();*/
	}
	
	@Test
	public void testGetAllSnippets() {
		assertEquals(3, articleController.getAllSnippets().size());
	}
}