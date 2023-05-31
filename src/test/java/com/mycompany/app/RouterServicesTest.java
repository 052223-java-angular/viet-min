package com.mycompany.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mock.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revature.app.models.Product;
import com.revature.app.screens.BrowseProductScreen;
import com.revature.app.screens.CartScreen;
import com.revature.app.screens.HomeScreen;
import com.revature.app.screens.LogInScreen;
import com.revature.app.screens.MainMenuScreen;
import com.revature.app.screens.OrderHistoryScreen;
import com.revature.app.screens.ProductDetailScreen;
import com.revature.app.screens.RegisterScreen;
import com.revature.app.screens.ReviewScreen;
import com.revature.app.services.RouterServices;
import com.revature.app.utils.SessionUtil;


public class RouterServicesTest {
    @Mock
    private Product product;
    @Mock
    private SessionUtil session;
    private RouterServices routerServices;
    
    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);

        routerServices = new RouterServices(session, product);
    }

    // @Test
    // public void testNavigateHome() {
    //     Scanner scan = new Scanner(System.in);
    //     HomeScreen homeScreen = mock(HomeScreen.class);
        
    //     routerServices.navigate("/home", scan);
    //     verify(homeScreen, times(1)).start(scan);
    // }

    // @Test
    // public void testNavigateLogin() {
    //     Scanner scan = new Scanner(System.in);
    //     LogInScreen loginScreen = mock(LogInScreen.class);

    //     routerServices.navigate("/login", scan);
    //     verify(loginScreen, times(1)).start(scan);
    // }

    // @Test
    // public void testNavigateRegister() {
    //     Scanner scan = new Scanner(System.in);
    //     RegisterScreen registerScreen = mock(RegisterScreen.class);

    //     routerServices.navigate("/register", scan);
    //     verify(registerScreen, times(1)).start(scan);
    // }

    // @Test
    // public void testNavigateReview() {
    //     Scanner scan = new Scanner(System.in);
    //     ReviewScreen reviewScreen = mock(ReviewScreen.class);

    //     routerServices.navigate("/review", scan);
    //     verify(reviewScreen, times(1)).start(scan);
    // }

    // @Test
    // public void testNavigateCart() {
    //     Scanner scan = new Scanner(System.in);
    //     CartScreen cartScreen = mock(CartScreen.class);

    //     routerServices.navigate("/cart", scan);
    //     verify(cartScreen, times(1)).start(scan);
    // }

    // @Test
    // public void testNavigateMenu() {
    //     Scanner scan = new Scanner(System.in);
    //     MainMenuScreen menuScreen = mock(MainMenuScreen.class);

    //     routerServices.navigate("/menu", scan);
    //     verify(menuScreen, times(1)).start(scan);
    // }

    // @Test
    // public void testNavigateBrowse() {
    //     Scanner scan = new Scanner(System.in);
    //     BrowseProductScreen browseScreen = mock(BrowseProductScreen.class);

    //     routerServices.navigate("/browse", scan);
    //     verify(browseScreen, times(1)).start(scan);
    // }

    // @Test
    // public void testNavigateDetail() {
    //     Scanner scan = new Scanner(System.in);
    //     ProductDetailScreen detailScreen = mock(ProductDetailScreen.class);

    //     routerServices.navigate("/detail", scan);
    //     verify(detailScreen, times(1)).start(scan);
    // }

    // @Test
    // public void testNavigateHistory() {
    //     Scanner scan = new Scanner(System.in);
    //     OrderHistoryScreen historyScreen = mock(OrderHistoryScreen.class);

    //     routerServices.navigate("/history", scan);
    //     verify(historyScreen, times(1)).start(scan);
    // }
}

