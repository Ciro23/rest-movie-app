package it.tino.postgres;

import java.util.List;

/**
 * General data repository for common CRUD operations.
 * @param <T> Data type handled by the repository.
 * @param <ID> Id type for the data handled by the repository.
 */
public interface Repository<T, ID> {
    
	T save(T entity);
    List<T> findAll();
    boolean delete(ID id);
}
