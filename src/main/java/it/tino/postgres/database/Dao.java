package it.tino.postgres.database;

import java.util.Collections;
import java.util.List;

public interface Dao<T, ID> {
	
	T insert(T entity);
	T update(T entity);
	
	T selectById(ID id);
	List<T> selectByCriteria(List<Criteria> criterias);
	default List<T> selectByCriteria(Criteria criteria) {
		return selectByCriteria(Collections.singletonList(criteria));
	}
	
	boolean delete(ID id);
}
