package org.cisco.blog.data;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

public class User {
	private int  id;
	private String username;
	private String name;
	private String email;
	private String password;
	private Timestamp create_time;
	private int score;
	private boolean active;

	public User() {
	}

	public User(String username, String name, String email, String password){
		this.username = username;
		this.name = name;
		this.email = email;
		this.password = password;
		this.score = 0;
		this.active = true;
		this.create_time = new Timestamp(System.currentTimeMillis());
	}

	public int getId (){
		return id;
	}

	public void setId (int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername (String username){
		this.username = username;
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

	public Timestamp getCreate_time(){
		return this.create_time;
	}

	
	public void setCreate_time(Timestamp create_time){
		this.create_time = create_time;
	}

	@Override
	public String toString() {
		return "User: " + this.id + ", " + this.name + ", " + 
	            this.email + ", " + this.password + ", " + this.create_time +
	            ", " + this.score + ", " + this.active;
	}



}