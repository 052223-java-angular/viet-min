package com.revature.app.screens;

import java.util.Scanner;

import com.revature.app.services.RouterServices;
import com.revature.app.utils.SessionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HomeScreen implements IScreen {
    private final RouterServices router;
    private SessionUtil session;
    private static final Logger log = LogManager.getLogger(HomeScreen.class);

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
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}