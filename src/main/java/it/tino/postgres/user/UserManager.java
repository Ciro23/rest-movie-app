package it.tino.postgres.user;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.tino.postgres.MovieAppException;
import it.tino.postgres.database.ConnectionManager;
import it.tino.postgres.database.Criteria;
import it.tino.postgres.user.database.UserDao;
import it.tino.postgres.user.database.UserDb;

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
    		UserDb userDb = domainToDb(user);
			UserDb insertedUserDb = UserDao.insert(userDb, connection);
			return dbToDomain(insertedUserDb, connection);
		} catch (MovieAppException e) {
			logger.error(e.getMessage(), e);
			throw new MovieAppException(e);
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
    		UserDb userDb = domainToDb(user);
			UserDb insertedUserDb = UserDao.update(userDb, connection);
			return dbToDomain(insertedUserDb, connection);
		} catch (MovieAppException e) {
			logger.error(e.getMessage(), e);
			throw new MovieAppException(e);
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
		} catch (MovieAppException e) {
			logger.error(e.getMessage(), e);
			throw new MovieAppException(e);
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
			if (userJdbc == null) {
				return null;
			}

			return dbToDomain(userJdbc, connection);
		} catch (MovieAppException e) {
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
		} catch (MovieAppException e) {
			logger.error(e.getMessage(), e);
			throw new MovieAppException(e);
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
		} catch (MovieAppException e) {
			logger.error(e.getMessage(), e);
			return false;
		} finally {
			if (connection != null) {
				connectionManager.close(connection);
			}
		}
	}
	
	private UserDb domainToDb(User user) {
		UserDb userDb = new UserDb();
		userDb.setId(user.getId());
		userDb.setUsername(user.getUsername());
		userDb.setPassword(user.getPassword());
		
		return userDb;
	}
	
	private List<User> dbToDomain(Collection<UserDb> usersJdbc, Connection connection) {
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
		for (UserDb userDb : usersJdbc) {
			User user = new User();
			user.setId(userDb.getId());
			user.setUsername(userDb.getUsername());
			user.setPassword(userDb.getPassword());
			
			users.add(user);
		}
		
		return users;
	}
	
	private User dbToDomain(UserDb userDb, Connection connection) {
		var users = dbToDomain(Collections.singleton(userDb), connection);
		if (users.isEmpty()) {
			return null;
		}
		return users.getFirst();
	}
}
