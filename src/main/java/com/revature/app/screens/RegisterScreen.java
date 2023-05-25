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
        String username = "";
        String password = "";

        while(true){
            System.out.println("Register an account");
            System.out.println("[b] Back to main menu");
            System.out.println("[x] Exit");

            username = getUsername(scan);

            if(username.equals("x")){
                break;
            }

            if(username.equals("b")){
                router.navigate("/home", scan);
                break;
            }

            password = getPassword(scan);

            if(password.equals("x")){
                break;
            }

            if(password.equals("b")){
                router.navigate("/home", scan);
                break;
            }

            userService.register(username, password);

            break;
            
        }
    }

    public String getUsername(Scanner scan){
        String username = "";
        while(true){
            System.out.println("\nEnter a username: ");
            username = scan.nextLine();

            if(username.equalsIgnoreCase("x")){
                return "x";
            }

            if(username.equalsIgnoreCase("b")){
                return "b";
            }
            if (!userService.isValidUserName(username)) {
                System.out.println("Username needs to be 8-20 characters long.");
                System.out.print("\nPress enter to continue...");
                scan.nextLine();
                continue;
            }

            if (!userService.isUniqueUserName(username)) {
                System.out.println("Username already in use!");
                System.out.print("\nPress enter to continue...");
                scan.nextLine();
                continue;
            }

            break;
        }
        return username;
    }

    public String getPassword(Scanner scan){
        String password = "";
        String confirmPassword = "";
        
        while(true){
            System.out.println("\nEnter a password: ");
            password = scan.nextLine();

            if(password.equalsIgnoreCase("x")){
                return "x";
            }

            if(password.equalsIgnoreCase("b")){
                return "b";
            }

            if (!userService.isValidPassword(password)) {
                System.out.println("Password needs to be minimum 8 characters, at least 1 letter and 1 number");
                System.out.print("\nPress enter to continue...");
                scan.nextLine();
                continue;
            }

            System.out.println("\nPlease confirm password: ");
            confirmPassword = scan.nextLine();

            if (confirmPassword.equalsIgnoreCase("x")) {
                return "x";
            }

            if(confirmPassword.equalsIgnoreCase("b")){
                return "b";
            }

            if (!userService.isSamePassword(password, confirmPassword)) {
                System.out.println("Passwords do not match");
                System.out.print("\nPress enter to continue...");
                scan.nextLine();
                continue;
            }


            break;
        }
        return password;
    }
    
}
