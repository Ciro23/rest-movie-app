package it.tino.postgres.review;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.tino.postgres.database.DaoManager;

public class ReviewDataSource implements ReviewRepository {

	protected static final Logger logger = LogManager.getLogger();
	
	private final Supplier<DaoManager> onGetDaoManager;
	
    public ReviewDataSource(Supplier<DaoManager> onGetDaoManager) {
    	this.onGetDaoManager = onGetDaoManager;
    }
    
    @Override
	public Review save(Review entity) {
    	try (DaoManager daoManager = onGetDaoManager.get()) {
			ReviewDao reviewDao = daoManager.getReviewDao();
			if (entity.getId() == 0) {
				return reviewDao.insert(entity);
			}
			return reviewDao.update(entity);
		} catch (SQLException e) {
			return null;
		}
	}
    
    @Override
    public List<Review> findAll() {
    	try (DaoManager daoManager = onGetDaoManager.get()) {
			ReviewDao reviewDao = daoManager.getReviewDao();
			return reviewDao.selectByCriteria(Collections.emptyList());
		} catch (SQLException e) {
			return Collections.emptyList();
		}
    }
    
    @Override
   	public Review findById(Integer id) {
    	try (DaoManager daoManager = onGetDaoManager.get()) {
			ReviewDao reviewDao = daoManager.getReviewDao();
			return reviewDao.selectById(id);
		} catch (SQLException e) {
			return null;
		}
   	}
    
	@Override
	public boolean delete(Integer id) {
		try (DaoManager daoManager = onGetDaoManager.get()) {
			ReviewDao reviewDao = daoManager.getReviewDao();
			return reviewDao.delete(id);
		} catch (SQLException e) {
			return false;
		}
	}
}
