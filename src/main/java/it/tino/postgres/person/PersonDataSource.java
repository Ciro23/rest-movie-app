package it.tino.postgres.person;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.tino.postgres.database.DatabaseTable;

public class PersonDataSource implements PersonRepository {

	protected static final Logger logger = LogManager.getLogger();
	
	private final PersonDao personDao;
    private final DatabaseTable<Person> database;
    private final Function<ResultSet, Person> onMapEntity;
    
    public PersonDataSource(PersonDao personDao, DatabaseTable<Person> database) {
    	this.personDao = personDao;
        this.database = database;
        onMapEntity = (resultSet) -> {
            try {
            	Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setBirth(resultSet.getDate("birth"));
                person.setGender(Person.Gender.fromId(resultSet.getString("gender")));
                
                return person;
            } catch (SQLException e) {
            	logger.error(e);
            	throw new RuntimeException(e);
            }
        };
    }
    
	@Override
	public Person save(Person entity) {
		if (entity.getId() == 0) {
			return personDao.insert(entity);
		}
		return personDao.update(entity);
	}
    
    @Override
    public List<Person> findAll() {
       return database.select("select * from people order by id", null, onMapEntity);
    }

    @Override
    public List<Person> findDirectorsByMovieId(int movieId) {
        String sql = "SELECT * FROM people p " +
                "JOIN movies_directors md ON p.id = md.director_id " +
                "WHERE md.movie_id = ?";
        
        Consumer<PreparedStatement> onSetParameters = (stmt) -> {
            try {
                stmt.setInt(1, movieId);
            } catch (SQLException e) {
            	logger.error(e);
            	throw new RuntimeException(e);
            }
        };
        
        return database.select(sql, onSetParameters, onMapEntity);
    }
    
    @Override
    public List<ActorRole> findActorsByMovieId(int movieId) {
        String sql = "SELECT p.*, ma.role, ma.cast_order FROM people p " +
                "JOIN movies_actors ma ON p.id = ma.actor_id " +
                "WHERE ma.movie_id = ?";
        
        Consumer<PreparedStatement> onSetParameters = (stmt) -> {
            try {
                stmt.setInt(1, movieId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };
        
        Function<ResultSet, Person> onMapActorRole = (resultSet) -> {
            Person person = onMapEntity.apply(resultSet);
            try {
            	ActorRole actorRole = new ActorRole();
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
            	throw new RuntimeException(e);
            }
        };
        
        return database.select(sql, onSetParameters, onMapActorRole)
                .stream()
                .map(p -> (ActorRole) p)
                .toList();
    }

	@Override
	public boolean delete(Integer id) {
		return personDao.delete(id);
	}
}
