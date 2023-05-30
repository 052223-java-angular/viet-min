package com.revature.app.screens;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

import com.revature.app.daos.CartItemDAO;
import com.revature.app.daos.ProductDAO;
import com.revature.app.models.Cart;
import com.revature.app.models.Product;
import com.revature.app.services.CartItemService;
import com.revature.app.services.CartService;
import com.revature.app.services.ProductService;
import com.revature.app.services.RouterServices;
import com.revature.app.utils.SessionUtil;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductDetailScreen implements IScreen{
    private final RouterServices router;
    private final CartService cart;
    private SessionUtil session;
    private Product product;



    @Override
    public void start(Scanner scan) {
        while(true) {
            clearScreen();
            System.out.println(product.getName() + "                             Stock: " + product.getStock());
            System.out.println("\n" + product.getDescription());
            System.out.println(formatPrice());
            System.out.println("\n[1] Add " + product.getName() + " to cart?");
            System.out.println("[2] View Reviews");
            System.out.println("[x] to return to product browsing");
            System.out.print("\nEnter: ");
            String input = scan.nextLine();
            if (input.equals("1"))
                    addToCart(product, scan);
            else if (input.equals("2"))
                router.navigate("/review", scan);
            else if (input.equalsIgnoreCase("x")) {
                router.navigate("/browse", scan);
            }
            else {
                System.out.println("\nInvalid option!");
                System.out.print("Press enter to continue...");
                scan.nextLine();
            }
        }
    }

    private void addToCart(Product product, Scanner scan) {
        while(true) {
            System.out.println("Please choose an amount (max: " + product.getStock() + "): ");
            String quantity = scan.nextLine();
            if (isInt(quantity)) { //checks if valid numeric int
                if (Integer.parseInt(quantity) > 0 && Integer.parseInt(quantity) < product.getStock()) { //checks if between 1 and max stock
                    cart.add(session.getId(), product.getId(), Integer.parseInt(quantity));
                }
                else {
                    System.out.println("Invalid option! Please enter between (1-" + product.getStock()+")");
                    System.out.print("\nPress enter to continue...");
                    scan.nextLine();
                    break;
                }
            }
            else {
                System.out.println("Invalid option! Please enter between (1-" + product.getStock()+")");
                System.out.print("\nPress enter to continue...");
                scan.nextLine();
                break;
            }
        }
    }

    private boolean isInt(String input){
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    private String formatPrice() {
        Locale locale = new Locale("en", "US");      
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        return currencyFormatter.format(product.getPrice());
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
