package com.santosh.CollaborationBackEnd.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="user_details")
public class User {

	@Id
	private String id;
	
	private String name;
	
	private String role;
	
	private String password;
	
	private String address;
	
	private String mobile;
	
	@Column(name="isOnline")
	private String IsOnline;
	
	@Transient
	private String ErrorCode;
	
	@Transient
	private String ErrorMessage;
	
	@Transient
	private String IsOffline; 
	
	public String getIsOffline() {
		return IsOffline;
	}

	public void setIsOffline(String isOffline) {
		IsOffline = isOffline;
	}

	private String email;

	public String getId() {
		return id;
	}

	public String getErrorCode() {
		return ErrorCode;
	}

	public void setErrorCode(String errorCode) {
		ErrorCode = errorCode;
	}

	public String getErrorMessage() {
		return ErrorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		ErrorMessage = errorMessage;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getIsOnline() {
		return IsOnline;
	}

	public void setIsOnline(String isOnline) {
		IsOnline = isOnline;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setIsOffline(Date date) {
		
		
	}


}
