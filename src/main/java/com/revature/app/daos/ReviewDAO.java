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

    @Override
    public void save(Review obj) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "INSERT INTO review (id, rating, comment, user_id, product_id) VALUES (?, ?, ?, ?, ?)";

            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, obj.getId());
                ps.setInt(2, obj.getRating());
                ps.setString(3, obj.getComment());
                ps.setString(4, obj.getUser_id());
                ps.setString(4, obj.getProduct_id());
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

    public void update(Review obj) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "UPDATE review (rating, comment) VALUES (?, ?) WHERE id = ?";

            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setInt(2, obj.getRating());
                ps.setString(3, obj.getComment());
                ps.setString(1, obj.getId());
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Optional<Review> findById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public List findAll() {
        // not used
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

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
