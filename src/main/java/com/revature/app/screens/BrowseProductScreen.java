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

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BrowseProductScreen implements IScreen{
    private final RouterServices router;
    private final ProductService productService;

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
                    printProducts(prod, scan);
                    break;
                case "3":
                    prod = findByCategory(scan);
                    printProducts(prod, scan);
                    break;
                case "4":
                    prod = findByPrice(scan);
                    printProducts(prod, scan);
                    break;
            }
        }
    }

    //Helper methods
    private List<Product> findByName(Scanner scan) {
        System.out.print("Please enter product name: ");
        return productService.byName(scan.nextLine());
    }

    private List<Product> findByCategory(Scanner scan) {
        List<Category> category = new CategoryService(new CategoryDAO()).findAll();
        System.out.println("Please choose a product Category:");
        category.forEach(c -> System.out.println("[" + c.getId() + "] " + c.getName()));
        return productService.byCategory(scan.nextInt());
    }

    private List<Product> findByPrice(Scanner scan) {
        double minPrice, maxPrice = 0;
        int count = 0;
        System.out.println("Please enter minimum price:");
        minPrice = scan.nextDouble();
        do {
            if (count > 0)
            {
                System.out.println("Invalid option!");
                System.out.print("\nPress enter to continue...");
                scan.nextLine();
            }
            System.out.println("Please enter maximum price:");
            maxPrice = scan.nextDouble();
            count++;
        } while (maxPrice < minPrice);
        return productService.byPrice(minPrice, maxPrice);
    }

    private void printProducts(List<Product> prod, Scanner scan)
    {
        
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
