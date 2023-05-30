package com.mycompany.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mock.*;

import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revature.app.daos.ReviewDAO;
import com.revature.app.models.Review;
import com.revature.app.models.User;
import com.revature.app.services.ReviewService;
import com.revature.app.services.UserService;


public class ReviewServiceTest {
    @Mock
    private ReviewDAO reviewDao;
    @Mock
    private UserService userService;
    private ReviewService reviewService;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);

        reviewService = new ReviewService(reviewDao);
    }

    //This test asserts that a review is saved correctly
    @Test
    private void test_Save() {
        User user = userService.register("user1234", "pass1234");
        String userId = user.getId();
        String productId = "a353098a-fb53-11ed-be56-0242ac120002";
        String comment = "I love Elder Scrolls";
        int rating = 5;
        String reviewID = UUID.randomUUID().toString();

        Review expected = new Review(reviewID, rating, comment, userId, productId);
        reviewService.save(expected);

        Review actual = reviewService.findReview(userId, productId).get();
        assertEquals(expected, actual);
    }

    //This test asserts that review has new comment and rating
    @Test
    private void test_Update() {
        test_Save();
        User user = userService.register("user1234", "pass1234");
        String userId = user.getId();
        String productId = "a353098a-fb53-11ed-be56-0242ac120002";
        String comment = "I love sweet rolls";
        int rating = 4;
        String reviewID = UUID.randomUUID().toString();

        Review expected = new Review(reviewID, rating, comment, userId, productId);
        reviewService.update(expected);

        Review actual = reviewService.findReview(userId, productId).get();
        assertEquals(expected, actual);
    }

    //This test asserts that to find all reviews by a user
    @Test
    private void test_ReviewByUser() {
        test_Save();
        String userId = userService.findByName("user1234").getId();
        String productId = "a3531182-fb53-11ed-be56-0242ac120002";
        String comment = "Protect highrule";
        int rating = 4;
        String reviewID = UUID.randomUUID().toString();
        Review r2 = new Review(reviewID, rating, comment, userId, productId);

        reviewService.save(r2);

        List<Review> actual = reviewService.reviewByUser(userId);
        int expected = 2;

        assertEquals(expected, actual.size());
        
    }
}
