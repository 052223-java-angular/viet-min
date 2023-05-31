/**
 * A screen that displays and manages reviews for products and users.
 */
package com.revature.app.screens;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.revature.app.daos.ProductDAO;
import com.revature.app.models.Product;
import com.revature.app.models.Review;
import com.revature.app.models.User;
import com.revature.app.services.ProductService;
import com.revature.app.services.ReviewService;
import com.revature.app.services.RouterServices;
import com.revature.app.services.UserService;
import com.revature.app.utils.SessionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReviewScreen implements IScreen {
    private RouterServices router; // A service that handles navigation between screens
    private Product product; // The product that is being reviewed or null if reviewing by user
    private SessionUtil session; // A utility class that stores the current user's information
    private final ReviewService reviewService; // A service that handles review-related operations
    private final ProductService productService; // A service that handles product-related operations
    private UserService userService; // A service that handles user-related operations
    private static final Logger log = LogManager.getLogger(ReviewScreen.class); // A logger for logging messages

    /**
     * Starts the review screen and displays the appropriate options based on the product and user.
     * @param scan The scanner object for getting user input
     */
    @Override
    public void start(Scanner scan) {
        log.info("navigated to review screen");
        while(true) {
            if (product == null)
            {
                clearScreen();
                log.info("retrieving user review data");
                printReviewByUser(reviewService.reviewByUser(session.getId()));
                System.out.println("\nPress any key to return to main menu...");
                scan.nextLine();
                router.navigate("/menu", scan);
                break;
            }
            else {
                clearScreen();
                printReviewByProd(reviewService.reviewByProduct(product.getId()));
                System.out.println("\n[1] Add a review");
                System.out.println("Press any key to return to product detail...");
                System.out.print("\nEnter: ");

                if (scan.nextLine().equals("1"))
                {
                    Review review = reviewService.findReview(session.getId(), product.getId()).orElseGet(null);
                    if(review.getId() != null) {
                        clearScreen();
                        System.out.println("You have already made a review for " + product.getName());
                        System.out.println("Would you like to update your review? (y/n)");

                        if (scan.nextLine().equalsIgnoreCase("y")) {
                            log.info("user inputing new review");
                            updateReview(review, scan);
                            log.info("updating database with new user review");
                            reviewService.update(review);
                            log.info("navigating to product detail screen");
                            router.navigate("/detail", scan);
                        }
                        else {
                            System.out.print("Press any key to return to product detail...");
                            scan.nextLine();
                            log.info("navigating to product detail screen");
                            router.navigate("/detail", scan);
                        }
                    }
                    else {
                        log.info("creating new review");
                        review = new Review(session.getId(), product.getId());
                        updateReview(review, scan);
                        log.info("saving review data");
                        reviewService.save(review);
                        log.info("navigating to product detail screen");
                        router.navigate("/detail", scan);
                    }
                }
                else {
                    router.navigate("/detail", scan);
                }
            }
            break;
        }
    }

    /**
     * Prints the reviews for a given product in a formatted way.
     * @param reviews The list of reviews for the product
     */
    private void printReviewByProd (List<Review> reviews) {
        if (reviews.isEmpty()) {
            System.out.println("There are no reviews for" + product.getName());
        }
        log.info("printing product review data");
        for (Review review : reviews) {
            System.out.println("=====================================================");
            User user = userService.findById(review.getUser_id());
            System.out.println("Review by: " + user.getUsername());
            System.out.println("Rating: " + review.getRating() + "/5");
            System.out.println("Comment: " + review.getComment());
            }
    }

    /**
     * Prints the reviews made by the current user in a formatted way.
     * @param reviews The list of reviews made by the user
     */
    private void printReviewByUser (List<Review> reviews) {
        if (reviews == null) {
            System.out.println("You have not reviewed any products");
        }
        log.info("printing user review data");
        for (Review review : reviews) {
            product = productService.getProd(review.getProduct_id()).get();
            System.out.println("=====================================================");
            System.out.println("Review for : " + product.getName());
            System.out.println("Rating: " + review.getRating() + "/5");
            System.out.println("Comment: " + review.getComment());
            }
    }

    /**
     * Updates the review object with the user input for rating and comment.
     * @param review The review object to be updated
     * @param scan The scanner object for getting user input
     */
    private void updateReview(Review review, Scanner scan) {
        while(true) {
            System.out.println("Please enter a rating (1-5): ");
            String input = scan.nextLine();
            if (isInt(input)) {
                log.info("user input for new product rating " + input);
                review.setRating(Integer.parseInt(input));
            }
            else {
                log.warn("invalid input");
                System.out.println("Invalid option! Please enter between (1-" + product.getStock()+")");
                    System.out.print("\nPress enter to continue...");
                    scan.nextLine();
                    break;
            }
            System.out.println("Please add your thoughts on product: ");
            review.setComment(scan.nextLine());
            log.info("user input for new product comment");
            break;
        }
    }

    /**
     * Checks if a given string is an integer or not.
     * @param input The string to be checked
     * @return true if the string is an integer, false otherwise
     */
    private boolean isInt(String input){
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     * Clears the screen by printing escape characters.
     */
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}