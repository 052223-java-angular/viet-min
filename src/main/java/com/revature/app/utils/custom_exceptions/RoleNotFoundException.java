/**
 * A custom exception class that extends RuntimeException and represents the situation when a role is not found.
 */
package com.revature.app.utils.custom_exceptions;

public class RoleNotFoundException extends RuntimeException {
    /**
     * A constructor that calls the super constructor with a default message of "Role not found!".
     */
    public RoleNotFoundException() {
        super("Role not found!");
    }
}