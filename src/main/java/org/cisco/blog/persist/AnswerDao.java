package org.cisco.blog.persist;

import java.util.List;

public class AnswerDao implements DaoImpl< Answer, String> {


	public AnswerDao() {
	}

	public void openCurrentSessionwithTransaction() {
		HibernateUtil.openCurrentSessionwithTransaction();
	}
	
	public void closeCurrentSessionwithTransaction() {
		HibernateUtil.closeCurrentSessionwithTransaction();
	}
	
	public void openCurrentSession() {
		HibernateUtil.currentSession();
		
	}
	
	public void closeCurrentSessionOnException() {
		HibernateUtil.closeSessionOnException();
		
	}
	
	public void closeCurrentSession() {
		HibernateUtil.closeSession();
		
	}
	
	public void persist(Answer entity) {
		HibernateUtil.currentSession().save(entity);
	}

	public void update(Answer entity) {
		HibernateUtil.currentSession().update(entity);
	}

	public Answer findById(String id) {
		Answer answer = (Answer) HibernateUtil.currentSession().get(Answer.class, Integer.valueOf(id));
		return answer; 
	}
	
	public void delete(Answer entity) {
		HibernateUtil.currentSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<Answer> findAll() {
		List<Answer> answer = (List<Answer>) HibernateUtil.currentSession().createQuery("from Answer").list();
		return answer;
	}

	public void deleteAll() {
		List<Answer> entityList = findAll();
		for (Answer entity : entityList) {
			delete(entity);
		}
	}
}
