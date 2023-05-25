package com.revature.app.services;

import java.time.chrono.ThaiBuddhistDate;
import java.util.Scanner;

import com.revature.app.models.Session;
import com.revature.app.screens.HomeScreen;
import com.revature.app.screens.RegisterScreen;
import com.revature.app.screens.LoginScreen;

public class RouterServices {
    private Session session;
    public void navigate(String path, Scanner scan) {
        switch (path) {
            case "/home":
                new HomeScreen(this).start(scan);
                break;
            case "/login":
                new LoginScreen(new UserService(null, null),this).start(scan);
                break;
            case "/register":
                new RegisterScreen(new UserService(null, null),this).start(scan);
                break;
            case "/review":
                //new 
                break;
            default:
                break;
        }
    }
}
