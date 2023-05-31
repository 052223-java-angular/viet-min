/**
 * A class that represents the screen for viewing the details of a product.
 * It allows the user to see the product name, description, price, and stock.
 * It also allows the user to add the product to their cart or view its reviews.
 * It also handles the navigation to other screens using the router service and the session utility.
 */
package com.revature.app.screens;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

import com.revature.app.daos.CartItemDAO;
import com.revature.app.daos.ProductDAO;
import com.revature.app.models.Cart;
import com.revature.app.models.CartItem;
import com.revature.app.models.Product;
import com.revature.app.services.CartItemService;
import com.revature.app.services.CartService;
import com.revature.app.services.ProductService;
import com.revature.app.services.RouterServices;
import com.revature.app.utils.SessionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public class ProductDetailScreen implements IScreen{
    private final RouterServices router; // a service that handles navigation between screens
    private final CartService cart; // a service that handles cart operations
    private SessionUtil session; // a utility class that stores the user's session information
    private Product product; // a Product object that represents the product to be displayed
    private static final Logger log = LogManager.getLogger(ProductDetailScreen.class); // a logger for logging messages

    /**
     * The method that starts the screen and displays the user interface.
     * It takes a Scanner object as a parameter to get the user input.
     * @param scan a Scanner object for getting user input
     */
    @Override
    public void start(Scanner scan) {
        log.info("navigated to product detail screen for: " + product.getName());
        while(true) {
            clearScreen();
            log.info("displaying product details");
            System.out.println(product.getName() + "                             Stock: " + product.getStock());
            System.out.println("\n" + product.getDescription());
            System.out.println(formatPrice());
            System.out.println("\n[1] Add " + product.getName() + " to cart?");
            System.out.println("[2] View Reviews");
            System.out.println("[x] to return to product browsing");
            System.out.print("\nEnter: ");
            String input = scan.nextLine();
            if (input.equals("1")) {
                log.info("adding product to cart");
                addToCart(product, scan);
            }
            else if (input.equals("2")) {
                log.info("navigating to review screen");
                router.navigate("/review", scan);
            }
            else if (input.equalsIgnoreCase("x")) {
                log.info("navigating to " + session.getScreenHistory() + " screen");
                router.navigate(session.getScreenHistory().pop(), scan);
            }
            else {
                log.warn("invalid input");
                System.out.println("\nInvalid option!");
                System.out.print("Press enter to continue...");
                scan.nextLine();
            }
            break;
        }
        
    }
/**
     * A helper method that adds the product to the user's cart using the cart service.
     * It asks the user for the quantity of the product they want to add and validates it.
     * @param product a Product object that represents the product to be added
     * @param scan a Scanner object for getting user input
     */
    private void addToCart(Product product, Scanner scan) {
        while(true) {
            System.out.println("Please choose an amount (max: " + product.getStock() + "): ");
            String quantity = scan.nextLine();
            if (isInt(quantity)) { //checks if valid numeric int
                log.info("checking if quantity is valid");
                if (Integer.parseInt(quantity) > 0 && Integer.parseInt(quantity) < product.getStock()) { //checks if between 1 and max stock
                    cart.add(session.getId(), product.getId(), Integer.parseInt(quantity));
                    //need to add error check message for quantity > stock
                    log.info("added product to cart, navigating to product detail screen");
                    System.out.print("Successfully added to cart. Press any key to continue...");
                    scan.nextLine();
                    router.navigate("/detail", scan);
                }
                else {
                    log.warn("invalid input");
                    System.out.println("Invalid option! Please enter between (1-" + product.getStock()+")");
                    System.out.print("\nPress enter to continue...");
                    scan.nextLine();
                    break;
                }
            }
            else {
                log.warn("invalid input");
                System.out.println("Invalid option! Please enter between (1-" + product.getStock()+")");
                System.out.print("\nPress enter to continue...");
                scan.nextLine();
                break;
            }
        }
    }

    /**
     * A helper method that checks if a String input can be parsed as an integer.
     * @param input a String input from the user
     * @return true if the input can be parsed as an integer, false otherwise
     */
    private boolean isInt(String input){
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     * A helper method that formats the product price using the locale and currency of the user.
     * @return a String representing the formatted price
     */
    private String formatPrice() {
        Locale locale = new Locale("en", "US");      
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        return currencyFormatter.format(product.getPrice());
    }

    /**
     * A helper method that clears the screen by printing escape characters.
     */
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}