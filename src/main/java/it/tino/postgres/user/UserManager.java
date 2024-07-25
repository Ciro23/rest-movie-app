package it.tino.postgres.user;

import java.sql.Connection;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.tino.postgres.DaoException;
import it.tino.postgres.database.ConnectionManager;
import it.tino.postgres.database.Criteria;

public class UserManager {

	protected static final Logger logger = LogManager.getLogger();

	private final ConnectionManager connectionManager;
	private final UserDao userDao;
	
	public UserManager(ConnectionManager connectionManager, UserDao userDao) {
		this.connectionManager = connectionManager;
		this.userDao = userDao;
	}

	public User insert(User entity) {
		Connection connection = null;
    	try {
    		connection = connectionManager.connect();
			return userDao.insert(entity, connection);
		} catch (DaoException e) {
			logger.error(e.getMessage(), e);
			return null;
		} finally {
			if (connection != null) {
				connectionManager.close(connection);
			}
		}
	}
	
	public User update(User entity) {
		Connection connection = null;
    	try {
    		connection = connectionManager.connect();
			return userDao.update(entity, connection);
		} catch (DaoException e) {
			logger.error(e.getMessage(), e);
			return null;
		} finally {
			if (connection != null) {
				connectionManager.close(connection);
			}
		}
	}
    
    public List<User> selectAll() {
    	Connection connection = null;
    	try {
    		connection = connectionManager.connect();
			return userDao.selectByCriteria(Collections.emptyList(), connection);
		} catch (DaoException e) {
			logger.error(e.getMessage(), e);
			return Collections.emptyList();
		} finally {
			if (connection != null) {
				connectionManager.close(connection);
			}
		}
    }
    
   	public User selectById(Integer id) {
   		Connection connection = null;
    	try {
    		connection = connectionManager.connect();
			return userDao.selectById(id, connection);
		} catch (DaoException e) {
			logger.error(e.getMessage(), e);
			return null;
		} finally {
			if (connection != null) {
				connectionManager.close(connection);
			}
		}
   	}

    public List<User> selectByCriteria(Collection<Criteria> criteria) {
    	Connection connection = null;
    	try {
    		connection = connectionManager.connect();
			return userDao.selectByCriteria(criteria, connection);
		} catch (DaoException e) {
			logger.error(e.getMessage(), e);
			return Collections.emptyList();
		} finally {
			if (connection != null) {
				connectionManager.close(connection);
			}
		}
    }
    
    public List<User> selectByCriteria(Criteria criteria) {
    	return selectByCriteria(Collections.singleton(criteria));
    }

	public boolean delete(Integer id) {
		Connection connection = null;
		try {
			connection = connectionManager.connect();
			return userDao.delete(id, connection);
		} catch (DaoException e) {
			logger.error(e.getMessage(), e);
			return false;
		} finally {
			if (connection != null) {
				connectionManager.close(connection);
			}
		}
	}
}
