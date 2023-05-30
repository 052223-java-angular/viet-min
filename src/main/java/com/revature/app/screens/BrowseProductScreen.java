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

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BrowseProductScreen implements IScreen{
    private final RouterServices router;
    private final ProductService productService;
    private SessionUtil session;

    public void start(Scanner scan) {
        String input ="";
        exit:{
            while(true){
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
                    System.out.println("Goodbye!");
                    router.navigate("/home", scan);;
                    break;
                }
    
                if (input.equalsIgnoreCase("b")) {
                    router.navigate(session.getScreenHistory().pop(), scan);
                    break;
                }
    
                switch(input) {
                    case "b":
                        router.navigate(session.getScreenHistory().pop(), scan);
                        break;
                    case "x":
                        router.navigate("/menu", scan);
                        break;
                    case "1", "2", "3", "4":
                        searchProducts(input, scan);
                        break;
                    case "5":
                        session.getScreenHistory().push("/menu");
                        router.navigate("/cart", scan);
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
                        if (prod == null)
                            break exit;
                        printProducts(prod, scan);
                        break;
                }
            }
        }
    }

    //Helper methods
    private List<Product> findByName(Scanner scan) {
        while (true) {
            System.out.print("Please enter product name (x to cancel): ");
            String input = scan.nextLine();
            if (input.equalsIgnoreCase("x")) {
                break ;
            }
            // else if(input)
            return productService.byName(scan.nextLine());
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
        if (isInt(input) && Integer.parseInt(input) < category.size())
            return productService.byCategory(Integer.parseInt(input));

        else if(scan.nextLine().equalsIgnoreCase("x"))
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
        do {
            if (count == 0) {
                System.out.print("Please enter minimum price: ");
                input = scan.nextLine();
                if (!isDouble(input)) {
                    System.out.println("Invalid option!");
                    System.out.print("\nPress enter to continue...");
                    scan.nextLine();
                    continue;
                }
            }
            else {
                minPrice = Double.parseDouble(input);
                count++;
            }
            
            System.out.println("Please enter maximum price: ");
            input = scan.nextLine();
            if (!isDouble(input)) {
                System.out.println("Invalid option!");
                System.out.print("\nPress enter to continue...");
                scan.nextLine();
                continue;
            }
            else maxPrice = Double.parseDouble(input);

        } while (maxPrice < minPrice);
        return productService.byPrice(minPrice, maxPrice);
    }

    private void printProducts(List<Product> prod, Scanner scan) {
        exit: {
            while(true) {
                clearScreen();
                System.out.println("Please choose an item"); //(x to cancel):");
                int index=0;
                for (Product p : prod) {
                    System.out.println("[" + (index+1) + "] " + p.getName());
                    index++;
                }
                System.out.print("\nEnter: ");
                String input = scan.nextLine();
                if (isInt(input)) {
                    if (Integer.parseInt(input) > 0 && Integer.parseInt(input) <= prod.size()) {
                        Product product = prod.get(Integer.parseInt(input) - 1);
                        router.setProduct(product);
                        session.getScreenHistory().push("/browse");
                        router.navigate("/detail", scan);
                    } else {
                        System.out.println("Invalid option!");
                        System.out.print("\nPress enter to continue...");
                        scan.nextLine();
                        break exit;
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

    private boolean isDouble(String input) {
        try {
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
