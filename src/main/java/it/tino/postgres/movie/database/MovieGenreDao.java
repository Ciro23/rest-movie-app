package it.tino.postgres.movie.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.tino.postgres.MovieAppException;
import it.tino.postgres.database.Criteria;
import it.tino.postgres.movie.MovieGenre;

public class MovieGenreDao {
	
	protected static final Logger logger = LogManager.getLogger();
	private static final String TABLE_NAME = "movies_genres";
	
	private static Function<ResultSet, MovieGenre> getOnMapEntity() {
		return (resultSet) -> {
            try {
            	MovieGenre movieGenre = new MovieGenre();
            	movieGenre.setMovieId(resultSet.getInt("movie_id"));
            	movieGenre.setGenreId(resultSet.getInt("genre_id"));
  
                return movieGenre;
            } catch (SQLException e) {
            	logger.error(e);
            	throw new MovieAppException(e);
            }
        };
	}
	
	public static MovieGenre insert(MovieGenre entity, Connection connection) {
		String query = "insert into " + TABLE_NAME + " (movie_id, genre_id)"
				+ " values (?, ?)";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
        	int index = 0;
        	statement.setInt(++index, entity.getMovieId());
        	statement.setInt(++index, entity.getGenreId());

	        int affectedRows = statement.executeUpdate();
	        if (affectedRows == 0) {
	            throw new SQLException("Inserting entity failed, no rows affected.");
	        }

	        return entity;
	    } catch (SQLException e) {
	        logger.error(e.getMessage(), e);
	        throw new MovieAppException(e);
	    }
	}
	
	public static List<MovieGenre> insert(List<MovieGenre> entities, Connection connection) {
	    String query = "insert into " + TABLE_NAME + " (movie_id, genre_id)"
	    		+ " values (?, ?)";
	    
	    try (PreparedStatement statement = connection.prepareStatement(query)) {
	        for (MovieGenre entity : entities) {
	        	int index = 0;
	            statement.setInt(++index, entity.getMovieId());
	            statement.setInt(++index, entity.getGenreId());
	            statement.addBatch();
	        }

	        int[] affectedRows = statement.executeBatch();
	        
	        for (int count : affectedRows) {
	            if (count == 0) {
	                throw new SQLException("Inserting entity failed, no rows affected.");
	            }
	        }

	        return entities;
	    } catch (SQLException e) {
	        logger.error(e.getMessage(), e);
	        throw new MovieAppException(e);
	    }
	}
	
	public static List<MovieGenre> selectByCriteria(Collection<Criteria> criterias, Connection connection) {
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
            List<MovieGenre> entities = new ArrayList<>();
            while (resultSet.next()) {
            	MovieGenre entity = getOnMapEntity().apply(resultSet);
                entities.add(entity);
            }
            
            return entities;
        } catch (SQLException e) {
        	logger.error(e.getMessage(), e);
        	throw new MovieAppException(e);
        }
	}

	public static boolean delete(MovieGenre entity, Connection connection) {
		String query = "delete from " + TABLE_NAME + " where movie_id = ?"
				+ " and genre_id = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			int index = 0;
            statement.setInt(++index, entity.getMovieId());
            statement.setInt(++index, entity.getGenreId());
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
        	logger.error(e.getMessage(), e);
        	throw new MovieAppException(e);
        }
	}
	
	public static boolean deleteByMovie(int movieId, Connection connection) {
		String query = "delete from " + TABLE_NAME + " where movie_id = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			int index = 0;
            statement.setInt(++index, movieId);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
        	logger.error(e.getMessage(), e);
        	throw new MovieAppException(e);
        }
	}
}
