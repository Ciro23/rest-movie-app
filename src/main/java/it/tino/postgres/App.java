package it.tino.postgres;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.tino.postgres.database.Database;
import it.tino.postgres.database.DatabaseTable;
import it.tino.postgres.genre.Genre;
import it.tino.postgres.genre.GenreDao;
import it.tino.postgres.genre.GenreDataSource;
import it.tino.postgres.genre.GenreRepository;
import it.tino.postgres.genre.GenreRepositoryTester;
import it.tino.postgres.movie.Movie;
import it.tino.postgres.movie.MovieDao;
import it.tino.postgres.movie.MovieDataSource;
import it.tino.postgres.movie.MovieRepository;
import it.tino.postgres.movie.MovieRepositoryTester;
import it.tino.postgres.person.Person;
import it.tino.postgres.person.PersonDao;
import it.tino.postgres.person.PersonDataSource;
import it.tino.postgres.person.PersonRepository;
import it.tino.postgres.person.PersonRepositoryTester;
import it.tino.postgres.review.Review;
import it.tino.postgres.review.ReviewDao;
import it.tino.postgres.review.ReviewDataSource;
import it.tino.postgres.review.ReviewRepository;
import it.tino.postgres.review.ReviewRepositoryTester;
import it.tino.postgres.user.User;
import it.tino.postgres.user.UserDao;
import it.tino.postgres.user.UserDataSource;
import it.tino.postgres.user.UserRepository;
import it.tino.postgres.user.UserRepositoryTester;

public class App {
	protected static final Logger logger = LogManager.getLogger();

	public static void main(String[] args) {
		Database database = new Database("jdbc:postgresql://127.0.0.1:5432/movie_app", "postgres", "root");
		
		System.out.println("##########################");
		System.out.println("######### MOVIES #########");
		System.out.println("##########################");
		MovieDao moviesDao = new MovieDao(database);
		DatabaseTable<Movie> table = new DatabaseTable<Movie>(database);
		MovieRepository movieDataSource = new MovieDataSource(moviesDao, table);
		MovieRepositoryTester movieExample = new MovieRepositoryTester(movieDataSource);

		List<Movie> allMovies = movieExample.getAll();
		allMovies = movieExample.create();
		allMovies = movieExample.update(movieExample.getLatestMovie(allMovies));
		allMovies = movieExample.delete(movieExample.getLatestMovie(allMovies).getId());
		// -----

		System.out.println("\n##########################");
		System.out.println("######### PEOPLE #########");
		System.out.println("##########################");
		PersonDao personDao = new PersonDao(database);
		DatabaseTable<Person> peopleTable = new DatabaseTable<>(database);
		PersonRepository personDataSource = new PersonDataSource(personDao, peopleTable);
		PersonRepositoryTester personExample = new PersonRepositoryTester(personDataSource);

		List<Person> allPeople = personExample.getAll();
		allPeople = personExample.getDirectorsOfMovie();
		allPeople = personExample.getActorsOfMovie();
		allPeople = personExample.create();
		allPeople = personExample.update(personExample.getLatestPerson(allPeople));
		allPeople = personExample.delete(personExample.getLatestPerson(allPeople).getId());
		// -----

		System.out.println("\n##########################");
		System.out.println("########## USER ##########");
		System.out.println("##########################");
		UserDao userDao = new UserDao(database);
		DatabaseTable<User> usersTable = new DatabaseTable<>(database);
		UserRepository userRepository = new UserDataSource(userDao, usersTable);
		UserRepositoryTester userExample = new UserRepositoryTester(userRepository);

		List<User> allUsers = userExample.getAll();
		allUsers = userExample.create();
		allUsers = userExample.update(allUsers.getLast());
		allUsers = userExample.delete(allUsers.getLast().getId());
		// -----

		System.out.println("\n##########################");
		System.out.println("######### REVIEWS ########");
		System.out.println("##########################");
		ReviewDao reviewDao = new ReviewDao(database);
		DatabaseTable<Review> reviewsTable = new DatabaseTable<>(database);
		ReviewRepository reviewRepository = new ReviewDataSource(reviewDao, reviewsTable);
		ReviewRepositoryTester reviewExample = new ReviewRepositoryTester(reviewRepository);

		List<Review> allReviews = reviewExample.getAll();
		allReviews = reviewExample.create();
		allReviews = reviewExample.update(allReviews.getLast());
		allReviews = reviewExample.delete(allReviews.getLast().getId());
		// -----

		System.out.println("\n##########################");
		System.out.println("######### GENRES #########");
		System.out.println("##########################");
		GenreDao genreDao = new GenreDao(database);
		DatabaseTable<Genre> genresTable = new DatabaseTable<>(database);
		GenreRepository genreRepository = new GenreDataSource(genreDao, genresTable);
		GenreRepositoryTester genresExample = new GenreRepositoryTester(genreRepository);

		List<Genre> allGenres = genresExample.getAll();
		allGenres = genresExample.getGenresOfMovie();
		allGenres = genresExample.create();
		allGenres = genresExample.update(genresExample.getLatestGenre(allGenres));
		allGenres = genresExample.delete(genresExample.getLatestGenre(allGenres).getId());
		// -----
	}
}
