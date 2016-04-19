package org.cisco.blog.data;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Path("/user")
public class UserServiceHib {
	private static UserDao userDao;

	public UserServiceHib() {
		userDao = new UserDao();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
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

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
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

	@GET
	@Path("/{param}")
	@Produces({MediaType.APPLICATION_JSON})
	public User findById(@PathParam("param") String id) {
		userDao.openCurrentSession();
		User user = userDao.findById(id);
		userDao.closeCurrentSession();
		return user;
	}

	@DELETE
	@Path("/{param}")
	@Produces({MediaType.APPLICATION_JSON})
	public void delete(@PathParam("param") String id) throws Exception {
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

	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<User> findAll() {
		userDao.openCurrentSession();
		List<User> users = userDao.findAll();
		userDao.closeCurrentSession();
		return users;
	}
	
	@DELETE
	@Produces({MediaType.APPLICATION_JSON})
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