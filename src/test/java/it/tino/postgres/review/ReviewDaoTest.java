package it.tino.postgres.review;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.tino.postgres.database.Criteria;
import it.tino.postgres.database.Database;
import it.tino.postgres.database.H2TestUtil;

public class ReviewDaoTest {

	private ReviewDao reviewDao;
	
	/**
	 * "Double" values saved in the database may not be
	 * totally precised when fetched, for example 7.1 is
	 * fetched as 7.099999904632568. This tolerance is needed
	 * to discard these kind of minor discrepancies.
	 */
	private final double tolerance = 1e-6;
	
	// Default attributes for new Review instances.
	private int movieId = 1;
	private int userId = 1;
	private Timestamp creationDate = Timestamp.valueOf("2024-7-19 12:20:15");
	private double vote = 9.5;
	private String content = "My review";
	
	@BeforeEach
	void setUp() {
		H2TestUtil.createTables();

		Database database = H2TestUtil.getDatabase();
		reviewDao = new ReviewDao(database);
	}
	
	@AfterEach
	void tearDown() {
		H2TestUtil.dropTables();
	}
	
	@Test
	void insertTest() {
		Review review = createReviewObject();
		Review insertedReview = reviewDao.insert(review);
		
		assertNotEquals(0, insertedReview.getId());
		assertEquals(movieId, insertedReview.getMovieId());
		assertEquals(userId, insertedReview.getUserId());
		assertEquals(creationDate, insertedReview.getCreationDate());
		assertEquals(vote, insertedReview.getVote());
		assertEquals(content, insertedReview.getReview());
	}
	
	@Test
	void updateTest() {
		Review review = createReviewObject();
		Review insertedReview = reviewDao.insert(review);
		
		int movieId = 2;
		int userId = 2;
		Timestamp creationDate = Timestamp.valueOf("2024-7-20 16:30:00");
		double vote = 4;
		String content = "My review (updated)";
		
		insertedReview.setMovieId(movieId);
		insertedReview.setUserId(userId);
		insertedReview.setCreationDate(creationDate);
		insertedReview.setVote(vote);
		insertedReview.setReview(content);
		
		Review updatedReview = reviewDao.update(insertedReview);
		assertEquals(insertedReview.getId(), updatedReview.getId());
		assertEquals(movieId, updatedReview.getMovieId());
		assertEquals(userId, updatedReview.getUserId());
		assertEquals(creationDate, updatedReview.getCreationDate());
		assertEquals(vote, updatedReview.getVote());
		assertEquals(content, updatedReview.getReview());
	}
	
	@Test
	void selectByIdTest() {
		Review review = reviewDao.selectById(1);
		assertEquals(1, review.getId());
		assertEquals(2, review.getMovieId());
		assertEquals(1, review.getUserId());
		assertEquals(Timestamp.valueOf("2024-7-18 10:45:00"), review.getCreationDate());
		assertEquals(7.1, review.getVote(), tolerance);
		assertEquals("Good movie", review.getReview());
	}
	
	@Test
	void selectByUnexistendIdTest() {
		Review review = reviewDao.selectById(100);
		assertNull(review);
	}
	
	@Test
	void selectByCriteriaTest() {
		Criteria criteria = new Criteria("vote", "=", "7.1");
		Review review = reviewDao.selectByCriteria(criteria).get(0);
		assertEquals(7.1, review.getVote(), tolerance);
	}
	
	@Test
	void deleteTest() {
		boolean result = reviewDao.delete(1);
		assertTrue(result);
	}
	
	@Test
	void deleteUnexistentReviewTest() {
		boolean result = reviewDao.delete(100);
		assertFalse(result);
	}
	
	private Review createReviewObject() {
		Review review = new Review();
		review.setMovieId(movieId);
		review.setUserId(userId);
		review.setCreationDate(creationDate);
		review.setVote(vote);
		review.setReview(content);
		
		return review;
	}
}
