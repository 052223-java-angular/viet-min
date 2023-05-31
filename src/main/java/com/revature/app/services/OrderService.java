/**
 * A service class that provides business logic for orders.
 * It uses an OrderDAO object to perform CRUD operations on the orders table.
 */
package com.revature.app.services;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import com.revature.app.daos.OrderDAO;
import com.revature.app.models.Order;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OrderService {
    private final OrderDAO orderDAO;

    /**
     * Retrieves a list of orders that belong to a specific user by their id.
     * It uses the orderDAO to find all the orders that have the same user id as the parameter.
     * @param id The id of the user whose orders are to be retrieved.
     * @return A list of Order objects that belong to the given user. 
     */
    public List<Order> getOrders(String id) {
        return orderDAO.findAllByUser(id);
    }

    /**
     * Formats a given price as a currency string in US locale.
     * It uses the NumberFormat class to create a currency formatter for the US locale and applies it to the price.
     * @param price The price to be formatted as a currency string.
     * @return A string representation of the price in US currency format. 
     */
    public String formatPrice(double price) {
        Locale locale = new Locale("en", "US");      
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        return currencyFormatter.format(price);
    }

    /**
     * Saves a new order to the orders table.
     * It uses the orderDAO to save the Order object as a new record in the table.
     * @param order The Order object to be saved. 
     */
    public void save(Order order) {
        orderDAO.save(order);
    }
}