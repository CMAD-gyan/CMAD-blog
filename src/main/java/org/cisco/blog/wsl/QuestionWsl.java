package org.cisco.blog.wsl;
import java.sql.Timestamp;
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

import org.cisco.blog.persist.Question;
import org.cisco.blog.persist.QuestionService;
import org.cisco.blog.persist.User;
import org.cisco.blog.persist.UserService;

@Path("/questions")
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

	
	public  QuestionWsl() {
		
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
	
	public List<QuestionWsl> questionReadAll() throws Exception {
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
					                         b.getUser() == null ? "anonymous": b.getUser().getUserName(), 
					                        b.getUser() == null ? 0:b.getUser().getId() );
			list.add(up);
		}
		return list;
	}
	
	public void questionWrite() throws Exception {
		UserService userService = new UserService();
		User user = userService.findById(String.valueOf(userId));
		Question quest = new Question (title, text, user);
		QuestionService questService  = new QuestionService() ;
		questService.persist(quest);
	}
	
	public void questionDelete(String id) throws Exception {
		QuestionService questService  = new QuestionService() ;
		questService.delete(id);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<QuestionWsl> questionGet() throws Exception {
		QuestionWsl  a = new QuestionWsl();
		return a.questionReadAll();
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void QuestionCreate(QuestionWsl ques) throws Exception {
		ques.questionWrite();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void QuestionUpdate(QuestionWsl ques) throws Exception {
		ques.questionWrite();
	} 

	@DELETE
	@Path("/{param}")
	public void DeleteQuestion(@PathParam("param") String id) throws Exception {
		questionDelete(id);
	}
	
}
