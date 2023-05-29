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
    private String id;
    private String order_id;
    private String product_id;
    private int quantity;

    public OrderItems(String order_id, String product_id, int quantity){
        this.id = UUID.randomUUID().toString();
        this.order_id = order_id;
        this.product_id = product_id;
        this.quantity = quantity;
    }
}