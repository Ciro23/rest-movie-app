package it.tino.postgres.movie;

import org.junit.jupiter.api.BeforeEach;

import it.tino.postgres.database.H2TestUtil;
import it.tino.postgres.database.JdbcManager;
import it.tino.postgres.genre.GenreDao;
import it.tino.postgres.person.PersonDao;

public class MovieServiceTest {

	private JdbcManager database;
	private MovieDao movieDao;
	private PersonDao personDao;
	private GenreDao genreDao;
	
	@BeforeEach
	void setUp() {
		H2TestUtil.createTables();

		database = H2TestUtil.getDatabase();
		movieDao = new MovieDao(database);
		personDao = new PersonDao(database);
		genreDao = new GenreDao(database);
	}
	
//	@Test
//	void ciaoTest() throws SQLException {
//		MovieService movieService = new MovieService(database, movieDao, personDao, genreDao);
//		movieService.ciao();
//		
//		Movie movie = movieDao.selectById(3);
//		List<MovieActor> actors = personDao.selectActorsByMovieId(3);
//		List<Genre> genres = genreDao.selectByMovieId(3);
//		
//		assertNotNull(movie);
//		assertEquals(1, actors.size());
//		assertEquals(1, genres.size());
//	}
}
