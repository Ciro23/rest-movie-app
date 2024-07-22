package it.tino.postgres.genre;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.tino.postgres.database.DaoManager;

public class GenreDataSource implements GenreRepository {

	protected static final Logger logger = LogManager.getLogger();
	
	private final Supplier<DaoManager> onGetDaoManager;
    
    public GenreDataSource(Supplier<DaoManager> onGetDaoManager) {
    	this.onGetDaoManager = onGetDaoManager;
    }
    
    @Override
	public Genre save(Genre entity) {
    	try (DaoManager daoManager = onGetDaoManager.get()) {
			GenreDao genreDao = daoManager.getGenreDao();
			if (entity.getId() == 0) {
				return genreDao.insert(entity);
			}
			return genreDao.update(entity);
		} catch (SQLException e) {
			return null;
		}
	}

    @Override
    public List<Genre> findAll() {
    	try (DaoManager daoManager = onGetDaoManager.get()) {
			GenreDao genreDao = daoManager.getGenreDao();
			return genreDao.selectByCriteria(Collections.emptyList());
		} catch (SQLException e) {
			return Collections.emptyList();
		}
    }
    
    @Override
	public Genre findById(Integer id) {
    	try (DaoManager daoManager = onGetDaoManager.get()) {
			GenreDao genreDao = daoManager.getGenreDao();
			return genreDao.selectById(id);
		} catch (SQLException e) {
			return null;
		}
	}
    
    @Override
    public List<Genre> findByMovieId(int movieId) {
    	try (DaoManager daoManager = onGetDaoManager.get()) {
			GenreDao genreDao = daoManager.getGenreDao();
			return genreDao.selectByMovieId(movieId);
		} catch (SQLException e) {
			return Collections.emptyList();
		}
    }

    @Override
    public boolean delete(Integer id) {
    	try (DaoManager daoManager = onGetDaoManager.get()) {
			GenreDao genreDao = daoManager.getGenreDao();
			return genreDao.delete(id);
		} catch (SQLException e) {
			return false;
		}
    }
}
