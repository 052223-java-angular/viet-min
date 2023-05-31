/**
 * A class that represents an item in a shopping cart.
 * It has an id, a name, a cart id, a product id, a quantity and a price.
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
public class CartItem {
    /**
     * The id of the item, generated randomly using UUID.
     */
    private String id;
    /**
     * The name of the item.
     */
    private String name;
    /**
     * The id of the cart that contains the item.
     */
    private String cart_id;
    /**
     * The id of the product that corresponds to the item.
     */
    private String product_id;
    /**
     * The quantity of the item in the cart.
     */
    private int quantity;
    /**
     * The price of the item per unit.
     */
    private double price;

    /**
     * A constructor that takes the name, cart id, product id, quantity and price of the item and creates a new item with a random id.
     * @param name The name of the item.
     * @param cart_id The id of the cart that contains the item.
     * @param product_id The id of the product that corresponds to the item.
     * @param quantity The quantity of the item in the cart.
     * @param price The price of the item per unit.
     */
    public CartItem(String name, String cart_id, String product_id, int quantity, double price){
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.cart_id = cart_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.price = price;
    }
}