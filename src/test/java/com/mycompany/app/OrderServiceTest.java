package com.mycompany.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mock.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revature.app.daos.OrderDAO;
import com.revature.app.models.Order;
import com.revature.app.services.OrderService;

public class OrderServiceTest {
    @Mock
    private OrderDAO orderDAO;
    private OrderService orderService;
    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);

        orderService = new OrderService(orderDAO);
    }

    //This test asserts that an order is saved correctly
    @Test
    public void test_Save() {
        String orderId = "7b58194d-bea3-4061-bfd0-9f035cda18b2";
        String userId = "a353098a-fb53-11ed-be56-0242ac120002";
        double total = 1000.51;

        Order expected = new Order(orderId, userId, total);
        orderService.save(expected);

        verify(orderDAO, times(1)).save(expected);
    }

    //Test returns all orders by user
    @Test
    public void testFindAllByUser() {
        List<Order> expected = new ArrayList<>();

        expected.add(new Order("a353098a-fb53-11ed-be56-0242ac120002", "1", 200));
        expected.add(new Order("a3531022-fb53-11ed-be56-0242ac120002", "1", 666));

        when(orderDAO.findAllByUser("1")).thenReturn(expected);

        List<Order> actual = orderService.getOrders("1");

        assertEquals(expected, actual);
    }

    public void testFormatPrice() {
        String expected = "$10.99";
        double test = 10.98666;

        assertEquals(expected, orderService.formatPrice(test));
    }

}
