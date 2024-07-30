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

import it.tino.postgres.DaoException;
import it.tino.postgres.database.Criteria;

public class VMovieGenreDao {

	protected static final Logger logger = LogManager.getLogger();
	private static final String TABLE_NAME = "v_movies_genres";
	
	private static Function<ResultSet, MovieGenreView> getOnMapEntity() {
		return (resultSet) -> {
            try {
            	MovieGenreView movieGenre = new MovieGenreView();
            	movieGenre.setMovieId(resultSet.getInt("movie_id"));
            	movieGenre.setTitle(resultSet.getString("title"));
            	movieGenre.setReleaseDate(resultSet.getDate("release_date"));
            	movieGenre.setBudget(resultSet.getInt("budget"));
            	movieGenre.setBoxOffice(resultSet.getInt("box_office"));
            	movieGenre.setRuntime(resultSet.getInt("runtime"));
            	movieGenre.setOverview(resultSet.getString("overview"));
            	
            	movieGenre.setGenreId(resultSet.getInt("genre_id"));
            	movieGenre.setGenreName(resultSet.getString("name"));

  
                return movieGenre;
            } catch (SQLException e) {
            	logger.error(e);
            	throw new DaoException(e);
            }
        };
	}
	
	public static List<MovieGenreView> selectByCriteria(Collection<Criteria> criterias, Connection connection) {
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
            List<MovieGenreView> entities = new ArrayList<>();
            while (resultSet.next()) {
            	MovieGenreView entity = getOnMapEntity().apply(resultSet);
                entities.add(entity);
            }
            
            return entities;
        } catch (SQLException e) {
        	logger.error(e.getMessage(), e);
        	throw new DaoException(e);
        }
	}
	
	public static List<MovieGenreView> selectByCriteria(Criteria criteria, Connection connection) {
		return selectByCriteria(Collections.singleton(criteria), connection);
	}
}
