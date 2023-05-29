package com.revature.app.screens;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import javax.sound.midi.SysexMessage;

import com.revature.app.daos.CategoryDAO;
import com.revature.app.models.Cart;
import com.revature.app.models.CartItem;
import com.revature.app.models.Category;
import com.revature.app.models.Product;
import com.revature.app.services.CategoryService;
import com.revature.app.services.ProductService;
import com.revature.app.services.RouterServices;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BrowseProductScreen implements IScreen{
    private final RouterServices router;
    private final ProductService productService;
    private Cart cart;

    public void start(Scanner scan) {
        String input ="";
        while(true){
            clearScreen();
            System.out.println("Welcome to Paimon's Bargains! What would you like to do?");
            System.out.println("\n[1] Browse all products");
            System.out.println("[2] Search product by name");
            System.out.println("[3] Search product by category");
            System.out.println("[4] Search product by price");
            System.out.println("[b] Return to previous menu");
            System.out.println("[x] Exit");

            System.out.print("\nEnter: ");
            input = scan.nextLine().toLowerCase();

            if (input.equals("x")) {
                System.out.println("Goodbye!");
                break;
            }

            if (input.equals("b")) {
                router.navigate("/register", scan);
                break;
            }

            switch(input) {
                // case "x":
                //     System.out.println("Goodbye!");
                //     break;
                // case "b":
                //     router.navigate("/menu", scan);
                //     break;
                case "1", "2", "3", "4":
                    searchProducts(input, scan);
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

    public void searchProducts(String input, Scanner scan) {
        List<Product> prod;
        exit: {
            while(true) {
                clearScreen();
                prod = new ArrayList<Product>();
                switch(input) {
                    case "1":
                        prod = productService.allProducts();
                        printProducts(prod, scan);
                        break;
                    case "2":
                        prod = findByName(scan);
                        if (prod == null)
                            break exit;
                        printProducts(prod, scan);
                        break;
                    case "3":
                        prod = findByCategory(scan);
                        if (prod == null)
                        break exit;
                        printProducts(prod, scan);
                        break;
                    case "4":
                        prod = findByPrice(scan);
                        printProducts(prod, scan);
                        break;
                }
            }
        }
    }

    //Helper methods
    private List<Product> findByName(Scanner scan) {
        return null;
        // exit: {
        //     while (true) {
        //         System.out.print("Please enter product name (x to cancel): ");
        //         String input = getInput(scan);
        //         if (input.equals("x")) {
        //             break exit;
        //         }
        //         // else if(input)
        //         return productService.byName(scan.nextLine());
        //     }
        // }
    }

    private List<Product> findByCategory(Scanner scan) {
        List<Category> category = new CategoryService(new CategoryDAO()).findAll();
        clearScreen();
        System.out.println("Please choose a product Category (x to cancel):");
            category.forEach(c -> System.out.println("[" + c.getId() + "] " + c.getName()));
            System.out.print("\nEnter: ");

        if (scan.hasNextInt() && scan.nextInt() < category.size())
            return productService.byCategory(scan.nextInt());

        else if(scan.nextLine().equals("x"))
            return null;
        else {
            clearScreen();
            System.out.println("Invalid option!");
            System.out.print("\nPress enter to continue...");
            scan.nextLine();
            findByCategory(scan);
        }
        return null;
    }

    private List<Product> findByPrice(Scanner scan) {
        String input = "";
        double minPrice = 0,  maxPrice = 0;
        int count = 0;
        // do {
        // System.out.print("Please enter minimum price(x to cancel): ");
        // input = scan.nextLine();
        // //if (!productService.isValidPrice();
            
            
        //     if (count > 0)
        //     {
        //         System.out.println("Invalid option!");
        //         System.out.print("\nPress enter to continue...");
        //         scan.nextLine();
        //     }
        //     System.out.println("Please enter maximum price: ");
        //     maxPrice = scan.nextDouble();
        //     count++;
        // } while (maxPrice < minPrice);
        return productService.byPrice(minPrice, maxPrice);
    }

    private void printProducts(List<Product> prod, Scanner scan) {
        exit: {
            while(true) {
                clearScreen();
                System.out.println("Please choose an item"); //(x to cancel):");

                prod.forEach(p -> System.out.println("[" + p.getId() + "] " + p.getName() + "      Stock: " + p.getStock()));
                System.out.print("\nEnter: ");
                String input = scan.nextLine();
                if (prod  //input = product id
                    .stream()
                    .filter(p -> p.getId().equals(input))
                    .findFirst()
                    .isPresent()) {
                        Product product = prod.stream().filter(p -> p.getId().equals(input)).findFirst().get();
                        System.out.println("Please choose an amount (max: " + product.getStock() + "): ");
                        String quantity = scan.nextLine();
                        if (isInt(quantity)) {
                            if (Integer.parseInt(quantity) > 0 && Integer.parseInt(quantity) < product.getStock()) {
                                CartItem cartItem = new CartItem();
                                cartItem.add(product.getId(), quantity, cart);
                            }
                            else {
                                System.out.println("Invalid option! Please enter between (1-" + product.getStock()+")");
                                System.out.print("\nPress enter to continue...");
                                scan.nextLine();
                                break exit;
                            }
                        }
                        else {

                        }
                    }
                else {
                    System.out.println("Invalid option!");
                    System.out.print("\nPress enter to continue...");
                    scan.nextLine();
                    break exit;
                }
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
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
