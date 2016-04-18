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

@Path("/answer")
public class AnswerServiceHib {
	private static AnswerDao answerDao;

	public AnswerServiceHib() {
		answerDao = new AnswerDao();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void persist(Answer entity) throws Exception {
		answerDao.openCurrentSessionwithTransaction();
		try {
			answerDao.persist(entity);
		} catch (Exception e) {
			answerDao.closeCurrentSession();
			throw e;
		}
		answerDao.closeCurrentSessionwithTransaction();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void update(Answer entity) throws Exception {
		answerDao.openCurrentSessionwithTransaction();
		try {
			answerDao.update(entity);
		} catch (Exception e) {
			answerDao.closeCurrentSession();
			throw e;
		}
		answerDao.closeCurrentSessionwithTransaction();
	}
	
	@GET
	@Path("/{param}")
	@Produces({MediaType.APPLICATION_JSON})
	public Answer findById(@PathParam("param") String id) {
		answerDao.openCurrentSession();
		Answer answer = answerDao.findById(id);
		answerDao.closeCurrentSession();
		return answer;
	}

	@DELETE
	@Path("/{param}")
	@Produces({MediaType.APPLICATION_JSON})
	public void delete(@PathParam("param") String id) throws Exception {
		answerDao.openCurrentSessionwithTransaction();
		Answer answer = answerDao.findById(id);
		try {
			answerDao.delete(answer);
		} catch (Exception e) {
			answerDao.closeCurrentSession();
			throw e;
		}
		answerDao.closeCurrentSessionwithTransaction();
	}

	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<Answer> findAll() {
		answerDao.openCurrentSession();
		List<Answer> answer = answerDao.findAll();
		answerDao.closeCurrentSession();
		return answer;
	}
	
	@DELETE
	@Produces({MediaType.APPLICATION_JSON})
	public void deleteAll() throws Exception {
		answerDao.openCurrentSessionwithTransaction();
		try {
			answerDao.deleteAll();
		} catch (Exception e) {
			answerDao.closeCurrentSession();
			throw e;
		}
		answerDao.closeCurrentSessionwithTransaction();
	}

	public AnswerDao answerDao() {
		return answerDao;
	}
}
