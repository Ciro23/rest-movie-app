package it.tino.postgres.database;

import java.util.List;

import it.tino.postgres.Repository;

/**
 * Utility to help manually test data repositories.
 * Each method executes an action (save, find...) and prints
 * all the entities afterwards to manually check that everything
 * works as expected.
 * @param <T> Data type handled by the repository.
 * @param <ID> Id type for the data handled by the repository.
 */
abstract public class RepositoryTester<T, ID> {
    
    protected final Repository<T, ID> repository;
    
    public RepositoryTester(Repository<T, ID> repository) {    
        this.repository = repository;
    }
    
    /**
     * Callback to initialize a new object, used during
     * the insertion of a new object through the repository.
     */
    protected abstract T onCreateObject();
    
    /**
     * Callback to update each attribute of an object, used during
     * the update of an object through the repository.
     */
    protected abstract void onUpdateObject(T objectToUpdate);
    
    public List<T> getAll() {
        System.out.println("------- SELECT ALL -------");
        List<T> allRows = repository.findAll();
        System.out.println(allRows);
        System.out.println("--------------------------");
        
        return allRows;
    }
    
    public List<T> create() {
        System.out.println("--------- INSERT ---------");
        T entityToInsert = onCreateObject();
        T justCreatedEntity = repository.save(entityToInsert);
        
        System.out.println("Just inserted entity: " + justCreatedEntity);
        System.out.println("--------------------------");
        
        return repository.findAll();
    }
    
    public List<T> update(T entityToUpdate) {
        System.out.println("--------- UPDATE ---------");
        onUpdateObject(entityToUpdate);
        T justUpdatedEntity = repository.save(entityToUpdate);
        
        System.out.println("Just updated entity: " + justUpdatedEntity);
        System.out.println("--------------------------");
        
        return repository.findAll();
    }
    
    public List<T> delete(ID id) {
        System.out.println("--------- DELETE ---------");
        boolean success = repository.delete(id);
        List<T> allRows = repository.findAll();
        
        System.out.println("Deletion result: " + success);
        System.out.println(allRows);
        System.out.println("--------------------------");
        
        return allRows;
    }
}
