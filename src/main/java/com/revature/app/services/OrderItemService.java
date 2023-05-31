/**
 * A service class that provides business logic for order items.
 * It uses an OrderItemsDAO object to perform CRUD operations on the order items table.
 */
package com.revature.app.services;

import java.util.List;

import com.revature.app.daos.OrderItemsDAO;
import com.revature.app.models.OrderItems;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OrderItemService {
    private final OrderItemsDAO orderItemsDAO;

    /**
     * Retrieves a list of order items that belong to a specific order by its id.
     * It uses the orderItemsDAO to find all the order items that have the same order id as the parameter.
     * @param id The id of the order whose items are to be retrieved.
     * @return A list of OrderItems objects that belong to the given order. 
     */
    public List<OrderItems> getOrderItems(String id) {
        return orderItemsDAO.findbyOrderId(id);
    }

    /**
     * Saves a new order item to the order items table.
     * It uses the orderItemsDAO to save the OrderItems object as a new record in the table.
     * @param obj The OrderItems object to be saved. 
     */
    public void save(OrderItems obj) {
        orderItemsDAO.save(obj);
    }
}