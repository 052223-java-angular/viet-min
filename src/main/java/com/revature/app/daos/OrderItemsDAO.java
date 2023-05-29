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
    

    @Override
    public void update(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Optional findById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public List findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }
    
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
