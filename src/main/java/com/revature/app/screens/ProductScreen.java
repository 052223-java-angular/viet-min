package com.revature.app.screens;

import java.util.Scanner;

import com.revature.app.services.ProductService;
import com.revature.app.services.RouterServices;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductScreen implements IScreen{
    private final RouterServices router;
    private final ProductService product;

    public void start(Scanner scan) {
        String input ="";
        exit: {
            while(true){
                clearScreen();
                System.out.println("Welcome to Paimon's Bargains! What would you like to do?");
                System.out.println("\n[1] Browse all products");
                System.out.println("[2] Search product by name");
                System.out.println("[3] Search product by category");
                System.out.println("[4] Search product by price");
                System.out.println("[m] Return to previous menu");
                System.out.println("[x] Exit");

                input = scan.nextLine().toLowerCase();

                switch(input) {
                    case "x":
                        System.out.println("Goodbye!");
                        break exit;
                    case "m":
                        router.navigate("/menu", scan);
                        break exit;
                    case "1", "2", "3","4":
                        searchProducts(input);
                        break;
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

    //Helper methods
    public void searchProducts(String input) {

    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
