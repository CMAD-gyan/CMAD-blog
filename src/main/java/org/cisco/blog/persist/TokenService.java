package org.cisco.blog.persist;

public class TokenService {
	private static TokenDao tokenDao;

	public TokenService() {
		tokenDao = new TokenDao();
	}

	public void persist(Token entity) throws Exception {
		tokenDao.openCurrentSessionwithTransaction();
		try {
			tokenDao.persist(entity);
		} catch (Exception e) {
			tokenDao.closeCurrentSession();
			throw e;
		} 
		tokenDao.closeCurrentSessionwithTransaction();
	}

	public void update(Token entity) throws Exception {
		tokenDao.openCurrentSessionwithTransaction();
		try {
			tokenDao.update(entity);
		} catch (Exception e) {
			tokenDao.closeCurrentSession();
			throw e;
		} 
		tokenDao.closeCurrentSessionwithTransaction();
	}

	public User getUser(String UUID) {
		Token token = findByUUID(UUID);
		
		if (token != null){
			return token.getUser();
		}
		return null;
	}
	
	public Token findByUUID(String UUID) {
		tokenDao.openCurrentSession();
		Token token = tokenDao.findByUUID(UUID);
		return token;
	}
	
	public Token findByUserName(String username) {
		tokenDao.openCurrentSession();
		Token token = tokenDao.findByUserName(username);
		return token;
	}
	
	public void delete(String id) throws Exception {
		tokenDao.openCurrentSessionwithTransaction();
		Token token = tokenDao.findById(id);
		try {
			tokenDao.delete(token);
		} catch (Exception e) {
			tokenDao.closeCurrentSession();
			throw e;
		} 
		tokenDao.closeCurrentSessionwithTransaction();
	}
	
	public void deleteAll() throws Exception {
		tokenDao.openCurrentSessionwithTransaction();
		try {
			tokenDao.deleteAll();
		} catch (Exception e) {
			tokenDao.closeCurrentSession();
			throw e;
		} 
		tokenDao.closeCurrentSessionwithTransaction();
	}

	public TokenDao tokenDao() {
		return tokenDao;
	}
}