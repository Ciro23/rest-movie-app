package it.tino.postgres;

import java.sql.SQLException;
import java.util.List;
import java.util.function.Supplier;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.cdimascio.dotenv.Dotenv;
import it.tino.postgres.database.DaoManager;
import it.tino.postgres.database.JdbcManager;
import it.tino.postgres.genre.Genre;
import it.tino.postgres.genre.GenreDataSource;
import it.tino.postgres.genre.GenreRepository;
import it.tino.postgres.genre.GenreRepositoryTester;
import it.tino.postgres.movie.Movie;
import it.tino.postgres.movie.MovieDataSource;
import it.tino.postgres.movie.MovieRepository;
import it.tino.postgres.movie.MovieRepositoryTester;
import it.tino.postgres.person.Person;
import it.tino.postgres.person.PersonDataSource;
import it.tino.postgres.person.PersonRepository;
import it.tino.postgres.person.PersonRepositoryTester;
import it.tino.postgres.review.Review;
import it.tino.postgres.review.ReviewDataSource;
import it.tino.postgres.review.ReviewRepository;
import it.tino.postgres.review.ReviewRepositoryTester;
import it.tino.postgres.user.User;
import it.tino.postgres.user.UserDataSource;
import it.tino.postgres.user.UserRepository;
import it.tino.postgres.user.UserRepositoryTester;

public class App {
	protected static final Logger logger = LogManager.getLogger();

	public static void main(String[] args) {
		Dotenv dotEnv = Dotenv.load();
		AppProperties appProperties = AppProperties.getInstance(dotEnv);
		JdbcManager database = new JdbcManager(
				appProperties.getDatabaseUrl(),
				appProperties.getDatabaseUsername(),
				appProperties.getDatabasePassword()
		);
		
		Supplier<DaoManager> onGetDaoManager = () -> {
			try {
				return new DaoManager(database);
			} catch (SQLException e) {
				throw new RuntimeException();
			}
		};
		
		System.out.println("##########################");
		System.out.println("######### MOVIES #########");
		System.out.println("##########################");
		MovieRepository movieRepository = new MovieDataSource(onGetDaoManager);
		MovieRepositoryTester movieExample = new MovieRepositoryTester(movieRepository);
		
		List<Movie> allMovies = movieExample.getAll();
		allMovies = movieExample.create();
		allMovies = movieExample.update(movieExample.getLatestMovie(allMovies));
		allMovies = movieExample.delete(movieExample.getLatestMovie(allMovies).getId());
		//movieDataSource.createFirstMovie();
		// -----

		System.out.println("\n##########################");
		System.out.println("######### PEOPLE #########");
		System.out.println("##########################");
		PersonRepository personRepository = new PersonDataSource(onGetDaoManager);
		PersonRepositoryTester personExample = new PersonRepositoryTester(personRepository);

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
		UserRepository userRepository = new UserDataSource(onGetDaoManager);
		UserRepositoryTester userExample = new UserRepositoryTester(userRepository);

		List<User> allUsers = userExample.getAll();
		allUsers = userExample.create();
		allUsers = userExample.update(allUsers.getLast());
		allUsers = userExample.delete(allUsers.getLast().getId());
		// -----

		System.out.println("\n##########################");
		System.out.println("######### REVIEWS ########");
		System.out.println("##########################");
		ReviewRepository reviewRepository = new ReviewDataSource(onGetDaoManager);
		ReviewRepositoryTester reviewExample = new ReviewRepositoryTester(reviewRepository);

		List<Review> allReviews = reviewExample.getAll();
		allReviews = reviewExample.create();
		allReviews = reviewExample.update(allReviews.getLast());
		allReviews = reviewExample.delete(allReviews.getLast().getId());
		// -----

		System.out.println("\n##########################");
		System.out.println("######### GENRES #########");
		System.out.println("##########################");
		GenreRepository genreRepository = new GenreDataSource(onGetDaoManager);
		GenreRepositoryTester genresExample = new GenreRepositoryTester(genreRepository);

		List<Genre> allGenres = genresExample.getAll();
		allGenres = genresExample.getGenresOfMovie();
		allGenres = genresExample.create();
		allGenres = genresExample.update(genresExample.getLatestGenre(allGenres));
		allGenres = genresExample.delete(genresExample.getLatestGenre(allGenres).getId());
		// -----
	}
}
