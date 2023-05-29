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
            System.out.println("Welcome to Paimon's Bargains!");
            System.out.println("Please select from the menu:");

            System.out.println("[1] View Cart | [2] View Purchase History | [3] Browse Inventory | [4] View Your Reviews | [5] Logout");

            String input = scan.nextLine();
            switch(input) {
                case "1":
                    router.navigate("/cart", scan);
                    break;
                case "2":
                    router.navigate("/history", scan);
                    break;
                case "3":
                    router.navigate("/browse", scan);
                    break;
                case "4":
                    router.navigate("/review", scan);
                    break;
                case "5":
                    session = new SessionUtil();
                    router.navigate("/home", scan);
                    break;
                default:
                    System.out.print("Invalid option. Press any key to continue... ");
                    break;
            }
        }
    }
}
