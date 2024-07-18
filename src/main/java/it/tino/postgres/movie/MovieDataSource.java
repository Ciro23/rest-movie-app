package it.tino.postgres.movie;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.tino.postgres.database.DatabaseTable;

public class MovieDataSource implements MovieRepository {
    
	protected static final Logger logger = LogManager.getLogger();
	
    private final MovieDao movieDao;
    private final DatabaseTable<Movie> table;

    public MovieDataSource(MovieDao movieDao, DatabaseTable<Movie> table) {
        this.movieDao = movieDao;
		this.table = table;
    }
    
    @Override
    public Movie save(Movie movie) {
    	if (movie.getId() == 0) {
    		 return movieDao.insert(movie);
    	}
    	return movieDao.update(movie);
    }
    
    @Override
    public List<Movie> findAll() {
       return table.select("select * from movies", null, (resultSet) -> {
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
               throw new RuntimeException(e);
           }
       });
    }

	@Override
	public boolean delete(Integer id) {
		return movieDao.delete(id);
	}
}
