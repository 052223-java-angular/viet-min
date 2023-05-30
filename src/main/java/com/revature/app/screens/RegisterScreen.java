package com.revature.app.screens;

import java.util.Optional;
import java.util.Scanner;

import com.revature.app.services.RouterServices;
import com.revature.app.services.UserService;
import com.revature.app.utils.SessionUtil;
import com.revature.app.models.User;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegisterScreen implements IScreen {
    private final RouterServices router;
    private final UserService userService;
    private SessionUtil session;

    @Override
    public void start(Scanner scan) {
        String username = "";
        String password = "";

        while(true){
            clearScreen();
            System.out.println("Register an account");
            System.out.println("[b] Back ");
            System.out.println("[x] Back to main menu");

            username = getUsername(scan);

            if(username.equalsIgnoreCase("x")){
                router.navigate("/home", scan);
                break;
            }

            if(username.equalsIgnoreCase("b")){
                router.navigate(session.getScreenHistory().pop(), scan);
                break;
            }

            password = getPassword(scan);

            if(password.equalsIgnoreCase("x")){
                router.navigate("/home", scan);
                break;
            }

            if(password.equalsIgnoreCase("b")){
                router.navigate(session.getScreenHistory().pop(), scan);
                
                break;
            }

            userService.register(username, password);
            Optional<User> user = userService.login(username, password);
            session.setSession(user.get());
            session.getScreenHistory().push("/register");
            router.navigate("/browse", scan);
            break;
            
        }
    }

    public String getUsername(Scanner scan){
        String username = "";
        while(true){
            clearScreen();
            System.out.println("\nEnter a username: ");
            username = scan.nextLine();

            if(username.equalsIgnoreCase("x")){
                return "x";
            }

            if(username.equalsIgnoreCase("b")){
                return "b";
            }
            if (!userService.isValidUserName(username)) {
                clearScreen();
                System.out.println("Username needs to be 8-20 characters long.");
                System.out.print("\nPress enter to continue...");
                scan.nextLine();
                continue;
            }

            if (!userService.isUniqueUserName(username)) {
                clearScreen();
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
            clearScreen();
            System.out.println("\nEnter a password: ");
            password = scan.nextLine();

            if(password.equalsIgnoreCase("x")){
                return "x";
            }

            if(password.equalsIgnoreCase("b")){
                return "b";
            }

            if (!userService.isValidPassword(password)) {
                clearScreen();
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
                clearScreen();
                System.out.println("Passwords do not match");
                System.out.print("\nPress enter to continue...");
                scan.nextLine();
                continue;
            }


            break;
        }
        return password;
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
}