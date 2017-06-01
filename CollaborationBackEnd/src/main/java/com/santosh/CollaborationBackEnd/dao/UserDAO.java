package com.santosh.CollaborationBackEnd.dao;

import java.util.List;

import com.santosh.CollaborationBackEnd.model.User;

public interface UserDAO {
public User  get(String id);
	
	public List<User>  list();
	
	
	//If you are not using spring security,  you need to defin validate function
	public boolean isValidCredentials(String id, String password);
	
	
	public boolean save(User user);
	
	public boolean update(User user);
	
	public boolean setUserOffline(String userID);
	
	
	
}
