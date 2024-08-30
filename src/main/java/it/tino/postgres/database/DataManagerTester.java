package it.tino.postgres.database;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Utility to help manually test data managers.
 * Each method executes an action (save, find...) and prints
 * all the entities afterward to manually check that everything
 * works as expected.
 * @param <T> Data type handled by the manager.
 * @param <ID> Id type for the data handled by the manager.
 */
abstract public class DataManagerTester<T, ID> {
    
    protected abstract Supplier<List<T>> onSelectAll();
    protected abstract Function<T, T> onInsert();
    protected abstract Function<T, T> onUpdate();
    protected abstract Function<ID, Boolean> onDelete();
    
    /**
     * Callback to initialize a new object, used during
     * the insertion of a new object through the data manager.
     */
    protected abstract T onCreateObject();
    
    /**
     * Callback to update each attribute of an object, used during
     * the update of an object through the data manager.
     */
    protected abstract void onUpdateObject(T objectToUpdate);
    
    public List<T> getAll() {
        System.out.println("------- SELECT ALL -------");
        List<T> allRows = onSelectAll().get();
        System.out.println(allRows);
        System.out.println("--------------------------");
        
        return allRows;
    }
    
    public List<T> create() {
        System.out.println("--------- INSERT ---------");
        T entityToInsert = onCreateObject();
        T justCreatedEntity = onInsert().apply(entityToInsert);
        
        System.out.println("Just inserted entity: " + justCreatedEntity);
        System.out.println("--------------------------");
        
        return onSelectAll().get();
    }
    
    public List<T> update(T entityToUpdate) {
        System.out.println("--------- UPDATE ---------");
        onUpdateObject(entityToUpdate);
        T justUpdatedEntity = onUpdate().apply(entityToUpdate);
        
        System.out.println("Just updated entity: " + justUpdatedEntity);
        System.out.println("--------------------------");
        
        return onSelectAll().get();
    }
    
    public List<T> delete(ID id) {
        System.out.println("--------- DELETE ---------");
        boolean success = onDelete().apply(id);
        List<T> allRows = onSelectAll().get();
        
        System.out.println("Deletion result: " + success);
        System.out.println(allRows);
        System.out.println("--------------------------");
        
        return allRows;
    }
}
