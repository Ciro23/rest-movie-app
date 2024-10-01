package it.tino.restmovieapp.movie;

import it.tino.restmovieapp.SimpleManager;
import it.tino.restmovieapp.error.MovieAppException;
import it.tino.restmovieapp.mybatis.mapper.*;
import it.tino.restmovieapp.mybatis.model.*;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MovieManager {
    
	protected static final Logger logger = LogManager.getLogger();

	private final SqlSessionFactory sqlSessionFactory;
	private final MovieMapper movieMapper;
	private final SimpleManager<Movie, MovieDb, Integer> simpleManager;

	public MovieManager(SqlSessionFactory sqlSessionFactory, MovieMapper movieMapper) {
		this.sqlSessionFactory = sqlSessionFactory;
		this.movieMapper = movieMapper;

		SimpleManager.InsertFunction<MovieDb> onInsert = (sqlSession, key) -> {
			MovieDbMapper dao = sqlSession.getMapper(MovieDbMapper.class);
			return dao.insert(key);
		};
		SimpleManager.UpdateFunction<MovieDb> onUpdate = (sqlSession, key) -> {
			MovieDbMapper dao = sqlSession.getMapper(MovieDbMapper.class);
			return dao.updateByPrimaryKey(key);
		};
		SimpleManager.SelectFunction<MovieDb> onSelect = (sqlSession, key) -> {
			MovieDbMapper dao = sqlSession.getMapper(MovieDbMapper.class);
			return dao.select(key);
		};
		SimpleManager.SelectByIdFunction<MovieDb, Integer> onSelectById = (sqlSession, key) -> {
			MovieDbMapper dao = sqlSession.getMapper(MovieDbMapper.class);
			return dao.selectByPrimaryKey(key);
		};
		SimpleManager.DeleteFunction<Integer> onDelete = (sqlSession, key) -> {
			MovieDbMapper dao = sqlSession.getMapper(MovieDbMapper.class);
			return dao.deleteByPrimaryKey(key);
		};
		this.simpleManager = new SimpleManager<>(
				sqlSessionFactory,
				movieMapper,
				onInsert,
				onUpdate,
				onSelect,
				onSelectById,
				onDelete
		);
	}


	public Movie insert(Movie movie) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
			MovieDb dbEntity = movieMapper.domainToTarget(movie);

			MovieDbMapper movieDao = sqlSession.getMapper(MovieDbMapper.class);
			MovieDirectorDbMapper movieDirectorDao = sqlSession.getMapper(MovieDirectorDbMapper.class);
			MovieActorDbMapper movieActorDao = sqlSession.getMapper(MovieActorDbMapper.class);
			MovieGenreDbMapper movieGenreDao = sqlSession.getMapper(MovieGenreDbMapper.class);

			int affectedRows = movieDao.insert(dbEntity);
			movie.setId(dbEntity.getId());

			List<MovieDirectorDb> directors = getDirectors(movie);
			if (!directors.isEmpty()) {
				movieDirectorDao.insertMultiple(directors);
			}

			List<MovieActorDb> actors = getActors(movie);
			if (!actors.isEmpty()) {
				movieActorDao.insertMultiple(actors);
			}

			List<MovieGenreDb> genres = getGenres(movie);
			if (!genres.isEmpty()) {
				movieGenreDao.insertMultiple(genres);
			}

			if (affectedRows == 1) {
				sqlSession.commit();
                return movieMapper.sourceToDomain(dbEntity);
			}
			throw new MovieAppException("No affected rows on insert");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new MovieAppException(e);
		}
    }
    
    public Movie update(Movie movie) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
			MovieDb dbEntity = movieMapper.domainToTarget(movie);

			MovieDbMapper movieDao = sqlSession.getMapper(MovieDbMapper.class);
			MovieDirectorDbMapper movieDirectorDao = sqlSession.getMapper(MovieDirectorDbMapper.class);
			MovieActorDbMapper movieActorDao = sqlSession.getMapper(MovieActorDbMapper.class);
			MovieGenreDbMapper movieGenreDao = sqlSession.getMapper(MovieGenreDbMapper.class);

			int affectedRows = movieDao.updateByPrimaryKey(dbEntity);

			movieDirectorDao.delete(c -> c.where(
					MovieDirectorDbDynamicSqlSupport.movieId,
					SqlBuilder.isEqualTo(dbEntity.getId())
			));
			List<MovieDirectorDb> directors = getDirectors(movie);
			if (!directors.isEmpty()) {
				movieDirectorDao.insertMultiple(directors);
			}

			movieActorDao.delete(c -> c.where(
					MovieActorDbDynamicSqlSupport.movieId,
					SqlBuilder.isEqualTo(dbEntity.getId())
			));
			List<MovieActorDb> actors = getActors(movie);
			if (!actors.isEmpty()) {
				movieActorDao.insertMultiple(actors);
			}

			movieGenreDao.delete(c -> c.where(
					MovieGenreDbDynamicSqlSupport.movieId,
					SqlBuilder.isEqualTo(dbEntity.getId())
			));
			List<MovieGenreDb> genres = getGenres(movie);
			if (!genres.isEmpty()) {
				movieGenreDao.insertMultiple(genres);
			}

			if (affectedRows == 1) {
				sqlSession.commit();
                return movieMapper.sourceToDomain(dbEntity);
			}
			throw new MovieAppException("No affected rows on insert");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new MovieAppException(e);
		}
    }
    
    public List<Movie> selectAll() {
    	return simpleManager.selectAll();
    }
    
	public Movie selectById(int id) {
		return simpleManager.selectById(id);
	}
	
	public List<Movie> selectByCriteria(SelectDSLCompleter selectDSLCompleter) {
		return simpleManager.selectByCriteria(selectDSLCompleter);
	}

	public List<Movie> selectByDirectorId(int directorId) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
			VMovieDirectorDbMapper vMovieDirectorDao = sqlSession.getMapper(VMovieDirectorDbMapper.class);
			List<VMovieDirectorDb> vMovieDirectorDbList = vMovieDirectorDao.select(c -> c.where(
					VMovieDirectorDbDynamicSqlSupport.directorId,
					SqlBuilder.isEqualTo(directorId)
			));

			List<MovieDb> moviesDb = vMovieDirectorDbList
					.stream()
					.map(m -> {
						MovieDb movie = new MovieDb();
						movie.setId(m.getMovieId());
						movie.setTitle(m.getTitle());
						movie.setReleaseDate(m.getReleaseDate());
						movie.setBudget(m.getBudget());
						movie.setBoxOffice(m.getBoxOffice());
						movie.setRuntime(m.getRuntime());
						movie.setOverview(m.getOverview());
						return movie;
					})
					.toList();

			return movieMapper.sourceToDomain(moviesDb);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new MovieAppException(e);
		}
	}

	public List<Movie> selectByActorId(int actorId) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
			VMovieActorDbMapper vMovieActorDao = sqlSession.getMapper(VMovieActorDbMapper.class);
			List<VMovieActorDb> vMovieActorDbList = vMovieActorDao.select(c -> c.where(
					VMovieActorDbDynamicSqlSupport.actorId,
					SqlBuilder.isEqualTo(actorId)
			));

			List<MovieDb> moviesDb = vMovieActorDbList
					.stream()
					.map(m -> {
						MovieDb movie = new MovieDb();
						movie.setId(m.getMovieId());
						movie.setTitle(m.getTitle());
						movie.setReleaseDate(m.getReleaseDate());
						movie.setBudget(m.getBudget());
						movie.setBoxOffice(m.getBoxOffice());
						movie.setRuntime(m.getRuntime());
						movie.setOverview(m.getOverview());
						return movie;
					})
					.toList();

			return movieMapper.sourceToDomain(moviesDb);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new MovieAppException(e);
		}
	}

	public List<Movie> selectByGenreIdIn(List<Integer> genreIds) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
			VMovieGenreDbMapper vMovieGenreDao = sqlSession.getMapper(VMovieGenreDbMapper.class);
			List<VMovieGenreDb> vMovieGenresDbList = vMovieGenreDao.select(c -> c
					.where(VMovieGenreDbDynamicSqlSupport.genreId, SqlBuilder.isIn(genreIds))
			);

			List<MovieDb> moviesDb = vMovieGenresDbList
					.stream()
					.map(m -> {
						MovieDb movie = new MovieDb();
						movie.setId(m.getMovieId());
						movie.setTitle(m.getTitle());
						movie.setReleaseDate(m.getReleaseDate());
						movie.setBudget(m.getBudget());
						movie.setBoxOffice(m.getBoxOffice());
						movie.setRuntime(m.getRuntime());
						movie.setOverview(m.getOverview());
						return movie;
					})
					.toList();

			return new ArrayList<>(new HashSet<>(movieMapper.sourceToDomain(moviesDb)));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new MovieAppException(e);
		}
	}

	public boolean delete(int id) {
		return simpleManager.delete(id);
	}

	private List<MovieGenreDb> getGenres(Movie movie) {
		return movie.getGenreIds()
				.stream()
				.map(genre -> {
					MovieGenreDb movieGenre = new MovieGenreDb();
					movieGenre.setMovieId(movie.getId());
					movieGenre.setGenreId(genre);
					return movieGenre;
				})
				.toList();
	}
	
	private List<MovieDirectorDb> getDirectors(Movie movie) {
		return movie.getDirectorIds()
				.stream()
				.map(director -> {
					MovieDirectorDb movieDirector = new MovieDirectorDb();
					movieDirector.setMovieId(movie.getId());
					movieDirector.setDirectorId(director);
					return movieDirector;
				})
				.toList();
	}
	
	private List<MovieActorDb> getActors(Movie movie) {
		return movie.getActors()
				.stream()
				.map(starredMovie -> {
					MovieActorDb movieActor = new MovieActorDb();
					movieActor.setMovieId(movie.getId());
					movieActor.setActorId(starredMovie.getActorId());
					movieActor.setRole(starredMovie.getRoleName());
					movieActor.setCastOrder(starredMovie.getCastOrder());
					return movieActor;
				})
				.toList();
	}
}
