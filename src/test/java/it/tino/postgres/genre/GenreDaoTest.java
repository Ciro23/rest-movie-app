package it.tino.postgres.genre;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.tino.postgres.database.Criteria;
import it.tino.postgres.database.Database;
import it.tino.postgres.database.H2TestUtil;

public class GenreDaoTest {
	
	private GenreDao genreDao;
	
	// Default attributes for new Genre instances.
	private String name = "New genre";
	
	@BeforeEach
	void setUp() {
		H2TestUtil.createTables();

		Database database = H2TestUtil.getDatabase();
		genreDao = new GenreDao(database);
	}
	
	@AfterEach
	void tearDown() {
		H2TestUtil.dropTables();
	}
	
	@Test
	void insertTest() {
		Genre genre = createGenreObject();
		Genre insertedGenre = genreDao.insert(genre);
		
		assertNotEquals(0, insertedGenre.getId());
		assertEquals(name, insertedGenre.getName());
	}
	
	@Test
	void updateTest() {
		Genre genre = createGenreObject();
		Genre insertedGenre = genreDao.insert(genre);
		
		String genrename = "New genre (updated)";
		insertedGenre.setName(genrename);
		
		Genre updatedGenre = genreDao.update(insertedGenre);
		assertEquals(insertedGenre.getId(), updatedGenre.getId());
		assertEquals(genrename, updatedGenre.getName());
	}
	
	@Test
	void selectByIdTest() {
		Genre genre = genreDao.selectById(1);
		assertEquals(1, genre.getId());
		assertEquals("Comedy", genre.getName());
	}
	
	@Test
	void selectByUnexistendIdTest() {
		Genre genre = genreDao.selectById(100);
		assertNull(genre);
	}
	
	@Test
	void selectByCriteriaTest() {
		Criteria criteria = new Criteria("name", "=", "Comedy");
		Genre genre = genreDao.selectByCriteria(criteria).get(0);
		assertEquals("Comedy", genre.getName());
	}
	
	@Test
	void deleteTest() {
		boolean result = genreDao.delete(1);
		assertTrue(result);
	}
	
	@Test
	void deleteUnexistentGenreTest() {
		boolean result = genreDao.delete(100);
		assertFalse(result);
	}
	
	private Genre createGenreObject() {
		Genre genre = new Genre();
		genre.setName(name);
		
		return genre;
	}
}
