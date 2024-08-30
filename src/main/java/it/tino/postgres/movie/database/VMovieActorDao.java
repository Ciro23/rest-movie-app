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

import it.tino.postgres.person.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.tino.postgres.MovieAppException;
import it.tino.postgres.database.Criteria;

public class VMovieActorDao {

	protected static final Logger logger = LogManager.getLogger();
	private static final String TABLE_NAME = "v_movies_actors";
	
	private static Function<ResultSet, MovieActorView> getOnMapEntity() {
		return (resultSet) -> {
            try {
            	MovieActorView moviePerson = new MovieActorView();
            	moviePerson.setMovieId(resultSet.getInt("movie_id"));
            	moviePerson.setTitle(resultSet.getString("title"));
            	moviePerson.setReleaseDate(resultSet.getDate("release_date"));
            	moviePerson.setBudget(resultSet.getInt("budget"));
            	moviePerson.setBoxOffice(resultSet.getInt("box_office"));
            	moviePerson.setRuntime(resultSet.getInt("runtime"));
            	moviePerson.setOverview(resultSet.getString("overview"));
            	
            	moviePerson.setPersonId(resultSet.getInt("actor_id"));
            	moviePerson.setName(resultSet.getString("name"));
            	moviePerson.setBirth(resultSet.getDate("birth"));
            	moviePerson.setGender(Person.Gender.fromId(resultSet.getString("gender")));
            	moviePerson.setRoleName(resultSet.getString("role"));
            	moviePerson.setCastOrder(resultSet.getInt("cast_order"));
            	
                return moviePerson;
            } catch (SQLException e) {
            	logger.error(e);
            	throw new MovieAppException(e);
            }
        };
	}
	
	public static List<MovieActorView> selectByCriteria(Collection<Criteria> criterias, Connection connection) {
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
            List<MovieActorView> entities = new ArrayList<>();
            while (resultSet.next()) {
            	MovieActorView entity = getOnMapEntity().apply(resultSet);
                entities.add(entity);
            }
            
            return entities;
        } catch (SQLException e) {
        	logger.error(e.getMessage(), e);
        	throw new MovieAppException(e);
        }
	}
	
	public static List<MovieActorView> selectByCriteria(Criteria criteria, Connection connection) {
		return selectByCriteria(Collections.singleton(criteria), connection);
	}
}
