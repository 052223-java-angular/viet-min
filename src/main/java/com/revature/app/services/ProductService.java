package com.revature.app.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.revature.app.daos.ProductDAO;
import com.revature.app.models.Product;
import com.revature.app.utils.custom_exceptions.ProductNotFoundException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductService {
    private final ProductDAO productDao;

    public List<Product> allProducts() {
        //return pseudo();
        return productDao.findAll();
    }

    public List<Product> byName(String name) throws ProductNotFoundException {
        Optional<Product> prodOpt = productDao.findByName(name);
        //return pseudo();
        return (List<Product>)prodOpt.orElseThrow(ProductNotFoundException::new);
    }

    public List<Product> byCategory(int category) {
        //return pseudo();
        return productDao.findByCategory(category);
    }

    public List<Product> byPrice(double minPrice, double maxPrice) {
        //return pseudo();
        return productDao.findByPriceRange(minPrice, maxPrice);
    }

    public Optional<Product> getProd(String id) {
        return productDao.getProduct(id);
    }

    public void setStock(String id, int quantity) {
        productDao.setStock(id, quantity);
    }
}
