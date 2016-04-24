package org.cisco.blog.persist;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class UserDao implements DaoImpl<User, String> {

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
		User user = (User) criteria.add(Restrictions.eq("userName", username)).uniqueResult();
		return user; 
	}

	public boolean isValidPassword(String username, String password) {
		boolean result = false;
		Session session = HibernateUtil.currentSession();
		Criteria criteria = session.createCriteria(User.class);
		User user = (User) criteria.add(Restrictions.eq("userName", username)).uniqueResult();
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
