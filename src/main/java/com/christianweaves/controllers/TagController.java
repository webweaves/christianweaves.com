package com.christianweaves.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.christianweaves.entities.Tag;
import com.christianweaves.entities.TagDao;

@Named
@RequestScoped
public class TagController {

	@Inject
	TagDao tagDao;

	public void buttonClick() {
		System.out.println("tag:" + tagDao.getTag("5415"));
	}
	
	public Boolean tagExists(String tag) {
		tagDao.getTag(tag);
		return false;
	}

	public List<Tag> persistTags(List<String> tags) {
		List<Tag> returnTags = new ArrayList<>();
		for (String tag: tags) {
			Tag t = tagDao.getTag(tag);
			if (t == null) {
				t = new Tag(tag);
				tagDao.persist(t);
			}
			returnTags.add(t);
		}
		return returnTags;
	}
}
