package it.tino.postgres.movie;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.tino.postgres.DaoException;
import it.tino.postgres.database.DaoManager;
import it.tino.postgres.person.MovieActor;
import it.tino.postgres.person.Person;
import it.tino.postgres.person.Person.Gender;
import it.tino.postgres.person.PersonDao;

public class MovieDataSource implements MovieRepository {
    
	protected static final Logger logger = LogManager.getLogger();
	
    private final Supplier<DaoManager> onGetDaoManager;

    public MovieDataSource(Supplier<DaoManager> onGetDaoManager) {
        this.onGetDaoManager = onGetDaoManager;
    }
    
    @Override
    public Movie save(Movie movie) {
    	try (DaoManager daoManager = onGetDaoManager.get()) {
    		MovieDao movieDao = daoManager.getMovieDao();
    		if (movie.getId() == 0) {
    			return movieDao.insert(movie);
	       	}
	       	return movieDao.update(movie);
    	} catch (SQLException | DaoException e) {
			return null;
		}
    }
    
    @Override
    public List<Movie> findAll() {
    	try (DaoManager daoManager = onGetDaoManager.get()) {
    		MovieDao movieDao = daoManager.getMovieDao();
    		return movieDao.selectByCriteria(Collections.emptyList());
    	} catch (SQLException | DaoException e) {
			return Collections.emptyList();
		}
    }
    
    @Override
	public Movie findById(Integer id) {
		try (DaoManager daoManager = onGetDaoManager.get()) {
			MovieDao movieDao = daoManager.getMovieDao();
    		return movieDao.selectById(id);
    	} catch (SQLException | DaoException e) {
			return null;
		}
	}

	@Override
	public boolean delete(Integer id) {
		try (DaoManager daoManager = onGetDaoManager.get()) {
			MovieDao movieDao = daoManager.getMovieDao();
    		return movieDao.delete(id);
    	} catch (SQLException | DaoException e) {
			return false;
		}
	}
	
	public boolean createFirstMovie() {
		try (DaoManager daoManager = onGetDaoManager.get()) {
			MovieDao movieDao = daoManager.getMovieDao();
			PersonDao personDao = daoManager.getPersonDao();
			daoManager.beginTransaction();
			
			Movie movie = new Movie();
	    	movie.setTitle("Fly Me to the Moon");
	    	movie.setReleaseDate(Date.valueOf("2024-07-12"));
	    	movie.setBudget(100000000);
	    	movie.setBoxOffice(30000000);
	    	movie.setRuntime(132);
	    	movie.setOverview("Marketing maven Kelly Jones wreaks havoc on"
	    			+ " launch director Cole Davis's already difficult task."
	    			+ " When the White House deems the mission too important"
	    			+ " to fail, the countdown truly begins.");
	    	
	    	Person actor1 = new Person();
	    	actor1.setName("Scarlett Johansson");
	        actor1.setBirth(Date.valueOf("1984-11-22"));
	        actor1.setGender(Gender.FEMALE);
	        
	        Person actor2 = new Person();
	        actor2.setName("Channing Tatum");
	        actor2.setBirth(Date.valueOf("1980-4-26"));
	        actor2.setGender(Gender.MALE);
	    	
	    	try {
		    	Movie insertedMovie = movieDao.insert(movie);
		    	Person insertedActor1 = personDao.insert(actor1);
		    	Person insertedActor2 = personDao.insert(actor2);
		    	
		    	MovieActor movieActor1 = new MovieActor();
		    	movieActor1.setId(insertedActor1.getId());
		    	movieActor1.setName(insertedActor1.getName());
		    	movieActor1.setBirth(insertedActor1.getBirth());
		    	movieActor1.setGender(insertedActor1.getGender());
		    	movieActor1.setRoleName("Kelly Jones");
		    	movieActor1.setCastOrder(0);
		    	
		    	MovieActor movieActor2 = new MovieActor();
		    	movieActor2.setId(insertedActor2.getId());
		    	movieActor2.setName(insertedActor2.getName());
		    	movieActor2.setBirth(insertedActor2.getBirth());
		    	movieActor2.setGender(insertedActor2.getGender());
		    	movieActor2.setRoleName("Cole Davis");
		    	movieActor2.setCastOrder(1);
		    	
		    	personDao.insertActorOfMovie(insertedMovie.getId(), movieActor1);
		    	personDao.insertActorOfMovie(insertedMovie.getId(), movieActor2);
	    	} catch (DaoException e) {
	    		daoManager.rollbackTransaction();
	    	}
	    	
    		daoManager.commitTransaction();
    		
    		return true;
    	} catch (SQLException e) {
			return false;
		}
	}
}
