package com.revature.app.screens;

import java.util.Scanner;

import com.revature.app.models.User;
import com.revature.app.services.RouterServices;
import com.revature.app.services.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegisterScreen implements IScreen {
    private final UserService userService;
    private final RouterServices router;

    @Override
    public void start(Scanner scan) {
        exit: {
            while(true){
            //     System.out.println("Welcome to YOLP!");
            //     System.out.println("\n[1] Login screen");
            //     System.out.println("[2] register screen");
            //     System.out.println("[x] Exit");
            //     break;
            // }
            clearScreen();
            System.out.println("Hello Darkness my old friend");
            //break;
            }
        }
    }


    //Helper methods

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
