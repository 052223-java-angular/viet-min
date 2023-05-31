/**
 * A service class that provides business logic for reviews.
 * It uses a ReviewDAO object to perform CRUD operations on the reviews table.
 */
package com.revature.app.services;

import com.revature.app.daos.ReviewDAO;
import com.revature.app.models.Product;
import com.revature.app.models.Review;
import com.revature.app.utils.SessionUtil;
import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReviewService {
    private final ReviewDAO reviewDAO;

    /**
     * Retrieves a list of reviews that belong to a specific user by their id.
     * It uses the reviewDAO to find all the reviews that have the same user id as the parameter.
     * @param id The id of the user whose reviews are to be retrieved.
     * @return A list of Review objects that belong to the given user. 
     */
    public List<Review> reviewByUser(String id) {
        return reviewDAO.findAllByUser(id);
        //return productDao.findAll();
    }

    /**
     * Retrieves a list of reviews that belong to a specific product by its id.
     * It uses the reviewDAO to find all the reviews that have the same product id as the parameter.
     * @param id The id of the product whose reviews are to be retrieved.
     * @return A list of Review objects that belong to the given product. 
     */
    public List<Review> reviewByProduct(String id)  {
        //Optional<Product> prodOpt = productDao.findByName(name);
        return reviewDAO.findAllByProduct(id);
        //return (List<Product>)prodOpt.orElseThrow(ProductNotFoundException::new);
    }

    /**
     * Saves a new review to the reviews table.
     * It uses the reviewDAO to save the Review object as a new record in the table.
     * @param review The Review object to be saved. 
     */
    public void save(Review review) {
        reviewDAO.save(review);
    }

    /**
     * Updates an existing review in the reviews table.
     * It uses the reviewDAO to update the Review object by its user id and product id in the table. 
     * @param review The Review object to be updated. 
     */
    public void update(Review review) {
        reviewDAO.update(review);
    }

    /**
     * Retrieves an optional review object that belongs to a specific user and product by their ids.
     * It uses the reviewDAO to find the review that has the same user id and product id as the parameters.
     * @param userId The id of the user who wrote the review.
     * @param productId The id of the product that was reviewed.
     * @return An optional Review object that belongs to the given user and product. 
     */
    public Optional<Review> findReview(String userId, String productId) {
        return reviewDAO.findById(userId, productId);
    }
}