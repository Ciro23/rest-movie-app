package it.tino.postgres.person;

import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PersonDataSource implements PersonRepository {

	protected static final Logger logger = LogManager.getLogger();
	
	private final PersonDao personDao;
    
    public PersonDataSource(PersonDao personDao) {
    	this.personDao = personDao;
    }
    
	@Override
	public Person save(Person entity) {
		if (entity.getId() == 0) {
			return personDao.insert(entity);
		}
		return personDao.update(entity);
	}
    
    @Override
    public List<Person> findAll() {
       return personDao.selectByCriteria(Collections.emptyList());
    }
    
    @Override
	public Person findById(Integer id) {
		return personDao.selectById(id);
	}

    @Override
    public List<Person> findDirectorsByMovieId(int movieId) {
    	return personDao.selectDirectorsByMovieId(movieId);
    }
    
    @Override
    public List<MovieActor> findActorsByMovieId(int movieId) {
        return personDao.selectActorsByMovieId(movieId);
    }

	@Override
	public boolean delete(Integer id) {
		return personDao.delete(id);
	}
}
