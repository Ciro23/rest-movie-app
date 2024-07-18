package it.tino.postgres.movie;

import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MovieDataSource implements MovieRepository {
    
	protected static final Logger logger = LogManager.getLogger();
	
    private final MovieDao movieDao;

    public MovieDataSource(MovieDao movieDao) {
        this.movieDao = movieDao;
    }
    
    @Override
    public Movie save(Movie movie) {
    	if (movie.getId() == 0) {
    		 return movieDao.insert(movie);
    	}
    	return movieDao.update(movie);
    }
    
    @Override
    public List<Movie> findAll() {
       return movieDao.selectByCriteria(Collections.emptyList());
    }
    
    @Override
	public Movie findById(Integer id) {
		return movieDao.selectById(id);
	}

	@Override
	public boolean delete(Integer id) {
		return movieDao.delete(id);
	}
}
