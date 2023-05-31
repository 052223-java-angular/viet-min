/**
 * A custom exception class that extends RuntimeException and represents the situation when a cart item is not found.
 */
package com.revature.app.utils.custom_exceptions;

public class CartItemNoFoundException extends RuntimeException {
    /**
     * A constructor that calls the super constructor with a default message of "CartItem not found!".
     */
    public CartItemNoFoundException() {
        super("CartItem not found!");
    }
}