package com.christianweaves.entities;

import javax.ejb.Stateless;

@Stateless
public class UserService {

	public User find(String username, String password) {
		return new User();
	}

}
