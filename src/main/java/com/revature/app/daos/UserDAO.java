/**
 * A data access object (DAO) that implements the CrudDAO interface for the User model.
 * It provides methods to perform CRUD (create, read, update, delete) operations on the users table in the database.
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

import com.revature.app.models.User;
import com.revature.app.utils.ConnectionFactory;

public class UserDAO implements CrudDAO<User>{

    /**
     * Saves a new user object to the database.
     * @param Object the user object to be saved
     * @throws RuntimeException if any exception occurs while connecting to the database or executing the SQL statement
     */
    @Override
    public void save(User Object) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "insert into users (id, username, password, role_id) values (?, ?, ?, ?)";

            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, Object.getId());
                ps.setString(2, Object.getUsername());
                ps.setString(3, Object.getPassword());
                ps.setString(4, Object.getRoleId());
                ps.executeUpdate();
            }

        }catch (SQLException e) {
            throw new RuntimeException("Unable to connect to db \n" + e);
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties \n" + e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to load jdbc \n" + e);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * Updates an existing user object in the database by its id and password.
     * @param id the id of the user object to be updated
     * @throws RuntimeException if any exception occurs while connecting to the database or executing the SQL statement
     */
    @Override
    public void update(String id) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "update users set password='?' where id = '?'";

            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, sql);
                ps.setString(2, sql);
                ps.executeUpdate();
            }
        }catch (SQLException e) {
            throw new RuntimeException("Unable to connect to db \n" + e);
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties \n" + e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to load jdbc \n" + e);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * Deletes an existing user object from the database by its id.
     * @param id the id of the user object to be deleted
     * @throws RuntimeException if any exception occurs while connecting to the database or executing the SQL statement
     */
    @Override
    public void delete(String id) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "delete from users where id = ?";

            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, id);
                ps.executeUpdate();
            }
        }catch (SQLException e) {
            throw new RuntimeException("Unable to connect to db \n" + e);
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties \n" + e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to load jdbc \n" + e);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * Finds a user object by its id in the database.
     * @param id the id of the user object to be found
     * @return an optional containing the user object if found, or empty otherwise
     * @throws RuntimeException if any exception occurs while connecting to the database or executing the SQL statement
     */
    @Override
    public Optional<User> findById(String id) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "select * from users where id = ?";

            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, id);
                try(ResultSet rs = ps.executeQuery()){
                    if(rs.next()){
                        User user = new User();
                        user.setId(rs.getString("id"));
                        user.setUsername(rs.getString("username"));
                        user.setPassword(rs.getString("password"));
                        user.setRoleId(rs.getString("role_id"));
                        return Optional.of(user);
                    }
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException("Unable to connect to db \n" + e);
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties \n" + e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to load jdbc \n" + e);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    /**
     * Finds all user objects in the database.
     * @return a list of all user objects in the database
     * @throws RuntimeException if any exception occurs while connecting to the database or executing the SQL statement
     */
    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "select * from users";
            try(PreparedStatement ps = conn.prepareStatement(sql)){
                try(ResultSet rs = ps.executeQuery()){
                    while(rs.next()){
                        User user = new User();
                        user.setId("id");
                        user.setUsername("username");
                        user.setPassword("password");
                        user.setRoleId("role_id");
                        users.add(user);
                    }
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException("Unable to connect to db \n" + e);
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties \n" + e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to load jdbc \n" + e);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return users;
    }

    /**
     * Finds a user object by its username in the database.
     * @param username the username of the user object to be found
     * @return an optional containing the user object if found, or empty otherwise
     * @throws RuntimeException if any exception occurs while connecting to the database or executing the SQL statement
     */
    public Optional<User> findByUsername(String username) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "select * from users where username = ?";
            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, username);
                try(ResultSet rs = ps.executeQuery()){
                    if(rs.next()){
                        User user = new User();
                        user.setId(rs.getString("id"));
                        user.setUsername(rs.getString("username"));
                        user.setPassword(rs.getString("password"));
                        user.setRoleId(rs.getString("role_id"));
                        return Optional.of(user);
                    }
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException("Unable to connect to db \n" + e);
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties \n" + e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to load jdbc \n" + e);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
    
}