package it.tino.postgres.person;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.tino.postgres.database.DaoManager;

public class PersonDataSource implements PersonRepository {

	protected static final Logger logger = LogManager.getLogger();
	
	private final Supplier<DaoManager> onGetDaoManager;
	
    public PersonDataSource(Supplier<DaoManager> onGetDaoManager) {
    	this.onGetDaoManager = onGetDaoManager;
    }
    
	@Override
	public Person save(Person entity) {
		try (DaoManager daoManager = onGetDaoManager.get()) {
			PersonDao personDao = daoManager.getPersonDao();
			if (entity.getId() == 0) {
				return personDao.insert(entity);
			}
			return personDao.update(entity);	
		} catch (SQLException e) {
			return null;
		}
	}
    
    @Override
    public List<Person> findAll() {
    	try (DaoManager daoManager = onGetDaoManager.get()) {
			PersonDao personDao = daoManager.getPersonDao();
			return personDao.selectByCriteria(Collections.emptyList());
		} catch (SQLException e) {
			return Collections.emptyList();
		}
    }
    
    @Override
	public Person findById(Integer id) {
    	try (DaoManager daoManager = onGetDaoManager.get()) {
			PersonDao personDao = daoManager.getPersonDao();
			return personDao.selectById(id);
		} catch (SQLException e) {
			return null;
		}
	}

    @Override
    public List<Person> findDirectorsByMovieId(int movieId) {
    	try (DaoManager daoManager = onGetDaoManager.get()) {
			PersonDao personDao = daoManager.getPersonDao();
			return personDao.selectDirectorsByMovieId(movieId);
		} catch (SQLException e) {
			return Collections.emptyList();
		}
    }
    
    @Override
    public List<MovieActor> findActorsByMovieId(int movieId) {
    	try (DaoManager daoManager = onGetDaoManager.get()) {
			PersonDao personDao = daoManager.getPersonDao();
			return personDao.selectActorsByMovieId(movieId);
		} catch (SQLException e) {
			return Collections.emptyList();
		}
    }

	@Override
	public boolean delete(Integer id) {
		try (DaoManager daoManager = onGetDaoManager.get()) {
			PersonDao personDao = daoManager.getPersonDao();
			return personDao.delete(id);
		} catch (SQLException e) {
			return false;
		}
	}
}
