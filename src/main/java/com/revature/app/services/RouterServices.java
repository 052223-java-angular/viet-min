/**
 * A service class that provides navigation logic for different screens.
 * It uses a SessionUtil object to store the current user session information.
 * It also uses a Product object to store the current product information.
 */
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

    /**
     * Navigates to a specific screen based on the given path and scanner.
     * It creates a new instance of the corresponding screen class and calls its start method with the scanner.
     * @param path The path of the screen to be navigated to.
     * @param scan The scanner object to be used for user input. 
     */
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
                new ReviewScreen(this, product, session, getReviewService(), getProductService(), getUserService()).start(scan);
                break;
            case "/cart":
                new CartScreen(this, getCartService(), session, getPaymentService(), 0).start(scan);;
                //new 
                break;
            case "/menu":
                new MainMenuScreen(this, session).start(scan);
                break;
            case "/browse":
                new BrowseProductScreen(this, getProductService(), session).start(scan);
                break;
            case "/detail":
                new ProductDetailScreen(this, getCartService(), session, product).start(scan);
                break;
            case "/history":
                new OrderHistoryScreen(this, session, getOrderService(), getOrderItemService()).start(scan);
            default:
                break;
        }
    }

    /**
     * Creates and returns a new UserService object with a UserDAO and a RoleService object as parameters.
     * @return A UserService object that provides business logic for users. 
     */
    private UserService getUserService() {
        return new UserService(new UserDAO(), getRoleService());
    }

    /**
     * Creates and returns a new RoleService object with a RoleDAO object as a parameter.
     * @return A RoleService object that provides business logic for roles. 
     */
    private RoleService getRoleService() {
        return new RoleService(new RoleDAO());
    }

    /**
     * Creates and returns a new ProductService object with a ProductDAO object as a parameter.
     * @return A ProductService object that provides business logic for products. 
     */
    private ProductService getProductService() {
        return new ProductService(new ProductDAO());
    }

    /**
     * Creates and returns a new CartService object with a CartDAO, a CartItemService and a UserService object as parameters.
     * @return A CartService object that provides business logic for carts. 
     */
    private CartService getCartService(){
        return new CartService(new CartDAO(), getCartItemService(), getUserService());
    }

    /**
     * Creates and returns a new CartItemService object with a CartItemDAO and a ProductService object as parameters.
     * @return A CartItemService object that provides business logic for cart items. 
     */
    private CartItemService getCartItemService(){
        return new CartItemService(new CartItemDAO() ,getProductService());
    }

    /**
     * Creates and returns a new ReviewService object with a ReviewDAO object as a parameter.
     * @return A ReviewService object that provides business logic for reviews. 
     */
    private ReviewService getReviewService(){
        return new ReviewService(new ReviewDAO());
    }

    /**
     * Creates and returns a new PaymentService object with a ProductService object as a parameter.
     * @return A PaymentService object that provides business logic for payments. 
     */
    private PaymentService getPaymentService(){
        return new PaymentService(getProductService());
    }

    /**
     * Sets the product field of this class to the given product parameter.
     * @param prod The Product object to be set as the current product. 
     */
    public void setProduct(Product prod) {
        this.product = prod;
    }

    /**
     * Creates and returns a new OrderService object with an OrderDAO object as a parameter.
     * @return An OrderService object that provides business logic for orders. 
     */
    private OrderService getOrderService(){
        return new OrderService(new OrderDAO());
    }

    /**
     * Creates and returns a new OrderItemService object with an OrderItemsDAO object as a parameter.
     * @return An OrderItemService object that provides business logic for order items. 
     */
    private OrderItemService getOrderItemService(){
        return new OrderItemService(new OrderItemsDAO());
    }
}