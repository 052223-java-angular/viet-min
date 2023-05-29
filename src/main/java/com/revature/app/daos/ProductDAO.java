package com.revature.app.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.revature.app.models.Product;
import com.revature.app.utils.ConnectionFactory;

public class ProductDAO implements CrudDAO {

    @Override
    public void save(Object obj) {
        // Stretch goals for admin role
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public void update(String id) {
        // Stretch goals for admin role
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(String id) {
        // Stretch goal for admin role
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Optional<Product> findById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public List<Product> findAll() {
        // Displays all products - sort list in business logic?
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "SELECT * FROM products";
            try(PreparedStatement ps = conn.prepareStatement(sql)){
                try(ResultSet rs = ps.executeQuery()){
                    List<Product> products = new ArrayList<>();
                    while(rs.next()){
                        Product product = new Product();
                        product.setId(rs.getString("id"));
                        product.setName(rs.getString("name"));
                        product.setDescription(rs.getString("description"));
                        product.setPrice(rs.getDouble("price"));
                        product.setCategory(rs.getInt("category"));
                        product.setStock(rs.getInt("stock"));
                        
                        products.add(product);
                    }
                    return products;
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

    public List<Product> findByCategory(int category) {
        // Displays products that fit a category
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "SELECT * FROM products WHERE category = ?";

            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setInt(1, category);
                try(ResultSet rs = ps.executeQuery()){
                    List<Product> products = new ArrayList<>();
                    while(rs.next()){
                        Product product = new Product();
                        product.setId(rs.getString("id"));
                        product.setName(rs.getString("name"));
                        product.setDescription(rs.getString("description"));
                        product.setPrice(rs.getDouble("price"));
                        product.setCategory(rs.getInt("category"));
                        product.setStock(rs.getInt("stock"));

                        products.add(product);
                    }
                    return products;
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

    public Optional<Product> findByName(String prodName) {
        // Displays products that match name search
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "SELECT * FROM products WHERE = ?";

            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, prodName);
                try(ResultSet rs = ps.executeQuery()){
                    if(rs.next()){
                        Product product = new Product();
                        product.setId(rs.getString("id"));
                        product.setName(rs.getString("name"));
                        product.setDescription(rs.getString("description"));
                        product.setPrice(rs.getDouble("price"));
                        product.setCategory(rs.getInt("category"));
                        product.setStock(rs.getInt("stock"));
                        
                        return Optional.of(product);
                    }
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException("Unable to connect to db \n" + e);
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties \n" + e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to load jdbc \n" + e);
        }
        return Optional.empty();
    }

    public List<Product> findByPriceRange(double minPrice, double maxPrice) {
        // Displays products that match name search
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "SELECT * FROM products WHERE price > ? AND price < ?";

            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setDouble(1, minPrice);
                ps.setDouble(2, maxPrice);
                try(ResultSet rs = ps.executeQuery()){
                    List<Product> products = new ArrayList<>();
                    while(rs.next()){
                        Product product = new Product();
                        product.setId(rs.getString("id"));
                        product.setName(rs.getString("name"));
                        product.setDescription(rs.getString("description"));
                        product.setPrice(rs.getDouble("price"));
                        product.setCategory(rs.getInt("category"));
                        product.setStock(rs.getInt("stock"));

                        products.add(product);
                    }
                    return products;
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

    public Optional<Product> getProduct(String id) {
        // gets product from id
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "SELECT * FROM products WHERE id = ?";

            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, id);
                try(ResultSet rs = ps.executeQuery()){
                    Product product = new Product();
                    if(rs.next()){
                        product.setId(rs.getString("id"));
                        product.setName(rs.getString("name"));
                        product.setDescription(rs.getString("description"));
                        product.setPrice(rs.getDouble("price"));
                        product.setCategory(rs.getInt("category"));
                        product.setStock(rs.getInt("stock"));
                        return Optional.of(product);
                    }
                    
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException("Unable to connect to db \n" + e);
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties \n" + e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to load jdbc \n" + e);
        }
        return Optional.empty();
    }
}
