/**
 * A class that represents an item in an order placed by a user in an online store.
 * It has an id, an order id, a product id and a quantity.
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
public class OrderItems {
    /**
     * The id of the item, generated randomly using UUID.
     */
    private String id;
    /**
     * The id of the order that contains the item.
     */
    private String order_id;
    /**
     * The id of the product that corresponds to the item.
     */
    private String product_id;
    /**
     * The quantity of the item in the order.
     */
    private int quantity;

    /**
     * A constructor that takes the order id, the product id and the quantity of the item and creates a new item with a random id.
     * @param order_id The id of the order that contains the item.
     * @param product_id The id of the product that corresponds to the item.
     * @param quantity The quantity of the item in the order.
     */
    public OrderItems(String order_id, String product_id, int quantity){
        this.id = UUID.randomUUID().toString();
        this.order_id = order_id;
        this.product_id = product_id;
        this.quantity = quantity;
    }
}