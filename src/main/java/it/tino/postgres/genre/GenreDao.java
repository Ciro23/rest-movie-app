package it.tino.postgres.genre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import it.tino.postgres.MovieAppException;
import it.tino.postgres.database.Dao;
import it.tino.postgres.database.SimpleDao;

public class GenreDao extends SimpleDao<Genre, Integer> implements Dao<Genre, Integer> {

	public GenreDao(Connection connection) {
		super(connection);
	}
	
	@Override
	protected String getTableName() {
		return "genres";
	}

	@Override
	protected Function<ResultSet, Genre> getOnMapEntity() {
		return (resultSet) -> {
            try {
            	Genre genre = new Genre();
            	genre.setId(resultSet.getInt("id"));
            	genre.setName(resultSet.getString("name"));
            	
            	return genre;
            } catch (SQLException e) {
            	logger.error(e);
                throw new MovieAppException(e);
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
            } catch (SQLException e) {
            	logger.error(e);
            	throw new MovieAppException(e);
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
            } catch (SQLException e) {
            	logger.error(e);
            	throw new MovieAppException(e);
            }
        };
		
		return update(genre, query, onSetParameters);
	}
	
	public List<Genre> selectByMovieId(int movieId) {
		String sql = "SELECT g.id, g.name FROM genres g"
                + " JOIN movies_genres mg ON g.id = mg.genre_id"
                + " WHERE mg.movie_id = ?";
        
        Consumer<PreparedStatement> onSetParameters = (stmt) -> {
            try {
                stmt.setInt(1, movieId);
            } catch (SQLException e) {
            	logger.error(e);
                throw new MovieAppException(e);
            }
        };
        
        return select(sql, onSetParameters, getOnMapEntity());
	}
}
