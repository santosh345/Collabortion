package com.santosh.CollaborationBackEnd.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name="JobApplication_Details")
public class JobApplication {
@Id
private Long Id;
	
@Column(name="user_id")
private String userID;

@Column(name="job_id")
private Long JobId;

@Column(name="date_applied")
private Date dateapplied;


public Long getId() {
	return Id;
}

public void setId(Long id) {
	Id = id;
}

public String getUserID() {
	return userID;
}

public void setUserID(String userID) {
	this.userID = userID;
}

public Long getJobId() {
	return JobId;
}

public void setJobId(Long jobId) {
	JobId = jobId;
}

public Date getDateapplied() {
	return dateapplied;
}

public void setDateapplied(Date dateapplied) {
	this.dateapplied = dateapplied;
}

public String getRemarks() {
	return remarks;
}

public void setRemarks(String remarks) {
	this.remarks = remarks;
}

public char getStatus() {
	return status;
}

public void setStatus(char status) {
	this.status = status;
}

private String remarks;

private char status;


	
}
