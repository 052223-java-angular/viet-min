package com.revature.app.services;

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

        return productDao.findAll();
    }

    public List<Product> byName(String name) throws ProductNotFoundException {
        Optional<Product> prodOpt = productDao.findByName(name);

        return (List<Product>)prodOpt.orElseThrow(ProductNotFoundException::new);
    }

    public List<Product> byCategory(int category) {
        return productDao.findByCategory(category);
    }

    public List<Product> byPrice(double minPrice, double maxPrice) {
        return productDao.findByPriceRange(minPrice, maxPrice);
    }
}
