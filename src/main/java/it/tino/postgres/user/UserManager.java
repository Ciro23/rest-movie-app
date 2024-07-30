package it.tino.postgres.user;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.tino.postgres.DaoException;
import it.tino.postgres.database.ConnectionManager;
import it.tino.postgres.database.Criteria;
import it.tino.postgres.user.database.UserDao;
import it.tino.postgres.user.database.UserJdbc;

public class UserManager {

	protected static final Logger logger = LogManager.getLogger();

	private final ConnectionManager connectionManager;
	
	public UserManager(ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
	}

	public User insert(User user) {
		Connection connection = null;
    	try {
    		connection = connectionManager.connect();
    		UserJdbc userJdbc = domainToDb(user);
			UserJdbc insertedUserJdbc = UserDao.insert(userJdbc, connection);
			return dbToDomain(insertedUserJdbc, connection);
		} catch (DaoException e) {
			logger.error(e.getMessage(), e);
			return null;
		} finally {
			if (connection != null) {
				connectionManager.close(connection);
			}
		}
	}
	
	public User update(User user) {
		Connection connection = null;
    	try {
    		connection = connectionManager.connect();
    		UserJdbc userJdbc = domainToDb(user);
			UserJdbc insertedUserJdbc = UserDao.update(userJdbc, connection);
			return dbToDomain(insertedUserJdbc, connection);
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
			var usersJdbc = UserDao.selectByCriteria(Collections.emptyList(), connection);
			return dbToDomain(usersJdbc, connection);
		} catch (DaoException e) {
			logger.error(e.getMessage(), e);
			return Collections.emptyList();
		} finally {
			if (connection != null) {
				connectionManager.close(connection);
			}
		}
    }
    
   	public User selectById(int id) {
   		Connection connection = null;
    	try {
    		connection = connectionManager.connect();
			var userJdbc = UserDao.selectById(id, connection);
			return dbToDomain(userJdbc, connection);
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
    		var usersJdbc =  UserDao.selectByCriteria(criteria, connection);
    		return dbToDomain(usersJdbc, connection);
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

	public boolean delete(int id) {
		Connection connection = null;
		try {
			connection = connectionManager.connect();
			return UserDao.delete(id, connection);
		} catch (DaoException e) {
			logger.error(e.getMessage(), e);
			return false;
		} finally {
			if (connection != null) {
				connectionManager.close(connection);
			}
		}
	}
	
	private UserJdbc domainToDb(User user) {
		UserJdbc userJdbc = new UserJdbc();
		userJdbc.setId(user.getId());
		userJdbc.setUsername(user.getUsername());
		userJdbc.setPassword(user.getPassword());
		
		return userJdbc;
	}
	
	private List<User> dbToDomain(Collection<UserJdbc> usersJdbc, Connection connection) {
//		List<Integer> userIds = usersJdbc
//				.stream()
//				.map(u -> u.getId())
//				.toList();
//		
//		List<Review> reviews = ReviewDao.selectByCriteria(
//				new Criteria("user_id", "in", userIds),
//				connection
//		);
		
		List<User> users = new ArrayList<>();
		for (UserJdbc userJdbc : usersJdbc) {
			User user = new User();
			user.setId(userJdbc.getId());
			user.setUsername(userJdbc.getUsername());
			user.setPassword(userJdbc.getPassword());
			
			users.add(user);
		}
		
		return users;
	}
	
	private User dbToDomain(UserJdbc userJdbc, Connection connection) {
		var users = dbToDomain(Collections.singleton(userJdbc), connection);
		if (users.isEmpty()) {
			return null;
		}
		return users.getFirst();
	}
}
