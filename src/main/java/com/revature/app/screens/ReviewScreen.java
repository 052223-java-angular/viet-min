package com.revature.app.screens;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.revature.app.models.Product;
import com.revature.app.models.Review;
import com.revature.app.models.User;
import com.revature.app.services.ReviewService;
import com.revature.app.services.RouterServices;
import com.revature.app.services.UserService;
import com.revature.app.utils.SessionUtil;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReviewScreen implements IScreen {
    private RouterServices router;
    private Product product;
    private SessionUtil session;
    private final ReviewService reviewService;
    private UserService userService;

    @Override
    public void start(Scanner scan) {
        while(true) {
            if (product.getId() == null)
            {
                clearScreen();
                printReviewByUser(reviewService.reviewByUser(session.getId()));
                System.out.println("Any key to return to main menu...");
                router.navigate("/menu", scan);
            }
            else {
                clearScreen();
                printReviewByProd(reviewService.reviewByProduct(product.getId()));
                System.out.println("[1] Add a review");
                System.out.println("Any key to return to product detail...");
                System.out.print("\nEnter: ");

                if (scan.nextLine().equals("1"))
                {

                }
                else {
                    router.navigate("/detail", scan);
                }
            }
        }
    }

    private void printReviewByProd (List<Review> reviews) {
        if (reviews.isEmpty()) {
            System.out.println("There are no reviews for" + product.getName());
        }
        for (Review review : reviews) {
            System.out.println("=====================================================");
            User user = userService.findById(review.getUser_id());
            System.out.println("Review by: " + session.getUsername());
            System.out.println("Rating: " + review.getRating() + "/5");
            System.out.println("Comment: " + review.getComment());
            }
    }

    private void printReviewByUser (List<Review> reviews) {
        if (reviews == null) {
            System.out.println("You have not reviewed any products");
        }
        for (Review review : reviews) {
            System.out.println("=====================================================");
            System.out.println("Review for : " + product.getName());
            System.out.println("Rating: " + review.getRating() + "/5");
            System.out.println("Comment: " + review.getComment());
            }
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
