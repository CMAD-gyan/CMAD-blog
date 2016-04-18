package org.cisco.blog.data;

import java.util.List;

public class AnswerService {
	private static AnswerDao answerDao;

	public AnswerService() {
		answerDao = new AnswerDao();
	}

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
	
	public Answer findById(String id) {
		answerDao.openCurrentSession();
		Answer answer = answerDao.findById(id);
		answerDao.closeCurrentSession();
		return answer;
	}

	public void delete(String id) throws Exception {
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

	public List<Answer> findAll() {
		answerDao.openCurrentSession();
		List<Answer> answer = answerDao.findAll();
		answerDao.closeCurrentSession();
		return answer;
	}
	
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
