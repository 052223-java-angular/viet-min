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
    private String id;
    private String user_id;
    private List<CartItem> items;

    public Cart(String user_id) {
        this.id = UUID.randomUUID().toString();
        this.user_id = user_id;
        this.items = new ArrayList<>();
    }
}