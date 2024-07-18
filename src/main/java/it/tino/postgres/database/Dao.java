package it.tino.postgres.database;

public interface Dao<T, ID> {
	
	T insert(T entity);
	T update(T entity);
	T selectById(ID id);
	boolean delete(ID id);
}
