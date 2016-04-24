package org.cisco.blog.persist;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class TokenDao implements DaoImpl< Token, String> {
	public TokenDao() {
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
	
	public void persist(Token entity) {
		HibernateUtil.currentSession().save(entity);
	}

	public void update(Token entity) {
		HibernateUtil.currentSession().update(entity);
	}

	public Token findByUUID(String UUID) {
		Token token = (Token) HibernateUtil.currentSession().get(Token.class, UUID);
		return token; 
	}
	
	public Token findByUserName(String name) {
		Session session = HibernateUtil.currentSession();
		Criteria criteria = session.createCriteria(Token.class);
		Token token = (Token) criteria.add(Restrictions.eq("userName", name)).uniqueResult();
		return token;
	}
	
	public void delete(Token entity) {
		HibernateUtil.currentSession().delete(entity);
	}

	public List<Token> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteAll() {
		List<Token> entityList = findAll();
		for (Token entity : entityList) {
			delete(entity);
		}
	}

	public Token findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}
}
