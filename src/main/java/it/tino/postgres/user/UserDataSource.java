package it.tino.postgres.user;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.tino.postgres.database.DaoManager;

public class UserDataSource implements UserRepository {

	protected static final Logger logger = LogManager.getLogger();
	
	private final Supplier<DaoManager> onGetDaoManager;
	
	public UserDataSource(Supplier<DaoManager> onGetDaoManager) {
    	this.onGetDaoManager = onGetDaoManager;
    }

    @Override
	public User save(User entity) {
    	try (DaoManager daoManager = onGetDaoManager.get()) {
			UserDao userDao = daoManager.getUserDao();
			if (entity.getId() == 0) {
				return userDao.insert(entity);
			}
			return userDao.update(entity);
		} catch (SQLException e) {
			return null;
		}
	}
    
    @Override
    public List<User> findAll() {
    	try (DaoManager daoManager = onGetDaoManager.get()) {
			UserDao userDao = daoManager.getUserDao();
			return userDao.selectByCriteria(Collections.emptyList());
		} catch (SQLException e) {
			return Collections.emptyList();
		}
    }
    
    @Override
   	public User findById(Integer id) {
    	try (DaoManager daoManager = onGetDaoManager.get()) {
			UserDao userDao = daoManager.getUserDao();
			return userDao.selectById(id);
		} catch (SQLException e) {
			return null;
		}
   	}

	@Override
	public boolean delete(Integer id) {
		try (DaoManager daoManager = onGetDaoManager.get()) {
			UserDao userDao = daoManager.getUserDao();
			return userDao.delete(id);
		} catch (SQLException e) {
			return false;
		}
	}
}
