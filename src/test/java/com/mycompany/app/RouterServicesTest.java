package com.mycompany.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mock.*;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revature.app.models.Product;
import com.revature.app.screens.HomeScreen;
import com.revature.app.services.CartItemService;
import com.revature.app.services.CartService;
import com.revature.app.services.OrderItemService;
import com.revature.app.services.OrderService;
import com.revature.app.services.PaymentService;
import com.revature.app.services.ProductService;
import com.revature.app.services.ReviewService;
import com.revature.app.services.RoleService;
import com.revature.app.services.RouterServices;
import com.revature.app.services.UserService;
import com.revature.app.utils.SessionUtil;


public class RouterServicesTest {

    @Mock
    private Scanner scan;
    @Mock
    private Product product;
    @Mock
    private SessionUtil session;
    @Mock
    private UserService userService;
    @Mock
    private RoleService roleService;
    @Mock
    private ProductService productService;
    @Mock
    private CartService cartService;
    @Mock
    private CartItemService cartItemService;
    @Mock
    private ReviewService reviewService;
    @Mock
    private PaymentService paymentService;
    @Mock
    private OrderService orderService;
    @Mock
    private OrderItemService orderItemService;
    private RouterServices routerServices;
    
    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);

        routerServices = new RouterServices(session, product);
    }

    @Test
    public void test_navigate_home() {
        String expected = HomeScreen.class.toString();

        routerServices.navigate("/home", scan);
    }
}
