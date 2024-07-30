package it.tino.postgres.movie;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.tino.postgres.DaoException;
import it.tino.postgres.database.ConnectionManager;
import it.tino.postgres.database.Criteria;
import it.tino.postgres.movie.database.MovieActorDao;
import it.tino.postgres.movie.database.MovieActorView;
import it.tino.postgres.movie.database.MovieDao;
import it.tino.postgres.movie.database.MovieDirectorDao;
import it.tino.postgres.movie.database.MovieDirectorView;
import it.tino.postgres.movie.database.MovieGenreDao;
import it.tino.postgres.movie.database.MovieGenreView;
import it.tino.postgres.movie.database.MovieJdbc;
import it.tino.postgres.movie.database.VMovieActorDao;
import it.tino.postgres.movie.database.VMovieDirectorDao;
import it.tino.postgres.movie.database.VMovieGenreDao;

public class MovieManager {
    
	protected static final Logger logger = LogManager.getLogger();
	
	private final ConnectionManager connectionManager;
	
    public MovieManager(ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
	}

	public Movie insert(Movie movie) {
    	Connection connection = null;
    	try {
    		connection = connectionManager.connect();
			connectionManager.beginTransaction(connection);
			
			MovieJdbc movieJdbc = domainToDb(movie, connection);
			MovieJdbc insertedMovieJdbc = MovieDao.insert(movieJdbc, connection);
			movie.setId(insertedMovieJdbc.getId());
			
			List<MovieGenre> genres = getGenres(movie);
			MovieGenreDao.deleteByMovie(movie.getId(), connection);
			MovieGenreDao.insert(genres, connection);
			
			List<MovieDirector> directors = getDirectors(movie);
			MovieDirectorDao.deleteByDirector(movie.getId(), connection);
			MovieDirectorDao.insert(directors, connection);
			
			List<MovieActor> actors = getActors(movie);
			MovieActorDao.deleteByActor(movie.getId(), connection);
			MovieActorDao.insert(actors, connection);
			
			connectionManager.commitTransaction(connection);
			connectionManager.endTransaction(connection);
			
			return dbToDomain(insertedMovieJdbc, connection);
    	} catch (DaoException e) {
    		logger.error(e.getMessage(), e);
			return null;
		} finally {
			if (connection != null) {
				connectionManager.close(connection);
			}
		}
    }
    
    public Movie update(Movie movie) {
    	Connection connection = null;
    	try {
    		connection = connectionManager.connect();
			connectionManager.beginTransaction(connection);
			
			MovieJdbc movieJdbc = domainToDb(movie, connection);
			MovieJdbc insertedMovieJdbc = MovieDao.update(movieJdbc, connection);
			movie.setId(insertedMovieJdbc.getId());
			
			List<MovieGenre> genres = getGenres(movie);
			MovieGenreDao.deleteByMovie(movie.getId(), connection);
			MovieGenreDao.insert(genres, connection);
			
			List<MovieDirector> directors = getDirectors(movie);
			MovieDirectorDao.deleteByDirector(movie.getId(), connection);
			MovieDirectorDao.insert(directors, connection);
			
			List<MovieActor> actors = getActors(movie);
			MovieActorDao.deleteByActor(movie.getId(), connection);
			MovieActorDao.insert(actors, connection);
			
			connectionManager.commitTransaction(connection);
			connectionManager.endTransaction(connection);
			
			return dbToDomain(insertedMovieJdbc, connection);
    	} catch (DaoException e) {
    		logger.error(e.getMessage(), e);
			return null;
		} finally {
			if (connection != null) {
				connectionManager.close(connection);
			}
		}
    }
    
    public List<Movie> selectAll() {
    	Connection connection = null;
    	try {
    		connection = connectionManager.connect();
    		var moviesJdbc = MovieDao.selectByCriteria(Collections.emptyList(), connection);
    		return dbToDomain(moviesJdbc, connection);
    	} catch (DaoException e) {
    		logger.error(e.getMessage(), e);
			return Collections.emptyList();
		} finally {
			if (connection != null) {
				connectionManager.close(connection);
			}
		}
    }
    
	public Movie selectById(int id) {
		Connection connection = null;
		try {
			connection = connectionManager.connect();
			var moviesJdbc = MovieDao.selectById(id, connection);
			return dbToDomain(moviesJdbc, connection);
    	} catch (DaoException e) {
    		logger.error(e.getMessage(), e);
			return null;
		} finally {
			if (connection != null) {
				connectionManager.close(connection);
			}
		}
	}
	
	public List<Movie> selectByCriteria(Collection<Criteria> criterias) {
		Connection connection = null;
		try {
			connection = connectionManager.connect();
			var moviesJdbc = MovieDao.selectByCriteria(criterias, connection);
    		return dbToDomain(moviesJdbc, connection);
    	} catch (DaoException e) {
    		logger.error(e.getMessage(), e);
			return Collections.emptyList();
		} finally {
			if (connection != null) {
				connectionManager.close(connection);
			}
		}
	}
	
	public List<Movie> selectByCriteria(Criteria criteria) {
		return selectByCriteria(Collections.singleton(criteria));
	}

	public boolean delete(int id) {
		Connection connection = null;
		try {
			connection = connectionManager.connect();
    		return MovieDao.delete(id, connection);
    	} catch (DaoException e) {
    		logger.error(e.getMessage(), e);
			return false;
		} finally {
			if (connection != null) {
				connectionManager.close(connection);
			}
		}
	}
	
	private List<Movie> dbToDomain(Collection<MovieJdbc> moviesJdbc, Connection connection) {
		List<Integer> movieIds = moviesJdbc
				.stream()
				.map(m -> m.getId())
				.toList();
		
		List<MovieGenreView> movieGenres = VMovieGenreDao.selectByCriteria(
				new Criteria("movie_id", "in", movieIds),
				connection
		);
		List<MovieDirectorView> movieDirectors = VMovieDirectorDao.selectByCriteria(
				new Criteria("movie_id", "in", movieIds),
				connection
		);
		List<MovieActorView> movieActors = VMovieActorDao.selectByCriteria(
				new Criteria("movie_id", "in", movieIds),
				connection
		);
		
		List<Movie> movies = new ArrayList<>();
		for (MovieJdbc movieJdbc : moviesJdbc) {
			List<MovieGenre> genres = movieGenres
					.stream()
					.filter(mg -> mg.getMovieId() == movieJdbc.getId())
					.map(mg -> {
						MovieGenre movieGenre = new MovieGenre();
						movieGenre.setMovieId(mg.getMovieId());
						movieGenre.setGenreId(mg.getGenreId());
						return movieGenre;
					})
					.toList();
			List<MovieDirector> directors = movieDirectors
					.stream()
					.filter(md -> md.getMovieId() == movieJdbc.getId())
					.map(md -> {
						MovieDirector directedMovie = new MovieDirector();
						directedMovie.setMovieId(md.getMovieId());
						directedMovie.setDirectorId(md.getPersonId());
						return directedMovie;
					})
					.toList();
			List<MovieActor> actors = movieActors
					.stream()
					.filter(ma -> ma.getPersonId() == movieJdbc.getId())
					.map(ma -> {
						MovieActor starredMovie = new MovieActor();
						starredMovie.setMovieId(ma.getMovieId());
						starredMovie.setActorId(ma.getPersonId());
						starredMovie.setRoleName(ma.getRoleName());
						starredMovie.setCastOrder(ma.getCastOrder());
						return starredMovie;
					})
					.toList();
			
			Movie movie = new Movie();
			movie.setId(movieJdbc.getId());
			movie.setTitle(movieJdbc.getTitle());
			movie.setReleaseDate(movieJdbc.getReleaseDate());
			movie.setBudget(movieJdbc.getBudget());
			movie.setBoxOffice(movieJdbc.getBoxOffice());
			movie.setRuntime(movieJdbc.getRuntime());
			movie.setOverview(movieJdbc.getOverview());
			movie.setGenres(genres);
			movie.setDirectors(directors);
			movie.setActors(actors);
			
			movies.add(movie);
		}
		
		return movies;
	}
	
	private Movie dbToDomain(MovieJdbc movieJdbc, Connection connection) {
		var movies = dbToDomain(Collections.singleton(movieJdbc), connection);
		if (movies.isEmpty()) {
			return null;
		}
		return movies.getFirst();
	}
	
	private MovieJdbc domainToDb(Movie movie, Connection connection) {
		MovieJdbc movieJdbc = new MovieJdbc();
		movieJdbc.setId(movie.getId());
		movieJdbc.setTitle(movie.getTitle());
		movieJdbc.setReleaseDate(movie.getReleaseDate());
		movieJdbc.setBudget(movie.getBudget());
		movieJdbc.setBoxOffice(movie.getBoxOffice());
		movieJdbc.setRuntime(movie.getRuntime());
		movieJdbc.setOverview(movie.getOverview());
		
		return movieJdbc;
	}
	
	private List<MovieGenre> getGenres(Movie movie) {
		return movie.getGenres()
				.stream()
				.map(genre -> {
					MovieGenre movieGenre = new MovieGenre();
					movieGenre.setMovieId(movie.getId());
					movieGenre.setGenreId(genre.getGenreId());
					return movieGenre;
				})
				.toList();
	}
	
	private List<MovieDirector> getDirectors(Movie movie) {
		return movie.getDirectors()
				.stream()
				.map(directedMovie -> {
					MovieDirector movieDirector = new MovieDirector();
					movieDirector.setMovieId(movie.getId());
					movieDirector.setDirectorId(directedMovie.getDirectorId());
					return movieDirector;
				})
				.toList();
	}
	
	private List<MovieActor> getActors(Movie movie) {
		return movie.getActors()
				.stream()
				.map(starredMovie -> {
					MovieActor movieActor = new MovieActor();
					movieActor.setMovieId(movie.getId());
					movieActor.setActorId(starredMovie.getActorId());
					movieActor.setRoleName(starredMovie.getRoleName());
					movieActor.setCastOrder(starredMovie.getCastOrder());
					return movieActor;
				})
				.toList();
	}
}
