package it.tino.postgres.user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.BiConsumer;
import java.util.function.Function;

import it.tino.postgres.database.Dao;
import it.tino.postgres.database.Database;
import it.tino.postgres.database.SimpleDao;

public class UserDao extends SimpleDao<User, Integer> implements Dao<User, Integer> {

	public UserDao(Database database) {
		super(database);
	}

	@Override
	protected String getTableName() {
		return "users";
	}

	@Override
	protected Function<ResultSet, User> getOnMapEntity() {
		return (resultSet) -> {
            try {
            	User user = new User();
            	user.setId(resultSet.getInt("id"));
            	user.setUsername(resultSet.getString("username"));
            	user.setPassword(resultSet.getString("password"));
                
            	return user;
            } catch (SQLException e) {
            	logger.error(e);
                throw new RuntimeException(e);
            }
        };
	}
	
	@Override
	public User insert(User user) {
		String query = "insert into users (username, password) values (?, ?)";
		BiConsumer<User, PreparedStatement> onSetParameters = (entity, stmt) -> {
            int index = 0;
            try {
                stmt.setString(++index, entity.getUsername());
                stmt.setString(++index, entity.getPassword());
            } catch (Exception e) {
            	logger.error(e);
                throw new RuntimeException(e);
            }
        };
        
        return insert(user, query, onSetParameters);
	}

	@Override
	public User update(User user) {
		String query = "update users set username = ?, password = ? where id = ?";
		BiConsumer<User, PreparedStatement> onSetParameters = (entity, stmt) -> {
            int index = 0;
            try {
                stmt.setString(++index, entity.getUsername());
                stmt.setString(++index, entity.getPassword());
                stmt.setInt(++index, entity.getId());
            } catch (Exception e) {
            	logger.error(e);
                throw new RuntimeException(e);
            }
        };
        
        return insert(user, query, onSetParameters);
	}
}
