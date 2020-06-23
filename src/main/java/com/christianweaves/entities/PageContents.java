package com.christianweaves.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_pagecontents")
public class PageContents {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
    @ManyToOne
    @JoinColumn(name="articleId")
    private Article article;

    @Column
    private Integer theIndex;
    
	@Column
	private String contentText;
	
	@Column
	private String contentReference;

	public PageContents() {}
	
	public PageContents(String addPageContentItem) {
		contentText = addPageContentItem;
	}

	public String getContentReference() {
		return contentReference;
	}

	public void setContentReference(String contentReference) {
		this.contentReference = contentReference;
	}

	public String getContentText() {
		return contentText;
	}

	public void setContentText(String contentText) {
		this.contentText = contentText;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}
	
	@Override
	public String toString() {
		return contentText;
	}

	public Integer getTheIndex() {
		return theIndex;
	}

	public void setTheIndex(Integer theIndex) {
		this.theIndex = theIndex;
	}
}