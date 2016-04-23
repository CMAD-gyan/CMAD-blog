package org.cisco.blog.persist;

public class SessionService {
	private static SessionDao sessionDao;

	public SessionService() {
		sessionDao = new SessionDao();
	}

	public void persist(Session entity) throws Exception {
		sessionDao.openCurrentSessionwithTransaction();
		try {
			sessionDao.persist(entity);
		} catch (Exception e) {
			sessionDao.closeCurrentSession();
			throw e;
		} 
		sessionDao.closeCurrentSessionwithTransaction();
	}

	public void update(Session entity) throws Exception {
		sessionDao.openCurrentSessionwithTransaction();
		try {
			sessionDao.update(entity);
		} catch (Exception e) {
			sessionDao.closeCurrentSession();
			throw e;
		} 
		sessionDao.closeCurrentSessionwithTransaction();
	}
	
	public Session findByUUID(String UUID) {
		sessionDao.openCurrentSession();
		Session session = sessionDao.findByUUID(UUID);
		//User user=session.getUser();
		//sessionDao.closeCurrentSession();
		//add expiry time
		return session;
	}	
	
	public void delete(String id) throws Exception {
		sessionDao.openCurrentSessionwithTransaction();
		Session session = sessionDao.findById(id);
		try {
			sessionDao.delete(session);
		} catch (Exception e) {
			sessionDao.closeCurrentSession();
			throw e;
		} 
		//sessionDao.delete(session);
		sessionDao.closeCurrentSessionwithTransaction();
	}
	
	public void deleteAll() throws Exception {
		sessionDao.openCurrentSessionwithTransaction();
		try {
			sessionDao.deleteAll();
		} catch (Exception e) {
			sessionDao.closeCurrentSession();
			throw e;
		} 
		//sessionDao.deleteAll();
		sessionDao.closeCurrentSessionwithTransaction();
	}

	public SessionDao sessionDao() {
		return sessionDao;
	}
}