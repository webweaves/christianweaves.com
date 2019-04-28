package com.christianweaves.controllers;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@SessionScoped
public class TestController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1410305507424333070L;

	@PostConstruct
	public void init() {
		System.out.println("TEST");
	}
	
}
