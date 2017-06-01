package com.santosh.CollaborationBackEnd.dao.impl;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.santosh.CollaborationBackEnd.dao.UserDAO;
import com.santosh.CollaborationBackEnd.model.User;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO{
	 private static Logger log = LoggerFactory.getLogger(UserDAOImpl.class);
		
		private  SessionFactory  sessionFactory;
		
		
		public UserDAOImpl(SessionFactory sessionFactory)
		{
			this.sessionFactory=sessionFactory;
		}	
		
		public boolean delete(String id)
		{
			log.debug("Starting of the method delete");
			log.debug("Trying to delte the record : " + id);
			try
			{
			
			sessionFactory.getCurrentSession().delete(get(id));
			log.debug("successfully delted the record :" + id);
			}catch(Exception e)
			{
				log.debug("record does not exist with the id " + id);
				return false;
				
			}
			log.debug("Ending of the method delete");
			return true;
		}
		

		public User get(String id) {
			 User user = (User) sessionFactory.getCurrentSession().get(User.class, id);
			 
			 return user;
		}

		@SuppressWarnings("unchecked")
		public List<User> list() {
			 return sessionFactory.getCurrentSession().createQuery("from User").list();
			
		}
	/**\
	 * If spring security used, we no need to write this method
	 */
		public boolean isValidCredentials(String id, String password) {
			Query query =  sessionFactory.getCurrentSession().createQuery("from User where id =? and password = ? ");
			query.setString(0, id);
			query.setString(1, password);
			
			 if( query.uniqueResult() ==null)
			 {
				 return false;
			 }
			 else
			 {
				 return true;
			 }
			
		
		}

		public boolean save(User user) {
			try {
				sessionFactory.getCurrentSession().save(user);
				return true;
			} catch (HibernateException e) {
				
				e.printStackTrace();
				return false;
			}
		
		}

		public boolean update(User user) {
		try {
			sessionFactory.getCurrentSession().update(user);
			return true;
		} catch (HibernateException e) {
		
			e.printStackTrace();
			return false;
		}
		}

		public boolean setUserOffline(String userID){ 
		log.debug("Setting user:"+userID+" as offline");
		try{
		User user=(User) sessionFactory.getCurrentSession().createQuery("from User where userID='"+userID+"'").uniqueResult();
		user.setIsOnline("No");
		user.setIsOffline(new Date(0));
		log.debug(userID+"is now Offline");
		update(user);
		return true;
		}
		catch(Exception e){
			log.debug("Error occured while setting user offline");
			return false;
		}
	}

	}
