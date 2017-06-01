package com.santosh.CollaborationBackEnd.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name="Comments")
public class Comment {
@Transient
private long blogId;

@Id

private String commentUser_Id;

private String commentText;

private Date commentDate;

private char reportComment;

public long getBlogId() {
	return blogId;
}

public void setBlogId(long blogId) {
	this.blogId = blogId;
}

public String getCommentUser_Id() {
	return commentUser_Id;
}

public void setCommentUser_Id(String commentUser_Id) {
	this.commentUser_Id = commentUser_Id;
}

public String getCommentText() {
	return commentText;
}

public void setCommentText(String commentText) {
	this.commentText = commentText;
}

public Date getCommentDate() {
	return commentDate;
}

public void setCommentDate(Date commentDate) {
	this.commentDate = commentDate;
}

public char getReportComment() {
	return reportComment;
}

public void setReportComment(char reportComment) {
	this.reportComment = reportComment;
}




}
