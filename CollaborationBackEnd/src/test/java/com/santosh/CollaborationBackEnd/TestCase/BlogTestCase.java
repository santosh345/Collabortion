package com.santosh.CollaborationBackEnd.TestCase;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.santosh.CollaborationBackEnd.dao.BlogDAO;
import com.santosh.CollaborationBackEnd.model.Blog;
public class BlogTestCase {
@Autowired static AnnotationConfigApplicationContext context;
	
	@Autowired  static Blog Blog;
	
	@Autowired  static BlogDAO  BlogDAO;
	
	@BeforeClass
	public static  void init()
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("com.santosh");
		context.refresh();
		
		Blog = (Blog) context.getBean("blog");
		
		BlogDAO = (BlogDAO) context.getBean("blogDAO");
		 
	}
	
	
	@Test
		public void createblogTestCase()
		{
			long d= System.currentTimeMillis();
			Date today=new Date(d);
			Blog.setId("04");
			Blog.setTitle("Spring MVC");
			Blog.setUser_id("User004");
			Blog.setComments("Hello My name is Tarun");
			Blog.setDescription("Difficult blog");
			Blog.setStatus("p");
			Blog.setDatecreated(today);
			boolean flag =	BlogDAO.save(Blog);
		       
		       assertEquals("createblogTestCase ",true,flag);
		}
		
	@Test
	
		public void updateblog()
		{
			long d= System.currentTimeMillis();
			Date today=new Date(d);

			BlogDAO.getblogbyid("03");
			Blog.setId("03");
			Blog.setTitle("Java");
			Blog.setUser_id("User003");
			Blog.setComments("Good blog");
			Blog.setDescription("My latest blog");
			Blog.setStatus("A");
			Blog.setDatecreated(today);
			
			boolean flag= BlogDAO.update(Blog);
			assertEquals("updateblog", true, flag);
		}
	
	//@Test
	public void deleteblog()
	{
		BlogDAO.getblogbyid("01");
		boolean flag= BlogDAO.delete(Blog);
		assertEquals("deleteblog",true, flag);
	
	}
	
	@Test
	public void listblogs()
	{
		BlogDAO.getblogbyid("01");
		boolean flag = BlogDAO.list()!=null;
		assertEquals("listblogs", true, flag);
	}
	
}
