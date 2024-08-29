package it.tino.postgres.review;

import java.sql.Connection;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.tino.postgres.MovieAppException;
import it.tino.postgres.database.ConnectionManager;
import it.tino.postgres.database.Criteria;
import it.tino.postgres.review.database.ReviewDao;

public class ReviewManager {

	protected static final Logger logger = LogManager.getLogger();
    
	private final ConnectionManager connectionManager;
	
	public ReviewManager(ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
	}

	public Review insert(Review entity) {
		Connection connection = null;
    	try {
    		connection = connectionManager.connect();
			return ReviewDao.insert(entity, connection);
		} catch (MovieAppException e) {
			logger.error(e.getMessage(), e);
			throw new MovieAppException(e);
		} finally {
			if (connection != null) {
				connectionManager.close(connection);
			}
		}
	}
	
	public Review update(Review entity) {
		Connection connection = null;
    	try {
    		connection = connectionManager.connect();
			return ReviewDao.update(entity, connection);
		} catch (MovieAppException e) {
			logger.error(e.getMessage(), e);
			throw new MovieAppException(e);
		} finally {
			if (connection != null) {
				connectionManager.close(connection);
			}
		}
	}
    
    public List<Review> selectAll() {
    	Connection connection = null;
    	try {
    		connection = connectionManager.connect();
			return ReviewDao.selectByCriteria(Collections.emptyList(), connection);
		} catch (MovieAppException e) {
			logger.error(e.getMessage(), e);
			throw new MovieAppException(e);
		} finally {
			if (connection != null) {
				connectionManager.close(connection);
			}
		}
    }
    
   	public Review selectById(int id) {
   		Connection connection = null;
    	try {
    		connection = connectionManager.connect();
			return ReviewDao.selectById(id, connection);
		} catch (MovieAppException e) {
			logger.error(e.getMessage(), e);
			return null;
		} finally {
			if (connection != null) {
				connectionManager.close(connection);
			}
		}
   	}
   	
   	public List<Review> selectByCriteria(Collection<Criteria> criteria) {
   		Connection connection = null;
    	try {
    		connection = connectionManager.connect();
			return ReviewDao.selectByCriteria(criteria, connection);
		} catch (MovieAppException e) {
			logger.error(e.getMessage(), e);
			throw new MovieAppException(e);
		} finally {
			if (connection != null) {
				connectionManager.close(connection);
			}
		}
    }
    
    public List<Review> selectByCriteria(Criteria criteria) {
    	return selectByCriteria(Collections.singleton(criteria));
    }
    
	public boolean delete(int id) {
		Connection connection = null;
		try {
			connection = connectionManager.connect();
			return ReviewDao.delete(id, connection);
		} catch (MovieAppException e) {
			logger.error(e.getMessage(), e);
			return false;
		} finally {
			if (connection != null) {
				connectionManager.close(connection);
			}
		}
	}
}
