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
@Path("/question")
public class QuestionServiceHib {
	private static QuestionDao questionDao;

	public QuestionServiceHib() {
		questionDao = new QuestionDao();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void persist(Question entity) throws Exception {
		questionDao.openCurrentSessionwithTransaction();
		try {
			questionDao.persist(entity);
		} catch (Exception e) {
			questionDao.closeCurrentSession();
			throw e;
		} 
		questionDao.closeCurrentSessionwithTransaction();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void update(Question entity) throws Exception {
		questionDao.openCurrentSessionwithTransaction();
		try {
			questionDao.update(entity);
		} catch (Exception e) {
			questionDao.closeCurrentSession();
			throw e;
		} 
		questionDao.closeCurrentSessionwithTransaction();
	}
	
	@GET
	@Path("/{param}")
	@Produces({MediaType.APPLICATION_JSON})
	public Question findById(@PathParam("param") String id) {
		questionDao.openCurrentSession();
		Question question = questionDao.findById(id);
		questionDao.closeCurrentSession();
		return question;
	}

	@DELETE
	@Path("/{param}")
	@Produces({MediaType.APPLICATION_JSON})
	public void delete(@PathParam("param") String id) throws Exception {
		questionDao.openCurrentSessionwithTransaction();
		Question question = questionDao.findById(id);
		try {
			questionDao.delete(question);
		} catch (Exception e) {
			questionDao.closeCurrentSession();
			throw e;
		} 		
		questionDao.closeCurrentSessionwithTransaction();
	}

	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<Question> findAll() {
		questionDao.openCurrentSession();
		List<Question> question = questionDao.findAll();
		questionDao.closeCurrentSession();
		return question;
	}
	
	@DELETE
	@Produces({MediaType.APPLICATION_JSON})
	public void deleteAll() throws Exception {
		questionDao.openCurrentSessionwithTransaction();
		try {
			questionDao.deleteAll();
		} catch (Exception e) {
			questionDao.closeCurrentSession();
			throw e;
		}
		questionDao.closeCurrentSessionwithTransaction();
	}

	public QuestionDao questionDao() {
		return questionDao;
	}
}
