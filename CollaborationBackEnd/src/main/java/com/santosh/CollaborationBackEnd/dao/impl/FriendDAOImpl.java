package com.santosh.CollaborationBackEnd.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.santosh.CollaborationBackEnd.dao.FriendDAO;
import com.santosh.CollaborationBackEnd.model.Friend;
@Repository("friendDAO")
@EnableTransactionManagement
@Transactional
public class FriendDAOImpl implements FriendDAO {

private static final Logger log = LoggerFactory.getLogger(UserDAOImpl.class);

@Autowired
private SessionFactory sessionFactory;

public FriendDAOImpl(SessionFactory sessionFactory) {
	try {
		this.sessionFactory = sessionFactory;
	} catch (Exception e) {
		log.error(" Unable to connect to db");
		e.printStackTrace();
	}
}

private Integer getMaxId() {
	log.debug("->->Starting of the method getMaxId");

	String hql = "select max(id) from Friend";
	Query query = sessionFactory.openSession().createQuery(hql);
	Integer maxID;
	try {
		maxID = (Integer) query.uniqueResult();
	} catch (Exception e) {
		
		e.printStackTrace();
		return 100;
	}
	log.debug("Max id :" + maxID);
	return maxID;

}

@Transactional
public boolean save(Friend friend) {

	try {
		log.debug("*********************88Previous id " + getMaxId());
		//friend.setUserId(getMaxId() + 1);
		log.debug("***********************generated id:" + getMaxId());
		sessionFactory.getCurrentSession().save(friend);
		return true;
	} catch (HibernateException e) {
		
		e.printStackTrace();
		return false;
	}

}

@Transactional
public boolean update(Friend friend) {

	try {
		log.debug("Starting of the method update");
		log.debug("user ID : " + friend.getUserId() + " Friend id :" + friend.getFriend_Id());
		sessionFactory.getCurrentSession().update(friend);
		log.debug("Successfully updated");
		return true;
	} catch (HibernateException e) {
		
		e.printStackTrace();
		log.debug("Not able to update the status");
		return false;
	}

}

@Transactional
public void delete(String userID, String friendID) {
	Friend friend = new Friend();
	friend.setFriend_Id(friendID);
	friend.setUserId(userID);
	sessionFactory.openSession().delete(friend);
}

/**
 * get the friends who are already accepcted
 */

@Transactional
public List<Friend> getMyFriends(String userID) {
	/*
	 * select user_id from c_friend where user_id='Srinivas' and status ='A'
	 * UNION select friend_id from c_friend where user_id='Srinivas' and
	 * status ='A' MINUS select user_id from c_friend where
	 * user_id='Srinivas';
	 */
	String hql1 = "select friendID  from Friend where userID='" + userID + "' and status = 'A' ";

			/*+ " union  " +*/

	String hql2= "select userID from Friend where friendID='" + userID + "' and status = 'A'";

	log.debug("getMyFriends hql1 : " + hql1);
	log.debug("getMyFriends hql2 : " + hql2);

	@SuppressWarnings("unchecked")
	List<Friend> list1 = sessionFactory.openSession().createQuery(hql1).list();
	@SuppressWarnings("unchecked")
	List<Friend> list2 = sessionFactory.openSession().createQuery(hql2).list();
	
	
	
	list1.addAll(list2);

	return list1;

}

@SuppressWarnings("unchecked")
@Transactional
public List<Friend> getNewFriendRequests(String friendID) {
	String hql = "select userID from Friend where friendID=" + "'" + friendID + "' and status ='" + "N'";

	log.debug(hql);
	 return  sessionFactory.openSession().createQuery(hql).list();



}


@SuppressWarnings("unchecked")
@Transactional
public List<Friend> getRequestsSendByMe(String userID) {
	String hql = "select friendID from Friend where userID=" + "'" + userID + "' and status ='" + "N'";

	log.debug(hql);
	return  sessionFactory.openSession().createQuery(hql).list();

}


@Transactional
public Friend get(String userID, String friendID) {
	String hql = "from Friend where userID=" + "'" + userID + "' and friendID= '" + friendID + "'";

	log.debug("hql: " + hql);
	Query query = sessionFactory.openSession().createQuery(hql);

	return (Friend) query.uniqueResult();

}
@Transactional
public Friend get(String userID)
{
	String hql = "from Friend where userID=" + "'" + userID + "' or friendID= '" + userID + "'";

	log.debug("hql: " + hql);
	Query query = sessionFactory.openSession().createQuery(hql);

	return (Friend) query.uniqueResult();

}

@Transactional
public void setOnline(String friendID) {
	log.debug("Starting of the metnod setOnline");
	//String hql = " UPDATE Friend	SET isOnline = 'Y' where friendID='" + friendID + "'";
	
	String hql = " UPDATE Friend	SET isOnline = 'Y' where friendID= ?";
	
	
	
	
	log.debug("hql: " + hql);
	Query query = sessionFactory.openSession().createQuery(hql);
	query.setString(0, friendID);
	
	query.executeUpdate();
	log.debug("Ending of the metnod setOnline");

}

@Transactional
public void setOffLine(String friendID) {
	log.debug("Starting of the metnod setOffLine");
	String hql = " UPDATE Friend	SET isOnline = 'N' where friendID='" + friendID + "'";
	log.debug("hql: " + hql);
	Query query = sessionFactory.openSession().createQuery(hql);
	query.executeUpdate();
	log.debug("Ending of the metnod setOffLine");

}
	
}
