package org.cisco.blog.persist;
import java.sql.Timestamp;
import java.util.Set;

public class Question {
	private int  questionId;
	private String title;
	private String text;
	private Timestamp createTime;
	private Timestamp updateTime;
	private int viewsCount;
	private int upvoteCount;
	private User user;
	private Set<Answer> answers;
	public Question( ) {
	}
	
	public Question(String title, String text, User user) {
		this.title = title;
		this.text  = text;
		this.user  = user;
		this.createTime  = new Timestamp(System.currentTimeMillis());
		this.updateTime  = new Timestamp(System.currentTimeMillis());
		this.viewsCount  = 0;
		this.upvoteCount = 0;
	}

	public int getQuestionId (){
		return questionId;
	}

	public void setQuestionId (int id) {
		this.questionId = id;
	}

	public String getTitle () {
		return title;
	}

	public void setTitle (String title){
		this.title = title;
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
	
	public int getViewsCount (){
		return viewsCount;
	}

	public void setViewsCount (int count) {
		this.viewsCount = count;
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
	
	public Set<Answer> getAnswers () {
		return answers;
	}
	
	public void setAnswers (Set<Answer> answers) {
		this.answers = answers;
	}
	
	@Override
	public String toString() {
		String retval;
		retval = "Question: " + this.questionId + ", " + this.title + ", " + 
	            this.text + ", " + this.createTime + ", " + this.updateTime +
	            ", " + this.upvoteCount + ", " + this.viewsCount + ", " + this.user.toString(); 
	    return retval;
	}
}