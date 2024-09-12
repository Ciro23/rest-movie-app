package it.tino.restmovieapp.review;

import it.tino.restmovieapp.SimpleManager;
import it.tino.restmovieapp.mybatis.mapper.ReviewDbMapper;
import it.tino.restmovieapp.mybatis.model.ReviewDb;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewManager {

	private final SimpleManager<Review, ReviewDb, Integer> simpleManager;

	public ReviewManager(SqlSessionFactory sqlSessionFactory, ReviewDbObjectMapper reviewDbObjectMapper) {
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
				reviewDbObjectMapper,
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
		if (review.getCreationDate() == null) {
			Review existingReview = selectById(review.getId());
			review.setCreationDate(existingReview.getCreationDate());
		}
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
