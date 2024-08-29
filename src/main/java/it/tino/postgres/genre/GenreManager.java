package it.tino.postgres.genre;

import java.sql.Connection;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.tino.postgres.MovieAppException;
import it.tino.postgres.database.ConnectionManager;
import it.tino.postgres.database.Criteria;
import it.tino.postgres.genre.database.GenreDao;

public class GenreManager {

	protected static final Logger logger = LogManager.getLogger();

	private final ConnectionManager connectionManager;

	public GenreManager(ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
	}

	public Genre insert(Genre entity) {
		Connection connection = null;
		try {
			connection = connectionManager.connect();
			return GenreDao.insert(entity, connection);
		} catch (MovieAppException e) {
			logger.error(e.getMessage(), e);
			throw new MovieAppException(e);
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
			return GenreDao.update(entity, connection);
		} catch (MovieAppException e) {
			logger.error(e.getMessage(), e);
			throw new MovieAppException(e);
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
			return GenreDao.selectByCriteria(Collections.emptyList(), connection);
		} catch (MovieAppException e) {
			logger.error(e.getMessage(), e);
			throw new MovieAppException(e);
		} finally {
			if (connection != null) {
				connectionManager.close(connection);
			}
		}
	}

	public Genre selectById(int id) {
		Connection connection = null;
		try {
			connection = connectionManager.connect();
			return GenreDao.selectById(id, connection);
		} catch (MovieAppException e) {
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
			return GenreDao.selectByCriteria(criteria, connection);
		} catch (MovieAppException e) {
			logger.error(e.getMessage(), e);
			throw new MovieAppException(e);
		} finally {
			if (connection != null) {
				connectionManager.close(connection);
			}
		}
	}

	public List<Genre> selectByCriteria(Criteria criteria) {
		return selectByCriteria(Collections.singleton(criteria));
	}

	public boolean delete(int id) {
		Connection connection = null;
		try {
			connection = connectionManager.connect();
			return GenreDao.delete(id, connection);
		} catch (MovieAppException e) {
			logger.error(e);
			return false;
		} finally {
			if (connection != null) {
				connectionManager.close(connection);
			}
		}
	}
}
