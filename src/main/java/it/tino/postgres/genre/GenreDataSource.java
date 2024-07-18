package it.tino.postgres.genre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.tino.postgres.database.DatabaseTable;

public class GenreDataSource implements GenreRepository {

	protected static final Logger logger = LogManager.getLogger();
	
	private final GenreDao genreDao;
    private final DatabaseTable<Genre> database;
    private final Function<ResultSet, Genre> onMapEntity;
    
    public GenreDataSource(GenreDao genreDao, DatabaseTable<Genre> database) {
    	this.genreDao = genreDao;
        this.database = database;
        onMapEntity = (resultSet) -> {
            try {
            	Genre genre = new Genre();
            	genre.setId(resultSet.getInt("id"));
            	genre.setName(resultSet.getString("name"));
            	
            	return genre;
            } catch (SQLException e) {
            	logger.error(e);
                throw new RuntimeException(e);
            }
        };
    }
    
    @Override
	public Genre save(Genre entity) {
		if (entity.getId() == 0) {
			return genreDao.insert(entity);
		}
		return genreDao.update(entity);
	}

    @Override
    public List<Genre> findAll() {
        return database.select("select * from genres order by id", null, onMapEntity);
    }
    
    @Override
    public List<Genre> findByMovieId(int movieId) {
        String sql = "SELECT g.id, g.name FROM genres g"
                + " JOIN movies_genres mg ON g.id = mg.genre_id"
                + " WHERE mg.movie_id = ?";
        
        Consumer<PreparedStatement> onSetParameters = (stmt) -> {
            try {
                stmt.setInt(1, movieId);
            } catch (SQLException e) {
            	logger.error(e);
                throw new RuntimeException(e);
            }
        };
        
        return database.select(sql, onSetParameters, onMapEntity);
    }

    @Override
    public boolean delete(Integer id) {
        return genreDao.delete(id);
    }
}
