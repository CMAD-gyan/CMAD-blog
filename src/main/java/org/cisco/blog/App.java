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
		System.out.println("-----------------"  + " Questions "+"------------------");

		QuestionService qService = new QuestionService();
		
		try {
			qService.deleteAll();
		} catch (Exception e) {
		
		}
		
		Question question = new Question("What is java ?", "I am unable to understand the difference between so many java machines", user1);
		try {
			qService.persist(question);
		} catch (Exception e) {
		
		}
		
		Question question2 = new Question("What is jvm ?", "I am unable to understand the difference between so many java machines", user2);
		try {
			qService.persist(question2);
		} catch (Exception e) {
			System.out.println("-----------Unale to createb ");
		}
		
		question2.setUser(user1);
		try {
			qService.persist(question2);
		} catch (Exception e) {
			System.out.println("-----------Unale to createb ");
		}
		
		List<Question> ql = qService.findAll();
		for (Question b : ql) {
			System.out.println("-" + b.toString());
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
		

		
		
//		
//		
//		
//		qService.persist(question);
//		
//		
//		Question question1 = new Question("Anot how are you ?", " ass  this is a long test  dddd", user1);
//		qService.persist(question1);

		
		
		
		//userService.persist(user3);
		System.exit(0);
		
//		List<User> userl = userService.findAll();
//		System.out.println("User Persisted are :");
//		for (User b : userl) {
//			System.out.println("-" + b.toString());
//		}
//		System.out.println("*** Persist - end ***");
//
//		//userService.persist(user2);
//		User userToDel = userService.findByUsername("user2");
//		userService.delete( Integer.toString(userToDel.getId()));
//		
//		List<User> userl1 = userService.findAll();
//		System.out.println("User Persisted are :");
//		for (User b : userl1) {
//			System.out.println("-" + b.toString());
//		}
//		System.out.println("*** Persist - end ***");
//		
//		if (userService.isValidPassword("user1", "password1")) {
//			System.out.println("password for user1 matched");
//		} else {
//			System.out.println("password for user1 did not match");
//		}
//		
//		//user 2 is deleted 
//		if (userService.isValidPassword("user2", "password2")) {
//			System.out.println("password for user2 matched");
//		} else {
//			System.out.println("password for user2 did not match");
//		}
//		
//		if (userService.isValidPassword("user3", "password3")) {
//			System.out.println("password for user3 matched");
//		} else {
//			System.out.println("password for user3 did not match");
//		}
//		
//		
//		if (userService.isValidPassword("user3", "password5")) {
//			System.out.println("password for user3 matched");
//		} else {
//			System.out.println("password for user3 did not match");
//		}
//		
//		
//		Question question = new Question("how are you ?", "this is a long test  dddd", user1);
//		QuestionService qService = new QuestionService();
//		qService.deleteAll();
//		qService.persist(question);
//		
//		
//		Question question1 = new Question("Anot how are you ?", " ass  this is a long test  dddd", user1);
//		qService.persist(question1);
//		
//		
//		Answer answer = new Answer( "this is a long test  dddd", user1, question);
//		AnswerService aService = new AnswerService();
//		aService.deleteAll();
//		aService.persist(answer);
//		
//		
//		Answer answer2 = new Answer(" ass  this is a long test  dddd", user3, question1);
//		aService.persist(answer2);
//		
//		
//		
//		
    }
}
