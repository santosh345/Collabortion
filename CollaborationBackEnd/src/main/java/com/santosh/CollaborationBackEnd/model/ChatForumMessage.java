package com.santosh.CollaborationBackEnd.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name="ChatForumMessage_Details")
public class ChatForumMessage {
	
	@Transient
	private long chatForumId;
	
	private String userId;
	
	private String msgData;
	
	private Date msgTime;
	
	private char reportMessage;

	public long getChatForumId() {
		return chatForumId;
	}

	public void setChatForumId(long chatForumId) {
		this.chatForumId = chatForumId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMsgData() {
		return msgData;
	}

	public void setMsgData(String msgData) {
		this.msgData = msgData;
	}

	public Date getMsgTime() {
		return msgTime;
	}

	public void setMsgTime(Date msgTime) {
		this.msgTime = msgTime;
	}

	public char getReportMessage() {
		return reportMessage;
	}

	public void setReportMessage(char reportMessage) {
		this.reportMessage = reportMessage;
	}

}