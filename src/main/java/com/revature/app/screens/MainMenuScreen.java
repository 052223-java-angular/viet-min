package com.revature.app.screens;
import java.util.Scanner;

import com.revature.app.services.RouterServices;
import com.revature.app.utils.SessionUtil;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MainMenuScreen implements IScreen{
    private RouterServices router;
    private SessionUtil session;

    @Override
    public void start(Scanner scan) {
        while(true) {
            clearScreen();
            System.out.println("Welcome to Paimon's Bargains!");
            System.out.println("Please select from the menu:\n");

            System.out.println("[1] View Cart | [2] View Purchase History | [3] Browse Inventory | [4] View Your Reviews | [5] Logout\n");

            String input = scan.nextLine();
            switch(input) {
                case "1":
                    session.getScreenHistory().push("/menu");
                    router.navigate("/cart", scan);
                    break;
                case "2":
                    session.getScreenHistory().push("/menu");
                    router.navigate("/history", scan);
                    break;
                case "3":
                    session.getScreenHistory().push("/menu");
                    router.navigate("/browse", scan);
                    break;
                case "4":
                    session.getScreenHistory().push("/menu");
                    router.navigate("/review", scan);
                    break;
                case "5":
                    session.getScreenHistory().push("/menu");
                    router.navigate("/home", scan);
                    break;
                default:
                    System.out.print("Invalid option. Press any key to continue... ");
                    scan.nextLine();
                    break;
            }
        }
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
