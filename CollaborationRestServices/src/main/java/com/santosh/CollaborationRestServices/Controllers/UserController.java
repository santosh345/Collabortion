package com.santosh.CollaborationRestServices.Controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.santosh.CollaborationBackEnd.dao.UserDAO;
import com.santosh.CollaborationBackEnd.model.User;

@RestController
public class UserController {
private static Logger log=LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private User user;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/hello")
	public String sayHello()
	{
		return "  Hello from User rest service Modifed message";
	}

	//http://localhost:8080/CollborationRestService/users
	@GetMapping("/users")
	public ResponseEntity< List<User>> getAllUser()
	{
		List<User> userList =  userDAO.list();
		
		return   new ResponseEntity<List<User>>(userList, HttpStatus.OK);
	}

	@PostMapping("/addUser/")
	public String createUser(@RequestBody User newUser)
	{
		log.debug("Starting of createUser");
		user = userDAO.get(newUser.getId());
		if(user==null)
		{
			userDAO.save(newUser);
			log.debug("User added to database");
			log.debug("End of method createUser");
			return "User added";
		}
		else
		{
			log.debug("Error user already exists");
			log.debug("End of method createUser");
			return "User Not added";
		}
	}
	
	@PostMapping("/validatecredentials/")
	public String validatecredentials(@RequestBody User newUser)
	{
		log.debug("Starting of validatecredentials");
		boolean b = userDAO.isValidCredentials(newUser.getId(),newUser.getPassword());
		if(b)
		{
		return "Successfully logged in" ;
		}
		else
		{
			return "Invalid credentials please enter valid credentials";
		}
		
		
	}
	@PostMapping("/logoutuser")
	public void userLogout(@RequestBody User logoutUser)
	{
		log.debug("User Logging out");
		if(userDAO.setUserOffline(logoutUser.getId())){
			log.debug("User set Offline ,Removing session attributes");
			session.removeAttribute("UserID");
			session.removeAttribute("UserRole");
			//return "User logged out";
		}
		else{
			log.debug("Unable to set User offline");
			//return "User not logged out";
		}
	}
	
	
	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUserByID(@PathVariable("id") String id)
	{
		log.debug("**************Starting of the method getUserByID");
		log.info("***************Trying to get userdetails of the id " + id);
		user = userDAO.get(id);
		
		if(user==null)
		{
			user = new User();
			/*user.setErrorCode("404");
			user.setErrorMessage("User does not exist with the id :" + id);*/
		}
		else
		{
			/*user.setErrorCode("200");
			user.setErrorMessage("success");*/
		}
		
		log.info("**************** Name of the user is " + user.getName());
		log.debug("**************Ending of the method getUserByID");
	  return	new ResponseEntity<User>(user , HttpStatus.OK);
	}	
	
	


	
}
