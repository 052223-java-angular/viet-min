package com.revature.app.screens;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import com.revature.app.daos.CategoryDAO;
import com.revature.app.models.Category;
import com.revature.app.models.Product;
import com.revature.app.services.CategoryService;
import com.revature.app.services.ProductService;
import com.revature.app.services.RouterServices;
import com.revature.app.utils.SessionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BrowseProductScreen implements IScreen{
    private static final Logger log = LogManager.getLogger(BrowseProductScreen.class);
    private final RouterServices router;
    private final ProductService productService;
    private SessionUtil session;

    public void start(Scanner scan) {
        log.info("Browse Product Screen started");
        String input ="";
        exit:{
            while(true){
                router.setProduct(null);
                clearScreen();
                System.out.println("Welcome to Paimon's Bargains! What would you like to do?");
                System.out.println("\n[1] Browse all products");
                System.out.println("[2] Search product by name");
                System.out.println("[3] Search product by category");
                System.out.println("[4] Search product by price");
                System.out.println("[5] Go to cart");
                System.out.println("[b] back");
                System.out.println("[x] Exit");
    
                input = scan.nextLine().toLowerCase();
    
                if (input.equalsIgnoreCase("x")) {
                    log.info("User entered " + input + "moving to home screen");
                    System.out.println("Goodbye!");
                    router.navigate("/home", scan);;
                    break exit;
                }
    
                if (input.equalsIgnoreCase("b")) {
                    log.info("User entered " + input + "moving to previous screen, " + session.getScreenHistory());
                    router.navigate(session.getScreenHistory().pop(), scan);
                    break exit;
                }
    
                switch(input) {
                    case "b":
                        router.navigate(session.getScreenHistory().pop(), scan);
                        break exit;
                    case "x":
                        session.getScreenHistory().push("/browse");
                        router.navigate("/menu", scan);
                        break exit;
                    case "1", "2", "3", "4":
                        log.info("User entered" + input);
                        searchProducts(input, scan);
                        break exit;
                    case "5":
                        log.info("User entered" + input);
                        session.getScreenHistory().push("/menu");
                        router.navigate("/cart", scan);
                        break exit;
                    default:
                        log.warn("User entered" + input + "invalid, clearing screen");
                        clearScreen();
                        System.out.println("Invalid option!");
                        System.out.print("\nPress enter to continue...");
                        scan.nextLine();
                        break;
                }
            }
        }
        
    }

    public void searchProducts(String input, Scanner scan) {
        List<Product> prod;
        exit: {
            while(true) {
                log.info("Clearing screen");
                clearScreen();
                prod = new ArrayList<Product>();
                switch(input) {
                    case "1":
                        log.info("Searching all products");
                        prod = productService.allProducts();
                        printProducts(prod, scan);
                        break exit;
                    case "2":
                        log.info("Searching products by specific name ignoring case");
                        prod = findByName(scan);
                        if (prod == null)
                            break exit;
                        printProducts(prod, scan);
                        break exit;
                    case "3":
                        log.info("Searching products by category");
                        prod = findByCategory(scan);
                        if (prod == null)
                            break exit;
                        printProducts(prod, scan);
                        break exit;
                    case "4":
                        log.info("Searching products by price");
                        prod = findByPrice(scan);
                        if (prod == null)
                            break exit;
                        printProducts(prod, scan);
                        break exit;
                    default:
                        break exit;
                }
            }
        }
    }

    //Helper methods
    private List<Product> findByName(Scanner scan) {
        while (true) {
            System.out.print("Please enter product name (x to cancel): ");
            String input = scan.nextLine();
            log.info("User input" + input);
            if (input.equalsIgnoreCase("x")) {
                log.info("navigating back to main browse/search");
                break ;
            }
            // else if(input)
            log.info("database returning product by name");
            return productService.byName(scan.nextLine()); //ignore case in sql?
        }
        return null;
    }

    private List<Product> findByCategory(Scanner scan) {
        List<Category> category = new CategoryService(new CategoryDAO()).findAll();
        clearScreen();
        System.out.println("Please choose a product Category (x to cancel):");
            category.forEach(c -> System.out.println("[" + c.getId() + "] " + c.getName()));
            System.out.print("\nEnter: ");
            String input = scan.nextLine();
            log.info("User input" + input);
        if (isInt(input) && Integer.parseInt(input) < category.size()) {
            log.info("Displaying products in category");
            return productService.byCategory(Integer.parseInt(input));
        }

        else if(scan.nextLine().equalsIgnoreCase("x")) {
            log.info("navigating back to main browse/search screen");
            return null;
        }
        else {
            clearScreen();
            log.warn("invalid input");
            System.out.println("Invalid option!");
            System.out.print("\nPress enter to continue...");
            scan.nextLine();
            log.info("return to search by category");
            findByCategory(scan);
        }
        return null;
    }

    private List<Product> findByPrice(Scanner scan) {
        String input = "";
        double minPrice = 0,  maxPrice = 0;
        int count = 0;
        do {
            if (count == 0) {
                System.out.print("Please enter minimum price: ");
                input = scan.nextLine();
                log.info("user input" + input);
                if (!isDouble(input)) {
                    log.warn("invalid input");
                    System.out.println("Invalid option!");
                    System.out.print("\nPress enter to continue...");
                    scan.nextLine();
                    continue;
                }
            }
            else {
                log.info("user entered a correct minPrice");
                minPrice = Double.parseDouble(input);
                count++;
            }
            
            System.out.println("Please enter maximum price: ");
            input = scan.nextLine();
            if (!isDouble(input)) {
                log.warn("invalid input");
                System.out.println("Invalid option!");
                System.out.print("\nPress enter to continue...");
                scan.nextLine();
                continue;
            }
            else {
                log.info("valid input");
                maxPrice = Double.parseDouble(input);
            }

        } while (maxPrice < minPrice);
        log.info("correct min and max price format, returning products between range");
        return productService.byPrice(minPrice, maxPrice);
    }

    private void printProducts(List<Product> prod, Scanner scan) {

        while(true) {
            log.info("clear screen");
            clearScreen();
            System.out.println("Please choose an item"); //(x to cancel):");
            int index=0;
            log.info("displaying all products matching criteria");
            for (Product p : prod) {
                System.out.println("[" + (index+1) + "] " + p.getName());
                index++;
            }
            System.out.print("\nEnter: ");
            String input = scan.nextLine();
            if (isInt(input)) {
                if (Integer.parseInt(input) > 0 && Integer.parseInt(input) <= prod.size()) {
                    Product product = prod.get(Integer.parseInt(input) - 1);
                    log.info("moving to product detail screen");
                    router.setProduct(product);
                    session.getScreenHistory().push("/browse");
                    router.navigate("/detail", scan);
                    break;
                } else {
                    log.warn("invalid input");
                    System.out.println("Invalid option!");
                    System.out.print("\nPress enter to continue...");
                    scan.nextLine();
                    break;
                }
            } 
            else {
                log.warn("invalid input");
                System.out.println("Invalid option!");
                System.out.print("\nPress enter to continue...");
                scan.nextLine();
                break;
            }
        }

    }

    private boolean isInt(String input){
        try {
            log.info("checking if " + input + " is an int");
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    private boolean isDouble(String input) {
        try {
            log.info("checking if " + input + " is a double");
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
