package com.santosh.CollaborationBackEnd.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name="Friend_Details")
public class Friend {

public String getFriend_Id() {
		return Friend_Id;
	}

	public void setFriend_Id(String friend_Id) {
		Friend_Id = friend_Id;
	}

	public String getFriendStatus() {
		return FriendStatus;
	}

	public void setFriendStatus(String friendStatus) {
		FriendStatus = friendStatus;
	}

	public char getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(char isOnline) {
		this.isOnline = isOnline;
	}

@Id
private String Friend_Id;

private String FriendStatus;

private char isOnline;

private String UserId;

@Transient
private String Errorcode;

public String getErrorcode() {
	return Errorcode;
}

public void setErrorcode(String errorcode) {
	Errorcode = errorcode;
}

public String getErrormessage() {
	return Errormessage;
}

public void setErrormessage(String errormessage) {
	Errormessage = errormessage;
}

@Transient
private String Errormessage;

public String getUserId() {
	return UserId;
}

public void setUserId(String loggedInUserID) {
	UserId = loggedInUserID;
}

}
