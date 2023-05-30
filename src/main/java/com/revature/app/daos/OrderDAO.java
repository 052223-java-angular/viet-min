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

    @Override
    public void save(Order obj) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "insert into orders (id, user_id, total) values (?, ?, ?)";

            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, obj.getId());
                ps.setString(2, obj.getUser_id());
                ps.setDouble(4, obj.getTotal());

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
    public Optional<Order> findById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public List findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }
    
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
