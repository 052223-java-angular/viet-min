/**
 * A data access object (DAO) that implements the CrudDAO interface for the Review model.
 * It provides methods to perform CRUD (create, read, update, delete) operations on the reviews table in the database.
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

import com.revature.app.models.Review;
import com.revature.app.utils.ConnectionFactory;

public class ReviewDAO implements CrudDAO<Review>{

    /**
     * Saves a new review object to the database.
     * @param obj the review object to be saved
     * @throws RuntimeException if any exception occurs while connecting to the database or executing the SQL statement
     */
    @Override
    public void save(Review obj) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "INSERT INTO reviews (id, rating, comment, user_id, product_id) VALUES (?, ?, ?, ?, ?)";

            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, obj.getId());
                ps.setInt(2, obj.getRating());
                ps.setString(3, obj.getComment());
                ps.setString(4, obj.getUser_id());
                ps.setString(5, obj.getProduct_id());
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
     * Updates an existing review object in the database by its id.
     * @param id the id of the review object to be updated
     * @throws UnsupportedOperationException if this method is not implemented
     */
    @Override
    public void update(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    /**
     * Updates an existing review object in the database by its rating and comment.
     * @param obj the review object to be updated
     * @throws RuntimeException if any exception occurs while connecting to the database or executing the SQL statement
     */
    public void update(Review obj) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "UPDATE reviews set rating = ?, comment = ? WHERE id = ?";

            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setInt(1, obj.getRating());
                ps.setString(2, obj.getComment());
                ps.setString(3, obj.getId());
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
     * Deletes an existing review object from the database by its id.
     * @param id the id of the review object to be deleted
     * @throws UnsupportedOperationException if this method is not implemented
     */
    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    /**
     * Finds a review object by its id in the database.
     * @param id the id of the review object to be found
     * @return an optional containing the review object if found, or empty otherwise
     * @throws UnsupportedOperationException if this method is not implemented
     */
    @Override
    public Optional<Review> findById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    /**
     * Finds all review objects in the database.
     * @return a list of all review objects in the database
     * @throws UnsupportedOperationException if this method is not implemented
     */
    @Override
    public List findAll() {
        // not used
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    /**
     * Finds a review object by its user id and product id in the database.
     * @param userId the user id of the review object to be found
     * @param productId the product id of the review object to be found
     * @return an optional containing the review object if found, or empty otherwise
     * @throws RuntimeException if any exception occurs while connecting to the database or executing the SQL statement
     */
    public Optional<Review> findById(String userId, String productId) {
        // Displays a specific review for updating
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "SELECT * FROM reviews WHERE product_id = ? AND user_id = ?";

            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, productId);
                ps.setString(2, userId);
                try(ResultSet rs = ps.executeQuery()){
                    Review review = new Review();
                    while(rs.next()){
                        review.setId(rs.getString("id"));
                        review.setRating(rs.getInt("rating"));
                        review.setComment(rs.getString("comment"));
                        review.setUser_id(rs.getString("user_id"));
                        review.setProduct_id(rs.getString("product_id"));
                    }
                    return Optional.of(review);
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
     * Finds all review objects by their product id in the database.
     * @param prodId the product id of the review objects to be found
     * @return a list of all review objects with the given product id in the database
     * @throws RuntimeException if any exception occurs while connecting to the database or executing the SQL statement
     */
    public List<Review> findAllByProduct(String prodId)
    {
        // Displays all reviews for a product by product
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "SELECT * FROM reviews WHERE product_id = ?";

            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, prodId);
                try(ResultSet rs = ps.executeQuery()){
                    List<Review> reviews = new ArrayList<>();
                    while(rs.next()){
                        Review review = new Review();
                        review.setId(rs.getString("id"));
                        review.setRating(rs.getInt("rating"));
                        review.setComment(rs.getString("comment"));
                        review.setUser_id(rs.getString("user_id"));
                        review.setProduct_id(rs.getString("product_id"));

                        reviews.add(review);
                    }
                    return reviews;
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
     * Finds all review objects by their user id in the database.
     * @param userId the user id of the review objects to be found
     * @return a list of all review objects with the given user id in the database
     * @throws RuntimeException if any exception occurs while connecting to the database or executing the SQL statement
     */
    public List<Review> findAllByUser(String userId)
    {
        // Displays all reviews for a product by user
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql ="SELECT * FROM reviews WHERE user_id = ?";

            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, userId);
                try(ResultSet rs = ps.executeQuery()){
                    List<Review> reviews = new ArrayList<>();
                    while(rs.next()){
                        Review review = new Review();
                        review.setId(rs.getString("id"));
                        review.setRating(rs.getInt("rating"));
                        review.setComment(rs.getString("comment"));
                        review.setUser_id(rs.getString("user_id"));
                        review.setProduct_id(rs.getString("product_id"));

                        reviews.add(review);
                    }
                    return reviews;
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