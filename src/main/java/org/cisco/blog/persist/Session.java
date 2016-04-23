package org.cisco.blog.persist;
import java.sql.Timestamp;

public class Session {
	private String UUID;
	private Timestamp expiryTime;
	private String userName;
	private int userId;
	private User user;

	public Session() {
	}

	public Session(User user){
		this.userName = user.getName();
		this.userId = user.getId();
		this.expiryTime = new Timestamp(System.currentTimeMillis() + (60*60*1000));
		this.user = user;
	}

	public String getUUID (){
		return UUID;
	}

	public void setUUID (String uuid) {
		this.UUID = uuid;
	}

	public Timestamp getExpiryTime(){
		return this.expiryTime;
	}

	public void setExpiryTime(Timestamp expiryTime){
		this.expiryTime = expiryTime;
	}

	public String getUserName(){
		return this.userName;
	}

	public void setUserName(String username){
		this.userName = username;
	}

	public User getUser(){
		return this.user;
	}

	public void setUser(User user){
		this.user = user;
	}

	public int getId(){
		return this.userId;
	}

	public void setId(int id){
		this.userId = id;
	}
}