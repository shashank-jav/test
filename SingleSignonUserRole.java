package com.dgh.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the SSO_USER_ROLES database table.
 * 
 */
@Entity
@Table(name="SSO_USER_ROLES")
@NamedQuery(name="SingleSignonUserRole.findAll", query="SELECT u FROM SingleSignonUserRole u")

public class SingleSignonUserRole implements Serializable {
	
	
private static final long serialVersionUID = 1L;
	
	private Integer userRoleId;
	private Date createdOn;
	private String role;
	private SingleSignonUser singlesignonuser;
	
	public SingleSignonUserRole() {
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
	@SequenceGenerator(name = "SEQ", sequenceName = "SSO_USER_ROLES_SEQ", allocationSize=1, initialValue=2)
	@Column(name="USER_ROLE_ID")
	public Integer getUserRoleId() {
		return this.userRoleId;
	}

	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_ON", insertable=false)
	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@Column(name="ROLE")
	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	


	//bi-directional many-to-one association to User
	@ManyToOne()
	@JoinColumn(name="USER_ID")
	public SingleSignonUser getSinglesignonuser() {
		return singlesignonuser;
	}

	public void setSinglesignonuser(SingleSignonUser singlesignonuser) {
		this.singlesignonuser = singlesignonuser;
	}
}
