package com.revature.app.services;

import java.util.Scanner;

import com.revature.app.daos.CartDAO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CartService {
    private final CartDAO cartDAO;
    private final CartItemService cartItemService;

    public CartService start(Scanner scan) {
        return null;
    }

    public void remove(String item) {
    }

    public void modify(String item, String amount) {
    }
    
    
}
