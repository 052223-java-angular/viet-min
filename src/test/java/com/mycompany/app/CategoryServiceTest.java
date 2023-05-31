package com.mycompany.app;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revature.app.daos.CategoryDAO;
import com.revature.app.models.Category;
import com.revature.app.services.CategoryService;
public class CategoryServiceTest {
    @Mock
    private CategoryDAO categoryDAO;
    private CategoryService categoryService;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);

        categoryService = new CategoryService(categoryDAO);
    }

    //gets all order items in an order
    @Test
    public void testgetOrderItems() {
        List<Category> expected = new ArrayList<>();

        expected.add(new Category(1, "Cat1"));
        expected.add(new Category(2, "Cat2"));
        expected.add(new Category(3, "Cat3"));

        when(categoryDAO.findAll()).thenReturn(expected);

        List<Category> actual = categoryService.findAll();

        assertEquals(expected, actual);
    }

}
