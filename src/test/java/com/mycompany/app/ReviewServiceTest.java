package com.mycompany.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mock.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revature.app.daos.ReviewDAO;
import com.revature.app.models.Review;
import com.revature.app.services.ReviewService;
import com.revature.app.services.UserService;


public class ReviewServiceTest {
    @Mock
    private ReviewDAO reviewDAO;
    @Mock
    private UserService userService;
    private ReviewService reviewService;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);

        reviewService = new ReviewService(reviewDAO);
        
    }

    //This test asserts that a review is saved correctly
    @Test
    public void test_Save() {
        String userId = "7b58194d-bea3-4061-bfd0-9f035cda18b2";
        String productId = "a353098a-fb53-11ed-be56-0242ac120002";
        String comment = "I love Elder Scrolls";
        int rating = 5;
        String reviewID = UUID.randomUUID().toString();

        Review expected = new Review(reviewID, rating, comment, userId, productId);
        reviewService.save(expected);

        verify(reviewDAO, times(1)).save(expected);
    }

    //This test asserts that review has new comment and rating
    @Test
    public void test_Update() {
        test_Save();
        String userId = "7b58194d-bea3-4061-bfd0-9f035cda18b2";
        String productId = "a353098a-fb53-11ed-be56-0242ac120002";
        String comment = "I love sweet rolls";
        int rating = 4;
        String reviewID = UUID.randomUUID().toString();

        Review expected = new Review(reviewID, rating, comment, userId, productId);
        reviewService.update(expected);

        verify(reviewDAO, times(1)).update(expected);
    }

    //This test asserts to find all reviews by a user
    @Test
    public void test_ReviewByUser() {
        String userId = "7b58194d-bea3-4061-bfd0-9f035cda18b2";
        String productId = "a3531182-fb53-11ed-be56-0242ac120002";
        String comment = "Protect highrule";
        String productId2 = "a353098a-fb53-11ed-be56-0242ac120002";
        String comment2 = "I love Elder Scrolls";
        int rating = 4;
        String reviewID = UUID.randomUUID().toString();
        String reviewID2 = UUID.randomUUID().toString();
        Review r1 = new Review(reviewID, rating, comment, userId, productId);
        Review r2 = new Review(reviewID2, rating, comment2, userId, productId2);

        reviewService.save(r1);
        reviewService.save(r2);
        List<Review> expected = new ArrayList<>();
        expected.add(r1);
        expected.add(r2);

        when(reviewDAO.findAllByUser(userId)).thenReturn(expected);
        List<Review> actual = reviewService.reviewByUser(userId);

        assertEquals(expected, actual);    
    }

    //This test asserts to find all reviews by a product
    @Test
    public void test_ReviewByProduct() {
        String userId = "7b58194d-bea3-4061-bfd0-9f035cda18b2";
        String productId = "a3531182-fb53-11ed-be56-0242ac120002";
        String comment = "Protect highrule";
        String userId2 = "a353098a-fb53-11ed-be56-0242ac120002";
        String comment2 = "I love Elder Scrolls";
        int rating = 4;
        String reviewID = UUID.randomUUID().toString();
        String reviewID2 = UUID.randomUUID().toString();
        Review r1 = new Review(reviewID, rating, comment, userId, productId);
        Review r2 = new Review(reviewID2, rating, comment2, userId2, productId);

        reviewService.save(r1);
        reviewService.save(r2);
        List<Review> expected = new ArrayList<>();
        expected.add(r1);
        expected.add(r2);

        when(reviewDAO.findAllByProduct(productId)).thenReturn(expected);
        List<Review> actual = reviewService.reviewByProduct(productId);

        assertEquals(expected, actual);    
    }

    //This test asserts to find all reviews by a id
    @Test
    public void test_ReviewById() {
        String userId = "7b58194d-bea3-4061-bfd0-9f035cda18b2";
        String productId = "a3531182-fb53-11ed-be56-0242ac120002";
        String comment = "Protect highrule";
        int rating = 4;
        String reviewID = UUID.randomUUID().toString();
        Review expected = new Review(reviewID, rating, comment, userId, productId);

        reviewService.save(expected);

        when(reviewDAO.findById(userId, productId)).thenReturn(Optional.of(expected));
        Review actual = reviewService.findReview(userId, productId).get();

        assertEquals(expected, actual);    
    }
}
