package com.revature.app.screens;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

import org.apache.logging.log4j.core.net.ssl.SslConfiguration;

import com.revature.app.daos.OrderDAO;
import com.revature.app.daos.OrderItemsDAO;
import com.revature.app.models.Cart;
import com.revature.app.models.CartItem;
import com.revature.app.models.Order;
import com.revature.app.models.OrderItems;
import com.revature.app.services.CartService;
import com.revature.app.services.OrderItemService;
import com.revature.app.services.OrderService;
import com.revature.app.services.PaymentService;
import com.revature.app.services.RouterServices;
import com.revature.app.utils.SessionUtil;
import java.text.DecimalFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CartScreen implements IScreen{
    private final RouterServices router;
    private final CartService cart;
    private SessionUtil session;
    private final PaymentService paymentService;
    private final DecimalFormat df = new DecimalFormat("0.00");
    private static final Logger log = LogManager.getLogger(CartScreen.class);
    

    double total = 0;

    @Override
    public void start(Scanner scan) {
        log.info("Navigated to Cart Screen");
        String input = "";
        String item = "";
        int amount = 0;
        
        String cardNumber = "";
        String expirationDate = "";
        String securityCode = "";
        exit:{
            while(true){
                clearScreen();
                log.info("Displaying items in current cart with total price");
                Optional<Cart> cartOpt = cart.getCartByUserId(session.getId());
                Map<String, CartItem> itemMap = new HashMap<>();
                
                
                displayItems(cartOpt, itemMap, 0);

                System.out.println("------------------------------------------------------------------------------------------------------");
                System.out.println("[1] Continue shopping | [2] Remove item | [3] Modify item | [4] Checkout | [b] Back | [x] back to menu");
    
                input = scan.nextLine();
                switch(input){
                    case "b":
                        log.info("Returning to previous screen" + session.getScreenHistory());
                        router.navigate(session.getScreenHistory().pop(), scan);
                        break;
                   
                    case "1":
                        log.info("navigating to browse product screen");
                        session.getScreenHistory().push("/cart");
                        router.navigate("/browse", scan);
                        break;
                    case "2":
                        log.warn("No items in cart, cannot remove");
                        if(itemMap.size() == 0){
                            cartEmptyMessage(scan);
                            continue;
                        }else{
                            item = getItem(itemMap, scan);
                            cart.remove(itemMap.get(item).getId());
                            continue;
                        }
                    case "3":
                        if(itemMap.size() == 0){
                            cartEmptyMessage(scan);
                            continue;
                        }else{
                            item = getItem(itemMap, scan);
                            System.out.println("change amount to:");
                            amount = Integer.parseInt(scan.nextLine());
                            System.out.println(cart.modify(itemMap.get(item).getProduct_id(), itemMap.get(item).getId(), amount));
                            System.out.print("\nPress enter to continue...");
                            scan.nextLine();
                            continue;
                        }
                    case "4":
                        if(itemMap.size() == 0){
                            cartEmptyMessage(scan);
                            continue;
                        }else{
                            System.out.println("Your total will be: " + total);
                            cardNumber = getCardNumber(scan);
                            if(cardNumber == "x" || cardNumber == "b") continue; 
                            expirationDate = getExpirationDate(scan);
                            if(expirationDate == "x" || expirationDate == "b") continue; 
                            securityCode = getSecurityCode(scan);
                            if(securityCode == "x" || securityCode == "b") continue; 
                            String message = paymentService.pay(cardNumber, expirationDate, securityCode, cartOpt);
                            if(message.equals("Thank you for your purchase!")){
                                Order newOrder = new Order(session.getId(),total);
                                List<OrderItems> orderItems = new ArrayList<>();
                                itemMap.forEach((k, v) -> {
                                    OrderItems orderitem = new OrderItems(newOrder.getId(), v.getProduct_id(), v.getQuantity());
                                    orderItems.add(orderitem);
                                });
                                saveOrder(newOrder, orderItems);
                                cart.clear(cartOpt.get().getId());
                                
                                System.out.println(message);
                            }else{
                                System.out.println(message);
                                scan.nextLine();
                            }
                        }
                        break;
                    case "x":
                        router.navigate("/home", scan);
                        break;
                    default:
                        System.out.println("Invalid choice");
                        continue;
                }
    
                break;
            }
        }
        
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void cartEmptyMessage(Scanner scan){
        clearScreen();
        System.out.println("Your cart is empty");
        System.out.print("\nPress enter to continue...");
        scan.nextLine();
    }

    private String getCardNumber(Scanner scan){
        String cardNumber = "";
        
        while(true){
            System.out.println("\nEnter your card number: ");
            cardNumber = scan.nextLine();

            if(cardNumber.equalsIgnoreCase("x")){
                return "x";
            }

            if(cardNumber.equalsIgnoreCase("b")){
                return "b";
            }
            
            if(!paymentService.isValidCardNumber(cardNumber)){
                clearScreen();
                System.out.println("Invalid card number!");
                System.out.print("\nPress enter to continue...");
                scan.nextLine();
                continue;
            }
            break;
        }
        return cardNumber;
    }
    private String getExpirationDate(Scanner scan){
        String expirationDate = "";
        
        while(true){
            System.out.println("\nEnter the expiration date mm/yyyy: ");
            expirationDate = scan.nextLine();

            if(expirationDate.equalsIgnoreCase("x")){
                return "x";
            }

            if(expirationDate.equalsIgnoreCase("b")){
                return "b";
            }

            if(!paymentService.isValidExpirationDate(expirationDate)){
                clearScreen();
                System.out.println("Invalid expiration date!");
                System.out.print("\nPress enter to continue...");
                scan.nextLine();
                continue;
            }
            break;
        }
        return expirationDate;
    }
    private String getSecurityCode(Scanner scan){
        String securityCode = "";
        

        while(true){
            System.out.println("\nEnter the security code: ");
securityCode = scan.nextLine();

            if(securityCode.equalsIgnoreCase("x")){
                return "x";
            }

            if(securityCode.equalsIgnoreCase("b")){
                return "b";
            }

            if(!paymentService.isValidSecurityCode(securityCode)){
                clearScreen();
                System.out.println("Invalid expiration security code!");
                System.out.print("\nPress enter to continue...");
                scan.nextLine();
                continue;
            }
            break;
        }
        return securityCode;
    }

    private String getItem(Map map, Scanner scan){
        String item = "";
        

        while(true){
            System.out.println("\nChoose the item: ");
            item = scan.nextLine();

            if(item.equalsIgnoreCase("x")){
                log.info("returning to cart");
                return "x";
            }

            if(item.equalsIgnoreCase("b")){
                log.info("returning to cart");
                return "b";
            }

            if(!map.containsKey(item)){
                log.warn("invalid input");
                System.out.println("Invalid input! The choices are:" + map.keySet());
                System.out.print("\nPress enter to continue...");
                scan.nextLine();
                continue;
            }
            break;
        }
        log.info("removing item " + item + " from cart");
        return item;
    }

    private void displayItems(Optional<Cart> cartOpt, Map<String, CartItem> itemMap, double total){

        if(cartOpt.isPresent()){
            List<CartItem> cartItemList = cartOpt.get().getItems();
            
            log.info("printing cart items to screen");
            for(int i = 0; i < cartItemList.size(); i++){
                itemMap.put("p" + i, cartItemList.get(i));
                System.out.println(
                    String.format("%-60s","[p" + i + "]: " + cartItemList.get(i).getName())  + 
                    String.format("%-20s","(" + cartItemList.get(i).getQuantity() + ")*" + cartItemList.get(i).getPrice()) + 
                    "[" + df.format(cartItemList.get(i).getPrice() * cartItemList.get(i).getQuantity()) + "]"
                );
                total += cartItemList.get(i).getPrice() * cartItemList.get(i).getQuantity();
                this.total = total;
            }
            System.out.println(String.format("%88s","total: " + df.format(total)));
        }else{
            System.out.println("Cart is Empty");
        }
    }

    private void saveOrder(Order order, List<OrderItems> orderItems) {
        log.info("saving cart as a new order");
        new OrderService(new OrderDAO()).save(order);
        final OrderItemService orderItemService = new OrderItemService(new OrderItemsDAO());
        for (OrderItems o : orderItems) {
            orderItemService.save(o);
        }
    }
}