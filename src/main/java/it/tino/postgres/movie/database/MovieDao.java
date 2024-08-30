package it.tino.postgres.movie.database;

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

import it.tino.postgres.MovieAppException;
import it.tino.postgres.database.Criteria;

public class MovieDao {
	
	protected static final Logger logger = LogManager.getLogger();
	private static final String TABLE_NAME = "movies";

	private static Function<ResultSet, MovieDb> getOnMapEntity() {
		return (resultSet) -> {
            try {
            	MovieDb movie = new MovieDb();
         	    movie.setId(resultSet.getInt("id"));
         	    movie.setTitle(resultSet.getString("title"));
         	    movie.setReleaseDate(resultSet.getDate("release_date"));
         	    movie.setBudget(resultSet.getInt("budget"));
         	    movie.setBoxOffice(resultSet.getInt("box_office"));
         	    movie.setRuntime(resultSet.getInt("runtime"));
         	    movie.setOverview(resultSet.getString("overview"));
                
         	    return movie;
            } catch (SQLException e) {
            	logger.error(e);
                throw new MovieAppException(e);
            }
        };
	}

	public static MovieDb insert(MovieDb entity, Connection connection) {
		String query = "insert into " + TABLE_NAME + " (title, release_date, budget,"
				+ " box_office, runtime, overview) values (?, ?, ?, ?, ?, ?)";
		try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
        	int index = 0;
        	statement.setString(++index, entity.getTitle());
        	statement.setDate(++index, entity.getReleaseDate());
        	statement.setInt(++index, entity.getBudget());
        	statement.setInt(++index, entity.getBoxOffice());
        	statement.setInt(++index, entity.getRuntime());
        	statement.setString(++index, entity.getOverview());

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
	        throw new MovieAppException(e);
	    }
	}

	public static MovieDb update(MovieDb entity, Connection connection) {
		String query = "update " + TABLE_NAME + " set title = ?, release_date = ?,"
				+ " budget = ?, box_office = ?, runtime = ?, overview = ?"
				+ " where id = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
        	int index = 0;
        	statement.setString(++index, entity.getTitle());
        	statement.setDate(++index, entity.getReleaseDate());
        	statement.setInt(++index, entity.getBudget());
        	statement.setInt(++index, entity.getBoxOffice());
        	statement.setInt(++index, entity.getRuntime());
        	statement.setString(++index, entity.getOverview());
        	statement.setInt(++index, entity.getId());

	        statement.executeUpdate();
	        return selectById(entity.getId(), connection);
	    } catch (SQLException e) {
	        logger.error(e.getMessage(), e);
	        throw new MovieAppException(e);
	    }
	}

	public static MovieDb selectById(int id, Connection connection) {
		Criteria criteria = new Criteria("id", "=", id);
		List<MovieDb> entities = selectByCriteria(criteria, connection);
		
		if (entities.isEmpty()) {
			return null;
		}
		return entities.get(0);
	}

	public static List<MovieDb> selectByCriteria(Collection<Criteria> criterias, Connection connection) {
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
            List<MovieDb> entities = new ArrayList<>();
            while (resultSet.next()) {
                MovieDb entity = getOnMapEntity().apply(resultSet);
                entities.add(entity);
            }
            
            return entities;
        } catch (SQLException e) {
        	logger.error(e.getMessage(), e);
        	throw new MovieAppException(e);
        }
	}
	
	public static List<MovieDb> selectByCriteria(Criteria criteria, Connection connection) {
		return selectByCriteria(Collections.singleton(criteria), connection);
	}

	public static boolean delete(int id, Connection connection) {
		String query = "delete from " + TABLE_NAME + " where id = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, id);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
        	logger.error(e.getMessage(), e);
        	throw new MovieAppException(e);
        }
	}
}
