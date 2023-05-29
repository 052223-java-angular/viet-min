package com.revature.app.services;

import java.util.List;
import java.util.Optional;

import com.revature.app.daos.CartDAO;
import com.revature.app.models.Cart;
import com.revature.app.models.CartItem;
import com.revature.app.models.User;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CartService {
    private final CartDAO cartDAO;
    private final CartItemService cartItemService;
    private final UserService userService;

    public void createCart(String user_id) {
        Optional<User> userOpt = userService.findById(user_id);
        if (userOpt.isEmpty()) {
            System.out.println("user not found!");
            //create custom exception later
        }
        Cart cart = new Cart(userOpt.get().getId());
        cartDAO.save(cart);
    }
    public void add(String user_id, String item_id, int count) {
        Optional<Cart> cartOpt = cartDAO.findByUserId(user_id);
        if(cartOpt.isEmpty()){
            createCart(user_id);
        }
        cartItemService.add(item_id, count, cartOpt.get());
    }
    public void remove(String item_id) {
        cartItemService.remove(item_id);
    }

    public void modify(String item, int amount) {
        cartItemService.modify(item, amount);
    }    
    
    public Optional<Cart> getCartByUserId(String user_id) {
        Optional<Cart> cart = cartDAO.findByUserId(user_id);
        if(!cart.isEmpty()){
            cart.get().setItems(cartItemService.getCartItemByCartId(cart.get().getId())); 
        }
        return cart;
    }
}