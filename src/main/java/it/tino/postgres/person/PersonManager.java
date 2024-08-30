package it.tino.postgres.person;

import java.sql.Connection;
import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

import it.tino.postgres.movie.database.*;
import it.tino.postgres.person.database.PersonView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.tino.postgres.MovieAppException;
import it.tino.postgres.database.ConnectionManager;
import it.tino.postgres.database.Criteria;
import it.tino.postgres.movie.MovieActor;
import it.tino.postgres.movie.MovieDirector;
import it.tino.postgres.person.database.PersonDao;
import it.tino.postgres.person.database.PersonDb;

public class PersonManager {

	protected static final Logger logger = LogManager.getLogger();
	
	private final ConnectionManager connectionManager;
	
	public PersonManager(ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
	}

	public Person insert(Person person) {
		Connection connection = null;
		try {
			connection = connectionManager.connect();
			connectionManager.beginTransaction(connection);
			
			PersonDb personDb = domainToDb(person);
			PersonDb insertedPersonDb = PersonDao.insert(personDb, connection);
			person.setId(insertedPersonDb.getId());
			
			List<MovieDirector> directedMovies = getDirectedMovies(person);
			MovieDirectorDao.deleteByDirector(person.getId(), connection);
			MovieDirectorDao.insert(directedMovies, connection);
			
			List<MovieActor> starredMovies = getStarredMovies(person);
			MovieActorDao.deleteByActor(person.getId(), connection);
			MovieActorDao.insert(starredMovies, connection);
			
			connectionManager.commitTransaction(connection);
			return dbToDomain(insertedPersonDb, connection);
		} catch (MovieAppException e) {
			logger.error(e.getMessage(), e);
			if (connection != null) {
				connectionManager.rollbackTransaction(connection);
			}
			throw new MovieAppException(e);
		} finally {
			if (connection != null) {
				connectionManager.endTransaction(connection);
				connectionManager.close(connection);
			}
		}
	}
	
	public Person update(Person person) {
		Connection connection = null;
		try {
			connection = connectionManager.connect();
			connectionManager.beginTransaction(connection);
			
			PersonDb personDb = domainToDb(person);
			PersonDb insertedPersonDb = PersonDao.update(personDb, connection);
			person.setId(insertedPersonDb.getId());
			
			List<MovieDirector> directedMovies = getDirectedMovies(person);
			MovieDirectorDao.deleteByDirector(person.getId(), connection);
			MovieDirectorDao.insert(directedMovies, connection);
			
			List<MovieActor> starredMovies = getStarredMovies(person);
			MovieActorDao.deleteByActor(person.getId(), connection);
			MovieActorDao.insert(starredMovies, connection);
			
			connectionManager.commitTransaction(connection);
			return dbToDomain(insertedPersonDb, connection);
		} catch (MovieAppException e) {
			logger.error(e.getMessage(), e);
			if (connection != null) {
				connectionManager.rollbackTransaction(connection);
			}
			throw new MovieAppException(e);
		} finally {
			if (connection != null) {
				connectionManager.endTransaction(connection);
				connectionManager.close(connection);
			}
		}
	}
    
    public List<Person> selectAll() {
    	return selectByCriteria(Collections.emptyList());
    }
    
	public Person selectById(int id) {
		Connection connection = null;
    	try {
    		connection = connectionManager.connect();
    		var personJdbc = PersonDao.selectById(id, connection);
			if (personJdbc == null) {
				return null;
			}

			return dbToDomain(personJdbc, connection);
		} catch (MovieAppException e) {
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
    		var peopleJdbc = PersonDao.selectByCriteria(criterias, connection);
    		return dbToDomain(peopleJdbc, connection);
    	} catch (MovieAppException e) {
    		logger.error(e.getMessage(), e);
    		throw new MovieAppException(e);
		} finally {
			if (connection != null) {
				connectionManager.close(connection);
			}
		}
	}
	
	public List<Person> selectByCriteria(Criteria criteria) {
		return selectByCriteria(Collections.singleton(criteria));
	}

	public List<Person> selectByMovieId(int movieId) {
		Connection connection = null;
		try {
			connection = connectionManager.connect();
			Set<PersonView> moviePeople = new HashSet<>(
					VMovieActorDao.selectByCriteria(
							new Criteria("movie_id", "=", movieId),
							connection
					)
			);

			moviePeople.addAll(
					VMovieDirectorDao.selectByCriteria(
							new Criteria("movie_id", "=", movieId),
							connection
					)
			);

			Set<PersonDb> people = moviePeople
					.stream()
					.map(m -> {
						PersonDb person = new PersonDb();
						person.setId(m.getPersonId());
						person.setName(m.getName());
						person.setBirth(m.getBirth());
						person.setGender(m.getGender());
						return person;
					})
					.collect(Collectors.toSet());

			return dbToDomain(people, connection);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new MovieAppException(e);
		} finally {
			if (connection != null) {
				connectionManager.close(connection);
			}
		}
	}

	public boolean delete(int id) {
		Connection connection = null;
		try {
			connection = connectionManager.connect();
			return PersonDao.delete(id, connection);
		} catch (MovieAppException e) {
			logger.error(e.getMessage(), e);
			return false;
		} finally {
			if (connection != null) {
				connectionManager.close(connection);
			}
		}
	}
	
	private List<Person> dbToDomain(
		Collection<PersonDb> peopleJdbc,
		Connection connection
	) {
		List<Integer> personIds = peopleJdbc
				.stream()
				.map(PersonDb::getId)
				.toList();
		
		List<MovieDirectorView> movieDirectors = VMovieDirectorDao.selectByCriteria(
				new Criteria("director_id", "in", personIds),
				connection
		);
		List<MovieActorView> movieActors = VMovieActorDao.selectByCriteria(
				new Criteria("actor_id", "in", personIds),
				connection
		);
		
		List<Person> people = new ArrayList<>();
		for (PersonDb personDb : peopleJdbc) {
			List<MovieDirector> directedMovies = movieDirectors
					.stream()
					.filter(md -> md.getPersonId() == personDb.getId())
					.map(md -> {
						MovieDirector directedMovie = new MovieDirector();
						directedMovie.setMovieId(md.getMovieId());
						directedMovie.setDirectorId(md.getPersonId());
						return directedMovie;
					})
					.toList();
			List<MovieActor> starredMovies = movieActors
					.stream()
					.filter(ma -> ma.getPersonId() == personDb.getId())
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
			person.setId(personDb.getId());
			person.setName(personDb.getName());
			person.setBirth(personDb.getBirth().toLocalDate());
			person.setGender(personDb.getGender());
			person.setDirectedMovies(directedMovies);
			person.setStarredMovies(starredMovies);
			
			people.add(person);
		}
		
		return people;
	}
	
	private Person dbToDomain(PersonDb personDb, Connection connection) {
		var people = dbToDomain(Collections.singleton(personDb), connection);
		if (people.isEmpty()) {
			return null;
		}
		return people.getFirst();
	}
	
	private PersonDb domainToDb(Person person) {
		PersonDb personDb = new PersonDb();
		personDb.setId(person.getId());
		personDb.setName(person.getName());
		personDb.setBirth(Date.valueOf(person.getBirth()));
		personDb.setGender(person.getGender());
		return personDb;
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
