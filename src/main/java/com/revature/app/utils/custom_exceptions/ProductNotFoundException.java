/**
 * A custom exception class that extends RuntimeException and represents the situation when a product is not found.
 */
package com.revature.app.utils.custom_exceptions;

public class ProductNotFoundException extends RuntimeException {
    /**
     * A constructor that calls the super constructor with a default message of "Product not found!".
     */
    public ProductNotFoundException() {
        super("Product not found!");
    }
}