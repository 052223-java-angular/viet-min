package com.mycompany.app;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revature.app.daos.OrderItemsDAO;
import com.revature.app.models.OrderItems;
import com.revature.app.services.OrderItemService;

public class OrderItemServiceTest {
    @Mock
    private OrderItemsDAO orderItemsDAO;
    private OrderItemService orderItemService;
    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);

        orderItemService = new OrderItemService(orderItemsDAO);
    }

    //gets all order items in an order
    @Test
    public void testgetOrderItems() {
        List<OrderItems> expected = new ArrayList<>();

        expected.add(new OrderItems("a353098a-fb53-11ed-be56-0242ac120002", "1","2",200));
        expected.add(new OrderItems("a3531022-fb53-11ed-be56-0242ac120002", "1", "3", 666));

        when(orderItemsDAO.findbyOrderId("1")).thenReturn(expected);

        List<OrderItems> actual = orderItemService.getOrderItems("1");

        assertEquals(expected, actual);
    }

    //This test asserts that an orderitem is saved correctly
    @Test
    public void test_Save() {
        String itemId = "7b58194d-bea3-4061-bfd0-9f035cda18b2";
        String orderId = "1";
        String productId = "2";
        int quantity = 10;

        OrderItems expected = new OrderItems(itemId,orderId, productId, quantity);
        orderItemService.save(expected);

        verify(orderItemsDAO, times(1)).save(expected);
    }
}
