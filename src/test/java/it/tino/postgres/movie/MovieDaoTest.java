package it.tino.postgres.movie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.tino.postgres.database.Criteria;
import it.tino.postgres.database.JdbcManager;
import it.tino.postgres.database.H2TestUtil;

public class MovieDaoTest {

	private MovieDao movieDao;
	
	// Default attributes for new Movie instances.
	private String title = "New movie";
	private Date releaseDate = Date.valueOf("2024-7-19");
	private int budget = 150;
	private int boxOffice = 300;
	private int runtime = 90;
	private String overview = "A newly created movie";

	@BeforeEach
	void setUp() {
		H2TestUtil.createTables();

		JdbcManager database = H2TestUtil.getDatabase();
		movieDao = new MovieDao(database);
	}
	
	@AfterEach
	void tearDown() {
		H2TestUtil.dropTables();
	}
	
	@Test
	void insertTest() {
		Movie movie = createMovieObject();
		Movie insertedMovie = movieDao.insert(movie);
		
		assertNotEquals(0, insertedMovie.getId());
		assertEquals(title, insertedMovie.getTitle());
		assertEquals(releaseDate, insertedMovie.getReleaseDate());
		assertEquals(budget, insertedMovie.getBudget());
		assertEquals(boxOffice, insertedMovie.getBoxOffice());
		assertEquals(runtime, insertedMovie.getRuntime());
		assertEquals(overview, insertedMovie.getOverview());
	}
	
	@Test
	void updateTest() {
		Movie movie = createMovieObject();
		Movie insertedMovie = movieDao.insert(movie);
		
		String title = "New movie (updated)";
		Date releaseDate = Date.valueOf("2024-7-20");
		int budget = 300;
		int boxOffice = 450;
		int runtime = 120;
		String overview = "A newly created movie (updated)";

		insertedMovie.setTitle(title);
		insertedMovie.setReleaseDate(releaseDate);
		insertedMovie.setBudget(budget);
		insertedMovie.setBoxOffice(boxOffice);
		insertedMovie.setRuntime(runtime);
		insertedMovie.setOverview(overview);
		
		Movie updatedMovie = movieDao.update(insertedMovie);
		assertEquals(insertedMovie.getId(), updatedMovie.getId());
		assertEquals(title, updatedMovie.getTitle());
		assertEquals(releaseDate, updatedMovie.getReleaseDate());
		assertEquals(budget, updatedMovie.getBudget());
		assertEquals(boxOffice, updatedMovie.getBoxOffice());
		assertEquals(runtime, updatedMovie.getRuntime());
		assertEquals(overview, updatedMovie.getOverview());
	}
	
	@Test
	void selectByIdTest() {
		Movie movie = movieDao.selectById(1);
		assertEquals(1, movie.getId());
		assertEquals("Pulp Fiction", movie.getTitle());
		assertEquals(Date.valueOf("1994-10-14"), movie.getReleaseDate());
		assertEquals(8000000, movie.getBudget());
		assertEquals(213928762, movie.getBoxOffice());
		assertEquals(154, movie.getRuntime());
		assertEquals("Jules Winnfield (Samuel L. Jackson)"
				+ " and Vincent Vega (John Travolta) are two"
				+ " hitmen who are out to retrieve a suitcase"
				+ " stolen from their employer, mob boss Marsellus"
				+ " Wallace (Ving Rhames). Wallace has also asked"
				+ " Vincent to take his wife Mia (Uma Thurman) out"
				+ " a few days later when Wallace himself will be"
				+ " out of town. Butch Coolidge (Bruce Willis) is"
				+ " an aging boxer who is paid by Wallace to lose"
				+ " his fight. The lives of these seemingly unrelated"
				+ " people are woven together comprising of a series"
				+ " of funny, bizarre and uncalled-for incidents.", movie.getOverview());
	}
	
	@Test
	void selectByUnexistentIdTest() {
		Movie movie = movieDao.selectById(100);
		assertNull(movie);
	}
	
	@Test
	void selectByCriteriaTest() {
		Criteria criteria = new Criteria("title", "=", "Pulp Fiction");
		Movie movie = movieDao.selectByCriteria(criteria).get(0);
		assertEquals("Pulp Fiction", movie.getTitle());
	}
	
	@Test
	void deleteTest() {
		boolean result = movieDao.delete(1);
		assertTrue(result);
	}
	
	@Test
	void deleteUnexistentMovieTest() {
		boolean result = movieDao.delete(100);
		assertFalse(result);
	}
	
	private Movie createMovieObject() {
		Movie movie = new Movie();
		movie.setTitle(title);
		movie.setReleaseDate(releaseDate);
		movie.setBudget(budget);
		movie.setBoxOffice(boxOffice);
		movie.setRuntime(runtime);
		movie.setOverview(overview);
		
		return movie;
	}
}
