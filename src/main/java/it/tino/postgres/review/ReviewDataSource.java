package it.tino.postgres.review;

import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReviewDataSource implements ReviewRepository {

	protected static final Logger logger = LogManager.getLogger();
	
	private final ReviewDao reviewDao;

    public ReviewDataSource(ReviewDao reviewDao) {
    	this.reviewDao = reviewDao;
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
       return reviewDao.selectByCriteria(Collections.emptyList());
    }
    
    @Override
   	public Review findById(Integer id) {
   		return reviewDao.selectById(id);
   	}
    
	@Override
	public boolean delete(Integer id) {
		return reviewDao.delete(id);
	}
}
