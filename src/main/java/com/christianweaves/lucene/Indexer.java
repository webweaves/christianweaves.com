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
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.MMapDirectory;
import org.apache.lucene.store.RAMDirectory;

import com.christianweaves.entities.Article;
import com.christianweaves.entities.ArticleDao;

@Named
@RequestScoped
public class Indexer {

	@Inject
	private ArticleDao dao;
	
	public void createIndex() {
		Directory dir=new RAMDirectory();
		IndexWriter writer = null;
		try {
			writer = new IndexWriter(dir, new IndexWriterConfig(new EnglishAnalyzer()));
			for (Article article: dao.getAllArticles()) {
			    Document doc = new Document();    
		        String description = article.getTitle()
		                             + " " + article.getSubtitle() 
		                             + " " + article.getBody();
		        
		        doc.add(new TextField("description", description, Field.Store.NO));		
				writer.addDocument(doc);
			}
	
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		List<String> r = new ArrayList<>();
		String query = "Primefaces";
		int n = 5;
		
		try {
		    QueryParser qp = new QueryParser("content", new EnglishAnalyzer());
		    IndexSearcher indexSearcher = new IndexSearcher(DirectoryReader.open(dir));
		    TopDocs topDocs;
			topDocs = indexSearcher.search(qp.parse(query), n);
			for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
		        r.add(indexSearcher.doc(scoreDoc.doc).get("content"));
		    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
}
