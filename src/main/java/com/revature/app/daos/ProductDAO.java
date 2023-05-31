/**
 * A data access object (DAO) that implements the CrudDAO interface for the Product model.
 * It provides methods to perform CRUD (create, read, update, delete) operations on the products table in the database.
 */
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

    /**
     * Saves a new product object to the database.
     * @param obj the product object to be saved
     * @throws UnsupportedOperationException if this method is not implemented
     */
    @Override
    public void save(Object obj) {
        // Stretch goals for admin role
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    /**
     * Updates an existing product object in the database by its id.
     * @param id the id of the product object to be updated
     * @throws UnsupportedOperationException if this method is not implemented
     */
    @Override
    public void update(String id) {
        // Stretch goals for admin role
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    /**
     * Deletes an existing product object from the database by its id.
     * @param id the id of the product object to be deleted
     * @throws UnsupportedOperationException if this method is not implemented
     */
    @Override
    public void delete(String id) {
        // Stretch goal for admin role
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    /**
     * Finds a product object by its id in the database.
     * @param id the id of the product object to be found
     * @return an optional containing the product object if found, or empty otherwise
     * @throws UnsupportedOperationException if this method is not implemented
     */
    @Override
    public Optional<Product> findById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    /**
     * Finds all product objects in the database.
     * @return a list of all product objects in the database
     * @throws RuntimeException if any exception occurs while connecting to the database or executing the SQL statement
     */
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

    /**
     * Finds all product objects by their category in the database.
     * @param category the category of the product objects to be found
     * @return a list of all product objects with the given category in the database
     * @throws RuntimeException if any exception occurs while connecting to the database or executing the SQL statement
     */
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

    /**
     * Finds all product objects by their name in the database.
     * @param prodName the name of the product objects to be found
     * @return an optional containing a list of product objects if found, or empty otherwise
     * @throws RuntimeException if any exception occurs while connecting to the database or executing the SQL statement
     */
    public Optional<List<Product>> findByName(String prodName) {
        // Displays products that match name search
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "SELECT * FROM products WHERE LOWER(name) = LOWER(?)";

            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, prodName);
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
                    return Optional.of(products);
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

    /**
     * Finds all product objects by their price range in the database.
     * @param minPrice the minimum price of the product objects to be found
     * @param maxPrice the maximum price of the product objects to be found
     * @return a list of all product objects with the given price range in the database
     * @throws RuntimeException if any exception occurs while connecting to the database or executing the SQL statement
     */
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

    /**
     * Gets a product object by its id in the database.
     * @param id the id of the product object to be found
     * @return an optional containing the product object if found, or empty otherwise
     * @throws RuntimeException if any exception occurs while connecting to the database or executing the SQL statement
     */
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

    /**
     * Sets the stock of a product object in the database by its id and quantity.
     * @param id the id of the product object to be updated
     * @param quantity the quantity of the stock to be set
     * @throws RuntimeException if any exception occurs while connecting to the database or executing the SQL statement
     */
    public void setStock(String id, int quantity) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "UPDATE products set stock = ? WHERE id = ?";

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
}