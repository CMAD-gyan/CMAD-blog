package org.cisco.blog.data;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
//getSessionFactory() is a static method that provides a SessionFactory
//the creator of Sessions, the basic interfaces between a Java application
//and Hibernate.

//The SessionFactory is built with the StandardServiceRegistryBuilder,
//making use of Configuration. 

//The Configuration is where we can specify properties and mapping 
//documents to be used when creating a SessionFactory.
//So, every method that interacts with the database gets a Session, 
//making use of the getSessionFactory().


//Two basic methods are also used to close the Session, the closeCurrentSession
//and closeCurrentSessionwithTransaction(). Both methods use the session.close() 
//API method of Session to close the Session, but the second method first commits
//the transaction, using getTransaction().commit() API method.


//The basic CRUD methods to interact with a database are Create, Read, Update 
//and Delete.

//Create is done in persist(Book entity) method, with save(Object object) API 
//method of Session, that persists an entity to the database.
//Read is performed both in findById(String id) and in findAll() methods. 
//findById method uses get(Class theClass, Serializable id) API method of 
//Session to retrieve an object by its id, whereas findAll creates a new Query 
//with a String SQL query, to get all rows of the table in a list.
//Update is easily done in update(Book entity) method that uses update
//(Object object) API method of Session.
//Delete is performed in delete(Book entity) and deleteAll() methods, using the
//findById(String id) and findAll() methods respectively to retrieve the 
//objects from the database and then using delete(Object object) API method of 
//Session.

//https://examples.javacodegeeks.com/enterprise-java/hibernate/hibernate-jpa-dao-example/

public class UserDao implements UserDaoImpl<User, String> {


	public UserDao() {
	}

	public void openCurrentSessionwithTransaction() {
		HibernateUtil.openCurrentSessionwithTransaction();
	}
	
	public void closeCurrentSessionwithTransaction() {
		HibernateUtil.closeCurrentSessionwithTransaction();
	}
	
	public void openCurrentSession() {
		HibernateUtil.currentSession();
		
	}
	
	public void closeCurrentSession() {
		HibernateUtil.closeSession();
		
	}
	
	public void persist(User entity) {
		HibernateUtil.currentSession().save(entity);
	}

	public void update(User entity) {
		HibernateUtil.currentSession().update(entity);
	}

	public User findById(String id) {
		User user = (User) HibernateUtil.currentSession().get(User.class, Integer.valueOf(id));
		return user; 
	}

	public User findByUsername(String username) {
		Session session = HibernateUtil.currentSession();
		Criteria criteria = session.createCriteria(User.class);
		User user = (User) criteria.add(Restrictions.eq("username", username)).uniqueResult();
		return user; 
	}

	public boolean isValidPassword(String username, String password) {
		boolean result = false;
		Session session = HibernateUtil.currentSession();
		Criteria criteria = session.createCriteria(User.class);
		User user = (User) criteria.add(Restrictions.eq("username", username)).uniqueResult();
		if (user != null &&   password.equals(user.getPassword())) {
			
			result = true;
		}
		return result;
	}
	
	public void delete(User entity) {
		HibernateUtil.currentSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<User> findAll() {
		List<User> users = (List<User>) HibernateUtil.currentSession().createQuery("from User").list();
		return users;
	}

	public void deleteAll() {
		List<User> entityList = findAll();
		for (User entity : entityList) {
			delete(entity);
		}
	}
}
