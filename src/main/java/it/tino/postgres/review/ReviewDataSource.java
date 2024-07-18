package it.tino.postgres.review;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Function;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.tino.postgres.database.DatabaseTable;

public class ReviewDataSource implements ReviewRepository {

	protected static final Logger logger = LogManager.getLogger();
	
	private final ReviewDao reviewDao;
    private final DatabaseTable<Review> database;
    private final Function<ResultSet, Review> onMapEntity;

    public ReviewDataSource(ReviewDao reviewDao, DatabaseTable<Review> database) {
    	this.reviewDao = reviewDao;
        this.database = database;
        onMapEntity = (resultSet) -> {
            try {
                return new Review(
                		resultSet.getInt("id"),
                        resultSet.getInt("movie_id"),
                        resultSet.getInt("user_id"),
                        resultSet.getTimestamp("creation_date"),
                        resultSet.getDouble("vote"),
                        resultSet.getString("review")
                );
            } catch (SQLException e) {
            	logger.error(e);
            	throw new RuntimeException(e);
            }
        };
    }
    
    @Override
	public Review save(Review entity) {
		if (entity.getId() == 0) {
			return reviewDao.insert(entity);
		}
		return reviewDao.update(entity);
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
	public boolean delete(Integer id) {
		return reviewDao.delete(id);
	}
}
