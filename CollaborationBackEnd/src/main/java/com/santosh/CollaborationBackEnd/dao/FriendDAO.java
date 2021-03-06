package com.santosh.CollaborationBackEnd.dao;

import java.util.List;

import com.santosh.CollaborationBackEnd.model.Friend;

public interface FriendDAO {
public List<Friend> getMyFriends(String userID);
	
	public Friend get(String UserId, String friendID);
	
	
	public Friend get(String Friend_ID);
	
	
	//If you want to get all details of you friend
	//You can use get(userID) of UserDAO interface


	public boolean save(Friend friend);
	
	public boolean update(Friend friend);

	public void delete(String UserId, String Friend_Id);
	
	//select * from Friend where friendID = ? and status ='N'
	public List<Friend> getNewFriendRequests(String userID);
	
	public void setOnline(String userID);
	
	//select * from Friend where userID=? status = 'N'
	public List<Friend> getRequestsSendByMe(String userID);
	



}
