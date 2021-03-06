package com.christianweaves.lucene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.primefaces.context.RequestContext;

import com.christianweaves.controllers.ArticleController;
import com.christianweaves.entities.Article;
import com.christianweaves.entities.ArticleDao;
import com.christianweaves.entities.Tag;

@Named
@RequestScoped
public class InMemorySearch {

	Logger logger = Logger.getLogger(InMemorySearch.class);
	
	private int resultCount = 10;
	
	@Inject
	private ArticleDao dao;
	
	@Inject 
	private ArticleController articleController;
	
	private String query;
	private String snippetQuery;
	
	private List<Article> results;
	
	private String extractSearchStringFromArticle(Article article) {
        String description = article.getTitle()
                             + " " + article.getSubtitle()
                             + " " + article.getBody();
        //add the tags
        for (Tag tag: article.getTags()) {
        	description += " " +tag.getTag();
        }
        return description;
	}
	
	public String search() {
		Directory dir=new RAMDirectory();
		IndexWriter writer = null;
		try {
			writer = new IndexWriter(dir, new IndexWriterConfig(new StandardAnalyzer()));
			for (Article article: dao.getArticles()) {
			    Document doc = new Document();
			    String id = article.getId().toString();
			    String description = extractSearchStringFromArticle(article);
		        doc.add(new StringField("articleId", id, Field.Store.YES));		
		        doc.add(new TextField("description", description.toLowerCase(), Field.Store.YES));		
				writer.addDocument(doc);
			}
	
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<String> r = new ArrayList<>();
		
		try {
			//create a term to search 
		    Term term = new Term("description", getQuery().toLowerCase());
		    //create the term query object
		    Query queryObj = new FuzzyQuery(term, 1);
		    IndexSearcher indexSearcher = new IndexSearcher(DirectoryReader.open(dir));
		    TopDocs topDocs = indexSearcher.search(queryObj, resultCount);
			for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
		        r.add(indexSearcher.doc(scoreDoc.doc).get("description"));
		    }
			
			ScoreDoc[] hits = topDocs.scoreDocs;
			logger.debug("Found " + hits.length + " hits");
			results = new ArrayList<>();
			for (int i=0; i<hits.length; ++i) {
			    int docId = hits[i].doc;
			    Document d = indexSearcher.doc(docId);
			    results.add(dao.getArticleById(Long.valueOf(d.get("articleId"))));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} 	    
		
		if (results.size() > 0) {
			Map<String, Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
			sessionMapObj.put("results", results);
			return "/showResults.xhtml?faces-redirect=true";
		} else {
	        FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage("Search results",  "Nothing found!") );
	        RequestContext.getCurrentInstance().update("growlform:growl");
	        return null;
		}
	}
	
	public String snippetSearch() {
		List<Article> articles = loadSnippets();
		return null;
	}
	
	public List<Article> loadSnippets() {
		List<Article> articles = articleController.getAllSnippets();
		if (getSnippetQuery() == null) {
			return articles;
		}
		
		Directory dir=new RAMDirectory();
		IndexWriter writer = null;
		try {
			writer = new IndexWriter(dir, new IndexWriterConfig(new StandardAnalyzer()));
			for (Article article: articles) {
			    Document doc = new Document();
			    String id = article.getId().toString();
			    String description = extractSearchStringFromArticle(article);
		        doc.add(new StringField("articleId", id, Field.Store.YES));		
		        doc.add(new TextField("description", description.toLowerCase(), Field.Store.YES));		
				writer.addDocument(doc);
			}
	
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<String> r = new ArrayList<>();
		
		try {
			//create a term to search 
		    Term term = new Term("description", getSnippetQuery().toLowerCase());
		    //create the term query object
		    Query queryObj = new FuzzyQuery(term, 1);
		    IndexSearcher indexSearcher = new IndexSearcher(DirectoryReader.open(dir));
		    TopDocs topDocs = indexSearcher.search(queryObj, resultCount);
			for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
		        r.add(indexSearcher.doc(scoreDoc.doc).get("description"));
		    }
			
			ScoreDoc[] hits = topDocs.scoreDocs;
			logger.debug("Found " + hits.length + " hits");
			results = new ArrayList<>();
			for (int i=0; i<hits.length; ++i) {
			    int docId = hits[i].doc;
			    Document d = indexSearcher.doc(docId);
			    results.add(dao.getArticleById(Long.valueOf(d.get("articleId"))));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} 	    
		
		return results;
	}
	
	public static void main(String[] args) {
		new InMemorySearch().search();
		
	}

	public List<Article> getResults() {
		return results;
	}

	public void setResults(List<Article> results) {
		this.results = results;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getSnippetQuery() {
		return snippetQuery;
	}

	public void setSnippetQuery(String snippetQuery) {
		this.snippetQuery = snippetQuery;
	}
}