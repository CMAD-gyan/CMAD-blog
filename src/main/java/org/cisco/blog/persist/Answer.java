package org.cisco.blog.persist;
import java.sql.Timestamp;

public class Answer {
	private int  answerId;
	private String text;
	private Timestamp createTime;
	private Timestamp updateTime;
	private int upvoteCount;
	private User user;
	private Question question;
	
	public Answer() {
	}
	
	public Answer(String text, User user, Question question) {
		this.text  = text;
		this.user  = user;
		this.question  = question;
		this.createTime  = new Timestamp(System.currentTimeMillis());
		this.updateTime  = new Timestamp(System.currentTimeMillis());
		this.upvoteCount = 0;
	}

	public int getAnswerId (){
		return answerId;
	}

	public void setAnswerId (int id) {
		this.answerId = id;
	}

	public String getText () {
		return text;
	}

	public void setText (String text){
		this.text = text;
	}

	public Timestamp getCreateTime (){
		return this.createTime;
	}
	
	public void setCreateTime (Timestamp createTime){
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime (){
		return this.updateTime;
	}
	
	public void setUpdateTime (Timestamp updateTime){
		this.updateTime = updateTime;
	}

	public int getUpvoteCount (){
		return upvoteCount;
	}

	public void setUpvoteCount (int count) {
		this.upvoteCount = count;
	}

	public User getUser () {
		return user;
	}
	
	public void setUser (User user) {
		this.user = user;
	}
	
	public Question getQuestion () {
		return question;
	}
	
	public void setQuestion (Question question) {
		this.question = question;
	}
	
	@Override
	public String toString() {
		return "Answer: " + this.answerId + ", " + this.text+ ", " + 
	            this.createTime + ", " + this.updateTime +
	            ", " + this.upvoteCount + ", " + this.question.toString() + ", " + this.user.toString();
	}
}