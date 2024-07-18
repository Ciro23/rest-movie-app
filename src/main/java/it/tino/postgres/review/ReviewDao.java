package it.tino.postgres.review;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.BiConsumer;
import java.util.function.Function;

import it.tino.postgres.database.Dao;
import it.tino.postgres.database.Database;
import it.tino.postgres.database.SimpleDao;

public class ReviewDao extends SimpleDao<Review, Integer> implements Dao<Review, Integer> {
	
	public ReviewDao(Database database) {
		super(database);
	}

	@Override
	protected String getTableName() {
		return "reviews";
	}

	@Override
	protected Function<ResultSet, Review> getOnMapEntity() {
		return (resultSet) -> {
            try {
            	Review review = new Review();
            	review.setId(resultSet.getInt("id"));
            	review.setMovieId(resultSet.getInt("movie_id"));
            	review.setUserId(resultSet.getInt("user_id"));
            	review.setCreationDate(resultSet.getTimestamp("creation_date"));
            	review.setVote(resultSet.getDouble("vote"));
            	review.setReview(resultSet.getString("review"));

            	return review;
            } catch (SQLException e) {
            	logger.error(e);
            	throw new RuntimeException(e);
            }
        };
	}
	
	@Override
	public Review insert(Review review) {
		 String query = "insert into reviews (movie_id, user_id, vote, review)"
	                + " values (?, ?, ?, ?)";
		 
		 BiConsumer<Review, PreparedStatement> onSetParameters = (entity, stmt) -> {
	            int index = 0;
	            try {
	                stmt.setInt(++index, entity.getMovieId());
	                stmt.setInt(++index, entity.getUserId());
	                stmt.setDouble(++index, entity.getVote());
	                stmt.setString(++index, entity.getReview());
	            } catch (Exception e) {
	            	logger.error(e);
	            	throw new RuntimeException(e);
	            }
	        };
			
		return insert(review, query, onSetParameters);
	}

	@Override
	public Review update(Review review) {
		String query = "update reviews set movie_id = ?, user_id = ?, vote = ?, review = ?"
                + " where id = ?";
	 
		 BiConsumer<Review, PreparedStatement> onSetParameters = (entity, stmt) -> {
	            int index = 0;
	            try {
	                stmt.setInt(++index, entity.getMovieId());
	                stmt.setInt(++index, entity.getUserId());
	                stmt.setDouble(++index, entity.getVote());
	                stmt.setString(++index, entity.getReview());
	                stmt.setInt(++index, entity.getId());
	            } catch (Exception e) {
	            	logger.error(e);
	            	throw new RuntimeException(e);
	            }
	        };
			
		return update(review, query, onSetParameters);
	}
}
