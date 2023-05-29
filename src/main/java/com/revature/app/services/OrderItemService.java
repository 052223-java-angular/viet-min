package com.revature.app.services;

import java.util.List;

import com.revature.app.daos.OrderItemsDAO;
import com.revature.app.models.OrderItems;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OrderItemService {
    private final OrderItemsDAO orderItemsDAO;

    public List<OrderItems> getOrderItems(String id) {
        return orderItemsDAO.findbyOrderId(id);
    }

    public void save(OrderItems obj) {
        orderItemsDAO.save(obj);
    }
}