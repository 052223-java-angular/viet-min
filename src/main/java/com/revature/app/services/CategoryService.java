package com.revature.app.services;

import java.util.ArrayList;
import java.util.List;

import com.revature.app.daos.CategoryDAO;
import com.revature.app.models.Category;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CategoryService {
    private final CategoryDAO categoryDao;

    public List<Category> findAll() {
        List<Category> a = new ArrayList<>();
        Category b = new Category(1, "A");
        Category c = new Category(2, "b");
        Category d = new Category(3, "c");
        a.add(b);
        a.add(c);
        a.add(d);
        return a;
        //return categoryDao.findAll();
    }
}