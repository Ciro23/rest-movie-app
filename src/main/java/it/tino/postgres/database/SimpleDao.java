package it.tino.postgres.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

abstract public class SimpleDao<T extends Identifiable<ID>, ID> implements Dao<T, ID> {

	protected static final Logger logger = LogManager.getLogger();
	
	private final Database database;
	
	public SimpleDao(Database database) {
		this.database = database;
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
		
		try (Connection connection = database.connect()) {
            PreparedStatement statement = connection.prepareStatement(query.toString());
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
        	logger.error(e);
        }
		
        return Collections.emptyList();
	}

	@Override
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
	
	protected T insert(
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
	                System.out.println("generated id: " + id);
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

	protected T update(
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
}
