package com.revature.app.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.revature.app.models.CartItem;
import com.revature.app.utils.ConnectionFactory;

public class CartItemDAO implements CrudDAO<CartItem> {

    @Override
    public void save(CartItem cartItem) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "insert into cart_items (id, cart_id, product_id, quantity) values (?, ?, ?, ?)";

            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, cartItem.getId());
                ps.setString(2, cartItem.getCart_id());
                ps.setString(3, cartItem.getProduct_id());
                ps.setInt(4, cartItem.getQuantity());

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

    public void update(String id, int quantity) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "update cart_items set quantity = ? where id = ?";

            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setInt(1, quantity);
                ps.setString(2, id);
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
    public void delete(String id) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "delete from cart_items where id = ?";

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

    @Override
    public Optional<CartItem> findById(String id) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "select * from cart_items where id = ?";

            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, id);
                try(ResultSet rs = ps.executeQuery()){
                    if(rs.next()){
                        CartItem cartItem = new CartItem();
                        cartItem.setId(rs.getString("id"));
                        cartItem.setCart_id(rs.getString("cart_id"));
                        cartItem.setProduct_id(rs.getString("product_id"));
                        cartItem.setQuantity(rs.getInt("quantity"));
                        return Optional.of(cartItem);
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
        return  Optional.empty();
    }

    @Override
    public List<CartItem> findAll() {
        List<CartItem> cartItems = new ArrayList<>();
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "SELECT c.id, c.quantity, c.cart_id, c.product_id, p.name, p.price FROM cart_items c INNER JOIN products p ON c.product_id = p.id";
            try(PreparedStatement ps = conn.prepareStatement(sql)){
                try(ResultSet rs = ps.executeQuery()){
                    while(rs.next()){
                        CartItem cartItem = new CartItem();
                        cartItem.setId(rs.getString("id"));
                        cartItem.setName(rs.getString("name"));
                        cartItem.setCart_id(rs.getString("cart_id"));
                        cartItem.setProduct_id(rs.getString("product_id"));
                        cartItem.setQuantity(rs.getInt("quantity"));
                        cartItem.setPrice(rs.getDouble("price"));
                        cartItems.add(cartItem);
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
        return cartItems;
    }

    public List<CartItem> findByCartId(String cart_id) {
        List<CartItem> cartItems = new ArrayList<>();
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "SELECT c.id, c.quantity, c.cart_id, c.product_id, p.name, p.price FROM cart_items c INNER JOIN products p ON c.product_id = p.id AND c.cart_id = ?";
            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, cart_id);
                try(ResultSet rs = ps.executeQuery()){
                    while(rs.next()){
                        CartItem cartItem = new CartItem();
                        cartItem.setId(rs.getString("id"));
                        cartItem.setName(rs.getString("name"));
                        cartItem.setCart_id(rs.getString("cart_id"));
                        cartItem.setProduct_id(rs.getString("product_id"));
                        cartItem.setQuantity(rs.getInt("quantity"));
                        cartItem.setPrice(rs.getDouble("price"));
                        cartItems.add(cartItem);
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
        return cartItems;
    }

    public void deleteByCartId(String id) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "delete from cart_items where cart_id = ?";

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
    
}
