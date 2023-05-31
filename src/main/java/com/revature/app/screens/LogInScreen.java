/**
 * A class that represents the screen for logging in to the application.
 * It allows the user to enter their username and password and validates them using the user service.
 * It also handles the navigation to other screens using the router service and the session utility.
 */
package com.revature.app.screens;

import java.util.Optional;
import java.util.Scanner;

import com.revature.app.models.User;
import com.revature.app.services.RouterServices;
import com.revature.app.services.UserService;
import com.revature.app.utils.SessionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LogInScreen implements IScreen{
    private final RouterServices router; // a service that handles navigation between screens
    private final UserService userService; // a service that handles user operations
    private SessionUtil session; // a utility class that stores the user's session information
    private static final Logger log = LogManager.getLogger(LogInScreen.class); // a logger for logging messages

    /**
     * The method that starts the screen and displays the user interface.
     * It takes a Scanner object as a parameter to get the user input.
     * @param scan a Scanner object for getting user input
     */
    @Override
    public void start(Scanner scan) {
        log.info("navigated to login screen");
        String username = "";
        String password = "";

        while(true){
            clearScreen();
            System.out.println("Sign in here");
            System.out.println("[b] back");
            System.out.println("[x] Back to main menu");

            log.info("User input a username");
            username = getUsername(scan);

            if(username.equalsIgnoreCase("x")){
                log.info("Navigating to home screen");
                router.navigate("/home", scan);
                break;
            }

            if(username.equalsIgnoreCase("b")){
                log.info("Navigating to home screen");
                router.navigate(session.getScreenHistory().pop(), scan);
                break;
            }

            log.info("User input a password");
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
            Optional<User> user = userService.login(username, password);
            if(user.isEmpty()){
                clearScreen();
                log.warn("no user pass found in database");
                System.out.println("\nNo user found with that combination of username and password found");
                System.out.print("\nPress enter to continue...");
                scan.nextLine();
                continue;
            }else{
                log.info("User logged in, session created");
                session.setSession(user.get());
                System.out.println("success!");
                log.info("navigating to main menu screen screen");
                session.getScreenHistory().push("/login");
                router.navigate("/menu", scan);
                break;
            }
            
        }
    }

    /**
     * A helper method that gets the username from the user and returns it.
     * It returns "x" or "b" if the user wants to exit or go back.
     * @param scan a Scanner object for getting user input
     * @return a String representing the username or the user's choice
     */
    public String getUsername(Scanner scan){
        String username = "";

        System.out.println("\nEnter your username: ");
        username = scan.nextLine();

        return username.equalsIgnoreCase("x") ? "x" : username.equalsIgnoreCase("b") ? "b" : username;
    }

    /**
     * A helper method that gets the password from the user and returns it.
     * It returns "x" or "b" if the user wants to exit or go back.
     * @param scan a Scanner object for getting user input
     * @return a String representing the password or the user's choice
     */
    public String getPassword(Scanner scan){
        String password = "";
        
        System.out.println("\nEnter your password: ");
        password = scan.nextLine();

        return password.equalsIgnoreCase("x") ? "x" : password.equalsIgnoreCase("b") ? "b" : password;
    }

    /**
     * A helper method that clears the screen by printing escape characters.
     */
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
}