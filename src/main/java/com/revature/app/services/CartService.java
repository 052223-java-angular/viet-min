package com.revature.app.services;

import com.revature.app.daos.CartDAO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CartService {

    public void remove(String item) {
        CartDAO.remove(item);
    }

    public void modify(String item, String amount) {

    }
    
}
