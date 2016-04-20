package org.cisco.blog.persist;

import java.util.List;

import org.hibernate.Hibernate;

public class QuestionService {
	private static QuestionDao questionDao;

	public QuestionService() {
		questionDao = new QuestionDao();
	}

	public void persist(Question entity) throws Exception {
		questionDao.openCurrentSessionwithTransaction();
		try {
			questionDao.persist(entity);
		} catch (Exception e) {
			questionDao.closeCurrentSessionOnException();
			throw e;
		} 
		questionDao.closeCurrentSessionwithTransaction();
	}

	public void update(Question entity) throws Exception {
		questionDao.openCurrentSessionwithTransaction();
		try {
			questionDao.update(entity);
		} catch (Exception e) {
			questionDao.closeCurrentSessionOnException();
			throw e;
		} 
		questionDao.closeCurrentSessionwithTransaction();
	}
	
	public Question findById(String id) {
		questionDao.openCurrentSession();
		Question question = questionDao.findById(id);
		questionDao.closeCurrentSession();
		return question;
	}

	public void delete(String id) throws Exception {
		questionDao.openCurrentSessionwithTransaction();
		Question question = questionDao.findById(id);
		try {
			questionDao.delete(question);
		} catch (Exception e) {
			questionDao.closeCurrentSessionOnException();
			throw e;
		} 		
		questionDao.closeCurrentSessionwithTransaction();
	}

	public List<Question> findAll() {
		questionDao.openCurrentSession();
		List<Question> question = questionDao.findAll();
		questionDao.closeCurrentSession();
		return question;
	}
	
	public void deleteAll() throws Exception {
		questionDao.openCurrentSessionwithTransaction();
		try {
			questionDao.deleteAll();
		} catch (Exception e) {
			questionDao.closeCurrentSessionOnException();
			throw e;
		}
		questionDao.closeCurrentSessionwithTransaction();
	}

	public QuestionDao questionDao() {
		return questionDao;
	}
}
