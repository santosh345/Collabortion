package com.santosh.CollaborationBackEnd.dao;

import java.util.List;

import com.santosh.CollaborationBackEnd.model.Blog;

public interface BlogDAO 
{
public Blog get(String id);
	
public List<Blog> list();
		
public boolean save(Blog blog);
		
public boolean update(Blog blog);
		
public Blog getblogbyid(String id);
		
public boolean delete(Blog blog);
		
}
