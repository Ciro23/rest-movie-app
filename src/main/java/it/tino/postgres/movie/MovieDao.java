package it.tino.postgres.movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.BiConsumer;
import java.util.function.Function;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.tino.postgres.DaoException;
import it.tino.postgres.MovieAppException;
import it.tino.postgres.database.Dao;
import it.tino.postgres.database.SimpleDao;

public class MovieDao extends SimpleDao<Movie, Integer> implements Dao<Movie, Integer>  {
	
	protected static final Logger logger = LogManager.getLogger();
	
	public MovieDao(Connection connection) {
		super(connection);
	}
	
	@Override
	protected String getTableName() {
		return "movies";
	}

	@Override
	protected Function<ResultSet, Movie> getOnMapEntity() {
		return (resultSet) -> {
            try {
            	Movie movie = new Movie();
         	    movie.setId(resultSet.getInt("id"));
         	    movie.setTitle(resultSet.getString("title"));
         	    movie.setReleaseDate(resultSet.getDate("release_date"));
         	    movie.setBudget(resultSet.getInt("budget"));
         	    movie.setBoxOffice(resultSet.getInt("box_office"));
         	    movie.setRuntime(resultSet.getInt("runtime"));
         	    movie.setOverview(resultSet.getString("overview"));
                
         	    return movie;
            } catch (SQLException e) {
            	logger.error(e);
                throw new MovieAppException(e);
            }
        };
	}

	@Override
	public Movie insert(Movie movie)  {
		String query = "insert into movies (title, release_date, budget, box_office, runtime, overview)"
				+ " values (?, ?, ?, ?, ?, ?)";
		
		BiConsumer<Movie, PreparedStatement> onSetParameters = (entity, stmt) -> {
            int index = 0;
            try {
                stmt.setString(++index, entity.getTitle());
                stmt.setDate(++index, entity.getReleaseDate());
                stmt.setInt(++index, entity.getBudget());
                stmt.setInt(++index, entity.getBoxOffice());
                stmt.setInt(++index, entity.getRuntime());
                stmt.setString(++index, entity.getOverview());
            } catch (SQLException e) {
            	logger.error(e);
            	throw new DaoException(e);
            }
        };
		
		return insert(movie, query, onSetParameters);
	}

	@Override
	public Movie update(Movie movie) {
		String query = "update movies set title = ?, release_date = ?, budget = ?, box_office = ?,"
                + " runtime = ?, overview = ? where id = ?";
		
		BiConsumer<Movie, PreparedStatement> onSetParameters = (entity, stmt) -> {
            int index = 0;
            try {
                stmt.setString(++index, entity.getTitle());
                stmt.setDate(++index, entity.getReleaseDate());
                stmt.setInt(++index, entity.getBudget());
                stmt.setInt(++index, entity.getBoxOffice());
                stmt.setInt(++index, entity.getRuntime());
                stmt.setString(++index, entity.getOverview());
                stmt.setInt(++index, entity.getId());
            } catch (SQLException e) {
            	logger.error(e);
            	throw new DaoException(e);
            }
        };
		
		return update(movie, query, onSetParameters);
	}
}
