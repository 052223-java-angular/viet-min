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
        Cart cart = new Cart(user_id);
        cartDAO.save(cart);
    }

    public String add(String user_id, String product_id, int count) {
        Optional<Cart> cartOpt = cartDAO.findByUserId(user_id);
        if(cartOpt.isEmpty()){
            createCart(user_id);
            cartOpt = cartDAO.findByUserId(user_id);
        }
        cartOpt.get().setItems(cartItemService.getCartItemByCartId(cartOpt.get().getId()));
        return cartItemService.add(product_id, count, cartOpt.get());
    }
    public void remove(String item_id) {
        cartItemService.remove(item_id);
    }

    public String modify(String product_id, String cartItem_id, int amount) {
        return cartItemService.modify(product_id, cartItem_id, amount);
    }    
    
    public Optional<Cart> getCartByUserId(String user_id) {
        Optional<Cart> cart = cartDAO.findByUserId(user_id);
        if(!cart.isEmpty()){
            cart.get().setItems(cartItemService.getCartItemByCartId(cart.get().getId())); 
        }
        return cart;
    }
    public void clear(String id) {
        cartItemService.clearByCartId(id);
    }
}
