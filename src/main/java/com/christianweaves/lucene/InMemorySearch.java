package com.christianweaves.lucene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

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

import com.christianweaves.entities.Article;
import com.christianweaves.entities.ArticleDao;

@Named
@RequestScoped
public class InMemorySearch {

	private int resultCount = 10;
	
	@Inject
	private ArticleDao dao;
	
	private String query;
	
	private List<Article> results;
	
	public void createIndex() {
		Directory dir=new RAMDirectory();
		IndexWriter writer = null;
		try {
			writer = new IndexWriter(dir, new IndexWriterConfig(new StandardAnalyzer()));
			for (Article article: dao.getAllArticles()) {
			    Document doc = new Document();
			    String id = article.getId().toString();
		        String description = article.getTitle()
		                             + " " + article.getSubtitle() 
		                             + " " + article.getBody();
		        
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
			//create a term to search file name
		    Term term = new Term("description", getQuery().toLowerCase());
		    //create the term query object
		    Query query = new FuzzyQuery(term, 2);
		    IndexSearcher indexSearcher = new IndexSearcher(DirectoryReader.open(dir));
		    TopDocs topDocs = indexSearcher.search(query, resultCount);
			for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
		        r.add(indexSearcher.doc(scoreDoc.doc).get("description"));
		    }
			
			ScoreDoc[] hits = topDocs.scoreDocs;
			System.out.println("Found " + hits.length + " hits.");
			results = new ArrayList<>();
			for (int i=0; i<hits.length; ++i) {
			    int docId = hits[i].doc;
			    Document d = indexSearcher.doc(docId);
			    results.add(dao.getArticleById(Long.valueOf(d.get("articleId"))));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} 	    
	}
	
	public static void main(String[] args) {
		new InMemorySearch().createIndex();
		
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public List<Article> getResults() {
		return results;
	}

	public void setResults(List<Article> results) {
		this.results = results;
	}
}