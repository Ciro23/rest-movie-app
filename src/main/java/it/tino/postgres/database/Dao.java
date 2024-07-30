package it.tino.postgres.database;

import java.sql.Connection;

public interface Dao<T, ID> extends ViewDao<T> {
	
	T insert(T entity, Connection connection);
	T update(T entity, Connection connection);
	
	T selectById(ID id, Connection connection);
	
	boolean delete(ID id, Connection connection);
}
