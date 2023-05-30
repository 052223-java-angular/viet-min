/**
 * A service class that provides business logic for categories.
 * It uses a CategoryDAO object to perform CRUD operations on the categories table.
 */
package com.revature.app.services;

import java.util.ArrayList;
import java.util.List;

import com.revature.app.daos.CategoryDAO;
import com.revature.app.models.Category;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CategoryService {
    private final CategoryDAO categoryDao;

    /**
     * Retrieves a list of all the categories from the categories table.
     * It uses the categoryDao to find all the categories and returns them as a list.
     * @return A list of Category objects representing all the categories in the database.
     */
    public List<Category> findAll() {
        return categoryDao.findAll();
    }
}