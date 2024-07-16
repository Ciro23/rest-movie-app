package it.tino.postgres.review;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

import it.tino.postgres.database.DatabaseTable;
import it.tino.postgres.person.Person;

public class ReviewDataSource implements ReviewRepository {

    private final DatabaseTable<Review> database;
    private final Function<ResultSet, Review> onMapEntity;

    public ReviewDataSource(DatabaseTable<Review> database) {
        this.database = database;
        onMapEntity = (resultSet) -> {
            try {
                return new Review(
                        resultSet.getInt("movie_id"),
                        resultSet.getInt("user_id"),
                        resultSet.getTimestamp("creation_date"),
                        resultSet.getDouble("vote"),
                        resultSet.getString("review")
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };
    }
    
    @Override
    public int save(Review review) {
        boolean shouldUpdate = review.getCreationDate() != null;
        BiConsumer<Review, PreparedStatement> onSetParameters = (entity, stmt) -> {
            int index = 0;
            try {
                stmt.setInt(++index, entity.getMovieId());
                stmt.setInt(++index, entity.getUserId());
                stmt.setDouble(++index, entity.getVote());
                stmt.setString(++index, entity.getReview());
                
                // Used in the WHERE clause to select the row to modify.
                if (shouldUpdate) {
                    stmt.setInt(++index, entity.getMovieId());
                    stmt.setInt(++index, entity.getUserId());
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        };
        
        String query = "insert into reviews (movie_id, user_id, vote, review)"
                + " values (?, ?, ?, ?)";
        if (shouldUpdate) {
            query = "update reviews set movie_id = ?, user_id = ?, vote = ?, review = ?"
                    + " where movie_id = ? and user_id = ?";
        }
        
        return database.insertOrUpdate(
                review,
                query,
                onSetParameters
        );
    }
    
    @Override
    public List<Review> findAll() {
       return database.select(
               "select * from reviews order by creation_date desc",
               null,
               onMapEntity
       ); 
    }
    
    @Override
    public int delete(Review review) {
        return database.delete("delete from reviews where movie_id = ?"
                + " and user_id = ?", (stmt) -> {
            try {
                stmt.setInt(1, review.getMovieId());
                stmt.setInt(2, review.getUserId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
