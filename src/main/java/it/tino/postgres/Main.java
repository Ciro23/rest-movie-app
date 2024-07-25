package it.tino.postgres;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.tino.postgres.database.ConnectionManager;
import it.tino.postgres.genre.Genre;
import it.tino.postgres.genre.GenreDao;
import it.tino.postgres.genre.GenreManager;
import it.tino.postgres.genre.GenreManagerTester;
import it.tino.postgres.movie.Movie;
import it.tino.postgres.movie.MovieDao;
import it.tino.postgres.movie.MovieManager;
import it.tino.postgres.movie.MovieManagerTester;
import it.tino.postgres.person.Person;
import it.tino.postgres.person.PersonDao;
import it.tino.postgres.person.PersonManager;
import it.tino.postgres.person.PersonManagerTester;
import it.tino.postgres.review.Review;
import it.tino.postgres.review.ReviewDao;
import it.tino.postgres.review.ReviewManager;
import it.tino.postgres.review.ReviewManagerTester;
import it.tino.postgres.user.User;
import it.tino.postgres.user.UserDao;
import it.tino.postgres.user.UserManager;
import it.tino.postgres.user.UserManagerTester;

public class Main {
	protected static final Logger logger = LogManager.getLogger();

	public static void main(String[] args) {
		ConnectionManager connectionManager = ConnectionManager.getInstance();
		
		System.out.println("##########################");
		System.out.println("######### MOVIES #########");
		System.out.println("##########################");
		MovieDao movieDao = new MovieDao();
		MovieManager movieManager = new MovieManager(connectionManager, movieDao);
		MovieManagerTester movieExample = new MovieManagerTester(movieManager);
		
		List<Movie> allMovies = movieExample.getAll();
		allMovies = movieExample.create();
		allMovies = movieExample.update(movieExample.getLatestMovie(allMovies));
		allMovies = movieExample.delete(movieExample.getLatestMovie(allMovies).getId());
		//movieDataSource.createFirstMovie();
		// -----

		System.out.println("\n##########################");
		System.out.println("######### PEOPLE #########");
		System.out.println("##########################");
		PersonDao personDao = new PersonDao();
		PersonManager personManager = new PersonManager(connectionManager, personDao);
		PersonManagerTester personExample = new PersonManagerTester(personManager);

		List<Person> allPeople = personExample.getAll();
//		allPeople = personExample.getDirectorsOfMovie();
//		allPeople = personExample.getActorsOfMovie();
		allPeople = personExample.create();
		allPeople = personExample.update(personExample.getLatestPerson(allPeople));
		allPeople = personExample.delete(personExample.getLatestPerson(allPeople).getId());
		// -----

		System.out.println("\n##########################");
		System.out.println("########## USER ##########");
		System.out.println("##########################");
		UserDao userDao = new UserDao();
		UserManager userManager = new UserManager(connectionManager, userDao);
		UserManagerTester userExample = new UserManagerTester(userManager);

		List<User> allUsers = userExample.getAll();
		allUsers = userExample.create();
		allUsers = userExample.update(allUsers.getLast());
		allUsers = userExample.delete(allUsers.getLast().getId());
		// -----

		System.out.println("\n##########################");
		System.out.println("######### REVIEWS ########");
		System.out.println("##########################");
		ReviewDao reviewDao = new ReviewDao();
		ReviewManager reviewManager = new ReviewManager(connectionManager, reviewDao);
		ReviewManagerTester reviewExample = new ReviewManagerTester(reviewManager);

		List<Review> allReviews = reviewExample.getAll();
		allReviews = reviewExample.create();
		allReviews = reviewExample.update(allReviews.getLast());
		allReviews = reviewExample.delete(allReviews.getLast().getId());
		// -----

		System.out.println("\n##########################");
		System.out.println("######### GENRES #########");
		System.out.println("##########################");
		GenreDao genreDao = new GenreDao();
		GenreManager genreManager = new GenreManager(connectionManager, genreDao);
		GenreManagerTester genresExample = new GenreManagerTester(genreManager);

		List<Genre> allGenres = genresExample.getAll();
		//allGenres = genresExample.getGenresOfMovie();
		allGenres = genresExample.create();
		allGenres = genresExample.update(genresExample.getLatestGenre(allGenres));
		allGenres = genresExample.delete(genresExample.getLatestGenre(allGenres).getId());
		// -----
	}
}
