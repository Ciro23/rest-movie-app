package it.tino.postgres.genre.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.tino.postgres.DaoException;
import it.tino.postgres.database.Criteria;
import it.tino.postgres.database.Dao;
import it.tino.postgres.genre.Genre;

public class GenreDao implements Dao<Genre, Integer> {

	protected static final Logger logger = LogManager.getLogger();
	public static final String TABLE_NAME = "genres";
	
	protected Function<ResultSet, Genre> getOnMapEntity() {
		return (resultSet) -> {
            try {
            	Genre genre = new Genre();
            	genre.setId(resultSet.getInt("id"));
            	genre.setName(resultSet.getString("name"));
            	
            	return genre;
            } catch (SQLException e) {
            	logger.error(e);
                throw new DaoException(e);
            }
        };
	}

	@Override
	public Genre insert(Genre entity, Connection connection) {
		String query = "insert into genres (name) values (?)";
		try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
        	int index = 0;
        	statement.setString(++index, entity.getName());

	        int affectedRows = statement.executeUpdate();
	        if (affectedRows == 0) {
	            throw new SQLException("Inserting entity failed, no rows affected.");
	        }

	        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                Object id = generatedKeys.getObject("id");
	               logger.debug("generated id: " + id);
	                return selectById((Integer) id, connection);
	            } else {
	                throw new SQLException("Inserting entity failed, no ID obtained.");
	            }
	        }
	    } catch (SQLException e) {
	        logger.error(e.getMessage(), e);
	        throw new DaoException(e);
	    }
	}
	
	@Override
	public Genre update(Genre entity, Connection connection) {
		String query = "update genres set name = ? where id = ?";

		try (PreparedStatement statement = connection.prepareStatement(query)) {
        	int index = 0;
        	statement.setString(++index, entity.getName());
        	statement.setInt(++index, entity.getId());

	        statement.executeUpdate();
	        return selectById(entity.getId(), connection);
	    } catch (SQLException e) {
	        logger.error(e.getMessage(), e);
	        throw new DaoException(e);
	    }
	}

	@Override
	public Genre selectById(Integer id, Connection connection) {
		Criteria criteria = new Criteria("id", "=", id);
		List<Genre> entities = selectByCriteria(criteria, connection);
		
		if (entities.isEmpty()) {
			return null;
		}
		return entities.get(0);
	}

	@Override
	public List<Genre> selectByCriteria(Collection<Criteria> criterias, Connection connection) {
		StringBuilder query = new StringBuilder("select * from ")
				.append(TABLE_NAME)
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
            List<Genre> entities = new ArrayList<>();
            while (resultSet.next()) {
                Genre entity = getOnMapEntity().apply(resultSet);
                entities.add(entity);
            }
            
            return entities;
        } catch (SQLException e) {
        	logger.error(e.getMessage(), e);
        	throw new DaoException(e);
        }
	}

	@Override
	public boolean delete(Integer id, Connection connection) {
		String query = "delete from " + TABLE_NAME + " where id = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, id);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
        	logger.error(e.getMessage(), e);
        	throw new DaoException(e);
        }
	}
}
