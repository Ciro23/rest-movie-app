package it.tino.postgres.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Function;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.tino.postgres.database.DatabaseTable;

public class UserDataSource implements UserRepository {

	protected static final Logger logger = LogManager.getLogger();
	
	private final UserDao userDao;
    private final DatabaseTable<User> database;
    private final Function<ResultSet, User> onMapEntity;
    
    public UserDataSource(UserDao userDao, DatabaseTable<User> database) {
    	this.userDao = userDao;
        this.database = database;
        onMapEntity = (resultSet) -> {
            try {
                return new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password")
                );
            } catch (SQLException e) {
            	logger.error(e);
                throw new RuntimeException(e);
            }
        };
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
       return database.select("select * from users order by id", null, onMapEntity);
    }

	@Override
	public boolean delete(Integer id) {
		return userDao.delete(id);
	}
}
