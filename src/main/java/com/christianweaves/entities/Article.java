package com.christianweaves.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.NamedQuery;

@Entity
@Table(name = "tb_articles")
@NamedQueries({
	@NamedQuery(name = "allArticles", query = "SELECT a FROM Article a"),
	@NamedQuery(name = "Article.findById", query = "SELECT a FROM Article a where a.id = :id")
})

public class Article implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3352009585501912050L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column
	private String title;

	@Column
	private String subtitle;

	@Column
	private String body;

	@Column(name="date_added")
	private Date dateAdded;
	
	@Column
	private Boolean featured;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean archived;
	
	public Boolean getArchived() {
		return archived;
	}

	public void setArchived(Boolean archived) {
		this.archived = archived;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Article)) {
			return false;
		}
		Article other = (Article) obj;
		if (id != null) {
			if (!id.equals(other.id)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (title != null && !title.trim().isEmpty())
			result += "title: " + title;
		if (subtitle != null && !subtitle.trim().isEmpty())
			result += ", subtitle: " + subtitle;
		return result;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public Boolean getFeatured() {
		return featured;
	}

	public void setFeatured(Boolean featured) {
		this.featured = featured;
	}
	
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}