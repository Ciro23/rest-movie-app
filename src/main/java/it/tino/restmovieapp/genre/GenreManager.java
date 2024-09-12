package it.tino.restmovieapp.genre;

import it.tino.restmovieapp.SimpleManager;
import it.tino.restmovieapp.error.MovieAppException;
import it.tino.restmovieapp.mybatis.mapper.GenreDbMapper;
import it.tino.restmovieapp.mybatis.mapper.VMovieGenreDbDynamicSqlSupport;
import it.tino.restmovieapp.mybatis.mapper.VMovieGenreDbMapper;
import it.tino.restmovieapp.mybatis.model.GenreDb;
import it.tino.restmovieapp.mybatis.model.VMovieGenreDb;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;

import java.util.List;

public class GenreManager {

	protected static final Logger logger = LogManager.getLogger();

	private final SqlSessionFactory sqlSessionFactory;
	private final GenreMapper genreMapper;
	private final SimpleManager<Genre, GenreDb, Integer> simpleManager;

	public GenreManager(SqlSessionFactory sqlSessionFactory, GenreMapper genreMapper) {
		this.sqlSessionFactory = sqlSessionFactory;
		this.genreMapper = genreMapper;

		SimpleManager.InsertFunction<GenreDb> onInsert = (sqlSession, key) -> {
			GenreDbMapper dao = sqlSession.getMapper(GenreDbMapper.class);
			return dao.insert(key);
		};
		SimpleManager.UpdateFunction<GenreDb> onUpdate = (sqlSession, key) -> {
			GenreDbMapper dao = sqlSession.getMapper(GenreDbMapper.class);
			return dao.updateByPrimaryKey(key);
		};
		SimpleManager.SelectFunction<GenreDb> onSelect = (sqlSession, key) -> {
			GenreDbMapper dao = sqlSession.getMapper(GenreDbMapper.class);
			return dao.select(key);
		};
		SimpleManager.SelectByIdFunction<GenreDb, Integer> onSelectById = (sqlSession, key) -> {
			GenreDbMapper dao = sqlSession.getMapper(GenreDbMapper.class);
			return dao.selectByPrimaryKey(key);
		};
		SimpleManager.DeleteFunction<Integer> onDelete = (sqlSession, key) -> {
			GenreDbMapper dao = sqlSession.getMapper(GenreDbMapper.class);
			return dao.deleteByPrimaryKey(key);
		};
		this.simpleManager = new SimpleManager<>(
				sqlSessionFactory,
				genreMapper,
				onInsert,
				onUpdate,
				onSelect,
				onSelectById,
				onDelete
		);
	}

	public Genre insert(Genre genre) {
		return simpleManager.insert(genre);
	}

	public Genre update(Genre genre) {
		return simpleManager.update(genre);
	}

	public List<Genre> selectAll() {
		return simpleManager.selectAll();
	}

	public Genre selectById(int id) {
		return simpleManager.selectById(id);
	}

	public List<Genre> selectByMovieId(int movieId) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
			VMovieGenreDbMapper vMovieGenreDao = sqlSession.getMapper(VMovieGenreDbMapper.class);
			List<VMovieGenreDb> vMovieGenresDbList = vMovieGenreDao.select(c -> c.where(
					VMovieGenreDbDynamicSqlSupport.movieId,
					SqlBuilder.isEqualTo(movieId)
			));

			List<GenreDb> genresDb = vMovieGenresDbList
					.stream()
					.map(m -> {
						GenreDb genreDb = new GenreDb();
						genreDb.setId(m.getGenreId());
						genreDb.setName(m.getName());
						return genreDb;
					})
					.toList();

			return genreMapper.sourceToDomain(genresDb);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new MovieAppException(e);
		}
	}

	public List<Genre> selectByCriteria(SelectDSLCompleter selectDSLCompleter) {
		return simpleManager.selectByCriteria(selectDSLCompleter);
	}

	public boolean delete(int id) {
		return simpleManager.delete(id);
	}
}
