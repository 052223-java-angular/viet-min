package com.revature.app.utils.custom_exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException() {
        super("Product not found!");
    }
}
