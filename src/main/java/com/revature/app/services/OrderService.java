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

    public List<Order> getOrders(String id) {
        return orderDAO.findAllByUser(id);
    }

    public String formatPrice(double price) {
        Locale locale = new Locale("en", "US");      
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        return currencyFormatter.format(price);
    }

    public void save(Order order) {
        orderDAO.save(order);
    }
}
