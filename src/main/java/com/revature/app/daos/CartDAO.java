/**
 * A data access object (DAO) that implements the CrudDAO interface for the Cart model.
 * It provides methods to perform CRUD (create, read, update, delete) operations on the cart table in the database.
 */
package com.revature.app.daos;

import java.util.List;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.revature.app.models.Cart;
import com.revature.app.models.User;
import com.revature.app.utils.ConnectionFactory;

public class CartDAO implements CrudDAO<Cart>{

    /**
     * Saves a new cart object to the database.
     * @param cart the cart object to be saved
     * @throws RuntimeException if any exception occurs while connecting to the database or executing the SQL statement
     */
    @Override
    public void save(Cart cart) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "insert into cart (id, user_id) values (?, ?)";

            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, cart.getId());
                ps.setString(2, cart.getUser_id());
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
     * Updates an existing cart object in the database.
     * @param id the id of the cart object to be updated
     * @throws UnsupportedOperationException if this method is not implemented
     */
    @Override
    public void update(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    /**
     * Deletes an existing cart object from the database.
     * @param id the id of the cart object to be deleted
     * @throws UnsupportedOperationException if this method is not implemented
     */
    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    /**
     * Finds a cart object by its id in the database.
     * @param id the id of the cart object to be found
     * @return an optional containing the cart object if found, or empty otherwise
     * @throws UnsupportedOperationException if this method is not implemented
     */
    @Override
    public Optional<Cart> findById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    /**
     * Finds all cart objects in the database.
     * @return a list of all cart objects in the database
     * @throws UnsupportedOperationException if this method is not implemented
     */
    @Override
    public List<Cart> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    /**
     * Finds a cart object by its user_id in the database.
     * @param user_id the user_id of the cart object to be found
     * @return an optional containing the cart object if found, or empty otherwise
     * @throws RuntimeException if any exception occurs while connecting to the database or executing the SQL statement
     */
    public Optional<Cart> findByUserId(String user_id) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "select * from cart where user_id = ?";
            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, user_id);
                try(ResultSet rs = ps.executeQuery()){
                    if(rs.next()){
                        Cart cart = new Cart();
                        cart.setId(rs.getString("id"));
                        cart.setUser_id(rs.getString("user_id"));;
                        return Optional.of(cart);
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