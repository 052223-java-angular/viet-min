package com.revature.app.screens;

import java.util.Scanner;

import com.revature.app.services.RouterServices;
import com.revature.app.utils.SessionUtil;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HomeScreen implements IScreen {
    private final RouterServices router;
    private SessionUtil session;

    @Override
    public void start(Scanner scan) {
        String input = "";

        exit: {
            while(true){
                clearScreen();
                System.out.println("Welcome to Paimon's Bargains!");
                System.out.println("\n[1] Login to your account");
                System.out.println("[2] Register new Account");
                System.out.println("[x] Exit");

                System.out.print("\nEnter: ");
                input = scan.nextLine();

                switch (input.toLowerCase()) {
                case "1":
                    session.getScreenHistory().push("/home");
                    router.navigate("/login", scan);
                    break exit;
                case "2":
                    session.getScreenHistory().push("/home");
                    router.navigate("/register", scan);
                    break exit;
                case "x":
                    System.out.println("\nSee you next time!");
                    break exit;
                default:
                    clearScreen();
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