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
    private String id;
    private String user_id;
    private String payment_id;
    private double total;

    public Order(String user_id, String payment_id, double total){
        this.id = UUID.randomUUID().toString();
        this.user_id = user_id;
        this.payment_id = payment_id;
        this.total = total;
    }
}
