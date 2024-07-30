package it.tino.postgres.user.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.tino.postgres.DaoException;
import it.tino.postgres.database.Criteria;

public class UserDao {

	protected static final Logger logger = LogManager.getLogger();
	private static final String TABLE_NAME = "users";

	private static Function<ResultSet, UserJdbc> getOnMapEntity() {
		return (resultSet) -> {
            try {
            	UserJdbc user = new UserJdbc();
            	user.setId(resultSet.getInt("id"));
            	user.setUsername(resultSet.getString("username"));
            	user.setPassword(resultSet.getString("password"));
                
            	return user;
            } catch (SQLException e) {
            	logger.error(e);
                throw new DaoException(e);
            }
        };
	}

	public static UserJdbc insert(UserJdbc entity, Connection connection) {
		String query = "insert into users (username, password) values (?, ?)";
		try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			int index = 0;
			statement.setString(++index, entity.getUsername());
			statement.setString(++index, entity.getPassword());

			int affectedRows = statement.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("Inserting entity failed, no rows affected.");
			}

			try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					Object id = generatedKeys.getObject("id");
					logger.debug("generated id: " + id);
					return selectById((Integer) id, connection);
				} else {
					throw new SQLException("Inserting entity failed, no ID obtained.");
				}
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

	public static UserJdbc update(UserJdbc entity, Connection connection) {
		String query = "update users set username = ?, password = ? where id = ?";
		
		try (PreparedStatement statement = connection.prepareStatement(query)) {
        	int index = 0;
        	statement.setString(++index, entity.getUsername());
        	statement.setString(++index, entity.getPassword());
        	statement.setInt(++index, entity.getId());

	        statement.executeUpdate();
	        return selectById(entity.getId(), connection);
	    } catch (SQLException e) {
	        logger.error(e.getMessage(), e);
	        throw new DaoException(e);
	    }
	}

	public static UserJdbc selectById(int id, Connection connection) {
		Criteria criteria = new Criteria("id", "=", id);
		List<UserJdbc> entities = selectByCriteria(criteria, connection);
		
		if (entities.isEmpty()) {
			return null;
		}
		return entities.get(0);
	}

	public static List<UserJdbc> selectByCriteria(Collection<Criteria> criterias, Connection connection) {
		StringBuilder query = new StringBuilder("select * from ")
				.append(TABLE_NAME)
				.append(" where 1 = 1");
		
		List<Object> queryParameters = new ArrayList<>();
		for (Criteria criteria : criterias) {
			query.append(" and ");
			query.append(criteria.getField());
			
			if ("in".equalsIgnoreCase(criteria.getOperator()) && criteria.getValue() instanceof Collection<?>) {
	            Collection<?> values = (Collection<?>) criteria.getValue();
	            if (values.isEmpty()) {
	                query.append(" in (null)");
	            } else {
	                query.append(" in (");
	                String placeholders = String.join(",", Collections.nCopies(values.size(), "?"));
	                query.append(placeholders);
	                query.append(")");
	                queryParameters.addAll(values);
	            }
	        } else {
	            query.append(criteria.getOperator());
	            query.append("?");
	            queryParameters.add(criteria.getValue());
	        }
		}
		
		try (PreparedStatement statement = connection.prepareStatement(query.toString())) {
            for (int i = 0; i < queryParameters.size(); i++) {
            	statement.setObject(i + 1, queryParameters.get(i));
            }
            
            ResultSet resultSet = statement.executeQuery();
            List<UserJdbc> entities = new ArrayList<>();
            while (resultSet.next()) {
            	UserJdbc entity = getOnMapEntity().apply(resultSet);
                entities.add(entity);
            }
            
            return entities;
        } catch (SQLException e) {
        	logger.error(e.getMessage(), e);
        	throw new DaoException(e);
        }
	}
	
	public static List<UserJdbc> selectByCriteria(Criteria criteria, Connection connection) {
		return selectByCriteria(Collections.singleton(criteria), connection);
	}

	public static boolean delete(int id, Connection connection) {
		String query = "delete from " + TABLE_NAME + " where id = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, id);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
        	logger.error(e.getMessage(), e);
        	throw new DaoException(e);
        }
	}
}
