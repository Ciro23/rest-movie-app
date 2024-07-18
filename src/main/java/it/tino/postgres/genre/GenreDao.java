package it.tino.postgres.genre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.BiConsumer;
import java.util.function.Function;

import it.tino.postgres.database.Dao;
import it.tino.postgres.database.Database;
import it.tino.postgres.database.SimpleDao;

public class GenreDao extends SimpleDao<Genre, Integer> implements Dao<Genre, Integer> {

	public GenreDao(Database database) {
		super(database);
	}
	
	@Override
	protected String getTableName() {
		return "genres";
	}

	@Override
	protected Function<ResultSet, Genre> getOnMapEntity() {
		return (resultSet) -> {
            try {
                return new Genre(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                );
            } catch (SQLException e) {
            	logger.error(e);
                throw new RuntimeException(e);
            }
        };
	}

	@Override
	public Genre insert(Genre genre) {
		String query = "insert into genres (name) values (?)";
		
		BiConsumer<Genre, PreparedStatement> onSetParameters = (entity, stmt) -> {
            int index = 0;
            try {
                stmt.setString(++index, entity.getName());
            } catch (Exception e) {
            	logger.error(e);
            	throw new RuntimeException(e);
            }
        };
		
		return insert(genre, query, onSetParameters);
	}

	@Override
	public Genre update(Genre genre) {
		String query = "update genres set name = ? where id = ?";
		
		BiConsumer<Genre, PreparedStatement> onSetParameters = (entity, stmt) -> {
            int index = 0;
            try {
                stmt.setString(++index, entity.getName());
                stmt.setInt(++index, entity.getId());
            } catch (Exception e) {
            	logger.error(e);
            	throw new RuntimeException(e);
            }
        };
		
		return insert(genre, query, onSetParameters);
	}
}
