package com.revature.app.utils.custom_exceptions;

public class CartItemNoFoundException extends RuntimeException {
    public CartItemNoFoundException() {
        super("CartItem not found!");
    }
}
