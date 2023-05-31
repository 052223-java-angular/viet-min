/**
 * A class that represents the home screen for the application.
 * It allows the user to choose between logging in or registering a new account.
 * It also handles the navigation to other screens using the router service and the session utility.
 */
package com.revature.app.screens;

import java.util.Scanner;

import com.revature.app.services.RouterServices;
import com.revature.app.utils.SessionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HomeScreen implements IScreen {
    private final RouterServices router; // a service that handles navigation between screens
    private SessionUtil session; // a utility class that stores the user's session information
    private static final Logger log = LogManager.getLogger(HomeScreen.class); // a logger for logging messages

    /**
     * The method that starts the screen and displays the user interface.
     * It takes a Scanner object as a parameter to get the user input.
     * @param scan a Scanner object for getting user input
     */
    @Override
    public void start(Scanner scan) {
        log.info("Navigated to home screen");
        String input = "";

        exit: {
            while(true){
                log.info("clear screen");
                clearScreen();
                System.out.println("Welcome to Paimon's Bargains!");
                System.out.println("\n[1] Login to your account");
                System.out.println("[2] Register new Account");
                System.out.println("[x] Exit");

                System.out.print("\nEnter: ");
                input = scan.nextLine();

                switch (input.toLowerCase()) {
                case "1":
                    log.info("navigating login screen");
                    session.getScreenHistory().push("/home");
                    router.navigate("/login", scan);
                    break exit;
                case "2":
                    log.info("navigating to register screen");
                    session.getScreenHistory().push("/home");
                    router.navigate("/register", scan);
                    break exit;
                case "x":
                    log.info("Exiting program");
                    System.out.println("\nSee you next time!");
                    break exit;
                default:
                    clearScreen();
                    log.warn("invalid input");
                    System.out.println("Invalid option!");
                    System.out.print("\nPress enter to continue...");
                    scan.nextLine();
                    break;
                }
            }
        }
    }

    //Helper Method
    /**
     * A helper method that clears the screen by printing escape characters.
     */
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}