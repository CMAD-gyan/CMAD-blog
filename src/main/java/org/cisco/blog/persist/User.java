package org.cisco.blog.persist;
import java.sql.Timestamp;

public class User {
	private int  id;
	private String userName;
	private String name;
	private String email;
	private String password;
	private Timestamp createTime;
	private int score;
	private boolean active;

	public User() {
	}

	public User(String username, String name, String email, String password){
		this.userName = username;
		this.name = name;
		this.email = email;
		this.password = password;
		this.score = 0;
		this.active = true;
		this.createTime = new Timestamp(System.currentTimeMillis());
	}

	public int getId (){
		return id;
	}

	public void setId (int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName (String username){
		this.userName = username;
	}
	
	public String getName() {
		return name;
	}

	public void setName (String name){
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail (String email){
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword (String password){
		this.password = password;
	}
	
	
	public int getScore() {
		return score;
	}

	public void setScore (int score){
		this.score = score;
	}

	
	public boolean getActive() {
		return active;
	}

	public void setActive (boolean active){
		this.active = active;
	}
	
	
	public boolean isActive(){
		return active;
	}

	public Timestamp getCreateTime(){
		return this.createTime;
	}

	
	public void setCreateTime(Timestamp createTime){
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "User: " + this.id + ", " + this.name + ", " + 
	            this.email + ", " + this.password + ", " + this.createTime +
	            ", " + this.score + ", " + this.active;
	}



}