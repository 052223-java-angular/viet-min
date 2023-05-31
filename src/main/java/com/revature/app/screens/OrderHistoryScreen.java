/**
 * A class that represents the screen for viewing the user's order history.
 * It allows the user to select an order and see its details, such as the products and quantities.
 * It also handles the navigation to other screens using the router service and the session utility.
 */
package com.revature.app.screens;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.revature.app.daos.ProductDAO;
import com.revature.app.models.Order;
import com.revature.app.models.OrderItems;
import com.revature.app.models.Product;
import com.revature.app.services.OrderItemService;
import com.revature.app.services.OrderService;
import com.revature.app.services.ProductService;
import com.revature.app.services.RouterServices;
import com.revature.app.utils.SessionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OrderHistoryScreen implements IScreen {
    private RouterServices router; // a service that handles navigation between screens
    private SessionUtil session; // a utility class that stores the user's session information
    private OrderService orderService; // a service that handles order operations
    private OrderItemService orderItemService; // a service that handles order item operations
    private static final Logger log = LogManager.getLogger(OrderHistoryScreen.class); // a logger for logging messages

    /**
     * The method that starts the screen and displays the user interface.
     * It takes a Scanner object as a parameter to get the user input.
     * @param scan a Scanner object for getting user input
     */
    @Override
    public void start(Scanner scan) {
        log.info("navigated to Order history screen");
        List<Order> orders = orderService.getOrders(session.getId());

        while(true) {
            clearScreen();
            if (orders.isEmpty()) {
                log.warn("No data");
                System.out.print("You have no previous orders. Please press any key to return to main menu...");
                scan.nextLine();
                log.info("navigating to main menu screen");
                router.navigate("/menu", scan);
            }
            System.out.println("Please select the order you wish to view: ");
            int counter = 1;
            log.info("printing order history objects");
            for (Order order : orders) {
                System.out.println("["+ counter++ + "] " + orderService.formatPrice(order.getTotal()));
            }
            System.out.println("\n[b] to return to main menu");

            String input = scan.nextLine();
            if (input.equalsIgnoreCase("b")) {
                log.info("navigating to main menu screen");{
                router.navigate(session.getScreenHistory().pop(), scan);
            }
                break;
            }else if (isInt(input)) {
                if (Integer.parseInt(input) > 0 && Integer.parseInt(input) <= orders.size()) {
                    log.info("retrieving order details data");
                    Order order = orders.get(Integer.parseInt(input) - 1);
                    List<OrderItems> orderItems = orderItemService.getOrderItems(order.getId());
                    clearScreen();
                    printOrderItems(orderItems);
                    System.out.println("Press any key to return to Order History...");
                    scan.nextLine();
                    log.info("navigating to order history screen");
                    router.navigate("/history", scan);
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

    /**
     * A helper method that prints the order items and their quantities in a formatted way.
     * @param orderItems a List of OrderItems objects that represents the items in an order
     */
    public void printOrderItems(List<OrderItems> orderItems) {
        log.info("printing order details");
        System.out.format("%-20s %12s\n", "Product", "Quantity");
        for (OrderItems orderItem : orderItems) {
            Optional<Product> product = new ProductService(new ProductDAO()).getProd(orderItem.getProduct_id());
            
            System.out.format("%-20s %12s\n", product.get().getName(), orderItem.getQuantity());
        }
    }

    /**
     * A helper method that checks if a String input can be parsed as an integer.
     * @param input a String input from the user
     * @return true if the input can be parsed as an integer, false otherwise
     */
    private boolean isInt(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
    
    /**
     * A helper method that clears the screen by printing escape characters.
     */
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
}