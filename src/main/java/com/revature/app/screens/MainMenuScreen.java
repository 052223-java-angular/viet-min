/**
 * A class that represents the main menu screen for the application.
 * It allows the user to choose from different options such as viewing their cart, purchase history, inventory, or reviews.
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
public class MainMenuScreen implements IScreen{
    private RouterServices router; // a service that handles navigation between screens
    private SessionUtil session; // a utility class that stores the user's session information
    private static final Logger log = LogManager.getLogger(MainMenuScreen.class); // a logger for logging messages

    /**
     * The method that starts the screen and displays the user interface.
     * It takes a Scanner object as a parameter to get the user input.
     * @param scan a Scanner object for getting user input
     */
    @Override
    public void start(Scanner scan) {
        log.info("navigated to main menu screen");
        while(true) {
            clearScreen();
            System.out.println("Welcome to Paimon's Bargains!");
            System.out.println("Please select from the menu:\n");

            System.out.println("[1] View Cart | [2] View Purchase History | [3] Browse Inventory | [4] View Your Reviews | [5] Logout\n");

            String input = scan.nextLine();
            switch(input) {
                case "1":
                    log.info("navigating to cart screen");
                    session.getScreenHistory().push("/menu");
                    router.navigate("/cart", scan);
                    break;
                case "2":
                    log.info("navigating to purchase history screen");
                    session.getScreenHistory().push("/menu");
                    router.navigate("/history", scan);
                    break;
                case "3":
                    log.info("navigating to browse products screen");
                    session.getScreenHistory().push("/menu");
                    router.navigate("/browse", scan);
                    break;
                case "4":
                    log.info("navigating to User reviews screen");
                    session.getScreenHistory().push("/menu");
                    router.navigate("/review", scan);
                    break;
                case "5":
                    log.info("Log out, navigating to home screen");
                    session.getScreenHistory().push("/menu");
                    router.navigate("/home", scan);
                    break;
                default:
                    log.warn("invalid input");
                    System.out.print("Invalid option. Press any key to continue... ");
                    scan.nextLine();
                    break;
            }
        }
    }

    /**
     * A helper method that clears the screen by printing escape characters.
     */
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}