package it.tino.postgres.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.annotation.Nullable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Represents a database table, allowing to execute
 * CRUD operations using JDBC.
 * @param <T> The POJO type representing each row.
 */
public class DatabaseTable<T> {
    
	protected static final Logger logger = LogManager.getLogger();
    private final Database db;
    
    public DatabaseTable(Database db) {
        this.db = db;
    }

    /**
     * Performs both inserts and updates, based on the query.
     * @param entity The entity to insert or update.
     * @param query It can be an insert prepared statement or an update one.<br>
     * E.g. "INSERT INTO my_table (my_column) VALUES (?)"<br>
     * E.g. "UPDATE my_table SET my_column = ? WHERE id = ?"
     * @param onSetParameters Callback to assign a value to every
     * parameter ("?") used in the prepared statement, or null if none are present.
     * @return The number of affected rows.
     */
    public int insertOrUpdate(
        T entity,
        String query,
        @Nullable BiConsumer<T, PreparedStatement> onSetParameters
    ) {
        try (Connection connection = db.connect()) {
            PreparedStatement statement = connection.prepareStatement(query);
            if (onSetParameters != null) {
                onSetParameters.accept(entity, statement);
            }
            
            int affectedRows = statement.executeUpdate();
            
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                long id = generatedKeys.getLong(1);
	                logger.trace("GENERATED ID: " + id);
	            } else {
	                throw new SQLException("Inserting entity failed, no ID obtained.");
	            }
	        }
            
            return affectedRows;
        } catch (SQLException e) {
            logger.error(e);
            return 0;
        }
    }
    
    /**
     * Reads rows returning the mapped objects.
     * @param query E.g. "SELECT * FROM my_table WHERE id = ?"
     * @param onSetParameters Callback to assign a value to every
     * parameter ("?") used in the prepared statement, or null if none are present.
     * @param onMapEntity A callback used to map all {@link ResultSet} attributes
     * to the POJO object.
     * @return The mapped selected objects.
     */
    public List<T> select(
        String query,
        @Nullable Consumer<PreparedStatement> onSetParameters,
        Function<ResultSet, T> onMapEntity
    ) {
        try (Connection connection = db.connect()) {
            PreparedStatement statement = connection.prepareStatement(query);
            if (onSetParameters != null) {
                onSetParameters.accept(statement);
            }
            
            ResultSet resultSet = statement.executeQuery();
            
            List<T> entities = new ArrayList<>();
            while (resultSet.next()) {
                T entity = onMapEntity.apply(resultSet);
                entities.add(entity);
            }
            
            return entities;
        } catch (SQLException e) {
        	logger.error(e);
            return Collections.emptyList();
        }
    }
    
    /**
     * Performs the deletion of one or more rows.
     * @param query E.g. "DELETE FROM my_table WHERE id = ?"<br>
     * E.g. "DELETE FROM my_table WHERE id IN (?, ?, ?)"
     * @param onSetParameters Callback to assign a value to every
     * parameter ("?") used in the prepared statement, or null if none are present.
     * @return The number of affected rows.
     */
    public int delete(
        String query,
        @Nullable Consumer<PreparedStatement> onSetParameters
    ) {
        try (Connection connection = db.connect()) {
            PreparedStatement statement = connection.prepareStatement(query);
            if (onSetParameters != null) {
                onSetParameters.accept(statement);
            }
            
            return statement.executeUpdate();
        } catch (SQLException e) {
        	logger.error(e);
            return 0;
        }
    }
}
