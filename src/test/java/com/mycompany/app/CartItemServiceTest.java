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

import com.revature.app.daos.CartItemDAO;
import com.revature.app.daos.ProductDAO;
import com.revature.app.models.Cart;
import com.revature.app.models.CartItem;
import com.revature.app.models.Product;
import com.revature.app.services.CartItemService;
import com.revature.app.services.ProductService;

public class CartItemServiceTest {
    @Mock
    private CartItemDAO cartItemDAO;
    @Mock
    private ProductDAO productDAO;
    private CartItemService cartItemService;
    private ProductService productService;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);

        productService = new ProductService(productDAO);
        cartItemService = new CartItemService(cartItemDAO, productService);
    }

    // //Returns a string stating successful adding of item to cart
    // @Test
    // public void testAdd() {
    //     String prod_id = "1";
    //     int count = 6;
    //     List<CartItem> cI = new ArrayList<>();
    //     CartItem cartItem = new CartItem("1", "book", "1", "2", 5, 22.1);
    //     cI.add(cartItem);
    //     Cart c = new Cart("1", "1", cI);
    //     Product p = new Product("2", "The Elder Scrolls", "Fus ro Dah", 9.99, 1, 42);

    //     when(productService.getProd(p.getId())).thenReturn(Optional.of(p));
    //     cartItemService.add(prod_id, count, c);
        
    //     verify(cartItemDAO, times(1)).save(cartItem);
    // }

    //finds all items belonging to a cart id
    @Test
    public void testGetCartItemsByCartId() {
        List<CartItem> cI = new ArrayList<>();
        CartItem a = new CartItem("1", "book", "1", "1", 5, 22.1);
        CartItem b = new CartItem("1", "book", "1", "1", 5, 22.1);
        CartItem c = new CartItem("1", "book", "1", "1", 5, 22.1);
        cI.add(a);
        cI.add(b);
        cI.add(c);

        when(cartItemDAO.findByCartId("1")).thenReturn(cI);

        List<CartItem> actual = cartItemService.getCartItemByCartId("1");

        assertEquals(cI, actual);
    }

    //tests delete cartitems
    @Test
    public void testDelete() {
        CartItem cartItem = new CartItem("1", "book", "1", "1", 5, 22.1);

        cartItemService.remove(cartItem.getId());

        verify(cartItemDAO, times(1)).delete(cartItem.getId());
    }

    //tests clearing all cartitem
    @Test
    public void testClear() {
        CartItem cartItem = new CartItem("1", "book", "1", "1", 5, 22.1);

        cartItemService.clearByCartId(cartItem.getCart_id());

        verify(cartItemDAO, times(1)).deleteByCartId(cartItem.getCart_id());
    }
}
