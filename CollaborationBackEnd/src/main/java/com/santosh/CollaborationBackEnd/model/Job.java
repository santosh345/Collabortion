package com.santosh.CollaborationBackEnd.model;

import java.sql.Clob;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name="Job_Details")
public class Job {
@Id
private String Job_ID;

private String Job_Name;

private Clob JobDescription;

private double JobSalary;

public String getJob_ID() {
	return Job_ID;
}

public void setJob_ID(String job_ID) {
	Job_ID = job_ID;
}

public String getJob_Name() {
	return Job_Name;
}

public void setJob_Name(String job_Name) {
	Job_Name = job_Name;
}

public Clob getJobDescription() {
	return JobDescription;
}

public void setJobDescription(Clob jobDescription) {
	JobDescription = jobDescription;
}

public double getJobSalary() {
	return JobSalary;
}

public void setJobSalary(double jobSalary) {
	JobSalary = jobSalary;
}


}
