package it.tino.postgres.database;

import java.sql.SQLException;

/**
 * This class allows the handling of the lifecycle of the database
 * connection in an higher layer than DAOs, to manage transactions
 */
public class DaoManager extends DaoFactory implements AutoCloseable {
	
	public DaoManager(JdbcManager jdbcManager) throws SQLException {
		super(jdbcManager.connect());
	}

	public void beginTransaction() throws SQLException {
        connection.setAutoCommit(false);
    }

    public void commitTransaction() throws SQLException {
        connection.commit();
        connection.setAutoCommit(true);
    }

    public void rollbackTransaction() throws SQLException {
        connection.rollback();
        connection.setAutoCommit(true);
    }

    @Override
    public void close() throws SQLException {
        connection.close();
    }
}
