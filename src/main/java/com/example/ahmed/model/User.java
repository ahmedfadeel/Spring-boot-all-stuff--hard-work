package com.example.ahmed.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;

import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="users")

public class User {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private long id;	
@Column(unique=true ,nullable = false)
private  String userName;

private String password;
private String roles;
private boolean active;
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getRoles() {
	return roles;
}
public void setRoles(String roles) {
	this.roles = roles;
}
public boolean isActive() {
	return active;
}
public void setActive(boolean active) {
	this.active = active;
}

	
}
