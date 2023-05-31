package com.mycompany.app;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revature.app.daos.CartDAO;
import com.revature.app.daos.CartItemDAO;
import com.revature.app.models.Cart;
import com.revature.app.services.CartItemService;
import com.revature.app.services.CartService;
import com.revature.app.services.ProductService;
import com.revature.app.services.UserService;
public class CartServiceTest {
    @Mock
    private CartDAO cartDAO;
    @Mock
    private CartItemDAO cartItemDAO;
    private CartService cartService;
    private CartItemService cartItemService;
    private UserService userService;
    private ProductService productService;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);

        
        cartItemService = new CartItemService(cartItemDAO, productService);
        cartService = new CartService(cartDAO, cartItemService, userService);
    }

    //test asserts that a new cart is saved
    @Test
    public void testCreateCart() {
        String userId = "1";

        cartService.createCart(userId);

        verify(cartDAO, times(1)).save(any(Cart.class));
    }

    /*
     * All below methods use CartItemsService and tests will be found there
     */

    // //testing add method
    // @Test
    // public void testAdd() {
    //     String user = "1";
    //     String prod = "1";
    //     int count = 5;
    //     Cart c = new Cart("1", user, null);
    //     cartDAO.save(c);
    //     //asserts will create cart if cart is empty
    //     cartService.add(user, prod, count);
    //     verify(cartDAO, times(1)).save(any(Cart.class));
    // }

    // //testing remove method
    // @Test
    // public void testRemove() {
    //     CartItem cartItem = new CartItem("1", "book", "1", "1", 5, 22.1);
    //     //asserts will create cart if cart is empty

    //     doNothing().when(cartItemDAO).delete(cartItem.getId());

    //     cartService.remove(cartItem.getId());
    //     verify(cartItemDAO, times(1)).delete(cartItem.getId());
    // }

    // //testing modify method
    // @Test
    // public void testModify() {
    //     CartItem cartItem = new CartItem("1", "book", "1", "1", 5, 22.1);
    //     //asserts will create cart if cart is empty

    //     cartService.modify(cartItem.getProduct_id(), cartItem.getId(), 7);
    //     verify(cartItemService, times(1)).modify(cartItem.getProduct_id(), cartItem.getId(), 7);
    // }

    // //gets cart object by userId
    // @Test
    // public void testGetCartByUserId() {
    //     Cart expected = new Cart("1", "1", null);

    //     when(cartDAO.findByUserId(expected.getUser_id())).thenReturn(Optional.of(expected));

    //     Cart actual = cartService.getCartByUserId(expected.getUser_id()).get();

    //     assertEquals(expected, actual);
    // }

    // //tests clearing cart
    // @Test
    // public void testClear() {
    //     List<CartItem> c = new ArrayList<>();
    //     CartItem cartItem = new CartItem("1", "book", "1", "1", 5, 22.1);
    //     c.add(cartItem);
    //     Cart expected = new Cart("1", "1", c);

    //     //doNothing().when(cartDAO).save(expected);
    //     cartService.clear(expected.getId());

    //     verify(cartItemService, times(1)).clearByCartId(expected.getId());
    // }
}
