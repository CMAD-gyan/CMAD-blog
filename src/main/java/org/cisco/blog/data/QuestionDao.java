package org.cisco.blog.data;

import java.util.List;


public class QuestionDao implements DaoImpl< Question, String> {


	public QuestionDao() {
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
	
	public void closeCurrentSession() {
		HibernateUtil.closeSession();
		
	}
	
	public void persist(Question entity) {
		HibernateUtil.currentSession().save(entity);
	}

	public void update(Question entity) {
		HibernateUtil.currentSession().update(entity);
	}

	public Question findById(String id) {
		Question question = (Question) HibernateUtil.currentSession().get(Question.class, Integer.valueOf(id));
		return question; 
	}
	
	public void delete(Question entity) {
		HibernateUtil.currentSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<Question> findAll() {
		List<Question> question = (List<Question>) HibernateUtil.currentSession().createQuery("from Question").list();
		return question;
	}

	public void deleteAll() {
		List<Question> entityList = findAll();
		for (Question entity : entityList) {
			delete(entity);
		}
	}
}
