package com.revature.app.utils.custom_exceptions;

public class CartNotFoundException extends RuntimeException{
    public CartNotFoundException() {
        super("Cart not found!");
    }
}
