package it.tino.postgres.person;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.tino.postgres.DaoException;
import it.tino.postgres.database.ConnectionManager;
import it.tino.postgres.database.Criteria;
import it.tino.postgres.movie.MovieActor;
import it.tino.postgres.movie.MovieDirector;
import it.tino.postgres.movie.database.MovieActorDao;
import it.tino.postgres.movie.database.MovieActorView;
import it.tino.postgres.movie.database.MovieDirectorDao;
import it.tino.postgres.movie.database.MovieDirectorView;
import it.tino.postgres.movie.database.VMovieActorDao;
import it.tino.postgres.movie.database.VMovieDirectorDao;
import it.tino.postgres.person.database.PersonDao;
import it.tino.postgres.person.database.PersonJdbc;

public class PersonManager {

	protected static final Logger logger = LogManager.getLogger();
	
	private final ConnectionManager connectionManager;
	private final PersonDao personDao;
	
	private final MovieDirectorDao movieDirectorDao;
	private final VMovieDirectorDao vMovieDirectorDao;
	
	private final MovieActorDao movieActorDao;
	private final VMovieActorDao vMovieActorDao;
	
	public PersonManager(
		ConnectionManager connectionManager,
		PersonDao personDao,
		MovieDirectorDao movieDirectorDao,
		VMovieDirectorDao vMovieDirectorDao,
		MovieActorDao movieActorDao,
		VMovieActorDao vMovieActorDao
	) {
		this.connectionManager = connectionManager;
		this.personDao = personDao;
		this.movieDirectorDao = movieDirectorDao;
		this.vMovieDirectorDao = vMovieDirectorDao;
		this.movieActorDao = movieActorDao;
		this.vMovieActorDao = vMovieActorDao;
	}

	public Person insert(Person person) {
		Connection connection = null;
		try {
			connection = connectionManager.connect();
			connectionManager.beginTransaction(connection);
			
			PersonJdbc personJdbc = domainToDb(person, connection);
			PersonJdbc insertedPersonJdbc = personDao.insert(personJdbc, connection);
			person.setId(insertedPersonJdbc.getId());
			
			List<MovieDirector> directedMovies = getDirectedMovies(person);
			movieDirectorDao.deleteByDirector(person.getId(), connection);
			movieDirectorDao.insert(directedMovies, connection);
			
			List<MovieActor> starredMovies = getStarredMovies(person);
			movieActorDao.deleteByActor(person.getId(), connection);
			movieActorDao.insert(starredMovies, connection);
			
			connectionManager.commitTransaction(connection);
			connectionManager.endTransaction(connection);
			
			return dbToDomain(insertedPersonJdbc, connection);
		} catch (DaoException e) {
			logger.error(e.getMessage(), e);
			return null;
		} finally {
			if (connection != null) {
				connectionManager.close(connection);
			}
		}
	}
	
	public Person update(Person person) {
		Connection connection = null;
		try {
			connection = connectionManager.connect();
			connectionManager.beginTransaction(connection);
			
			PersonJdbc personJdbc = domainToDb(person, connection);
			PersonJdbc insertedPersonJdbc = personDao.update(personJdbc, connection);
			person.setId(insertedPersonJdbc.getId());
			
			List<MovieDirector> directedMovies = getDirectedMovies(person);
			movieDirectorDao.deleteByDirector(person.getId(), connection);
			movieDirectorDao.insert(directedMovies, connection);
			
			List<MovieActor> starredMovies = getStarredMovies(person);
			movieActorDao.deleteByActor(person.getId(), connection);
			movieActorDao.insert(starredMovies, connection);
			
			connectionManager.commitTransaction(connection);
			connectionManager.endTransaction(connection);
			
			return dbToDomain(insertedPersonJdbc, connection);
		} catch (DaoException e) {
			logger.error(e.getMessage(), e);
			return null;
		} finally {
			if (connection != null) {
				connectionManager.close(connection);
			}
		}
	}
    
    public List<Person> selectAll() {
    	return selectByCriteria(Collections.emptyList());
    }
    
	public Person selectById(Integer id) {
		Connection connection = null;
    	try {
    		connection = connectionManager.connect();
    		var peopleJdbc = personDao.selectById(id, connection);
			return dbToDomain(peopleJdbc, connection);
		} catch (DaoException e) {
			logger.error(e.getMessage(), e);
			return null;
		} finally {
			if (connection != null) {
				connectionManager.close(connection);
			}
		}
	}
	
	public List<Person> selectByCriteria(Collection<Criteria> criterias) {
		Connection connection = null;
		try {
			connection = connectionManager.connect();
    		var peopleJdbc = personDao.selectByCriteria(criterias, connection);
    		return dbToDomain(peopleJdbc, connection);
    	} catch (DaoException e) {
    		logger.error(e.getMessage(), e);
			return Collections.emptyList();
		} finally {
			if (connection != null) {
				connectionManager.close(connection);
			}
		}
	}
	
	public List<Person> selectByCriteria(Criteria criteria) {
		return selectByCriteria(Collections.singleton(criteria));
	}

	public boolean delete(Integer id) {
		Connection connection = null;
		try {
			connection = connectionManager.connect();
			return personDao.delete(id, connection);
		} catch (DaoException e) {
			logger.error(e.getMessage(), e);
			return false;
		} finally {
			if (connection != null) {
				connectionManager.close(connection);
			}
		}
	}
	
	private List<Person> dbToDomain(
		Collection<PersonJdbc> peopleJdbc,
		Connection connection
	) {
		List<Integer> personIds = peopleJdbc
				.stream()
				.map(p -> p.getId())
				.toList();
		
		List<MovieDirectorView> movieDirectors = vMovieDirectorDao.selectByCriteria(
				new Criteria("director_id", "in", personIds),
				connection
		);
		List<MovieActorView> movieActors = vMovieActorDao.selectByCriteria(
				new Criteria("actor_id", "in", personIds),
				connection
		);
		
		List<Person> people = new ArrayList<Person>();
		for (PersonJdbc personJdbc : peopleJdbc) {
			List<MovieDirector> directedMovies = movieDirectors
					.stream()
					.filter(md -> md.getPersonId() == personJdbc.getId())
					.map(md -> {
						MovieDirector directedMovie = new MovieDirector();
						directedMovie.setMovieId(md.getMovieId());
						directedMovie.setDirectorId(md.getPersonId());
						return directedMovie;
					})
					.toList();
			List<MovieActor> starredMovies = movieActors
					.stream()
					.filter(ma -> ma.getPersonId() == personJdbc.getId())
					.map(ma -> {
						MovieActor starredMovie = new MovieActor();
						starredMovie.setMovieId(ma.getMovieId());
						starredMovie.setActorId(ma.getPersonId());
						starredMovie.setRoleName(ma.getRoleName());
						starredMovie.setCastOrder(ma.getCastOrder());
						return starredMovie;
					})
					.toList();

			Person person = new Person();
			person.setId(personJdbc.getId());
			person.setName(personJdbc.getName());
			person.setBirth(personJdbc.getBirth());
			person.setGender(personJdbc.getGender());
			person.setDirectedMovies(directedMovies);
			person.setStarredMovies(starredMovies);
			
			people.add(person);
		}
		
		return people;
	}
	
	private Person dbToDomain(PersonJdbc personJdbc, Connection connection) {
		var people = dbToDomain(Collections.singleton(personJdbc), connection);
		if (people.isEmpty()) {
			return null;
		}
		return people.getFirst();
	}
	
	private PersonJdbc domainToDb(Person person, Connection connection) {
		PersonJdbc personJdbc = new PersonJdbc();
		personJdbc.setId(person.getId());
		personJdbc.setName(person.getName());
		personJdbc.setBirth(new Date(person.getBirth().getTime()));
		personJdbc.setGender(person.getGender());
		return personJdbc;
	}
	
	private List<MovieDirector> getDirectedMovies(Person person) {
		return person.getDirectedMovies()
				.stream()
				.map(directedMovie -> {
					MovieDirector movieDirector = new MovieDirector();
					movieDirector.setMovieId(directedMovie.getMovieId());
					movieDirector.setDirectorId(person.getId());
					return movieDirector;
				})
				.toList();
	}
	
	private List<MovieActor> getStarredMovies(Person person) {
		return person.getStarredMovies()
				.stream()
				.map(starredMovie -> {
					MovieActor movieActor = new MovieActor();
					movieActor.setMovieId(starredMovie.getMovieId());
					movieActor.setActorId(person.getId());
					movieActor.setRoleName(starredMovie.getRoleName());
					movieActor.setCastOrder(starredMovie.getCastOrder());
					return movieActor;
				})
				.toList();
	}
}
