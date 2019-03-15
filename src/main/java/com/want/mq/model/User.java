package com.want.mq.model;

import java.io.Serializable;

public class User implements Serializable{
	private static final long serialVersionUID = 1625156032570921456L;
	private String id;
	private String name; //ldap givenName + sn
	private String gender;
	private String ou;	
	private String password;
	
	public User() {	
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getOu() {
		return ou;
	}
	public void setOu(String ou) {
		this.ou = ou;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
