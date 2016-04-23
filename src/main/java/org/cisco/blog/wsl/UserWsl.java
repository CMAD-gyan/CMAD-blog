package org.cisco.blog.wsl;
import java.util.ArrayList;
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
import org.cisco.blog.persist.User;
import org.cisco.blog.persist.UserService;

@Path("/users")
public class UserWsl {
	protected int userId;
	protected String username;
	protected String name;
	protected String email;
	
	//@JsonIgnoreProperties
	protected String password;
	
	UserWsl (int userId,  String username, String name, String email) {
		this.username = username;
		this.name = name;
		this.email = email;
		this.userId = userId;
	}
	
	UserWsl ( String username, String name, String email, String password) {
		this.username = username;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public UserWsl ( ) {

	}
	
	public int getUserId() {
		return this.userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getUsername() {
		return this.username;
	}

	public String getEmail() {
		return this.email;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password =  password;
	}

	public void userWrite() throws Exception {
		User user = new User(username, name,email, password);
		UserService userService  = new UserService() ;
		userService.persist(user);
	}
	
	public List<UserWsl> userReadAll() throws Exception {
		UserService userService  = new UserService() ;
		List<UserWsl> list = new ArrayList<UserWsl>();
		List<User> user = userService.findAll();
		for (User b : user) {
			UserWsl up = new UserWsl(b.getId(), b.getUserName(), b.getName(), b.getEmail());
			list.add(up);
		}
		return list;
	}
	
	@GET
	@Path("/{param}")
	@Produces(MediaType.APPLICATION_JSON)
	public UserWsl userGet(@PathParam("param") String param) throws Exception {
		String username=null;
		String id = null;
		User user = null;
		UserWsl userWsl;
		
		UserService userService  = new UserService() ;
		try{
			if (Integer.parseInt(param) > 0) {
				id = param;
			}
		} catch (Exception e) {
			username = param;
		}
		
		if (username == null) {
			user = userService.findById(id);
			userWsl = new UserWsl (user.getId(), user.getUserName(), user.getName(), user.getEmail());
		} else {
			user = userService.findByUsername(username);
			userWsl = new UserWsl (user.getId(), user.getUserName(), user.getName(), user.getEmail());
		}
		return userWsl;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void UserCreate(UserWsl user) throws Exception {
		user.userWrite();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void UserUpdate(UserWsl user) throws Exception {
		user.userWrite();
	} 
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserWsl> ueseGet() throws Exception {
		UserWsl  a = new UserWsl();
		return a.userReadAll();
	}
	
	@DELETE
	@Path("/{param}")
	public void DeleteUser(@PathParam("param") String param) throws Exception {
		String username=null;
		String id = null;
		User user = null;
		
		UserService userService  = new UserService() ;
		try{
			if (Integer.parseInt(param) > 0) {
				id = param;
			}
		} catch (Exception e) {
			username = param;
		}
		
		if (username == null) {
			userService.delete(id);
		} else {
			user = userService.findByUsername(username);
			userService.delete(String.valueOf(user.getId())) ;
		}
	}
}
