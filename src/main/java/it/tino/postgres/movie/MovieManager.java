package it.tino.postgres.movie;

import java.sql.Connection;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.tino.postgres.DaoException;
import it.tino.postgres.database.ConnectionManager;
import it.tino.postgres.database.Criteria;

public class MovieManager {
    
	protected static final Logger logger = LogManager.getLogger();
	
	private final ConnectionManager connectionManager;
	private final MovieDao movieDao;
	
    public MovieManager(ConnectionManager connectionManager, MovieDao movieDao) {
		this.connectionManager = connectionManager;
		this.movieDao = movieDao;
	}

	public Movie insert(Movie movie) {
    	Connection connection = null;
    	try {
    		connection = connectionManager.connect();
			return movieDao.insert(movie, connection);
    	} catch (DaoException e) {
    		logger.error(e.getMessage(), e);
			return null;
		} finally {
			if (connection != null) {
				connectionManager.close(connection);
			}
		}
    }
    
    public Movie update(Movie movie) {
    	Connection connection = null;
    	try {
    		connection = connectionManager.connect();
	       	return movieDao.update(movie, connection);
    	} catch (DaoException e) {
    		logger.error(e.getMessage(), e);
			return null;
		} finally {
			if (connection != null) {
				connectionManager.close(connection);
			}
		}
    }
    
    public List<Movie> selectAll() {
    	Connection connection = null;
    	try {
    		connection = connectionManager.connect();
    		return movieDao.selectByCriteria(Collections.emptyList(), connection);
    	} catch (DaoException e) {
    		logger.error(e.getMessage(), e);
			return Collections.emptyList();
		} finally {
			if (connection != null) {
				connectionManager.close(connection);
			}
		}
    }
    
	public Movie selectById(Integer id) {
		Connection connection = null;
		try {
			connection = connectionManager.connect();
    		return movieDao.selectById(id, connection);
    	} catch (DaoException e) {
    		logger.error(e.getMessage(), e);
			return null;
		} finally {
			if (connection != null) {
				connectionManager.close(connection);
			}
		}
	}
	
	public List<Movie> selectByCriteria(Collection<Criteria> criterias) {
		Connection connection = null;
		try {
			connection = connectionManager.connect();
    		return movieDao.selectByCriteria(criterias, connection);
    	} catch (DaoException e) {
    		logger.error(e.getMessage(), e);
			return Collections.emptyList();
		} finally {
			if (connection != null) {
				connectionManager.close(connection);
			}
		}
	}
	
	public List<Movie> selectByCriteria(Criteria criteria) {
		return selectByCriteria(Collections.singleton(criteria));
	}

	public boolean delete(Integer id) {
		Connection connection = null;
		try {
			connection = connectionManager.connect();
    		return movieDao.delete(id, connection);
    	} catch (DaoException e) {
    		logger.error(e.getMessage(), e);
			return false;
		} finally {
			if (connection != null) {
				connectionManager.close(connection);
			}
		}
	}
}
