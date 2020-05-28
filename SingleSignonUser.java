package com.dgh.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the SSO_USERS database table.
 * 
 */
@Entity
@Table(name="SSO_USERS")
@NamedQueries ({
	@NamedQuery(name="findUser", query="from SingleSignonUser where username = :username"),
	@NamedQuery(name="deleteUser", query="delete from SingleSignonUser where username = :username")
})

public class SingleSignonUser implements Serializable   {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String firstName;
	private String lastName;
	private String mobile;
	private String company;
	
	private String username;
	private String password;
	private Integer enabled;
	
	private Date createdOn;
	private Date updatedOn;
	
	private String token;
	private Date tokenCreatedOn;
	private Date tokenExpiresOn;
	private String tempColumn;
	
	private List<SingleSignonUserRole> userRoles = new ArrayList<>();
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
	@SequenceGenerator(name = "SEQ", sequenceName = "SSO_USERS_SEQ", allocationSize=1, initialValue=2)
	@Column(name="ID")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="FIRST_NAME")
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@Column(name="LAST_NAME")
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Column(name="MOBILE")
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Column(name="COMPANY")
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	@Column(name="USERNAME")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(name="PASSWORD")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name="ENABLED")
	public Integer getEnabled() {
		return enabled;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_ON",insertable=false)
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPDATED_ON")
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	@Column(name="TOKEN")
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="TOKEN_CREATED_ON",insertable=true,updatable=true)
	public Date getTokenCreatedOn() {
		return tokenCreatedOn;
	}
	public void setTokenCreatedOn(Date tokenCreatedOn) {
		this.tokenCreatedOn = tokenCreatedOn;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="TOKEN_EXPIRES_ON",insertable=true,updatable=true)
	public Date getTokenExpiresOn() {
		return tokenExpiresOn;
	}
	public void setTokenExpiresOn(Date tokenExpiresOn) {
		this.tokenExpiresOn = tokenExpiresOn;
	}
	@Column(name="TEMP_COLUMN")
	public String getTempColumn() {
		return tempColumn;
	}
	public void setTempColumn(String tempColumn) {
		this.tempColumn = tempColumn;
	}
	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER,mappedBy="singlesignonuser", cascade=CascadeType.ALL)
	public List<SingleSignonUserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(List<SingleSignonUserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public SingleSignonUserRole addUserRole(SingleSignonUserRole userRole) {
		getUserRoles().add(userRole);
		userRole.setSinglesignonuser(this);;

		return userRole;
	}

	public SingleSignonUserRole removeUserRole(SingleSignonUserRole userRole) {
		getUserRoles().remove(userRole);
		userRole.setSinglesignonuser(null);

		return userRole;
	}
}