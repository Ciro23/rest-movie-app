package it.tino.postgres.database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class H2TestUtil {

	private static final String URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
	private static final String USER = "sa";
	private static final String PASSWORD = "";

	public static final JdbcManager jdbcManager = new JdbcManager(URL, USER, PASSWORD);
	
	public static DaoManager getDaoManager() throws SQLException {
		return new DaoManager(jdbcManager);
	}
	
	public static void createTables() {
        try (Connection connection = jdbcManager.connect()) {
            executeSqlFile(connection, "/schema.sql");
            executeSqlFile(connection, "/data.sql");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
	
	public static void dropTables() {
        try (Connection connection = jdbcManager.connect();
             Statement statement = connection.createStatement()) {
            statement.execute("DROP ALL OBJECTS;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	private static void executeSqlFile(
		Connection connection,
		String filePath
	) throws SQLException, IOException {
        try (InputStream inputStream = H2TestUtil.class.getResourceAsStream(filePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
             Statement statement = connection.createStatement()) {

            StringBuilder sql = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sql.append(line);
                if (line.trim().endsWith(";")) {
                    statement.execute(sql.toString());
                    sql.setLength(0);
                }
            }
        }
    }
}
