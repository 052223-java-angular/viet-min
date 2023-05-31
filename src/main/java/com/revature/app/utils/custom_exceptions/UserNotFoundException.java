/**
 * A custom exception class that extends RuntimeException and represents the situation when a user is not found.
 */
package com.revature.app.utils.custom_exceptions;

public class UserNotFoundException extends RuntimeException{
    /**
     * A constructor that calls the super constructor with a default message of "User not found!".
     */
    public UserNotFoundException() {
        super("User not found!");
    }
}