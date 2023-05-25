package com.revature.app.services;

import java.util.List;

import com.revature.app.daos.ProductDao;
import com.revature.app.models.Product;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductService {
    private final ProductDao productDao;
    public List<Product> products = allProducts();

    public List<Product> allProducts() {

        return productDao.findAll();
    }

    public List<Product> byName(String name) {
        return products.stream().filter(prod -> prod.getName().contains(name)).toList();
    }

    public List<Product> byCategory(String category) {
        return products.stream().filter(prod -> prod.getCategory().equals(category)).toList();
    }

    public List<Product> byPrice(double minPrice, double maxPrice) {
        return products.stream().filter(prod -> prod.getPrice() > minPrice && prod.getPrice() < maxPrice).toList();
    }
}
