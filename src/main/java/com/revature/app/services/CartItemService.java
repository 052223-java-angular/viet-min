package com.revature.app.services;

import java.util.Optional;
import java.util.Scanner;

import com.revature.app.daos.CartItemDAO;
import com.revature.app.models.Cart;
import com.revature.app.models.CartItem;
import com.revature.app.models.Product;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CartItemService {

    private final CartItemDAO cartItemDAO;
    private final ProductService productService;

    public void add(String product_id, int count, Cart cart) {
        Optional<Product> productOpt = productService.getById(product_id);
        if (productOpt.isEmpty()) {
            System.out.println("Product not found");
            //need to add custom exceptions
        }

        for(CartItem cartItem : cart.getItems()){
            if(cartItem.getProduct_id() == product_id){
                count += cartItem.getQuantity();
            }
        }

        cartItemDAO.save(product_id);

    }
    

    public void modify(String item, int count) {
        cartItemDAO.update(item);
    }

    public void remove(String item) {
        cartItemDAO.delete(item);
    }

    public void getCartItemById(String id){

    }
    
}
