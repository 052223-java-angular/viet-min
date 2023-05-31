/**
 * A data access object (DAO) that implements the CrudDAO interface for the Order model.
 * It provides methods to perform CRUD (create, read, update, delete) operations on the orders table in the database.
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

import com.revature.app.models.Order;
import com.revature.app.utils.ConnectionFactory;

public class OrderDAO implements CrudDAO<Order>{

    /**
     * Saves a new order object to the database.
     * @param obj the order object to be saved
     * @throws RuntimeException if any exception occurs while connecting to the database or executing the SQL statement
     */
    @Override
    public void save(Order obj) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "insert into orders (id, user_id, total) values (?, ?, ?)";

            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, obj.getId());
                ps.setString(2, obj.getUser_id());
                ps.setDouble(3, obj.getTotal());

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
     * Updates an existing order object in the database by its id.
     * @param id the id of the order object to be updated
     * @throws UnsupportedOperationException if this method is not implemented
     */
    @Override
    public void update(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    /**
     * Deletes an existing order object from the database by its id.
     * @param id the id of the order object to be deleted
     * @throws UnsupportedOperationException if this method is not implemented
     */
    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    /**
     * Finds an order object by its id in the database.
     * @param id the id of the order object to be found
     * @return an optional containing the order object if found, or empty otherwise
     * @throws UnsupportedOperationException if this method is not implemented
     */
    @Override
    public Optional<Order> findById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    /**
     * Finds all order objects in the database.
     * @return a list of all order objects in the database
     * @throws UnsupportedOperationException if this method is not implemented
     */
    @Override
    public List findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }
    
    /**
     * Finds all order objects by their user_id in the database.
     * @param id the user_id of the order objects to be found
     * @return a list of all order objects with the given user_id in the database
     * @throws RuntimeException if any exception occurs while connecting to the database or executing the SQL statement
     */
    public List<Order> findAllByUser(String id) {
        // Displays all orders made by user
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "SELECT * FROM orders WHERE user_id = ?";

            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, id);
                try(ResultSet rs = ps.executeQuery()){
                    List<Order> orders = new ArrayList<>();
                    while(rs.next()){
                        Order order = new Order();
                        order.setId(rs.getString("id"));
                        order.setUser_id(rs.getString("user_id"));
                        order.setTotal(rs.getDouble("total"));
                        
                        orders.add(order);
                    }
                    return orders;
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