package com.santosh.CollaborationBackEnd.dao;

import java.util.List;

import com.santosh.CollaborationBackEnd.model.ChatForum;
import com.santosh.CollaborationBackEnd.model.ChatForumMessage;

public interface ChatForumDAO {
	
	public List<ChatForum> getAllChatForum();
	
	public ChatForum getChatForumById(long chatForumId);
	
	public boolean createChatForum(ChatForum chatForum);
	
	public boolean addMessage(ChatForumMessage chatForumMessage,long chatForumId);
	
	//public boolean removeChatForumMessage(ChatForumMessage chatForumMessage,long chatForumId);
	
	public boolean removeChatForumMessage(long chatForumId);
	
	public boolean deleteChatForum(ChatForum chatForum);

}