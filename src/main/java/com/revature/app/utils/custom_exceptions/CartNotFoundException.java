/**
 * A custom exception class that extends RuntimeException and represents the situation when a cart is not found.
 */
package com.revature.app.utils.custom_exceptions;

public class CartNotFoundException extends RuntimeException{
    /**
     * A constructor that calls the super constructor with a default message of "Cart not found!".
     */
    public CartNotFoundException() {
        super("Cart not found!");
    }
}