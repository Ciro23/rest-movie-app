package it.tino.postgres.review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.BiConsumer;
import java.util.function.Function;

import it.tino.postgres.MovieAppException;
import it.tino.postgres.database.Dao;
import it.tino.postgres.database.SimpleDao;

public class ReviewDao extends SimpleDao<Review, Integer> implements Dao<Review, Integer> {
	
	public ReviewDao(Connection connection) {
		super(connection);
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
            	throw new MovieAppException(e);
            }
        };
	}
	
	@Override
	public Review insert(Review review) {
		 String query = "insert into reviews (movie_id, user_id,"
		 		+ " creation_date, vote, review) values (?, ?, ?, ?, ?)";
		 
		 BiConsumer<Review, PreparedStatement> onSetParameters = (entity, stmt) -> {
	            int index = 0;
	            try {
	                stmt.setInt(++index, entity.getMovieId());
	                stmt.setInt(++index, entity.getUserId());
	                stmt.setTimestamp(++index, entity.getCreationDate());
	                stmt.setDouble(++index, entity.getVote());
	                stmt.setString(++index, entity.getReview());
	            } catch (SQLException e) {
	            	logger.error(e);
	            	throw new MovieAppException(e);
	            }
	        };
			
		return insert(review, query, onSetParameters);
	}

	@Override
	public Review update(Review review) {
		String query = "update reviews set movie_id = ?, user_id = ?,"
				+ " creation_date = ?, vote = ?, review = ? where id = ?";
	 
		 BiConsumer<Review, PreparedStatement> onSetParameters = (entity, stmt) -> {
	            int index = 0;
	            try {
	                stmt.setInt(++index, entity.getMovieId());
	                stmt.setInt(++index, entity.getUserId());
	                stmt.setTimestamp(++index, entity.getCreationDate());
	                stmt.setDouble(++index, entity.getVote());
	                stmt.setString(++index, entity.getReview());
	                stmt.setInt(++index, entity.getId());
	            } catch (SQLException e) {
	            	logger.error(e);
	            	throw new MovieAppException(e);
	            }
	        };
			
		return update(review, query, onSetParameters);
	}
}
