/**
 * A class that represents an order placed by a user in an online store.
 * It has an id, a user id and a total amount.
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

public class Order {
    /**
     * The id of the order, generated randomly using UUID.
     */
    private String id;
    /**
     * The id of the user who placed the order.
     */
    private String user_id;
    /**
     * The total amount of the order.
     */
    private double total;

    /**
     * A constructor that takes the user id and the total amount of the order and creates a new order with a random id.
     * @param user_id The id of the user who placed the order.
     * @param total The total amount of the order.
     */
    public Order(String user_id, double total){
        this.id = UUID.randomUUID().toString();
        this.user_id = user_id;
        this.total = total;
    }
}