package it.tino.postgres.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.tino.postgres.database.Criteria;
import it.tino.postgres.database.JdbcManager;
import it.tino.postgres.database.H2TestUtil;
import it.tino.postgres.person.Person.Gender;

public class PersonDaoTest {

	private PersonDao personDao;
	
	// Default attributes for new Person instances.
	private String name = "New person";
	private Date birth = Date.valueOf("2024-7-19");
	private Gender gender = Gender.MALE;
	
	@BeforeEach
	void setUp() {
		H2TestUtil.createTables();

		JdbcManager database = H2TestUtil.getDatabase();
		personDao = new PersonDao(database);
	}
	
	@AfterEach
	void tearDown() {
		H2TestUtil.dropTables();
	}
	
	@Test
	void insertTest() {
		Person person = createPersonObject();
		Person insertedPerson = personDao.insert(person);
		
		assertNotEquals(0, insertedPerson.getId());
		assertEquals(name, insertedPerson.getName());
		assertEquals(birth, insertedPerson.getBirth());
		assertEquals(gender, insertedPerson.getGender());
	}
	
	@Test
	void updateTest() {
		Person person = createPersonObject();
		Person insertedPerson = personDao.insert(person);
		
		String name = "New person (updated)";
		Date birth = Date.valueOf("2024-7-20");
		Gender gender = Gender.FEMALE;
		
		insertedPerson.setName(name);
		insertedPerson.setBirth(birth);
		insertedPerson.setGender(gender);
		
		Person updatedPerson = personDao.update(insertedPerson);
		assertEquals(insertedPerson.getId(), updatedPerson.getId());
		assertEquals(name, updatedPerson.getName());
		assertEquals(birth, updatedPerson.getBirth());
		assertEquals(gender, updatedPerson.getGender());
	}
	
	@Test
	void selectByIdTest() {
		Person person = personDao.selectById(1);
		assertEquals(1, person.getId());
		assertEquals("John Travolta", person.getName());
		assertEquals(Date.valueOf("1954-2-18"), person.getBirth());
		assertEquals(Gender.MALE, person.getGender());
	}
	
	@Test
	void selectByUnexistentIdTest() {
		Person person = personDao.selectById(100);
		assertNull(person);
	}
	
	@Test
	void selectByCriteriaTest() {
		Criteria criteria = new Criteria("name", "=", "John Travolta");
		Person person = personDao.selectByCriteria(criteria).get(0);
		assertEquals("John Travolta", person.getName());
	}
	
	@Test
	void deleteTest() {
		boolean result = personDao.delete(1);
		assertTrue(result);
	}
	
	@Test
	void deleteUnexistentPersonTest() {
		boolean result = personDao.delete(100);
		assertFalse(result);
	}
	
	@Test
	void insertDirectorOfMovieTest() {
		boolean result = personDao.insertDirectorOfMovie(1, 1);
		assertTrue(result);
	}
	
	@Test
	void insertUnexistentDirectorOfMovieTest() {
		boolean result = personDao.insertDirectorOfMovie(1, 100);
		assertFalse(result);
	}
	
	@Test
	void selectDirectorsByMovieTest() {
		List<Person> directors = personDao.selectDirectorsByMovieId(1);
		assertEquals(1, directors.size());
	}
	
	@Test
	void insertActorOfMovieTest() {
		MovieActor actor = new MovieActor();
		actor.setId(2);
		actor.setRoleName("Spiderman");
		actor.setCastOrder(1);
		
		boolean result = personDao.insertActorOfMovie(1, actor);
		assertTrue(result);
	}
	
	@Test
	void insertUnexistentActorOfMovieTest() {
		MovieActor actor = new MovieActor();
		actor.setId(100);
		actor.setRoleName("Spiderman");
		actor.setCastOrder(1);
		
		boolean result = personDao.insertActorOfMovie(1, actor);
		assertFalse(result);
	}
	
	@Test
	void selectActorsByMovieTest() {
		List<MovieActor> actors = personDao.selectActorsByMovieId(1);
		assertEquals(2, actors.size());
		assertEquals("Vincent Vega", actors.get(0).getRoleName());
		assertEquals(0, actors.get(0).getCastOrder());
	}
	
	private Person createPersonObject() {
		Person person = new Person();
		person.setName(name);
		person.setBirth(birth);
		person.setGender(gender);
		
		return person;
	}
}
