package org.cisco.blog.wsl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Set;
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

import org.cisco.blog.persist.Answer;
import org.cisco.blog.persist.AnswerService;
import org.cisco.blog.persist.Question;
import org.cisco.blog.persist.QuestionService;
import org.cisco.blog.persist.User;
import org.cisco.blog.persist.UserService;

@Path("/answers")
public class AnswerWsl {
	private int   	    answerId;
	private String      text;
	private Timestamp 	updateTime;
	private int 		upvoteCount;
	private String      questionTitle;
	private String      username;
	private int         userId;
	private int         questionId;

	AnswerWsl(String text, Timestamp updateTime,
			  int upvoteCount, String questionTitle, String username, 
			  int userId, int questionId) {
		this.text = text;
		this.updateTime = updateTime;
		this.upvoteCount = upvoteCount;
		this.questionTitle = questionTitle;
		this.username = username;
		this.userId = userId;
		this.questionId = questionId;
	}
	
	public AnswerWsl() {
		
	}
	
	public void setAnswerId (int answerId) {
		this.answerId = answerId;
	}

	public int getAnswerId () {
		return this.answerId;
	}

	
	public String getText () {
		return this.text; 
	}
	
	public void setText (String text) {
		this.text = text;
	}

	public Timestamp getUpdateTime () {
		return this.updateTime;
	}
	
	public void setUpdateTime (Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	

	public int getUpvoteCount () {
		return this.upvoteCount;
	}
	
	public void setUpvoteCount (int upvoteCount) {
		this.upvoteCount = upvoteCount;
	}
	
	public String getQuestionTitle (String questionTitle) {
		return this.questionTitle;
	}
	
	
	public void setQuestionTitle (String questionTitle) {
		this.questionTitle = questionTitle;
	}


	public String getUsername () {
		return this.username;
	}
	
	public void setUsername (String username) {
		this.username = username;
	}

	public int getUserId () {
		return this.userId;
	}
	
	public void setUserId (int userId) {
		this.userId = userId;
	}
	
	public int getQuestionId () {
		return this.questionId;
	}
	
	public void setQuestionId (int questionId) {
		this.questionId = questionId;
	}
	
	
	

	public List<AnswerWsl> answersReadAll() throws Exception {
		AnswerService answerService  = new AnswerService() ;
		List<AnswerWsl> list = new ArrayList<AnswerWsl>();
		List<Answer> ans = answerService.findAll();
		for (Answer a : ans) {
			AnswerWsl up = new AnswerWsl( a.getText() , a.getUpdateTime(),
					                      a.getUpvoteCount(), a.getQuestion().getTitle(), 
					                      a.getUser() == null ? "anonymous": a.getUser().getUserName(), 
							                         a.getUser() == null ? 0:a.getUser().getId(),
							                        		 a.getQuestion().getQuestionId());
			list.add(up);
		}
		return list;
	}
	
	public void questionWrite() throws Exception {
		UserService userService = new UserService();
		User user = userService.findById(String.valueOf(userId));
		QuestionService questService  = new QuestionService() ;
		Question ques = questService.findById(String.valueOf(questionId));
		AnswerService anwerService  = new AnswerService() ;
		Answer ans = new Answer (text, user, ques);
		anwerService.persist(ans);
	}
	
	public List<AnswerWsl> answerFromQId(String questionId){
		List<AnswerWsl> list = new ArrayList<AnswerWsl>(); 
		QuestionService questService  = new QuestionService();
		Question ques = questService.findById(String.valueOf(questionId));
		Set<Answer> ans = ques.getAnswers();
		
		for (Answer a : ans) {
			AnswerWsl up = new AnswerWsl( a.getText() , a.getUpdateTime(),
					                      a.getUpvoteCount(), a.getQuestion().getTitle(), 
					                      a.getUser() == null ? "anonymous": a.getUser().getUserName(), 
							              a.getUser() == null ? 0:a.getUser().getId(),
							              a.getQuestion().getQuestionId());
			list.add(up);
		}
		return list;
	}
	
	public void answerDelete(String id) throws Exception {
		AnswerService ansService  = new AnswerService() ;
		ansService.delete(id);
	}
	
	@GET
	@Path("/{param}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<AnswerWsl> GetAnswer(@PathParam("param") String param) throws Exception {
		return answerFromQId(param);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<AnswerWsl> GetAnswer() throws Exception {
		AnswerWsl  a = new AnswerWsl();
		return a.answersReadAll();
	}
		
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void CreateAnswer(AnswerWsl ans) throws Exception {
		questionWrite();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void UpdateAnswer(AnswerWsl ques) throws Exception {
	} 

	@DELETE
	@Path("/{param}")
	public void DeleteAnswer(@PathParam("param") String id) throws Exception {
		AnswerWsl q = new AnswerWsl();
		q.answerDelete(id);
	}		
}