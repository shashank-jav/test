package com.dgh.model;

import java.util.Date;



public class SingleSignonUserRoleModel  {
	
	
	
	private Integer userRoleId;
	private Date createdOn;
	private String role;
	private SingleSignonUserModel user;
	
	public SingleSignonUserRoleModel() {
	}

	public Integer getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public SingleSignonUserModel getUser() {
		return user;
	}

	public void setUser(SingleSignonUserModel user) {
		this.user = user;
	}

	
	
	
}
