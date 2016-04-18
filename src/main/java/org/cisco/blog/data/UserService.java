package org.cisco.blog.data;

import java.util.List;


public class UserService {
	private static UserDao userDao;

	public UserService() {
		userDao = new UserDao();
	}

	public void persist(User entity) throws Exception {
		userDao.openCurrentSessionwithTransaction();
		try {
			userDao.persist(entity);
		} catch (Exception e) {
			userDao.closeCurrentSession();
			throw e;
		} 
		userDao.closeCurrentSessionwithTransaction();
	}

	public void update(User entity) throws Exception {
		userDao.openCurrentSessionwithTransaction();
		try {
			userDao.update(entity);
		} catch (Exception e) {
			userDao.closeCurrentSession();
			throw e;
		} 
		userDao.closeCurrentSessionwithTransaction();
	}

	public User findByUsername(String username) {
		userDao.openCurrentSession();
		User user = userDao.findByUsername(username);
		userDao.closeCurrentSession();
		return user;
	}
	
	public boolean  isValidPassword(String username, String password) {
		userDao.openCurrentSession();
		boolean result =  userDao.isValidPassword (username, password);
		userDao.closeCurrentSession();
		return result;
	}

	public User findById(String id) {
		userDao.openCurrentSession();
		User user = userDao.findById(id);
		userDao.closeCurrentSession();
		return user;
	}

	public void delete(String id) throws Exception {
		userDao.openCurrentSessionwithTransaction();
		User user = userDao.findById(id);
		try {
			userDao.delete(user);
		} catch (Exception e) {
			userDao.closeCurrentSession();
			throw e;
		} 
		//userDao.delete(user);
		userDao.closeCurrentSessionwithTransaction();
	}

	public List<User> findAll() {
		userDao.openCurrentSession();
		List<User> users = userDao.findAll();
		userDao.closeCurrentSession();
		return users;
	}
	
	public void deleteAll() throws Exception {
		userDao.openCurrentSessionwithTransaction();
		try {
			userDao.deleteAll();
		} catch (Exception e) {
			userDao.closeCurrentSession();
			throw e;
		} 
		//userDao.deleteAll();
		userDao.closeCurrentSessionwithTransaction();
	}

	public UserDao userDao() {
		return userDao;
	}

}