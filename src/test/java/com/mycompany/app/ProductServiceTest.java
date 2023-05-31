package com.mycompany.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mock.*;
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

import com.revature.app.daos.ProductDAO;
import com.revature.app.models.Product;
import com.revature.app.services.ProductService;


public class ProductServiceTest {
    @Mock
    private ProductDAO productDAO;
    
    private ProductService productService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        productService = new ProductService(productDAO);
    }

    //returns list of all products
    @Test
    public void testAllProducts() {
        List<Product> expected = new ArrayList<>();

        expected.add(new Product("a353098a-fb53-11ed-be56-0242ac120002", "The Elder Scrolls", "Fus ro Dah", 9.99, 1, 42));
        expected.add(new Product("a3531022-fb53-11ed-be56-0242ac120002", "Zelda", "Fus ro Dah", 9.99, 1, 42));

        when(productDAO.findAll()).thenReturn(expected);

        List<Product> actual = productService.allProducts();

        assertEquals(expected, actual);
    }

    //return product by category
    @Test
    public void testbyCategory() {
        List<Product> expected = new ArrayList<>();

        expected.add(new Product("a353098a-fb53-11ed-be56-0242ac120002", "The Elder Scrolls", "Fus ro Dah", 9.99, 1, 42));
        expected.add(new Product("a3531022-fb53-11ed-be56-0242ac120002", "Zelda", "Fus ro Dah", 9.99, 1, 42));
        when(productDAO.findByCategory(expected.get(0).getCategory())).thenReturn(expected);

        List<Product> actual = productService.byCategory(expected.get(0).getCategory());

        assertEquals(expected, actual);
    }

    //return product by name
    @Test
    public void testbyName() {
        List<Product> expected = new ArrayList<>();
        Product a = new Product("a353098a-fb53-11ed-be56-0242ac120002", "The Elder Scrolls", "Fus ro Dah", 9.99, 1, 42);
        expected.add(a);

        when(productDAO.findByName(expected.get(0).getName())).thenReturn(Optional.of(expected));

        List<Product> actual = productService.byName(expected.get(0).getName());

        assertEquals(expected, actual);
    }

    //returns products between min and max prices
    @Test
    public void testbyPrice() {
        final double minPrice = 8.00;
        final double maxPrice = 10.00;
        List<Product> expected = new ArrayList<>();

        expected.add(new Product("a353098a-fb53-11ed-be56-0242ac120002", "The Elder Scrolls", "Fus ro Dah", 9.99, 1, 42));
        expected.add(new Product("a3531022-fb53-11ed-be56-0242ac120002", "Zelda", "Fus ro Dah", 9.99, 1, 42));
        when(productDAO.findByPriceRange(minPrice, maxPrice)).thenReturn(expected);

        List<Product> actual = productService.byPrice(minPrice, maxPrice);

        assertEquals(expected, actual);
    }

    //returns product by product id
    @Test
    public void getProd() {
        Product expected = new Product("a353098a-fb53-11ed-be56-0242ac120002", "The Elder Scrolls", "Fus ro Dah", 9.99, 1, 42);
        when(productDAO.getProduct(expected.getId())).thenReturn(Optional.of(expected));

        Optional<Product> actual = productService.getProd(expected.getId());
        assertEquals(expected, actual.get());
    }

    //returns sets Stock for item
    @Test
    public void setStock() {
        String id = "a353098a-fb53-11ed-be56-0242ac120002";
        int stock = 21;
        productService.setStock(id, stock);

        verify(productDAO, times(1)).setStock(id, stock);
    }
}
