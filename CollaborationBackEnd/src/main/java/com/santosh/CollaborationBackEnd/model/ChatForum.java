package com.santosh.CollaborationBackEnd.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name="Chat_Forum_details")
public class ChatForum {

	@Id
	private String chatForumId;
	
	private String isChatorForum;
	
	public String getChatForumId() {
		return chatForumId;
	}

	public void setChatForumId(String chatForumId) {
		this.chatForumId = chatForumId;
	}

	public String getIsChatorForum() {
		return isChatorForum;
	}

	public void setIsChatorForum(String isChatorForum) {
		this.isChatorForum = isChatorForum;
	}

	public List<ChatForumMessage> getMessages() {
		return messages;
	}

	public void setMessages(List<ChatForumMessage> messages) {
		this.messages = messages;
	}

	private List<ChatForumMessage> messages=new LinkedList<ChatForumMessage>();

	
	
}
