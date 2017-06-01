package com.santosh.CollaborationBackEnd.TestCase;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.santosh.CollaborationBackEnd.dao.UserDAO;
import com.santosh.CollaborationBackEnd.model.User;

public class UserTestCase {
@Autowired  static AnnotationConfigApplicationContext context;
	
	@Autowired  static User user;
	
	@Autowired  static UserDAO  userDAO;
	
	
	
	@BeforeClass
	public static  void init()
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("com.santosh");
		context.refresh();
		
		user = (User) context.getBean("user");
		
		userDAO = (UserDAO) context.getBean("userDAO");
		 
	}
	
	
	@Test
	
	public void validateCredentialsTestCase()
	{
		
	boolean flag =	  userDAO.isValidCredentials("User001","brave");
	
	assertEquals("validateCredentialsTestCase", false , flag);		
	}
	
	
	@Test
	public void createUserTestCase()
	{
		user = new User();
		user.setId("santosh");
		user.setName("santosh2");
		user.setPassword("sant2");
		user.setRole("Student1");
		user.setAddress("Chennai2");
		user.setMobile("7358521114");
		user.setIsOnline("No");
		user.setEmail("santosh@gmail.com");
	       boolean flag =userDAO.save(user);
	       
	       assertEquals("createUserTestCase ",true, flag);
	}
	
	
	@Test
	public void updateUserTestCase()
	{
		user = new User();
		user.setId("santosh2");
		user.setName("santosh3");
		user.setPassword("sant3");
		user.setRole("Student2");
		user.setAddress("Pune1");
		user.setMobile("9080556036");
		user.setIsOnline("Y");
		user.setEmail("nssantoshkumar93@gmail.com");

	       boolean flag =	userDAO.update(user);
	       
	       assertEquals("updateUserTestCase ",false, flag);
	}
	
	
	@Test
	public void getUserTestCase()
	{
		user =  userDAO.get("santosh");
		
		assertEquals("getUserTestCase", null, user);
		
		
		
	}
	
	
	@Test
	public void getAllUsersTestCase()
	{
		List<User> user=  userDAO.list();
	
		assertEquals("getAllUsersTestCase",5,user.size());
	}

}
