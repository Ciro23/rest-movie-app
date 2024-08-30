package it.tino.postgres.movie;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import it.tino.postgres.movie.database.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.tino.postgres.MovieAppException;
import it.tino.postgres.database.ConnectionManager;
import it.tino.postgres.database.Criteria;

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
			
			MovieDb movieDb = domainToDb(movie);
			MovieDb insertedMovieDb = MovieDao.insert(movieDb, connection);
			movie.setId(insertedMovieDb.getId());
			
			List<MovieGenre> genres = getGenres(movie);
			MovieGenreDao.insert(genres, connection);
			
			List<MovieDirector> directors = getDirectors(movie);
			MovieDirectorDao.insert(directors, connection);
			
			List<MovieActor> actors = getActors(movie);
			MovieActorDao.insert(actors, connection);
			
			connectionManager.commitTransaction(connection);
			return dbToDomain(insertedMovieDb, connection);
    	} catch (MovieAppException e) {
    		logger.error(e.getMessage(), e);
			if (connection != null) {
				connectionManager.rollbackTransaction(connection);
			}
    		throw new MovieAppException(e);
		} finally {
			if (connection != null) {
				connectionManager.endTransaction(connection);
				connectionManager.close(connection);
			}
		}
    }
    
    public Movie update(Movie movie) {
    	Connection connection = null;
    	try {
    		connection = connectionManager.connect();
			connectionManager.beginTransaction(connection);
			
			MovieDb movieDb = domainToDb(movie);
			MovieDb insertedMovieDb = MovieDao.update(movieDb, connection);
			movie.setId(insertedMovieDb.getId());
			
			List<MovieGenre> genres = getGenres(movie);
			MovieGenreDao.deleteByMovie(movie.getId(), connection);
			MovieGenreDao.insert(genres, connection);
			
			List<MovieDirector> directors = getDirectors(movie);
			MovieDirectorDao.deleteByMovie(movie.getId(), connection);
			MovieDirectorDao.insert(directors, connection);
			
			List<MovieActor> actors = getActors(movie);
			MovieActorDao.deleteByMovie(movie.getId(), connection);
			MovieActorDao.insert(actors, connection);
			
			connectionManager.commitTransaction(connection);
			return dbToDomain(insertedMovieDb, connection);
    	} catch (MovieAppException e) {
    		logger.error(e.getMessage(), e);
			if (connection != null) {
				connectionManager.rollbackTransaction(connection);
			}
    		throw new MovieAppException(e);
		} finally {
			if (connection != null) {
				connectionManager.endTransaction(connection);
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
    	} catch (MovieAppException e) {
    		logger.error(e.getMessage(), e);
    		throw new MovieAppException(e);
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
			var movieJdbc = MovieDao.selectById(id, connection);
			if (movieJdbc == null) {
				return null;
			}

			return dbToDomain(movieJdbc, connection);
    	} catch (MovieAppException e) {
    		logger.error(e.getMessage(), e);
			throw new MovieAppException(e);
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
    	} catch (MovieAppException e) {
    		logger.error(e.getMessage(), e);
    		throw new MovieAppException(e);
		} finally {
			if (connection != null) {
				connectionManager.close(connection);
			}
		}
	}
	
	public List<Movie> selectByCriteria(Criteria criteria) {
		return selectByCriteria(Collections.singleton(criteria));
	}

	public List<Movie> selectByDirectorId(int directorId) {
		Connection connection = null;
		try {
			connection = connectionManager.connect();
			List<MovieView> movieDirectors = new ArrayList<>(
					VMovieDirectorDao.selectByCriteria(
							new Criteria("director_id", "=", directorId),
							connection
					)
			);
			List<MovieDb> movies = mapViewToDbEntity(movieDirectors);
			return dbToDomain(movies, connection);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new MovieAppException(e);
		} finally {
			if (connection != null) {
				connectionManager.close(connection);
			}
		}
	}

	public List<Movie> selectByActorId(int actorId) {
		Connection connection = null;
		try {
			connection = connectionManager.connect();
			List<MovieView> movieActors = new ArrayList<>(
					VMovieActorDao.selectByCriteria(
							new Criteria("actor_id", "=", actorId),
							connection
					)
			);
			List<MovieDb> movies = mapViewToDbEntity(movieActors);
			return dbToDomain(movies, connection);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new MovieAppException(e);
        } finally {
			if (connection != null) {
				connectionManager.close(connection);
			}
		}
	}

	public List<Movie> selectByGenreId(int genreId) {
		Connection connection = null;
		try {
			connection = connectionManager.connect();
			List<MovieView> movieGenres = new ArrayList<>(
					VMovieGenreDao.selectByCriteria(
							new Criteria("genre_id", "=", genreId),
							connection
					)
			);
			List<MovieDb> movies = mapViewToDbEntity(movieGenres);
			return dbToDomain(movies, connection);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new MovieAppException(e);
		} finally {
			if (connection != null) {
				connectionManager.close(connection);
			}
		}
	}

	public boolean delete(int id) {
		Connection connection = null;
		try {
			connection = connectionManager.connect();
    		return MovieDao.delete(id, connection);
    	} catch (MovieAppException e) {
    		logger.error(e.getMessage(), e);
			return false;
		} finally {
			if (connection != null) {
				connectionManager.close(connection);
			}
		}
	}
	
	private List<Movie> dbToDomain(Collection<MovieDb> moviesJdbc, Connection connection) {
		List<Integer> movieIds = moviesJdbc
				.stream()
				.map(MovieDb::getId)
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
		for (MovieDb movieDb : moviesJdbc) {
			List<MovieGenre> genres = movieGenres
					.stream()
					.filter(mg -> mg.getMovieId() == movieDb.getId())
					.map(mg -> {
						MovieGenre movieGenre = new MovieGenre();
						movieGenre.setMovieId(mg.getMovieId());
						movieGenre.setGenreId(mg.getGenreId());
						return movieGenre;
					})
					.toList();
			List<MovieDirector> directors = movieDirectors
					.stream()
					.filter(md -> md.getMovieId() == movieDb.getId())
					.map(md -> {
						MovieDirector directedMovie = new MovieDirector();
						directedMovie.setMovieId(md.getMovieId());
						directedMovie.setDirectorId(md.getPersonId());
						return directedMovie;
					})
					.toList();
			List<MovieActor> actors = movieActors
					.stream()
					.filter(ma -> ma.getMovieId() == movieDb.getId())
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
			movie.setId(movieDb.getId());
			movie.setTitle(movieDb.getTitle());
			movie.setReleaseDate(movieDb.getReleaseDate().toLocalDate());
			movie.setBudget(movieDb.getBudget());
			movie.setBoxOffice(movieDb.getBoxOffice());
			movie.setRuntime(movieDb.getRuntime());
			movie.setOverview(movieDb.getOverview());
			movie.setGenres(genres);
			movie.setDirectors(directors);
			movie.setActors(actors);
			
			movies.add(movie);
		}
		
		return movies;
	}
	
	private Movie dbToDomain(MovieDb movieDb, Connection connection) {
		var movies = dbToDomain(Collections.singleton(movieDb), connection);
		if (movies.isEmpty()) {
			return null;
		}
		return movies.getFirst();
	}
	
	private MovieDb domainToDb(Movie movie) {
		MovieDb movieDb = new MovieDb();
		movieDb.setId(movie.getId());
		movieDb.setTitle(movie.getTitle());
		movieDb.setReleaseDate(Date.valueOf(movie.getReleaseDate()));
		movieDb.setBudget(movie.getBudget());
		movieDb.setBoxOffice(movie.getBoxOffice());
		movieDb.setRuntime(movie.getRuntime());
		movieDb.setOverview(movie.getOverview());
		
		return movieDb;
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

	private List<MovieDb> mapViewToDbEntity(Collection<MovieView> movieViews) {
		return movieViews
				.stream()
				.map(m -> {
					MovieDb movie = new MovieDb();
					movie.setId(m.getMovieId());
					movie.setTitle(m.getTitle());
					movie.setReleaseDate(m.getReleaseDate());
					movie.setBudget(m.getBudget());
					movie.setBoxOffice(m.getBoxOffice());
					movie.setRuntime(m.getRuntime());
					movie.setOverview(m.getOverview());
					return movie;
				})
				.toList();
	}
}
