package it.tino.postgres.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.tino.postgres.AppProperties;
import it.tino.postgres.MovieAppException;

/**
 * Offers all the methods to handle the complete lifecycle of the
 * database connection and its transactions.
 */
public class ConnectionManager {

	private static ConnectionManager instance;
	protected static final Logger logger = LogManager.getLogger();

	private final String url;
	private final String username;
	private final String password;

	private ConnectionManager() {
		AppProperties appProperties = AppProperties.getInstance();
		url = appProperties.getDatabaseUrl();
		username = appProperties.getDatabaseUsername();
		password = appProperties.getDatabasePassword();

		// https://www.postgresql.org/docs/7.4/jdbc-use.html
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			throw new MovieAppException("Errore durante il caricamento del driver per la connessione" +
					" a PostgreSQL", e);
		}
	}

	public static ConnectionManager getInstance() {
		if (instance == null) {
			instance = new ConnectionManager();
		}
		return instance;
	}

	public Connection connect() {
		try {
			return DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new MovieAppException(e);
		}
	}

	public void beginTransaction(Connection connection) {
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new MovieAppException(e);
		}
	}

	public void commitTransaction(Connection connection) {
		try {
			connection.commit();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new MovieAppException(e);
		}
	}

	public void rollbackTransaction(Connection connection) {
		try {
			connection.rollback();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new MovieAppException(e);
		}
	}
	
	public void endTransaction(Connection connection) {
		try {
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new MovieAppException(e);
		}
	}

	public void close(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new MovieAppException(e);
		}
	}
}
