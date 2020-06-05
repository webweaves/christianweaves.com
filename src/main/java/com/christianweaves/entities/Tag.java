package com.christianweaves.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tb_tags")
@NamedQueries({
	@NamedQuery(name = "tag", query = "SELECT t FROM Tag t WHERE t.tag = :tag")
})
public class Tag implements Serializable {

	private static final long serialVersionUID = 635222246890372552L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
 
    @Column
    private String tag;
    
    @ManyToMany (mappedBy = "tags")
	private List<Article> articles;

    public Tag() {
    }
    
    public Tag(String tag) {
    	this.tag = tag;
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
}