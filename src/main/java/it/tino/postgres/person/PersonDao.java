package it.tino.postgres.person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import it.tino.postgres.MovieAppException;
import it.tino.postgres.database.Dao;
import it.tino.postgres.database.SimpleDao;

public class PersonDao extends SimpleDao<Person, Integer> implements Dao<Person, Integer> {

	public PersonDao(Connection connection) {
		super(connection);
	}
	
	@Override
	protected String getTableName() {
		return "people";
	}

	@Override
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
            	throw new MovieAppException(e);
            }
        };
	}

	@Override
	public Person insert(Person person) {
		String query = "insert into people (name, birth, gender)"
				+ " values (?, ?, ?::gender)";
		
		BiConsumer<Person, PreparedStatement> onSetParameters = (entity, stmt) -> {
            int index = 0;
            try {
                stmt.setString(++index, entity.getName());
                stmt.setDate(++index, entity.getBirth());
                stmt.setString(++index, String.valueOf(entity.getGender().getId()));
            } catch (SQLException e) {
            	logger.error(e);
            	throw new MovieAppException(e);
            }
        };
		
		return insert(person, query, onSetParameters);
	}

	@Override
	public Person update(Person person) {
		String query = "update people set name = ?, birth = ?,"
				+ " gender = ?::gender where id = ?";
		
		BiConsumer<Person, PreparedStatement> onSetParameters = (entity, stmt) -> {
            int index = 0;
            try {
                stmt.setString(++index, entity.getName());
                stmt.setDate(++index, entity.getBirth());
                stmt.setString(++index, String.valueOf(entity.getGender().getId()));
                stmt.setInt(++index, entity.getId());
            } catch (SQLException e) {
            	logger.error(e);
            	throw new MovieAppException(e);
            }
        };
		
		return update(person, query, onSetParameters);
	}
	
	public boolean insertDirectorOfMovie(int movieId, int personId) {
		String query = "insert into movies_directors (movie_id, director_id)"
				+ " values (?, ?)";
		
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
            	throw new MovieAppException(e);
            }
        };
        
        return select(sql, onSetParameters, getOnMapEntity());
	}
	
	public boolean insertActorOfMovie(
		int movieId,
		MovieActor actor
	) {
		String query = "insert into movies_actors (movie_id, actor_id, role,"
				+ " cast_order) values (?, ?, ?, ?)";
		
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
                throw new MovieAppException(e);
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
            	throw new MovieAppException(e);
            }
        };
        
        return select(sql, onSetParameters, onMapActorRole)
                .stream()
                .map(p -> (MovieActor) p)
                .toList();
	}
}
