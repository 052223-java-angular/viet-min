package com.revature.app.services;

import java.util.Scanner;

import com.revature.app.daos.CartDAO;
import com.revature.app.daos.CartItemDAO;
import com.revature.app.daos.OrderDAO;
import com.revature.app.daos.OrderItemsDAO;
import com.revature.app.daos.ProductDAO;
import com.revature.app.daos.ReviewDAO;
import com.revature.app.daos.RoleDAO;
import com.revature.app.daos.UserDAO;
import com.revature.app.models.Cart;
import com.revature.app.models.Product;
import com.revature.app.screens.BrowseProductScreen;
import com.revature.app.screens.CartScreen;
import com.revature.app.screens.HomeScreen;
import com.revature.app.screens.LogInScreen;
import com.revature.app.screens.RegisterScreen;
import com.revature.app.utils.SessionUtil;

import lombok.AllArgsConstructor;

import com.revature.app.screens.ReviewScreen;
import com.revature.app.screens.MainMenuScreen;
import com.revature.app.screens.OrderHistoryScreen;
import com.revature.app.screens.ProductDetailScreen;


@AllArgsConstructor
public class RouterServices {
    private SessionUtil session;
    private Product product;

    public void navigate(String path, Scanner scan) {
        switch (path) {
            case "/home":
                new HomeScreen(this, session).start(scan);
                break;
            case "/login":
                new LogInScreen(this, getUserService(), session).start(scan);
                break;
            case "/register":
                new RegisterScreen(this, getUserService(), session).start(scan);
                break;
            case "/review":
                new ReviewScreen(this, product, session, getReviewService(), getUserService()).start(scan);
                break;
            case "/cart":
                new CartScreen(this, getCartService(), session, getPaymentService()).start(scan);;
                //new 
                break;
            case "/menu":
                new MainMenuScreen(this, session);
                break;
            case "/browse":
                new BrowseProductScreen(this, getProductService(), session).start(scan);
                break;
            case "/detail":
                new ProductDetailScreen(this, getCartService(), session, product).start(scan);
                break;
            case "/history":
                new OrderHistoryScreen(this, session, getOrderService(), getOrderItemService());
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
        return new CartService(new CartDAO(), getCartItemService(), getUserService());
    }

    private CartItemService getCartItemService(){
        return new CartItemService(new CartItemDAO() ,getProductService());
    }

    private ReviewService getReviewService(){
        return new ReviewService(new ReviewDAO());
    }

    private PaymentService getPaymentService(){
        return new PaymentService(getProductService());
    }
    public void setProduct(Product prod) {
        this.product = prod;
    }

    private OrderService getOrderService(){
        return new OrderService(new OrderDAO());
    }

    private OrderItemService getOrderItemService(){
        return new OrderItemService(new OrderItemsDAO());
    }
}
