package com.mycompany.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mock.*;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revature.app.daos.ProductDAO;
import com.revature.app.services.PaymentService;
import com.revature.app.services.ProductService;
public class PaymentServiceTest {
    @Mock
    ProductService productService;
    @Mock
    private PaymentService paymentService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        paymentService = new PaymentService(productService);
    }

    //checks if card number is valid
    @Test
    public void testIsValidCardNumber() {
        String visa = "4788888888888888";
        String mastercard = "51111111111";
        String amex = "346343543423432432423432423432";
        String discover = "6222";

        assertFalse(paymentService.isValidCardNumber(discover));
        assertTrue(paymentService.isValidCardNumber(visa));
        assertFalse(paymentService.isValidCardNumber(amex));
        assertFalse(paymentService.isValidCardNumber(mastercard));
    }

    //checks if expiration date is valid
    @Test
    public void testIsValidExpirationDate() {
        String invalidDate = "10/2022";
        String validDate = "10/2023";
        assertFalse(paymentService.isValidExpirationDate(invalidDate));
        assertTrue(paymentService.isValidExpirationDate(validDate));
    }
}
