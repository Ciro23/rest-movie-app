package it.tino.postgres.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.annotation.Nullable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.tino.postgres.DaoException;

/**
 * Offers simple reusable implementations of {@link Dao#selectById(Object)},
 * {@link Dao#selectByCriteria(Criteria)} and {@link Dao#delete(Object)}.<br>
 * Helper methods are also available to insert an entity and update it.<br>
 * Database connection is automatically opened and closed after each
 * operation.
 */
abstract public class SimpleDao<T extends Identifiable<ID>, ID>
	implements Dao<T, ID> {

	protected static final Logger logger = LogManager.getLogger();
	
	protected final Connection connection;
	
	public SimpleDao(Connection connection) {
		this.connection = connection;
	}
	
	abstract protected String getTableName();
	abstract protected Function<ResultSet, T> getOnMapEntity();

	@Override
	public T selectById(ID id) {
		Criteria criteria = new Criteria("id", "=", id);
		List<T> entities = selectByCriteria(criteria);
		
		if (entities.isEmpty()) {
			return null;
		}
		return entities.get(0);
	}
	
	@Override
	public List<T> selectByCriteria(List<Criteria> criterias) {
		StringBuilder query = new StringBuilder("select * from ")
				.append(getTableName())
				.append(" where 1 = 1");
		
		List<Object> queryParameters = new ArrayList<>();
		for (Criteria criteria : criterias) {
			query.append(" and ");
			query.append(criteria.getField());
			query.append(criteria.getOperator());
			query.append("?");
			queryParameters.add(criteria.getValue());
		}
		
		try (PreparedStatement statement = connection.prepareStatement(query.toString())) {
            for (int i = 0; i < queryParameters.size(); i++) {
            	statement.setObject(i + 1, queryParameters.get(i));
            }
            
            ResultSet resultSet = statement.executeQuery();
            List<T> entities = new ArrayList<>();
            while (resultSet.next()) {
                T entity = getOnMapEntity().apply(resultSet);
                entities.add(entity);
            }
            
            return entities;
        } catch (SQLException e) {
        	String message = "Error fetching entities with query"
        			+ " '" + query + "'";
        	logger.error(message, e);
        	throw new DaoException(message, e);
        }
	}

	@Override
	public boolean delete(ID id) {
		String query = "delete from " + getTableName() + " where id = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, id);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
        	String message = "Error deleting entity with id '" + id + "'";
        	logger.error(message, e);
        	throw new DaoException(message, e);
        }
	}
	
	/**
	 * Handles the JDBC operations to insert a new entity,
	 * with the help of the specific prepared statement and
	 * a callback to fill the value of every parameter in the
	 * query.
	 */
	@SuppressWarnings("unchecked")
	protected T insert(
		T entity,
		String query,
		BiConsumer<T, PreparedStatement> onSetParameters
	) {
		try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
	        if (onSetParameters != null) {
	            onSetParameters.accept(entity, statement);
	        }

	        int affectedRows = statement.executeUpdate();
	        if (affectedRows == 0) {
	            throw new SQLException("Inserting entity failed, no rows affected.");
	        }

	        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                Object id = generatedKeys.getObject("id");
	               logger.debug("generated id: " + id);
	                return selectById((ID) id);
	            } else {
	                throw new SQLException("Inserting entity failed, no ID obtained.");
	            }
	        }
	    } catch (SQLException e) {
	    	String message = "Error inserting entity with query"
	    			+ " '" + query + "'";
	        logger.error(message, e);
	        throw new DaoException(message, e);
	    }
	}
	
	/**
     * Reads rows returning the mapped objects, allowing to execute
     * custom advanced queries, for example containing JOIN statements.
     * @param query E.g. "SELECT * FROM my_table WHERE id = ?"
     * @param onSetParameters Callback to assign a value to every
     * parameter ("?") used in the prepared statement, or null if none are present.
     * @param onMapEntity A callback used to map all {@link ResultSet} attributes
     * to the POJO object.
     * @return The mapped selected objects.
     */
    protected List<T> select(
        String query,
        @Nullable Consumer<PreparedStatement> onSetParameters,
        Function<ResultSet, T> onMapEntity
    ) {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
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
        	String message = "Error fetching entities with query"
            		+ " '" + query + "'";
        	logger.error(message, e);
            throw new DaoException(message, e);
        }
    }

	/**
	 * Handles the JDBC operations to update an entity,
	 * with the help of the specific prepared statement and
	 * a callback to fill the value of every parameter in the
	 * query.
	 */
	protected T update(
		T entity,
		String query,
		BiConsumer<T, PreparedStatement> onSetParameters
	) {
		try (PreparedStatement statement = connection.prepareStatement(query)) {
	        if (onSetParameters != null) {
	            onSetParameters.accept(entity, statement);
	        }

	        statement.executeUpdate();
	        return selectById(entity.getId());
	    } catch (SQLException e) {
	    	String message = "Error updating entity with query"
            		+ " '" + query + "'";
	        logger.error(message, e);
	        throw new DaoException(message, e);
	    }
	}
}
