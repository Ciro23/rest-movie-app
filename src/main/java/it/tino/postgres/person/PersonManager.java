package it.tino.postgres.person;

import java.sql.Connection;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.tino.postgres.DaoException;
import it.tino.postgres.database.ConnectionManager;
import it.tino.postgres.database.Criteria;

public class PersonManager {

	protected static final Logger logger = LogManager.getLogger();
	
	private final ConnectionManager connectionManager;
	private final PersonDao personDao;
	
	public PersonManager(ConnectionManager connectionManager, PersonDao personDao) {
		this.connectionManager = connectionManager;
		this.personDao = personDao;
	}

	public Person insert(Person entity) {
		Connection connection = null;
		try {
			connection = connectionManager.connect();
			return personDao.insert(entity, connection);
		} catch (DaoException e) {
			logger.error(e.getMessage(), e);
			return null;
		} finally {
			if (connection != null) {
				connectionManager.close(connection);
			}
		}
	}
	
	public Person update(Person entity) {
		Connection connection = null;
		try {
			connection = connectionManager.connect();
			return personDao.update(entity, connection);	
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
    	Connection connection = null;
    	try {
    		connection = connectionManager.connect();
			return personDao.selectByCriteria(Collections.emptyList(), connection);
		} catch (DaoException e) {
			logger.error(e.getMessage(), e);
			return Collections.emptyList();
		} finally {
			if (connection != null) {
				connectionManager.close(connection);
			}
		}
    }
    
	public Person selectById(Integer id) {
		Connection connection = null;
    	try {
    		connection = connectionManager.connect();
			return personDao.selectById(id, connection);
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
    		return personDao.selectByCriteria(criterias, connection);
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
}
