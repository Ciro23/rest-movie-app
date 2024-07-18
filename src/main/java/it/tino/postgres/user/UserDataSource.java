package it.tino.postgres.user;

import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserDataSource implements UserRepository {

	protected static final Logger logger = LogManager.getLogger();
	
	private final UserDao userDao;
    
    public UserDataSource(UserDao userDao) {
    	this.userDao = userDao;
    }
    
    @Override
	public User save(User entity) {
		if (entity.getId() == 0) {
			return userDao.insert(entity);
		}
		return userDao.update(entity);
	}
    
    @Override
    public List<User> findAll() {
    	return userDao.selectByCriteria(Collections.emptyList());
    }
    
    @Override
   	public User findById(Integer id) {
   		return userDao.selectById(id);
   	}

	@Override
	public boolean delete(Integer id) {
		return userDao.delete(id);
	}
}
