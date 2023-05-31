/**
 * A service class that provides business logic for carts.
 * It uses a CartDAO object to perform CRUD operations on the carts table.
 * It also uses a CartItemService object to manage the cart items associated with each cart.
 * It also uses a UserService object to validate and retrieve user information.
 */
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

    /**
     * Creates a new cart for the user with the given id and saves it using the cartDAO.
     * @param user_id The id of the user who owns the cart.
     */
    public void createCart(String user_id) {
        Cart cart = new Cart(user_id);
        cartDAO.save(cart);
    }

    /**
     * Adds a new cart item to the cart of the user with the given id and product id and quantity.
     * If the user does not have a cart, it calls the createCart method to create one first.
     * Then, it calls the cartItemService add method to add the cart item to the cart.
     * @param user_id The id of the user who owns the cart.
     * @param product_id The id of the product to be added to the cart.
     * @param count The quantity of the product to be added to the cart.
     * @return A string message indicating the result of the operation.
     */
    public String add(String user_id, String product_id, int count) {
        Optional<Cart> cartOpt = cartDAO.findByUserId(user_id);
        if(cartOpt.isEmpty()){
            createCart(user_id);
            cartOpt = cartDAO.findByUserId(user_id);
        }
        cartOpt.get().setItems(cartItemService.getCartItemByCartId(cartOpt.get().getId()));
        return cartItemService.add(product_id, count, cartOpt.get());
    }
    
    /**
     * Removes an existing cart item with the given id from the cart items table.
     * It calls the cartItemService remove method to delete the cart item by its id.
     * @param item_id The id of the cart item to be removed.
     */
    public void remove(String item_id) {
        cartItemService.remove(item_id);
    }

    /**
     * Modifies an existing cart item with the given id and quantity in the cart items table.
     * It calls the cartItemService modify method to update the quantity of the cart item by its id and product id.
     * @param product_id The id of the product associated with the cart item.
     * @param cartItem_id The id of the cart item to be modified.
     * @param amount The new quantity of the product to be set for the cart item.
     * @return A string message indicating the result of the operation. 
     */
    public String modify(String product_id, String cartItem_id, int amount) {
        return cartItemService.modify(product_id, cartItem_id, amount);
    }    
    
    /**
     * Retrieves an optional cart object that belongs to a specific user by their id.
     * It uses the cartDAO to find the cart that has the same user id as the parameter.
     * If a cart is found, it also sets its items using the cartItemService getCartItemByCartId method. 
     * @param user_id The id of the user whose cart is to be retrieved.
     * @return An optional Cart object that belongs to the given user. 
     */
    public Optional<Cart> getCartByUserId(String user_id) {
        Optional<Cart> cart = cartDAO.findByUserId(user_id);
        if(!cart.isEmpty()){
            cart.get().setItems(cartItemService.getCartItemByCartId(cart.get().getId())); 
        }
        return cart;
    }

    /**
     * Clears all the cart items that belong to a specific cart by its id.
     * It calls the cartItemService clearByCartId method to delete all the cart items that have the same cart id as the parameter. 
     * @param id The id of the cart whose items are to be cleared. 
     */
    public void clear(String id) {
        cartItemService.clearByCartId(id);
    }
}