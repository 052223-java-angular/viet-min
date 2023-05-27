package com.revature.app.services;

import java.util.Optional;
import java.util.Scanner;

import com.revature.app.daos.CartItemDAO;
import com.revature.app.models.CartItem;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CartItemService {

    private final CartItemDAO cartItemDAO;
    //private final ProductService productService;
    public void add(String item, String amount) {
        //find cart if exist
            //create cart if not exist
        //find product if exist
            //throw error if not exist
        //check if selected product exist in the cart
            //update if exist
            //add if not
    }
    

    public void modify(String item, String amount) {
        //change quantity
    }

    public void remove(String item) {
        //delete product from cart
    }

    public void getCartItemById(String id){

    }


    public CartService start(Scanner scan) {
        return null;
    }
    
}
