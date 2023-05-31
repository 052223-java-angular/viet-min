/**
 * A data access object (DAO) that implements the CrudDAO interface for the OrderItems model.
 * It provides methods to perform CRUD (create, read, update, delete) operations on the order_items table in the database.
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

import com.revature.app.models.OrderItems;
import com.revature.app.utils.ConnectionFactory;

public class OrderItemsDAO implements CrudDAO<OrderItems>{

    /**
     * Saves a new order item object to the database.
     * @param obj the order item object to be saved
     * @throws RuntimeException if any exception occurs while connecting to the database or executing the SQL statement
     */
    @Override
    public void save(OrderItems obj) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "insert into order_items (id, order_id, product_id, quantity) values (?, ?, ?, ?)";

            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, obj.getId());
                ps.setString(2, obj.getOrder_id());
                ps.setString(3, obj.getProduct_id());
                ps.setInt(4, obj.getQuantity());

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
     * Updates an existing order item object in the database by its id.
     * @param id the id of the order item object to be updated
     * @throws UnsupportedOperationException if this method is not implemented
     */
    @Override
    public void update(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    /**
     * Deletes an existing order item object from the database by its id.
     * @param id the id of the order item object to be deleted
     * @throws UnsupportedOperationException if this method is not implemented
     */
    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    /**
     * Finds an order item object by its id in the database.
     * @param id the id of the order item object to be found
     * @return an optional containing the order item object if found, or empty otherwise
     * @throws UnsupportedOperationException if this method is not implemented
     */
    @Override
    public Optional findById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    /**
     * Finds all order item objects in the database.
     * @return a list of all order item objects in the database
     * @throws UnsupportedOperationException if this method is not implemented
     */
    @Override
    public List findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }
    
    /**
     * Finds all order item objects by their order_id in the database.
     * @param id the order_id of the order item objects to be found
     * @return a list of all order item objects with the given order_id in the database
     * @throws RuntimeException if any exception occurs while connecting to the database or executing the SQL statement
     */
    public List<OrderItems> findbyOrderId(String id) {
        // Displays all order items on an order
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "SELECT * FROM order_items WHERE order_id = ?";

            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, id);
                try(ResultSet rs = ps.executeQuery()){
                    List<OrderItems> orders = new ArrayList<>();
                    while(rs.next()){
                        OrderItems order = new OrderItems();
                        order.setId(rs.getString("id"));
                        order.setOrder_id(rs.getString("order_id"));
                        order.setProduct_id(rs.getString("product_id"));
                        order.setQuantity(rs.getInt("quantity"));
                        
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