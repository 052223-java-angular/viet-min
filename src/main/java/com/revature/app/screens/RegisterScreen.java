package com.revature.app.screens;

import java.util.Scanner;

import com.revature.app.services.RouterServices;
import com.revature.app.services.UserService;
import com.revature.app.models.User;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegisterScreen implements IScreen {
    private final RouterServices router;
    private final UserService userService;

    @Override
    public void start(Scanner scan) {
        String input = "";
        String username = "";
        String password = "";

        while(true){
            System.out.println("Register an account!");
            System.out.println("[x] Exit");

            username = getUsername(scan);

            if(username.equals("x")){
                break;
            }

            password = getPassword(scan);

            if(username.equals("x")){
                break;
            }

            User createdUser = userService.register(username, password);

            break;
            
        }
    }

    public String getUsername(Scanner scan){
        String username = "testusername";
        return username;
    }

    public String getPassword(Scanner scan){
        String password = "testpassword";
        return password;
    }
    
}
