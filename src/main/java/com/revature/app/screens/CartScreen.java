package com.revature.app.screens;

import java.util.Scanner;

import com.revature.app.services.CartService;
import com.revature.app.services.RouterServices;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CartScreen implements IScreen{
    private final RouterServices router;
    private final CartService cart;

    @Override
    public void start(Scanner scan) {
        String input = "";
        String item = "";
        String amount = "";
        while(true){
            System.out.println("[1] Continue shopping");
            System.out.println("[2] Remove item");
            System.out.println("[3] Modify item");
            System.out.println("[4] Checkout");
            System.out.println("[b] Back to main menu");
            System.out.println("[x] Exit");
            
            input = scan.nextLine();
            switch(input){
                case "b":
                    router.navigate("/home", scan);
                    break;
               
                case "1":
                    System.out.println("go shop some more");
                    //router.navigate("/home", scan);
                    break;
                case "2":
                    System.out.println("chose the item you want to delete");
                    item = scan.nextLine();
                    cart.remove(item);
                    break;
                case "3":
                    System.out.println("choose item you want to modify");
                    item = scan.nextLine();
                    System.out.println("change amount to:");
                    amount = scan.nextLine();
                    cart.modify(item, amount);
                    break;
                case "4":
                    System.out.println("Your total will be: ");
                    break;
                case "x":
                    break;
                default:
                    System.out.println("Invalid choice");
                    continue;
            }

            break;
        }
    }
    
}
