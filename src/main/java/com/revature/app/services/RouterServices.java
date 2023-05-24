package com.revature.app.services;

import java.util.Scanner;

import com.revature.app.screens.HomeScreen;
import com.revature.app.screens.RegisterScreen;

public class RouterServices {
    public void navigate(String path, Scanner scan) {
        switch (path) {
            case "/home":
                new HomeScreen(this).start(scan);
                break;
            case "/login":
                break;
            case "/register":
                new RegisterScreen(this).start(scan);
                break;
            case "/review":
                break;
            default:
                break;
        }
    }
}
