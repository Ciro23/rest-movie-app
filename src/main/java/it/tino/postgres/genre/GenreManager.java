package it.tino.postgres.genre;

import java.sql.Connection;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.tino.postgres.DaoException;
import it.tino.postgres.database.ConnectionManager;
import it.tino.postgres.database.Criteria;
import it.tino.postgres.genre.database.GenreDao;

public class GenreManager {

	protected static final Logger logger = LogManager.getLogger();

	private final ConnectionManager connectionManager;
	private final GenreDao genreDao;

	public GenreManager(ConnectionManager connectionManager, GenreDao genreDao) {
		this.connectionManager = connectionManager;
		this.genreDao = genreDao;
	}

	public Genre insert(Genre entity) {
		Connection connection = null;
		try {
			connection = connectionManager.connect();
			return genreDao.insert(entity, connection);
		} catch (DaoException e) {
			logger.error(e.getMessage(), e);
			return null;
		} finally {
			if (connection != null) {
				connectionManager.close(connection);
			}
		}
	}

	public Genre update(Genre entity) {
		Connection connection = null;
		try {
			connection = connectionManager.connect();
			return genreDao.update(entity, connection);
		} catch (DaoException e) {
			logger.error(e.getMessage(), e);
			return null;
		} finally {
			if (connection != null) {
				connectionManager.close(connection);
			}
		}
	}

	public List<Genre> selectAll() {
		Connection connection = null;
		try {
			connection = connectionManager.connect();
			return genreDao.selectByCriteria(Collections.emptyList(), connection);
		} catch (DaoException e) {
			logger.error(e.getMessage(), e);
			return Collections.emptyList();
		} finally {
			if (connection != null) {
				connectionManager.close(connection);
			}
		}
	}

	public Genre selectById(Integer id) {
		Connection connection = null;
		try {
			connection = connectionManager.connect();
			return genreDao.selectById(id, connection);
		} catch (DaoException e) {
			logger.error(e.getMessage(), e);
			return null;
		} finally {
			if (connection != null) {
				connectionManager.close(connection);
			}
		}
	}

	public List<Genre> selectByCriteria(Collection<Criteria> criteria) {
		Connection connection = null;
		try {
			connection = connectionManager.connect();
			return genreDao.selectByCriteria(criteria, connection);
		} catch (DaoException e) {
			logger.error(e.getMessage(), e);
			return Collections.emptyList();
		} finally {
			if (connection != null) {
				connectionManager.close(connection);
			}
		}
	}

	public List<Genre> selectByCriteria(Criteria criteria) {
		return selectByCriteria(Collections.singleton(criteria));
	}

	public boolean delete(Integer id) {
		Connection connection = null;
		try {
			connection = connectionManager.connect();
			return genreDao.delete(id, connection);
		} catch (DaoException e) {
			logger.error(e);
			return false;
		} finally {
			if (connection != null) {
				connectionManager.close(connection);
			}
		}
	}
}
