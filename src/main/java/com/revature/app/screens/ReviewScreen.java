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
    private RouterServices router;
    private Product product;
    private SessionUtil session;
    private final ReviewService reviewService;
    private final ProductService productService;
    private UserService userService;
    private static final Logger log = LogManager.getLogger(ReviewScreen.class);

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
        }
    }

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

    private boolean isInt(String input){
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
