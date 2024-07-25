package it.tino.postgres.database;

import java.sql.Connection;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public interface Dao<T, ID> {
	
	T insert(T entity, Connection connection);
	T update(T entity, Connection connection);
	
	T selectById(ID id, Connection connection);
	List<T> selectByCriteria(Collection<Criteria> criterias, Connection connection);
	default List<T> selectByCriteria(Criteria criteria, Connection connection) {
		return selectByCriteria(Collections.singletonList(criteria), connection);
	}
	
	boolean delete(ID id, Connection connection);
}
