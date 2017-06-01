package com.santosh.CollaborationBackEnd.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.santosh.CollaborationBackEnd.dao.BlogDAO;
import com.santosh.CollaborationBackEnd.model.Blog;


@Repository("blogDAO")
@EnableTransactionManagement
@Transactional
public class BlogDAOImpl implements BlogDAO{
	
private SessionFactory sessionFactory;

	
	public BlogDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
		
	}
@SuppressWarnings("unchecked")
@Transactional
public List<Blog> list() 
{
	
	return sessionFactory.getCurrentSession().createQuery("from Blog").list();

}
@Transactional
public boolean save(Blog blog) 
{
	 try {
		sessionFactory.getCurrentSession().save(blog);
		return true;
	} catch (Exception e) {
		e.printStackTrace();
		return false;
	}
	 

}
@Transactional
public boolean update(Blog blog) 
{
		
	try {
		sessionFactory.getCurrentSession().update(blog);
		return true;
	} catch (Exception e) {
		e.printStackTrace();
		return false;
	}


}
@Transactional
public Blog getblogbyid(String id) 
{
	return (Blog) sessionFactory.getCurrentSession().get(Blog.class, id);

}

@Transactional
public boolean delete(Blog blog) 
{
	try {
		sessionFactory.getCurrentSession().delete(blog);;
		return true;
	} catch (Exception e) {
		e.printStackTrace();
		return false;
	}
}
public Blog get(String id) {
	 Blog blog = (Blog) sessionFactory.getCurrentSession().get(Blog.class, id);
	 return blog;
}
}
