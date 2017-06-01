package com.santosh.CollaborationBackEnd.TestCase;

import java.util.Date;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.santosh.CollaborationBackEnd.dao.ChatForumDAO;
import com.santosh.CollaborationBackEnd.model.ChatForum;
import com.santosh.CollaborationBackEnd.model.ChatForumMessage;

public class ChatForumTestCase {
	
	@Autowired
	static AnnotationConfigApplicationContext context;
	
	@Autowired
	static ChatForum chatForum;
	
	@Autowired
	static ChatForumDAO chatForumDAO;
	
	@BeforeClass
	public static  void init()
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("com.santosh");
		context.refresh();
		chatForum = (ChatForum) context.getBean("chatForum");
		chatForumDAO = (ChatForumDAO) context.getBean("chatForumDAO");
	}


	@Test
	public void createChatForumTestCase() {
		ChatForumMessage message=new ChatForumMessage();
		message.setUserId("User001");
		message.setMsgData("Hello from User001");
		message.setMsgTime(new Date());
		chatForum.setIsChatorForum("Chat");
		chatForum.getMessages().add(message);
		boolean flag=chatForumDAO.createChatForum(chatForum);
		Assert.assertEquals("createChatForumTestCase",flag,false);
	}
	
	@Test
	public void getAllChatForumTestCase()
	{
		int flag=chatForumDAO.getAllChatForum().size();
		Assert.assertEquals("getAllChatForumTestCase",flag,1);
	}
	
	@Test
	public void getChatForumbyId()
	{
		boolean flag=false;
		ChatForum cfTest=chatForumDAO.getChatForumById(01);
		if(cfTest!=null)
			flag=true;
		Assert.assertEquals("getChatForumbyId",flag,true);
	}
	
	@Test
	public void addMessageTestCase()
	{
		ChatForumMessage message=new ChatForumMessage();
		message.setMsgData("Testing chat Forum");
		message.setMsgTime(new Date());
		message.setReportMessage('N');
		message.setUserId("User001");
		boolean flag=chatForumDAO.addMessage(message, 01);
		Assert.assertEquals("addMessageTestCase",flag,false);
	}
	
	//@Test
	public void removeChatForumMessageTestCase()
	{
		boolean flag=chatForumDAO.removeChatForumMessage(5);
		Assert.assertEquals("removeChatForumMessageTestCase",flag,true);
	}
	
	//@Test
	public void removeChatForum(){
		chatForum=chatForumDAO.getChatForumById(5);
		boolean flag=chatForumDAO.deleteChatForum(chatForum);
		Assert.assertEquals("removeChatForumMessageTestCase",flag,true);
	}

}