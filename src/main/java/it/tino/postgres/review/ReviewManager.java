package it.tino.postgres.review;

import it.tino.postgres.SimpleManager;
import it.tino.postgres.mybatis.mapper.ReviewDbMapper;
import it.tino.postgres.mybatis.model.ReviewDb;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewManager {

	private final SimpleManager<Review, ReviewDb, Integer> simpleManager;

	public ReviewManager(SqlSessionFactory sqlSessionFactory, ReviewMapper reviewMapper) {
		SimpleManager.InsertFunction<ReviewDb> onInsert = (sqlSession, key) -> {
			ReviewDbMapper dao = sqlSession.getMapper(ReviewDbMapper.class);
			return dao.insert(key);
		};
		SimpleManager.UpdateFunction<ReviewDb> onUpdate = (sqlSession, key) -> {
			ReviewDbMapper dao = sqlSession.getMapper(ReviewDbMapper.class);
			return dao.updateByPrimaryKey(key);
		};
		SimpleManager.SelectFunction<ReviewDb> onSelect = (sqlSession, key) -> {
			ReviewDbMapper dao = sqlSession.getMapper(ReviewDbMapper.class);
			return dao.select(key);
		};
		SimpleManager.SelectByIdFunction<ReviewDb, Integer> onSelectById = (sqlSession, key) -> {
			ReviewDbMapper dao = sqlSession.getMapper(ReviewDbMapper.class);
			return dao.selectByPrimaryKey(key);
		};
		SimpleManager.DeleteFunction<Integer> onDelete = (sqlSession, key) -> {
			ReviewDbMapper dao = sqlSession.getMapper(ReviewDbMapper.class);
			return dao.deleteByPrimaryKey(key);
		};
		this.simpleManager = new SimpleManager<>(
				sqlSessionFactory,
				reviewMapper,
				onInsert,
				onUpdate,
				onSelect,
				onSelectById,
				onDelete
		);
	}

	/**
	 * The current date time is saved using {@link Review#setCreationDate(LocalDateTime)}
	 * before insert, even if it already has a value.
	 */
	public Review insert(Review review) {
		review.setCreationDate(LocalDateTime.now());
		return simpleManager.insert(review);
	}
	
	public Review update(Review review) {
		return simpleManager.update(review);
	}
    
    public List<Review> selectAll() {
		return simpleManager.selectAll();
	}
    
   	public Review selectById(int id) {
		return simpleManager.selectById(id);
	}

    public List<Review> selectByCriteria(SelectDSLCompleter selectDSLCompleter) {
		return simpleManager.selectByCriteria(selectDSLCompleter);
	}

	public boolean delete(int id) {
		return simpleManager.delete(id);
	}
}
