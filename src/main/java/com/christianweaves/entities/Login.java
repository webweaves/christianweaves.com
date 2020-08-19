package com.christianweaves.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_logins")
public class Login implements Serializable {
	
	private static final long serialVersionUID = 3249233594739885950L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column
	private String username;
	
	@Column
	private String password;
	
	@Column
	private String ip;
	
	@Column(name="date_added")
	private Date dateAdded;

	public Login() {}
	
	public Login(String username, String password, String ipAddress, long timeStampMillis) {
		this.ip = ipAddress;
		this.username = username;
		this.password = password;
		dateAdded = new Date(timeStampMillis);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getLoginDate() {
		return dateAdded;
	}

	public void setLoginDate(Date dateAdded) {
		this.dateAdded = dateAdded;
	}
}
