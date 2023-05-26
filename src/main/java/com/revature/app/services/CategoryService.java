package com.revature.app.services;

import java.util.List;

import com.revature.app.daos.CategoryDAO;
import com.revature.app.models.Category;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CategoryService {
    private final CategoryDAO categoryDao;

    public List<Category> findAll() {
        return categoryDao.findAll();
    }
}