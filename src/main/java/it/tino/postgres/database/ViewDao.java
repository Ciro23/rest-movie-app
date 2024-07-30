package it.tino.postgres.database;

import java.sql.Connection;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public interface ViewDao<T> {

	List<T> selectByCriteria(Collection<Criteria> criterias, Connection connection);
	
	default List<T> selectByCriteria(Criteria criteria, Connection connection) {
		return selectByCriteria(Collections.singletonList(criteria), connection);
	}
}
