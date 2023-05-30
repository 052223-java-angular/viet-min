/**
 * A class that represents a review for a product in an online store.
 * It has an id, a rating, a comment, a user id and a product id.
 */
package com.revature.app.models;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Review {
    /**
     * The id of the review, generated randomly using UUID.
     */
    private String id;
    /**
     * The rating of the review, from 1 to 5 stars.
     */
    private int rating;
    /**
     * The comment of the review, optional.
     */
    private String comment;
    /**
     * The id of the user who wrote the review.
     */
    private String user_id;
    /**
     * The id of the product that the review is for.
     */
    private String product_id;

    /**
     * A constructor that takes the user id and the product id and creates a new review with a random id.
     * @param user_Id The id of the user who wrote the review.
     * @param prod_id The id of the product that the review is for.
     */
    public Review(String user_Id, String prod_id)
    {
        this.id = UUID.randomUUID().toString();
        this.product_id = prod_id;
        this.user_id = user_Id;
    }
}