package it.tino.postgres.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.BiConsumer;
import java.util.function.Function;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

abstract public class SimpleDao<T extends Identifiable<ID>, ID> {

	protected static final Logger logger = LogManager.getLogger();
	
	private final Database database;
	
	public SimpleDao(Database database) {
		this.database = database;
	}
	
	abstract protected String getTableName();
	abstract protected Function<ResultSet, T> getOnMapEntity();

	public T insert(
		T entity,
		String query,
		BiConsumer<T, PreparedStatement> onSetParameters
	) {
		try (Connection connection = database.connect()) {
	        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	        if (onSetParameters != null) {
	            onSetParameters.accept(entity, statement);
	        }

	        int affectedRows = statement.executeUpdate();
	        if (affectedRows == 0) {
	            throw new SQLException("Inserting entity failed, no rows affected.");
	        }

	        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                Object id = generatedKeys.getObject(1);
	                return selectById((ID) id);
	            } else {
	                throw new SQLException("Inserting entity failed, no ID obtained.");
	            }
	        }
	    } catch (SQLException e) {
	        logger.error(e);
	        return null;
	    }
	}

	public T update(
		T entity,
		String query,
		BiConsumer<T, PreparedStatement> onSetParameters
	) {
		try (Connection connection = database.connect()) {
	        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	        if (onSetParameters != null) {
	            onSetParameters.accept(entity, statement);
	        }

	        int affectedRows = statement.executeUpdate();
	        return selectById(entity.getId());
	    } catch (SQLException e) {
	        logger.error(e);
	        return null;
	    }
	}

	public T selectById(ID id) {
		String query = "select * from " + getTableName() + " where id = ?";
		try (Connection connection = database.connect()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setObject(1, id);
            
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getOnMapEntity().apply(resultSet);
            }
        } catch (SQLException e) {
        	logger.error(e);
        }
		
        return null;
	}

	public boolean delete(ID id) {
		String query = "delete from " + getTableName() + " where id = ?";
		try (Connection connection = database.connect()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setObject(1, id);
            
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
        	logger.error(e);
            return false;
        }
	}
}
