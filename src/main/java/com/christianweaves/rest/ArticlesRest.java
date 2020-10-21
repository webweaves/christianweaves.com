package com.christianweaves.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.christianweaves.entities.Article;
import com.christianweaves.entities.ArticleDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/articles")
public class ArticlesRest {

	@Inject
	private ArticleDao dao;
	
	@GET
	@Path("/hello")
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello() {
	    return "Hello World";
	}

	@GET
	@Path("/getFeatured")
	@Produces(MediaType.APPLICATION_JSON)
	public Response featuredArticles() {
	    Article article = dao.getFeaturedArticle();
		return Response.status(Response.Status.OK)
			.type(MediaType.APPLICATION_JSON)
			.entity(article)
			.build();
	}

	@GET
	@Path("/getAllArticles")
	@Produces(MediaType.APPLICATION_JSON)
	public Response allArticles() {
	    List<Article> articles = dao.getArticles();
		return Response.status(Response.Status.OK)
			.type(MediaType.APPLICATION_JSON)
			.entity(articles)
			.build();
	}

	@GET
	@Path("/getArticle")
	@Produces(MediaType.APPLICATION_JSON)
	public Response article(@QueryParam("articleId") Long articleId) {
	    Article article = dao.getArticleById(articleId);
		return Response.status(Response.Status.OK)
			.type(MediaType.APPLICATION_JSON)
			.entity(article)
			.build();
	}

	
}
