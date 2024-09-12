package it.tino.restmovieapp.person;

import it.tino.restmovieapp.error.MovieAppException;
import it.tino.restmovieapp.SimpleManager;
import it.tino.restmovieapp.mybatis.mapper.*;
import it.tino.restmovieapp.mybatis.model.*;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PersonManager {

	protected static final Logger logger = LogManager.getLogger();

	private final SqlSessionFactory sqlSessionFactory;
	private final PersonMapper personMapper;
	private final SimpleManager<Person, PersonDb, Integer> simpleManager;
	
	public PersonManager(SqlSessionFactory sqlSessionFactory, PersonMapper personMapper) {
		this.sqlSessionFactory = sqlSessionFactory;
		this.personMapper = personMapper;

		SimpleManager.InsertFunction<PersonDb> onInsert = (sqlSession, key) -> {
			PersonDbMapper dao = sqlSession.getMapper(PersonDbMapper.class);
			return dao.insert(key);
		};
		SimpleManager.UpdateFunction<PersonDb> onUpdate = (sqlSession, key) -> {
			PersonDbMapper dao = sqlSession.getMapper(PersonDbMapper.class);
			return dao.updateByPrimaryKey(key);
		};
		SimpleManager.SelectFunction<PersonDb> onSelect = (sqlSession, key) -> {
			PersonDbMapper dao = sqlSession.getMapper(PersonDbMapper.class);
			return dao.select(key);
		};
		SimpleManager.SelectByIdFunction<PersonDb, Integer> onSelectById = (sqlSession, key) -> {
			PersonDbMapper dao = sqlSession.getMapper(PersonDbMapper.class);
			return dao.selectByPrimaryKey(key);
		};
		SimpleManager.DeleteFunction<Integer> onDelete = (sqlSession, key) -> {
			PersonDbMapper dao = sqlSession.getMapper(PersonDbMapper.class);
			return dao.deleteByPrimaryKey(key);
		};
		this.simpleManager = new SimpleManager<>(
				sqlSessionFactory,
				personMapper,
				onInsert,
				onUpdate,
				onSelect,
				onSelectById,
				onDelete
		);
	}

	public Person insert(Person person) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
			PersonDb dbEntity = personMapper.domainToDb(person);

			PersonDbMapper personDao = sqlSession.getMapper(PersonDbMapper.class);
			MovieDirectorDbMapper movieDirectorDao = sqlSession.getMapper(MovieDirectorDbMapper.class);
			MovieActorDbMapper movieActorDao = sqlSession.getMapper(MovieActorDbMapper.class);

			int affectedRows = personDao.insert(dbEntity);
			person.setId(dbEntity.getId());

			movieDirectorDao.insertMultiple(getDirectedMovies(person));
			movieActorDao.insertMultiple(getStarredMovies(person));

			if (affectedRows == 1) {
				Person insertedPerson = personMapper.dbToDomain(dbEntity);
				sqlSession.commit();

				return insertedPerson;
			}
			throw new MovieAppException("No affected rows on insert");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new MovieAppException(e);
		}
	}
	
	public Person update(Person person) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
			PersonDb dbEntity = personMapper.domainToDb(person);

			PersonDbMapper personDao = sqlSession.getMapper(PersonDbMapper.class);
			MovieDirectorDbMapper movieDirectorDao = sqlSession.getMapper(MovieDirectorDbMapper.class);
			MovieActorDbMapper movieActorDao = sqlSession.getMapper(MovieActorDbMapper.class);

			int affectedRows = personDao.updateByPrimaryKey(dbEntity);

			movieDirectorDao.delete(c -> c.where(
					MovieDirectorDbDynamicSqlSupport.directorId,
					SqlBuilder.isEqualTo(dbEntity.getId())
			));
			movieDirectorDao.insertMultiple(getDirectedMovies(person));

			movieActorDao.delete(c -> c.where(
					MovieActorDbDynamicSqlSupport.actorId,
					SqlBuilder.isEqualTo(dbEntity.getId())
			));
			movieActorDao.insertMultiple(getStarredMovies(person));

			if (affectedRows == 1) {
				Person updatedPerson = personMapper.dbToDomain(dbEntity);
				sqlSession.commit();

				return updatedPerson;
			}
			throw new MovieAppException("No affected rows on insert");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new MovieAppException(e);
		}
	}
    
    public List<Person> selectAll() {
    	return simpleManager.selectAll();
    }
    
	public Person selectById(int id) {
		return simpleManager.selectById(id);
	}
	
	public List<Person> selectByCriteria(SelectDSLCompleter selectDSLCompleter) {
		return simpleManager.selectByCriteria(selectDSLCompleter);
	}
	
	public List<Person> selectByMovieId(int movieId) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
			VMovieDirectorDbMapper vMovieDirectorDao = sqlSession.getMapper(VMovieDirectorDbMapper.class);
			VMovieActorDbMapper vMovieActorDao = sqlSession.getMapper(VMovieActorDbMapper.class);

			List<VMovieDirectorDb> vMovieDirectorDbList = vMovieDirectorDao.select(c -> c.where(
					VMovieDirectorDbDynamicSqlSupport.movieId,
					SqlBuilder.isEqualTo(movieId)
			));
			List<VMovieActorDb> vMovieActorDbList = vMovieActorDao.select(c -> c.where(
					VMovieActorDbDynamicSqlSupport.movieId,
					SqlBuilder.isEqualTo(movieId)
			));

			Set<PersonDb> peopleDb = vMovieDirectorDbList
					.stream()
					.map(m -> {
						PersonDb person = new PersonDb();
						person.setId(m.getDirectorId());
						person.setName(m.getName());
						person.setBirth(m.getBirth());
						person.setGender(m.getGender());
						return person;
					})
					.collect(Collectors.toSet());
			peopleDb.addAll(
					vMovieActorDbList
							.stream()
							.map(m -> {
								PersonDb person = new PersonDb();
								person.setId(m.getActorId());
								person.setName(m.getName());
								person.setBirth(m.getBirth());
								person.setGender(m.getGender());
								return person;
							})
							.collect(Collectors.toSet())
			);

			return personMapper.dbToDomain(peopleDb);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new MovieAppException(e);
		}
	}

	public boolean delete(int id) {
		return simpleManager.delete(id);
	}
	
	private List<MovieDirectorDb> getDirectedMovies(Person person) {
		return person.getDirectedMovies()
				.stream()
				.map(directedMovie -> {
					MovieDirectorDb movieDirector = new MovieDirectorDb();
					movieDirector.setMovieId(directedMovie.getMovieId());
					movieDirector.setDirectorId(person.getId());
					return movieDirector;
				})
				.toList();
	}
	
	private List<MovieActorDb> getStarredMovies(Person person) {
		return person.getStarredMovies()
				.stream()
				.map(starredMovie -> {
					MovieActorDb movieActor = new MovieActorDb();
					movieActor.setMovieId(starredMovie.getMovieId());
					movieActor.setActorId(person.getId());
					movieActor.setRole(starredMovie.getRoleName());
					movieActor.setCastOrder(starredMovie.getCastOrder());
					return movieActor;
				})
				.toList();
	}
}
