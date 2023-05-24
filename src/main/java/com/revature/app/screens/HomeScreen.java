package com.revature.app.screens;

import java.util.Scanner;

import com.revature.app.services.RouterServices;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HomeScreen implements IScreen {
    private final RouterServices router;

    @Override
    public void start(Scanner scan) {
        String input = "";
        while(true){
            System.out.println("Welcome to YOLP!");
            System.out.println("\n[1] Login screen");
            System.out.println("[2] register screen");
            System.out.println("[x] Exit");
            break;
        }
    }
    
}
