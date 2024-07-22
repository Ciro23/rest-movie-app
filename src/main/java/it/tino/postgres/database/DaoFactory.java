package it.tino.postgres.database;

import java.sql.Connection;

import it.tino.postgres.genre.GenreDao;
import it.tino.postgres.movie.MovieDao;
import it.tino.postgres.person.PersonDao;
import it.tino.postgres.review.ReviewDao;
import it.tino.postgres.user.UserDao;

public class DaoFactory {

	protected final Connection connection;
	
	private MovieDao movieDao;
	private PersonDao personDao;
	private GenreDao genreDao;
	private UserDao userDao;
	private ReviewDao reviewDao;
	
	public DaoFactory(Connection connection) {
		this.connection = connection;
	}

	public MovieDao getMovieDao() {
		if (movieDao == null) {
			movieDao = new MovieDao(connection);
		}
		return movieDao;
	}
	
	public PersonDao getPersonDao() {
		if (personDao == null) {
			personDao = new PersonDao(connection);
		}
		return personDao;
	}
	
	public GenreDao getGenreDao() {
		if (genreDao == null) {
			genreDao = new GenreDao(connection);
		}
		return genreDao;
	}
	
	public UserDao getUserDao() {
		if (userDao == null) {
			userDao = new UserDao(connection);
		}
		return userDao;
	}
	
	public ReviewDao getReviewDao() {
		if (reviewDao == null) {
			reviewDao = new ReviewDao(connection);
		}
		return reviewDao;
	}
}
