/*
This interface allows decoupling Business layer logic objects from their persistence layer implementation.
source: https://www.baeldung.com/java-dao-pattern
 */
package org.pineapple.db.interfaces;

import java.util.List;
import java.util.Optional;

/**
 * This interface defines the functionality expected from a persistence layer implementation for object of type T.
 * @param <T>
 */
public interface DAO<T>
{
    /**
     * Returns the object with id from the database. The returned Optional object can indicate if the
     * requested object has been found by calling the isPresent() method on it.
     * @param id
     * @return
     */
    Optional<T> get(long id);

    /**
     * Returns a list of all objects of type T in the database.
     * @return
     */
    List<T> getAll();

    /**
     * Saves a new object of type T to the database.
     * @param t
     */
    void save(T t);

    /**
     * Updates an existing object of type T in the database.
     * The new values are given as an array of Strings
     * @param t
     */
    void update(T t);

    /**
     * Removes the given object of type T from the databse.
     * @param t
     */
    void delete(T t);
}
