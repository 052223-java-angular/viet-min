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

    @Override
    public void save(Cart cart) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "insert into users (id, user_id) values (?, ?)";

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
    public Optional<Cart> findById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public List<Cart> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

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