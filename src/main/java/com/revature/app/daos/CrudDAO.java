package com.revature.app.daos;

import java.util.List;
import java.util.Optional;

/**
 * A generic interface that defines the common CRUD (create, read, update, delete) operations for any model class.
 * @param <T> the type of the model class
 */
public interface CrudDAO<T> {
    /**
     * Saves a new object of type T to the database.
     * @param object the object of type T to be saved
     */
    void save(T object);

    /**
     * Updates an existing object of type T in the database by its id.
     * @param id the id of the object of type T to be updated
     */
    void update(String id);

    /**
     * Deletes an existing object of type T from the database by its id.
     * @param id the id of the object of type T to be deleted
     */
    void delete(String id);

    /**
     * Finds an object of type T by its id in the database.
     * @param id the id of the object of type T to be found
     * @return an optional containing the object of type T if found, or empty otherwise
     */
    Optional<T> findById(String id);

    /**
     * Finds all objects of type T in the database.
     * @return a list of all objects of type T in the database
     */
    List<T> findAll();
}