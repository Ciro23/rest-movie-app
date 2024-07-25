package it.tino.postgres.person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.tino.postgres.DaoException;
import it.tino.postgres.database.Criteria;
import it.tino.postgres.database.Dao;

public class PersonDao implements Dao<Person, Integer> {

	protected static final Logger logger = LogManager.getLogger();
	private static final String TABLE_NAME = "people";

	protected Function<ResultSet, Person> getOnMapEntity() {
		return (resultSet) -> {
            try {
            	Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setBirth(resultSet.getDate("birth"));
                person.setGender(Person.Gender.fromId(resultSet.getString("gender")));
                
                return person;
            } catch (SQLException e) {
            	logger.error(e);
            	throw new DaoException(e);
            }
        };
	}

	@Override
	public Person insert(Person entity, Connection connection) {
		String query = "insert into people (name, birth, gender)"
				+ " values (?, ?, ?::gender)";
		try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			int index = 0;
			statement.setString(++index, entity.getName());
			statement.setDate(++index, entity.getBirth());
			statement.setString(++index, String.valueOf(entity.getGender().getId()));

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

	@Override
	public Person update(Person entity, Connection connection) {
		String query = "update people set name = ?, birth = ?,"
				+ " gender = ?::gender where id = ?";
		
		try (PreparedStatement statement = connection.prepareStatement(query)) {
        	int index = 0;
        	statement.setString(++index, entity.getName());
        	statement.setDate(++index, entity.getBirth());
        	statement.setString(++index, String.valueOf(entity.getGender().getId()));
        	statement.setInt(++index, entity.getId());

	        statement.executeUpdate();
	        return selectById(entity.getId(), connection);
	    } catch (SQLException e) {
	        logger.error(e.getMessage(), e);
	        throw new DaoException(e);
	    }
	}

	@Override
	public Person selectById(Integer id, Connection connection) {
		Criteria criteria = new Criteria("id", "=", id);
		List<Person> entities = selectByCriteria(criteria, connection);
		
		if (entities.isEmpty()) {
			return null;
		}
		return entities.get(0);
	}

	@Override
	public List<Person> selectByCriteria(Collection<Criteria> criterias, Connection connection) {
		StringBuilder query = new StringBuilder("select * from ")
				.append(TABLE_NAME)
				.append(" where 1 = 1");
		
		List<Object> queryParameters = new ArrayList<>();
		for (Criteria criteria : criterias) {
			query.append(" and ");
			query.append(criteria.getField());
			query.append(criteria.getOperator());
			query.append("?");
			queryParameters.add(criteria.getValue());
		}
		
		try (PreparedStatement statement = connection.prepareStatement(query.toString())) {
            for (int i = 0; i < queryParameters.size(); i++) {
            	statement.setObject(i + 1, queryParameters.get(i));
            }
            
            ResultSet resultSet = statement.executeQuery();
            List<Person> entities = new ArrayList<>();
            while (resultSet.next()) {
            	Person entity = getOnMapEntity().apply(resultSet);
                entities.add(entity);
            }
            
            return entities;
        } catch (SQLException e) {
        	logger.error(e.getMessage(), e);
        	throw new DaoException(e);
        }
	}

	@Override
	public boolean delete(Integer id, Connection connection) {
		String query = "delete from " + TABLE_NAME + " where id = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, id);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
        	logger.error(e.getMessage(), e);
        	throw new DaoException(e);
        }
	}
	
	public boolean insertDirectorOfMovie(int movieId, int personId) {
		String query = "insert into movies_directors (movie_id, director_id)"
				+ " values (?, ?)";
		
		Connection connection = null;
		try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, movieId);
            statement.setInt(2, personId);

	        int affectedRows = statement.executeUpdate();
	        return affectedRows == 1;
	    } catch (SQLException e) {
	        logger.error(e);
	        return false;
	    }
	}
	
	public List<Person> selectDirectorsByMovieId(int movieId) {
		String sql = "SELECT * FROM people p " +
                "JOIN movies_directors md ON p.id = md.director_id " +
                "WHERE md.movie_id = ?";
        
        Consumer<PreparedStatement> onSetParameters = (stmt) -> {
            try {
                stmt.setInt(1, movieId);
            } catch (SQLException e) {
            	logger.error(e);
            	throw new DaoException(e);
            }
        };
        return null;
        //return select(sql, onSetParameters, getOnMapEntity());
	}
	
	public boolean insertActorOfMovie(
		int movieId,
		MovieActor actor
	) {
		String query = "insert into movies_actors (movie_id, actor_id, role,"
				+ " cast_order) values (?, ?, ?, ?)";
		
		Connection connection = null;
		try (PreparedStatement statement = connection.prepareStatement(query)) {
	        int index = 0;
            statement.setInt(++index, movieId);
            statement.setInt(++index, actor.getId());
            statement.setString(++index, actor.getRoleName());
            statement.setInt(++index, actor.getCastOrder());

	        int affectedRows = statement.executeUpdate();
	        return affectedRows == 1;
	    } catch (SQLException e) {
	        logger.error(e);
	        return false;
	    }
	}
	
	public List<MovieActor> selectActorsByMovieId(int movieId) {
		String sql = "SELECT p.*, ma.role, ma.cast_order FROM people p " +
                "JOIN movies_actors ma ON p.id = ma.actor_id " +
                "WHERE ma.movie_id = ?";
        
        Consumer<PreparedStatement> onSetParameters = (stmt) -> {
            try {
                stmt.setInt(1, movieId);
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        };
        
        Function<ResultSet, Person> onMapActorRole = (resultSet) -> {
            Person person = getOnMapEntity().apply(resultSet);
            try {
            	MovieActor actorRole = new MovieActor();
            	actorRole.setId(person.getId());
            	actorRole.setName(person.getName());
            	actorRole.setBirth(person.getBirth());
            	actorRole.setGender(person.getGender());
                actorRole.setMovieId(movieId);
                actorRole.setRoleName(resultSet.getString("role"));
                actorRole.setCastOrder(resultSet.getInt("cast_order"));
                
                return actorRole;
            } catch (SQLException e) {
            	logger.error(e);
            	throw new DaoException(e);
            }
        };
        return null;
//        return select(sql, onSetParameters, onMapActorRole)
//                .stream()
//                .map(p -> (MovieActor) p)
//                .toList();
	}
}
