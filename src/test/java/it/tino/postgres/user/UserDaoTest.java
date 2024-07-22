package it.tino.postgres.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.tino.postgres.database.Criteria;
import it.tino.postgres.database.DaoManager;
import it.tino.postgres.database.H2TestUtil;

public class UserDaoTest {

	private UserDao userDao;
	
	// Default attributes for new User instances.
	private String username = "new_user";
	private String password = "new_password";
	
	@BeforeEach
	void setUp() throws SQLException {
		H2TestUtil.createTables();

		DaoManager daoManager = H2TestUtil.getDaoManager();
		userDao = daoManager.getUserDao();
	}
	
	@AfterEach
	void tearDown() {
		H2TestUtil.dropTables();
	}
	
	@Test
	void insertTest() {
		User user = createUserObject();
		User insertedUser = userDao.insert(user);
		
		assertNotEquals(0, insertedUser.getId());
		assertEquals(username, insertedUser.getUsername());
		assertEquals(password, insertedUser.getPassword());
	}
	
	@Test
	void updateTest() {
		User user = createUserObject();
		User insertedUser = userDao.insert(user);
		
		String username = "new_username (updated)";
		String password = "new_password (updated";
		
		insertedUser.setUsername(username);
		insertedUser.setPassword(password);
		
		User updatedUser = userDao.update(insertedUser);
		assertEquals(insertedUser.getId(), updatedUser.getId());
		assertEquals(username, updatedUser.getUsername());
		assertEquals(password, updatedUser.getPassword());
	}
	
	@Test
	void selectByIdTest() {
		User user = userDao.selectById(1);
		assertEquals(1, user.getId());
		assertEquals("random_user", user.getUsername());
		assertEquals(
				"$2a$12$AhDZ6au1fPFhBr2OKYA95.9/3CshPd3d86XY2Kqr0kr2vDP.9eK9W",
				user.getPassword()
		);
	}
	
	@Test
	void selectByUnexistendIdTest() {
		User user = userDao.selectById(100);
		assertNull(user);
	}
	
	@Test
	void selectByCriteriaTest() {
		Criteria criteria = new Criteria("username", "=", "random_user");
		User user = userDao.selectByCriteria(criteria).get(0);
		assertEquals("random_user", user.getUsername());
	}
	
	@Test
	void deleteTest() {
		boolean result = userDao.delete(1);
		assertTrue(result);
	}
	
	@Test
	void deleteUnexistentUserTest() {
		boolean result = userDao.delete(100);
		assertFalse(result);
	}
	
	private User createUserObject() {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		
		return user;
	}
}
