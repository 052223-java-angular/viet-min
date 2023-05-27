package com.revature.app.utils.customException;

public class RoleNotExist extends RuntimeException {
    
    public RoleNotExist() {
        super("Role not found!");
    }
}
