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

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OrderHistoryScreen implements IScreen {
    private RouterServices router;
    private SessionUtil session;
    private OrderService orderService;
    private OrderItemService orderItemService;

    @Override
    public void start(Scanner scan) {
        List<Order> orders = orderService.getOrders(session.getId());

        while(true) {
            clearScreen();
            if (orders.isEmpty()) {
                System.out.print("You have no previous orders. Please press any key to return to main menu...");
                scan.nextLine();
                router.navigate("/menu", scan);
            }
            System.out.println("Please select the order you wish to view: ");
            int counter = 1;
            for (Order order : orders) {
                System.out.println("["+ counter + "]" + orderService.formatPrice(order.getTotal()));
            }
            System.out.println("\n[x] to return to main menu");

            String input = scan.nextLine();
            if (input.equals("x"))
                router.navigate("menu", scan);

            if (isInt(input)) {
                if (Integer.parseInt(input) > 0 && Integer.parseInt(input) <= orders.size()) {
                    Order order = orders.get(Integer.parseInt(input) - 1);
                    List<OrderItems> orderItems = orderItemService.getOrderItems(order.getId());
                    clearScreen();
                    printOrderItems(orderItems);
                    System.out.println("Press any key to return to Order History...");
                    router.navigate("/history", scan);
                    scan.nextLine();
                } else {
                    System.out.println("Invalid option!");
                    System.out.print("\nPress enter to continue...");
                    scan.nextLine();
                    break;
                }
            } 
            else {
                System.out.println("Invalid option!");
                System.out.print("\nPress enter to continue...");
                scan.nextLine();
                break;
            }
        }
    }

    public void printOrderItems(List<OrderItems> orderItems) {
        for (OrderItems orderItem : orderItems) {
            Optional<Product> product = new ProductService(new ProductDAO()).getProd(orderItem.getProduct_id());
            System.out.println(product.get().getName() + "            Quantity: " + orderItem.getQuantity());
        }
    }

    private boolean isInt(String input) {
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
