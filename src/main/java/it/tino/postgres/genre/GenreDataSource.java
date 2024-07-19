package it.tino.postgres.genre;

import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GenreDataSource implements GenreRepository {

	protected static final Logger logger = LogManager.getLogger();
	
	private final GenreDao genreDao;
    
    public GenreDataSource(GenreDao genreDao) {
    	this.genreDao = genreDao;
    }
    
    @Override
	public Genre save(Genre entity) {
		if (entity.getId() == 0) {
			return genreDao.insert(entity);
		}
		return genreDao.update(entity);
	}

    @Override
    public List<Genre> findAll() {
    	return genreDao.selectByCriteria(Collections.emptyList());
    }
    
    @Override
	public Genre findById(Integer id) {
		return genreDao.selectById(id);
	}
    
    @Override
    public List<Genre> findByMovieId(int movieId) {
        return genreDao.selectByMovieId(movieId);
    }

    @Override
    public boolean delete(Integer id) {
        return genreDao.delete(id);
    }
}
