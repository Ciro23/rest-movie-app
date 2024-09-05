package it.tino.postgres.genre;

import it.tino.postgres.SimpleManager;
import it.tino.postgres.mybatis.model.GenreDb;
import it.tino.postgres.mybatis.mapper.GenreDbMapper;
import jakarta.inject.Inject;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;

import java.util.List;

public class GenreManager {

	private final SimpleManager<Genre, GenreDb, Integer> simpleManager;

	public GenreManager(SqlSessionFactory sqlSessionFactory, GenreMapper genreMapper) {
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

	public List<Genre> selectByCriteria(SelectDSLCompleter selectDSLCompleter) {
		return simpleManager.selectByCriteria(selectDSLCompleter);
	}

	public boolean delete(int id) {
		return simpleManager.delete(id);
	}
}
