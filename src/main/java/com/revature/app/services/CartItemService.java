package com.revature.app.services;

import java.util.List;
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
        Optional<Product> productOpt = productService.getProd(product_id);
        if (productOpt.isEmpty()) {
            System.out.println("Product not found");
        }

        for(CartItem cartItem : cart.getItems()){
            if(cartItem.getProduct_id() == product_id){
                cartItemDAO.update(product_id, cartItem.getQuantity() + count);
                return;
            }
        }
        CartItem cartItem = new CartItem(productOpt.get().getName(), cart.getId(), product_id, count, productOpt.get().getPrice());
        cartItemDAO.save(cartItem);
    }
    

    public void modify(String item, int count) {
        cartItemDAO.update(item, count);
    }

    public void remove(String item) {
        cartItemDAO.delete(item);
    }

    public void getCartItemById(String id){

    }


    public List<CartItem> getCartItemByCartId(String cart_id) {
        return cartItemDAO.findByCartId(cart_id);
    }
    
}