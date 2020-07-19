package com.christianweaves.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.christianweaves.entities.Article;
import com.christianweaves.entities.ArticleDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/articles")
public class Articles {

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
	    
	    ObjectMapper mapper = new ObjectMapper();
		try {
			Article article = dao.getFeaturedArticle();
			String json = mapper.writeValueAsString(article);
			return Response.status(Response.Status.OK).entity(article).build();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

}
