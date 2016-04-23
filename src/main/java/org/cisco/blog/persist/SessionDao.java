package org.cisco.blog.persist;

import java.util.List;

public class SessionDao implements DaoImpl< Session, String> {
	public SessionDao() {
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
	
	public void persist(Session entity) {
		HibernateUtil.currentSession().save(entity);
	}

	public void update(Session entity) {
		HibernateUtil.currentSession().update(entity);
	}

	public Session findByUUID(String UUID) {
		Session session = (Session) HibernateUtil.currentSession().get(Session.class, UUID);
		return session; 
	}
	
	public void delete(Session entity) {
		HibernateUtil.currentSession().delete(entity);
	}

	public List<Session> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteAll() {
		List<Session> entityList = findAll();
		for (Session entity : entityList) {
			delete(entity);
		}
	}

	public Session findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}
}
