package it.tino.postgres.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcManager {

    /**
     * The URL to connect to the database of the form
     * jdbc:subprotocol:subname.<br>
     * E.g. "jdbc:postgresql://127.0.0.1:5432/my_database"<br>
     * E.g. "jdbc:mysql://localhost:3306/my_database"
     */
    private final String url;
    
    private final String username;
    private final String password;
    
    public JdbcManager(
        String url,
        String username,
        String password
    ) {
        this.url = url;
        this.username = username;
        this.password = password;
    }
    
    public Connection connect() throws SQLException {
    	return DriverManager.getConnection(url, username, password);
    }
}
