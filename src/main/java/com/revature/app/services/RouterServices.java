package com.revature.app.services;

import java.util.Scanner;

import com.revature.app.daos.RoleDAO;
import com.revature.app.daos.UserDAO;
import com.revature.app.screens.HomeScreen;
import com.revature.app.screens.LogInScreen;
import com.revature.app.screens.RegisterScreen;



public class RouterServices {
    public void navigate(String path, Scanner scan) {
        switch (path) {
            case "/home":
                new HomeScreen(this).start(scan);
                break;
            case "/login":
                new LogInScreen(this, getUserService()).start(scan);
                break;
            case "/register":
                new RegisterScreen(this, getUserService()).start(scan);
                break;
            case "/review":
                break;
            default:
                break;
        }
    }

    private UserService getUserService() {
        return new UserService(new UserDAO(), getRoleService());
    }

    private RoleService getRoleService() {
        return new RoleService(new RoleDAO());
    }

}
