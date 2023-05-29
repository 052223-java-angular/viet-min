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
import com.revature.app.services.ProductService;
import com.revature.app.services.RouterServices;
import com.revature.app.utils.SessionUtil;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CartScreen implements IScreen{
    private final RouterServices router;
    private final CartService cart;
    private SessionUtil session;


    @Override
    public void start(Scanner scan) {
        String input = "";
        String item = "";
        int amount = 0;
        exit:{
            while(true){
                clearScreen();
                System.out.println("[1] Continue shopping");
                System.out.println("[2] Remove item");
                System.out.println("[3] Modify item");
                System.out.println("[4] Checkout");
                System.out.println("[b] Back");
                System.out.println("[x] back to menu");
                Optional<Cart> ct = cart.getCartByUserId("38d853d5-8235-4d05-b285-d51f0b11ca6b");
                Map<String, String> idMap = new HashMap<>();
                if(ct.isPresent()){
                    List<CartItem> ci = ct.get().getItems();
                    
                    for(int i = 0; i < ci.size(); i++){
                        idMap.put("p" + i, ci.get(i).getProduct_id());
                        System.out.println("[p" + i + "]: " + ci.get(i).getName() + "----------[" + ci.get(i).getQuantity() + "]*" + ci.get(i).getPrice());
                    }
                }
                
    
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
                            System.out.println("chose the item you want to delete");
                            item = scan.nextLine();
                            cart.remove(idMap.get(item));
                            continue;
                        }
                    case "3":
                        if(idMap.size() == 0){
                            cartEmptyMessage(scan);
                            continue;
                        }else{
                            System.out.println("choose item you want to modify");
                            item = scan.nextLine();
                            System.out.println("change amount to:");
                            amount = Integer.parseInt(scan.nextLine());
                            cart.modify(idMap.get(item), amount);
                            continue;
                        }
                    case "4":
                        if(idMap.size() == 0){
                            cartEmptyMessage(scan);
                            continue;
                        }else{
                            System.out.println("Your total will be: ");
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
    
}
