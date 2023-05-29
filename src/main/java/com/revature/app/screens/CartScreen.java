package com.revature.app.screens;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

import org.apache.logging.log4j.core.net.ssl.SslConfiguration;

import com.revature.app.models.Cart;
import com.revature.app.models.CartItem;
import com.revature.app.services.CartService;
import com.revature.app.services.PaymentService;
import com.revature.app.services.ProductService;
import com.revature.app.services.RouterServices;
import com.revature.app.utils.SessionUtil;
import java.text.DecimalFormat;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CartScreen implements IScreen{
    private final RouterServices router;
    private final CartService cart;
    private SessionUtil session;
    private final DecimalFormat df = new DecimalFormat("0.00");

    @Override
    public void start(Scanner scan) {
        String input = "";
        String item = "";
        int amount = 0;
        
        String cardNumber = "";
        String expirtionDate = "";
        String securityCode = "";
        exit:{
            while(true){
                clearScreen();
                
                Optional<Cart> cartOpt = cart.getCartByUserId(session.getId());
                Map<String, String> idMap = new HashMap<>();
                double total = 0;
                if(cartOpt.isPresent()){
                    List<CartItem> cartItemList = cartOpt.get().getItems();
                    
                    for(int i = 0; i < cartItemList.size(); i++){
                        idMap.put("p" + i, cartItemList.get(i).getProduct_id());
                        System.out.println(
                            String.format("%-60s","[p" + i + "]: " + cartItemList.get(i).getName())  + 
                            String.format("%-20s","(" + cartItemList.get(i).getQuantity() + ")*" + cartItemList.get(i).getPrice()) + 
                            "[" + df.format(cartItemList.get(i).getPrice() * cartItemList.get(i).getQuantity()) + "]"
                        );
                        total += cartItemList.get(i).getPrice() * cartItemList.get(i).getQuantity();
                    }
                    System.out.println(String.format("%88s","total: " + df.format(total)));
                }else{
                    System.out.println("Cart is Empty");
                }

                System.out.println("[1] Continue shopping | [2] Remove item | [3] Modify item | [4] Checkout | [b] Back | [x] back to menu");
    
                input = scan.nextLine();
                switch(input){
                    case "b":
                        router.navigate(session.getScreenHistory().pop(), scan);
                        break;
                   
                    case "1":
                        System.out.println("go shop some more");
                        //router.navigate("/home", scan);
                        break;
                    case "2":
                        if(idMap.size() == 0){
                            cartEmptyMessage(scan);
                            continue;
                        }else{
                            item = getItem(idMap, scan);
                            cart.remove(idMap.get(item));
                            continue;
                        }
                    case "3":
                        if(idMap.size() == 0){
                            cartEmptyMessage(scan);
                            continue;
                        }else{
                            item = getItem(idMap, scan);
                            System.out.println("change amount to:");
                            amount = Integer.parseInt(scan.nextLine());
                            System.out.println(cart.modify(idMap.get(item), amount));
                            System.out.print("\nPress enter to continue...");
                            scan.nextLine();
                            continue;
                        }
                    case "4":
                        if(idMap.size() == 0){
                            cartEmptyMessage(scan);
                            continue;
                        }else{
                            System.out.println("Your total will be: " + total);
                            cardNumber = getCardNumber(scan);
                            expirtionDate = getExpirationDate(scan);
                            securityCode = getSecurityCode(scan);
                            if(PaymentService.pay(cardNumber, expirtionDate, securityCode)){
                                System.out.println("Thank you for your purchase!");
                                //need order service to be implemented
                                //orderService.add(cart);
                                cart.clear(cartOpt.get().getId());
                                //add to order history
                            }else{
                                System.out.println("Try again!");
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
            
            if(!PaymentService.isValidCardNumber(cardNumber)){
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

            if(!PaymentService.isValidExpirationDate(expirationDate)){
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

            if(!PaymentService.isValidSecurityCode(securityCode)){
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
                return "x";
            }

            if(item.equalsIgnoreCase("b")){
                return "b";
            }

            if(!map.containsKey(item)){
                System.out.println("Invalid input! The choices are:" + map.keySet());
                System.out.print("\nPress enter to continue...");
                scan.nextLine();
                continue;
            }
            break;
        }
        return item;
    }
    
}
