package it.tino.postgres;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.tino.postgres.database.ConnectionManager;
import it.tino.postgres.genre.Genre;
import it.tino.postgres.genre.GenreManager;
import it.tino.postgres.genre.GenreManagerTester;
import it.tino.postgres.movie.Movie;
import it.tino.postgres.movie.MovieManager;
import it.tino.postgres.movie.MovieManagerTester;
import it.tino.postgres.person.Person;
import it.tino.postgres.person.PersonManager;
import it.tino.postgres.person.PersonManagerTester;
import it.tino.postgres.review.Review;
import it.tino.postgres.review.ReviewManager;
import it.tino.postgres.review.ReviewManagerTester;
import it.tino.postgres.user.User;
import it.tino.postgres.user.UserManager;
import it.tino.postgres.user.UserManagerTester;

public class Main {
	protected static final Logger logger = LogManager.getLogger();

	public static void main(String[] args) {
		ConnectionManager connectionManager = ConnectionManager.getInstance();
		
		System.out.println("##########################");
		System.out.println("######### MOVIES #########");
		System.out.println("##########################");
		MovieManager movieManager = new MovieManager(connectionManager);
		MovieManagerTester movieExample = new MovieManagerTester(movieManager);
		
		List<Movie> allMovies = movieExample.create();
		allMovies = movieExample.update(movieExample.getLatestMovie(allMovies));
		movieExample.delete(movieExample.getLatestMovie(allMovies).getId());
		// -----

		System.out.println("\n##########################");
		System.out.println("######### PEOPLE #########");
		System.out.println("##########################");
		PersonManager personManager = new PersonManager(connectionManager);
		PersonManagerTester personExample = new PersonManagerTester(personManager);
		
		List<Person> allPeople = personExample.create();
		allPeople = personExample.update(personExample.getLatestPerson(allPeople));
		personExample.delete(personExample.getLatestPerson(allPeople).getId());
		// -----

		System.out.println("\n##########################");
		System.out.println("########## USER ##########");
		System.out.println("##########################");
		UserManager userManager = new UserManager(connectionManager);
		UserManagerTester userExample = new UserManagerTester(userManager);

		List<User> allUsers = userExample.create();
		allUsers = userExample.update(allUsers.getLast());
		userExample.delete(allUsers.getLast().getId());
		// -----

		System.out.println("\n##########################");
		System.out.println("######### REVIEWS ########");
		System.out.println("##########################");
		ReviewManager reviewManager = new ReviewManager(connectionManager);
		ReviewManagerTester reviewExample = new ReviewManagerTester(reviewManager);

		List<Review> allReviews = reviewExample.create();
		allReviews = reviewExample.update(allReviews.getLast());
		reviewExample.delete(allReviews.getLast().getId());
		// -----

		System.out.println("\n##########################");
		System.out.println("######### GENRES #########");
		System.out.println("##########################");
		GenreManager genreManager = new GenreManager(connectionManager);
		GenreManagerTester genresExample = new GenreManagerTester(genreManager);

		List<Genre> allGenres = genresExample.create();
		allGenres = genresExample.update(genresExample.getLatestGenre(allGenres));
		genresExample.delete(genresExample.getLatestGenre(allGenres).getId());
		// -----
	}
}
