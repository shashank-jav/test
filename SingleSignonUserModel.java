package com.dgh.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;


public class SingleSignonUserModel   {
	
	
	private Integer id;
	private String firstName;
	private String lastName;
	private String mobile;
	
	private String username;
	private String password;
	private Integer enabled;
	
	private Date createdOn;
	private Date updatedOn;
	
	private String token;
	private Date tokenCreatedOn;
	private Date tokenExpiresOn;
	
	private String tempColumn;
	

	private MultipartFile authletter;

	
	
	
	private List<SingleSignonUserRoleModel> userRoles = new ArrayList<>();
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
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
	public Integer getEnabled() {
		return enabled;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Date getTokenCreatedOn() {
		return tokenCreatedOn;
	}
	public void setTokenCreatedOn(Date tokenCreatedOn) {
		this.tokenCreatedOn = tokenCreatedOn;
	}
	public Date getTokenExpiresOn() {
		return tokenExpiresOn;
	}
	public void setTokenExpiresOn(Date tokenExpiresOn) {
		this.tokenExpiresOn = tokenExpiresOn;
	}
	
	
	public List<SingleSignonUserRoleModel> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(List<SingleSignonUserRoleModel> userRoles) {
		this.userRoles = userRoles;
	}

	public SingleSignonUserRoleModel addUserRole(SingleSignonUserRoleModel userRole) {
		getUserRoles().add(userRole);
		userRole.setUser(this);

		return userRole;
	}

	public SingleSignonUserRoleModel removeUserRole(SingleSignonUserRoleModel userRole) {
		getUserRoles().remove(userRole);
		userRole.setUser(null);

		return userRole;
	}
	
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	public MultipartFile getAuthletter() {
		return authletter;
	}
	public void setAuthletter(MultipartFile authletter) {
		this.authletter = authletter;
	}
	
	public String getTempColumn() {
		return tempColumn;
	}
	public void setTempColumn(String tempColumn) {
		this.tempColumn = tempColumn;
	}
	
	
	
}