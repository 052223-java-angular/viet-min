/**
 * A class that represents the screen for registering a new account for the application.
 * It allows the user to enter their username and password and validates them using the user service.
 * It also handles the navigation to other screens using the router service and the session utility.
 */
package com.revature.app.screens;

import java.util.Optional;
import java.util.Scanner;

import com.revature.app.services.RouterServices;
import com.revature.app.services.UserService;
import com.revature.app.utils.SessionUtil;
import com.revature.app.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegisterScreen implements IScreen {
    private final RouterServices router; // a service that handles navigation between screens
    private final UserService userService; // a service that handles user operations
    private SessionUtil session; // a utility class that stores the user's session information
    private static final Logger log = LogManager.getLogger(RegisterScreen.class); // a logger for logging messages

    /**
     * The method that starts the screen and displays the user interface.
     * It takes a Scanner object as a parameter to get the user input.
     * @param scan a Scanner object for getting user input
     */
    @Override
    public void start(Scanner scan) {
        log.info("navigated to register screen");
        String username = "";
        String password = "";

        while(true){
            clearScreen();
            System.out.println("Register an account");
            System.out.println("[b] Back ");
            System.out.println("[x] Back to main menu");

            log.info("validating user input for username");
            username = getUsername(scan);

            if(username.equalsIgnoreCase("x")){
                log.info("navigating to home screen");
                router.navigate("/home", scan);
                break;
            }

            if(username.equalsIgnoreCase("b")){
                log.info("navigating to home screen");
                router.navigate(session.getScreenHistory().pop(), scan);
                break;
            }

            log.info("validating user input for username");
            password = getPassword(scan);

            if(password.equalsIgnoreCase("x")){
                log.info("navigating to home screen");
                router.navigate("/home", scan);
                break;
            }

            if(password.equalsIgnoreCase("b")){
                log.info("navigating to home screen");
                router.navigate(session.getScreenHistory().pop(), scan);
                break;
            }

            log.info("inserting new user data");
            userService.register(username, password);
            log.info("logging user in");
            Optional<User> user = userService.login(username, password);
            session.setSession(user.get());
            log.info("navigating to main menu screen");
            session.getScreenHistory().push("/register");
            router.navigate("/menu", scan);
            break;
            
        }
    }

    /**
     * A helper method that gets the username from the user and validates it using the user service.
     * It returns the username if it is valid and unique, or "x" or "b" if the user wants to exit or go back.
     * @param scan a Scanner object for getting user input
     * @return a String representing the username or the user's choice
     */
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

    /**
     * A helper method that gets the password from the user and validates it using the user service.
     * It returns the password if it is valid and matches with the confirmation password, or "x" or "b" if the user wants to exit or go back.
     * @param scan a Scanner object for getting user input
     * @return a String representing the password or the user's choice
     */
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

    /**
     * A helper method that clears the screen by printing escape characters.
     */
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
}