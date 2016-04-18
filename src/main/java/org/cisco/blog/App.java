package org.cisco.blog;

import java.util.List;

import org.cisco.blog.data.*;


public class App 
{
    public static void main( String[] args )
    {
		UserService userService = new UserService();
		User user1 = new User("user1", "user1 ahokla" , "user1@gmail.com", "password1");
		User user2 = new User("user2", "user2 bhokla" ,"user2@gmail.com", "password2");
		User user3 = new User("user3", "user3 chokla" ,"user3@gmail.com", "password3");
		System.out.println("*** Persist - start ***");
		
		userService.deleteAll();
		userService.persist(user1);
		userService.persist(user2);
		userService.persist(user3);
		
		List<User> userl = userService.findAll();
		System.out.println("User Persisted are :");
		for (User b : userl) {
			System.out.println("-" + b.toString());
		}
		System.out.println("*** Persist - end ***");

		//userService.persist(user2);
		User userToDel = userService.findByUsername("user2");
		userService.delete( Integer.toString(userToDel.getId()));
		
		List<User> userl1 = userService.findAll();
		System.out.println("User Persisted are :");
		for (User b : userl1) {
			System.out.println("-" + b.toString());
		}
		System.out.println("*** Persist - end ***");
		
		if (userService.isValidPassword("user1", "password1")) {
			System.out.println("password for user1 matched");
		} else {
			System.out.println("password for user1 did not match");
		}
		
		//user 2 is deleted 
		if (userService.isValidPassword("user2", "password2")) {
			System.out.println("password for user2 matched");
		} else {
			System.out.println("password for user2 did not match");
		}
		
		if (userService.isValidPassword("user3", "password3")) {
			System.out.println("password for user3 matched");
		} else {
			System.out.println("password for user3 did not match");
		}
		
		
		if (userService.isValidPassword("user3", "password5")) {
			System.out.println("password for user3 matched");
		} else {
			System.out.println("password for user3 did not match");
		}
		
		
		System.exit(0);
    }
}
