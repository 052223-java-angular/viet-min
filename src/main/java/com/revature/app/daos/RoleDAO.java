/**
 * A data access object (DAO) that implements the CrudDAO interface for the Role model.
 * It provides methods to perform CRUD (create, read, update, delete) operations on the roles table in the database.
 */
package com.revature.app.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.revature.app.models.Role;
import com.revature.app.utils.ConnectionFactory;

public class RoleDAO implements CrudDAO<Role> {

    /**
     * Saves a new role object to the database.
     * @param Object the role object to be saved
     * @throws UnsupportedOperationException if this method is not implemented
     */
    @Override
    public void save(Role Object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    /**
     * Updates an existing role object in the database by its id.
     * @param id the id of the role object to be updated
     * @throws UnsupportedOperationException if this method is not implemented
     */
    @Override
    public void update(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    /**
     * Deletes an existing role object from the database by its id.
     * @param id the id of the role object to be deleted
     * @throws UnsupportedOperationException if this method is not implemented
     */
    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    /**
     * Finds a role object by its id in the database.
     * @param id the id of the role object to be found
     * @return an optional containing the role object if found, or empty otherwise
     * @throws UnsupportedOperationException if this method is not implemented
     */
    @Override
    public Optional<Role> findById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    /**
     * Finds all role objects in the database.
     * @return a list of all role objects in the database
     * @throws UnsupportedOperationException if this method is not implemented
     */
    @Override
    public List<Role> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }
    
    /**
     * Finds a role object by its name in the database.
     * @param name the name of the role object to be found
     * @return an optional containing the role object if found, or empty otherwise
     * @throws RuntimeException if any exception occurs while connecting to the database or executing the SQL statement
     */
    public Optional<Role> findByName(String name) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "select * from roles where name = ?";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, name);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        Role role = new Role();
                        role.setId(rs.getString("id"));
                        role.setName(rs.getString("name"));
                        return Optional.of(role);
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Unable to connect to db");
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to load jdbc");
        } catch (Exception e){
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }
}