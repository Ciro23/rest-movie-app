package it.tino.restmovieapp.genre;

import it.tino.restmovieapp.PaginatedResponse;
import it.tino.restmovieapp.SimpleManager;
import it.tino.restmovieapp.error.MovieAppException;
import it.tino.restmovieapp.mybatis.mapper.GenreDbDynamicSqlSupport;
import it.tino.restmovieapp.mybatis.mapper.GenreDbMapper;
import it.tino.restmovieapp.mybatis.mapper.VMovieGenreDbDynamicSqlSupport;
import it.tino.restmovieapp.mybatis.mapper.VMovieGenreDbMapper;
import it.tino.restmovieapp.mybatis.model.GenreDb;
import it.tino.restmovieapp.mybatis.model.VMovieGenreDb;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.dynamic.sql.SortSpecification;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;

import java.util.List;
import java.util.function.BiFunction;

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
		SimpleManager.CountFunction onCount = (sqlSession, key) -> {
			GenreDbMapper dao = sqlSession.getMapper(GenreDbMapper.class);
			return dao.count(key);
		};
		SimpleManager.SelectByIdFunction<GenreDb, Integer> onSelectById = (sqlSession, key) -> {
			GenreDbMapper dao = sqlSession.getMapper(GenreDbMapper.class);
			return dao.selectByPrimaryKey(key);
		};
		SimpleManager.DeleteFunction<Integer> onDelete = (sqlSession, key) -> {
			GenreDbMapper dao = sqlSession.getMapper(GenreDbMapper.class);
			return dao.deleteByPrimaryKey(key);
		};

		BiFunction<String, String, SortSpecification> onGetSortSpecification = (
				String sortField,
				String sortDirection
		) -> {
			SortSpecification sortSpec = GenreDbDynamicSqlSupport.name;
			if (sortField.equalsIgnoreCase("id")) {
				sortSpec = GenreDbDynamicSqlSupport.id;
			}

			if ("desc".equalsIgnoreCase(sortDirection)) {
				sortSpec = sortSpec.descending();
			}

			return sortSpec;
		};

		this.simpleManager = new SimpleManager<>(
				sqlSessionFactory,
				genreMapper,
				onInsert,
				onUpdate,
				onSelect,
				onCount,
				onSelectById,
				onDelete,
				onGetSortSpecification
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

	public PaginatedResponse<Genre> selectPaginated(
		int offset,
		int size,
		String sortField,
		String sortDirection
	) {
		return simpleManager.selectPaginated(offset, size, sortField, sortDirection);
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

	public PaginatedResponse<Genre> selectPaginatedByCriteria(
		SelectDSLCompleter selectDSLCompleter,
		CountDSLCompleter countDSLCompleter,
		int offset,
		int size,
		String sortField,
		String sortDirection
	) {
		return simpleManager.selectPaginatedByCriteria(
				selectDSLCompleter,
				countDSLCompleter,
				offset,
				size,
				sortField,
				sortDirection
		);
	}

	public boolean delete(int id) {
		return simpleManager.delete(id);
	}
}
