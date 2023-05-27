package com.revature.app.services;

import java.util.Scanner;

import com.revature.app.daos.CartDAO;
import com.revature.app.daos.CartItemDAO;
import com.revature.app.daos.ProductDAO;
import com.revature.app.daos.RoleDAO;
import com.revature.app.daos.UserDAO;
import com.revature.app.models.Session;
import com.revature.app.screens.BrowseProductScreen;
import com.revature.app.screens.CartScreen;
import com.revature.app.screens.HomeScreen;
import com.revature.app.screens.LogInScreen;
import com.revature.app.screens.RegisterScreen;
import com.revature.app.screens.LogInScreen;



public class RouterServices {
    private Session session;
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
                //new 
                break;
            case "/cart":
                new CartScreen(this, getCartService()).start(scan);;
                //new 
                break;
            case "/menu":
                break;
            case "/browse":
                new BrowseProductScreen(this, getProductService()).start(scan);
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

    private ProductService getProductService() {
        return new ProductService(new ProductDAO());
    }

    private CartService getCartService(){
        return new CartService(new CartDAO(), getCartItemService());
    }

    private CartItemService getCartItemService(){
        return new CartItemService(new CartItemDAO());
    }
}
