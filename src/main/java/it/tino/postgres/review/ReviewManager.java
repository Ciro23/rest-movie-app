package it.tino.postgres.review;

import java.sql.Connection;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.tino.postgres.DaoException;
import it.tino.postgres.database.ConnectionManager;
import it.tino.postgres.database.Criteria;

public class ReviewManager {

	protected static final Logger logger = LogManager.getLogger();
    
	private final ConnectionManager connectionManager;
	private final ReviewDao reviewDao;
	
	public ReviewManager(ConnectionManager connectionManager, ReviewDao reviewDao) {
		this.connectionManager = connectionManager;
		this.reviewDao = reviewDao;
	}

	public Review insert(Review entity) {
		Connection connection = null;
    	try {
    		connection = connectionManager.connect();
			return reviewDao.insert(entity, connection);
		} catch (DaoException e) {
			logger.error(e.getMessage(), e);
			return null;
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
			return reviewDao.update(entity, connection);
		} catch (DaoException e) {
			logger.error(e.getMessage(), e);
			return null;
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
			return reviewDao.selectByCriteria(Collections.emptyList(), connection);
		} catch (DaoException e) {
			logger.error(e.getMessage(), e);
			return Collections.emptyList();
		} finally {
			if (connection != null) {
				connectionManager.close(connection);
			}
		}
    }
    
   	public Review selectById(Integer id) {
   		Connection connection = null;
    	try {
    		connection = connectionManager.connect();
			return reviewDao.selectById(id, connection);
		} catch (DaoException e) {
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
			return reviewDao.selectByCriteria(criteria, connection);
		} catch (DaoException e) {
			logger.error(e.getMessage(), e);
			return Collections.emptyList();
		} finally {
			if (connection != null) {
				connectionManager.close(connection);
			}
		}
    }
    
    public List<Review> selectByCriteria(Criteria criteria) {
    	return selectByCriteria(Collections.singleton(criteria));
    }
    
	public boolean delete(Integer id) {
		Connection connection = null;
		try {
			connection = connectionManager.connect();
			return reviewDao.delete(id, connection);
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
