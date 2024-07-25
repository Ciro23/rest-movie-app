package it.tino.postgres;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * Gives access to every application properties.
 */
public class AppProperties {

	private static AppProperties instance = null;
	
	private String databaseUrl;
	private String databaseUsername;
	private String databasePassword;
	
	private AppProperties() {
		Dotenv dotEnv = Dotenv.load();
		databaseUrl = dotEnv.get("DB_URL");
		databaseUsername = dotEnv.get("DB_USER");
		databasePassword = dotEnv.get("DB_PASSWORD");
	}
	
	public static AppProperties getInstance() {
		if (instance == null) {
			instance = new AppProperties();
		}
		return instance;
	}

	public String getDatabaseUrl() {
		return databaseUrl;
	}

	public String getDatabaseUsername() {
		return databaseUsername;
	}

	public String getDatabasePassword() {
		return databasePassword;
	}
}
