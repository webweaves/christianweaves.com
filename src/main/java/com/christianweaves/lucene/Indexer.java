package com.christianweaves.lucene;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
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
public class Indexer {

	@Inject
	private ArticleDao dao;
	
	private String query;
	
	public void createIndex() {
		Directory dir=new RAMDirectory();
		IndexWriter writer = null;
		try {
			writer = new IndexWriter(dir, new IndexWriterConfig(new EnglishAnalyzer()));
			for (Article article: dao.getAllArticles()) {
			    Document doc = new Document();
			    String id = article.getId().toString();
		        String description = article.getTitle()
		                             + " " + article.getSubtitle() 
		                             + " " + article.getBody();
		        
		        doc.add(new StringField("articleId", id, Field.Store.YES));		
		        doc.add(new TextField("description", description, Field.Store.YES));		
				writer.addDocument(doc);
			}
	
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		List<String> r = new ArrayList<>();
		
		int n = 5;
		
		try {
			//create a term to search file name
		    Term term = new Term("description", getQuery());
		    //create the term query object
		    Query query = new FuzzyQuery(term, 2);
		      
		      
		    QueryParser qp = new QueryParser("description", new EnglishAnalyzer());
		    IndexSearcher indexSearcher = new IndexSearcher(DirectoryReader.open(dir));
		    TopDocs topDocs = indexSearcher.search(query, n);
			for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
		        r.add(indexSearcher.doc(scoreDoc.doc).get("description"));
		    }
			
			ScoreDoc[] hits = topDocs.scoreDocs;
			System.out.println("Found " + hits.length + " hits.");
			for (int i=0; i<hits.length; ++i) {
			    int docId = hits[i].doc;
			    Document d = indexSearcher.doc(docId);
			    System.out.println((i + 1) + ". " + d.get("articleId") + "\t" + d.get("description").substring(0,200));
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	    
	}
	
	public static void main(String[] args) {
		new Indexer().createIndex();
		
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
}