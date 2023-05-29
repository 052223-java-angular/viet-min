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

    public List<Review> reviewByUser(String id) {
        return reviewDAO.findAllByUser(id);
        //return productDao.findAll();
    }

    public List<Review> reviewByProduct(String id)  {
        //Optional<Product> prodOpt = productDao.findByName(name);
        return reviewDAO.findAllByProduct(id);
        //return (List<Product>)prodOpt.orElseThrow(ProductNotFoundException::new);
    }

    public void save(Review review) {
        reviewDAO.save(review);
    }

    public void update(Review review) {
        reviewDAO.update(review);
    }

    public Optional<Review> findReview(String userId, String productId) {
        return reviewDAO.findById(userId, productId);
    }
}
