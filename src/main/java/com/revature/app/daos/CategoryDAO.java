/**
 * A data access object (DAO) that implements the CrudDAO interface for the Category model.
 * It provides methods to perform CRUD (create, read, update, delete) operations on the category table in the database.
 */
package com.revature.app.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.revature.app.models.Category;
import com.revature.app.utils.ConnectionFactory;


public class CategoryDAO implements CrudDAO {
    /**
     * Saves a new category object to the database.
     * @param obj the category object to be saved
     * @throws UnsupportedOperationException if this method is not implemented
     */
    @Override
    public void save(Object obj) {
        // Stretch goals for admin role
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    /**
     * Updates an existing category object in the database by its id.
     * @param id the id of the category object to be updated
     * @throws UnsupportedOperationException if this method is not implemented
     */
    @Override
    public void update(String id) {
        // Stretch goals for admin role
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    /**
     * Deletes an existing category object from the database by its id.
     * @param id the id of the category object to be deleted
     * @throws UnsupportedOperationException if this method is not implemented
     */
    @Override
    public void delete(String id) {
        // Stretch goal for admin role
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    /**
     * Finds a category object by its id in the database.
     * @param id the id of the category object to be found
     * @return an optional containing the category object if found, or empty otherwise
     * @throws UnsupportedOperationException if this method is not implemented
     */
    @Override
    public Optional findById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    /**
     * Finds all category objects in the database.
     * @return a list of all category objects in the database
     * @throws RuntimeException if any exception occurs while connecting to the database or executing the SQL statement
     */
    @Override
    public List<Category> findAll() {
        // Displays all categories
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "SELECT * FROM category";
            try(PreparedStatement ps = conn.prepareStatement(sql)){
                try(ResultSet rs = ps.executeQuery()){
                    List<Category> categories = new ArrayList<>();
                    while(rs.next()){
                        Category category = new Category();
                        category.setId(rs.getInt("id"));
                        category.setName(rs.getString("name"));
                        
                        categories.add(category);
                    }
                    return categories;
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException("Unable to connect to db \n" + e);
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties \n" + e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to load jdbc \n" + e);
        }
    }
}