/**
 * A class that represents a shopping cart for an online store.
 * It has an id, a user id and a list of cart items.
 */
package com.revature.app.models;

import java.util.ArrayList;
import java.util.List;
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
public class Cart {
    /**
     * The id of the cart, generated randomly using UUID.
     */
    private String id;
    /**
     * The id of the user who owns the cart.
     */
    private String user_id;
    /**
     * The list of items in the cart, each item has a product and a quantity.
     */
    private List<CartItem> items = new ArrayList<>();;

    /**
     * A constructor that takes a user id and creates a new cart with a random id.
     * @param user_id The id of the user who owns the cart.
     */
    public Cart(String user_id) {
        this.id = UUID.randomUUID().toString();
        this.user_id = user_id;
    }
}