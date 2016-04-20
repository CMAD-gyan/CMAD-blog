package org.cisco.blog.wsl;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.cisco.blog.persist.Question;
import org.cisco.blog.persist.QuestionService;

public class QuestionWsl {
	private int   	    questionId;
	private String 	    title;
	private String      text;
	private Timestamp 	updateTime;
	private int 		viewsCount;
	private int 		upvoteCount;
	private String      username;
	private int         userId;
	
	
	QuestionWsl(String title, String text) {
		this.title = title;
		this.text = text;
	}

	QuestionWsl(int questionId, String title, 
			     String text, Timestamp updateTime,
			     int viewsCount, int upvoteCount, 
			     String username, int userId ) {
		this.questionId    = questionId; 
		this.title         = title; 
		this.text          = text; 
		this.updateTime    = updateTime; 
		this.viewsCount    = viewsCount;
		this.upvoteCount   = upvoteCount;
		this.username      = username; 
		this.userId        = userId;
	}

	
	QuestionWsl() {
		
	}
	
	
	public int getQuestionId() {
		return this.questionId;
	}
	public void setQuestionId(int id){
		this.questionId = id;
	}
	
	
	public String getTitle () {
		return this.title;
	}
	
	public void setTitle (String title) {
		this.title = title;
	}
	
	public String getText () {
		return this.text;
	}
	
	public void setText (String text) {
		this.text = text;
	}
	
	public String getUsername () {
		return this.username;
	}
	
	public void getUsername (String username) {
		this.username = username;
	}
	
	public int getViewsCount () {
		return this.viewsCount;
	}
	
	public void setViewsCount (int viewsCount) {
		this.viewsCount = viewsCount;
	}
	
	public int getUpvoteCount () {
		return this.upvoteCount;
	}
	
	public void setUpvoteCount (int upvoteCount) {
		this.upvoteCount = upvoteCount;
	}
	
	public int getUserId () {
		return this.userId;
	}
	
	public void setUserId (int userId) {
		this.userId = userId;
	}
	
	
	
	public void setUpdateTime (Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	
	public Timestamp getUpdateTime () {
		return this.updateTime;
	}
	
	public List<QuestionWsl> userReadAll() throws Exception {
		QuestionService userService  = new QuestionService() ;
		List<QuestionWsl> list = new ArrayList<QuestionWsl>();
		List<Question> user = userService.findAll();
		for (Question b : user) {
			QuestionWsl up = new QuestionWsl(b.getQuestionId(), 
					                         b.getTitle(), 
					                         b.getText(),
					                         b.getUpdateTime(),
					                         b.getViewsCount(),
					                         b.getUpvoteCount(),
					                         b.getUser().getUserName(), 
					                         b.getUser().getId() );
			list.add(up);
		}
		return list;
	}
	

}
