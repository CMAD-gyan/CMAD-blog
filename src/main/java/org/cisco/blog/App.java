package org.cisco.blog;
import java.util.List;

import org.cisco.blog.persist.*;


public class App 
{
	public static void main( String[] args )
    {
		password_and_user ();
		question_and_user();
	
    }
	
	//question test
	static void question_and_user( )
    {
		UserService userService = new UserService();
		User user1 = new User("user1", "user1 ahokla" , "user1@gmail.com", "password1");
		User user2 = new User("user2", "user2 bhokla" ,"user2@gmail.com", "password2");
		User user3 = new User("user3", "user3 chokla" ,"user3@gmail.com", "password3");
		
		try {
			userService.deleteAll();
		} catch (Exception e) {
		}
		
		try {
			userService.persist(user1);
		} catch (Exception e) {
		}
		
		try {
			userService.persist(user2);
		} catch (Exception e) {
		}
		
		try {
			userService.persist(user3);
		} catch (Exception e) {
		}
		
		
		QuestionService qService = new QuestionService();
		
		try {
			qService.deleteAll();
		} catch (Exception e) {
		
		}
		
		Question question = new Question("What is java ?", "Why java became popular and then not so now ", user1);
		try {
			qService.persist(question);
		} catch (Exception e) {
		
		}
		
		Question question2 = new Question("What is jvm ?", "I am unable to understand the difference between so many java machines", user2);
		try {
			qService.persist(question2);
		} catch (Exception e) {
			System.out.println("-----------Unale to create ");
		}
		
		question2.setUser(user1);
		try {
			qService.persist(question2);
		} catch (Exception e) {
			System.out.println("-----------Unale to create ");
		}
		
		Answer answer = new Answer( "this is a long test  dddd", user3, question);
		AnswerService aService = new AnswerService();
		
		try {
			aService.deleteAll();
		} catch (Exception e) {
			System.out.println("-----------Unale to deleteall ");
		}
		
		
		try {
			aService.persist(answer);
		} catch (Exception e) {
			System.out.println("-----------Unale to deleteall ");
		}
		
		
		
		
		Answer answer2 = new Answer(" bass  this is a long test  dddd", user1, question2);
		
		try {
			aService.persist(answer2);
		} catch (Exception e) {
			System.out.println("-----------Unale to deleteall ");
		}
		
		List<Answer> al = aService.findAll();
		for (Answer b : al) {
			System.out.println("-" + b.toString());
		}
		
		
    }
	
    static void password_and_user( )
    {
		UserService userService = new UserService();
		User user1 = new User("user1", "user1 ahokla" , "user1@gmail.com", "password1");
		User user2 = new User("user2", "user2 bhokla" ,"user2@gmail.com", "password2");
		User user3 = new User("user3", "user3 chokla" ,"user3@gmail.com", "password3");
		
		try {
			userService.deleteAll();
		} catch (Exception e) {
		}
		
		try {
			userService.persist(user1);
		} catch (Exception e) {
		}
		
		try {
			userService.persist(user2);
		} catch (Exception e) {
		}
		
		try {
			userService.persist(user3);
		} catch (Exception e) {
		}
		
		
		List<User> userl = userService.findAll();
		for (User b : userl) {
			System.out.println("-" + b.toString());
		}
		System.out.println("-----------------"  + " Deleting User2 "+"------------------");

		User userToDel = userService.findByUsername("user2");
		try {
			userService.delete( Integer.toString(userToDel.getId()));
		} catch (Exception e) {
		
		}
		
		List<User> userl2 = userService.findAll();
		for (User b : userl2) {
			System.out.println("-" + b.toString());
		}
		System.out.println("-----------------"  + " Password Verification "+"------------------");
		
		//user 2 is deleted 
		System.out.println("----user2 is deleted and password verification should fail");
		if (userService.isValidPassword("user2", "password2")) {
			System.out.println("password for user2 matched");
		} else {
			System.out.println("password for user2 did not match");
		}
		
		System.out.println("----Password verification should pass");
		if (userService.isValidPassword("user3", "password3")) {
			System.out.println("password for user3 matched");
		} else {
			System.out.println("password for user3 did not match");
		}

		System.out.println("-----Invalid password Password verification should fail");
		if (userService.isValidPassword("user3", "password5")) {
			System.out.println("password for user3 matched");
		} else {
			System.out.println("password for user3 did not match");
		}
		try {
			userService.deleteAll();
		} catch (Exception e) {
		}
		
    }
    
}