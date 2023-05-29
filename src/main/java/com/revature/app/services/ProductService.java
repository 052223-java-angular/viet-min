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
        return pseudo();
        //return productDao.findAll();
    }

    public List<Product> byName(String name) throws ProductNotFoundException {
        //Optional<Product> prodOpt = productDao.findByName(name);
        return pseudo();
        //return (List<Product>)prodOpt.orElseThrow(ProductNotFoundException::new);
    }

    public List<Product> byCategory(int category) {
        return pseudo();
        //return productDao.findByCategory(category);
    }

    public List<Product> byPrice(double minPrice, double maxPrice) {
        return pseudo();
        //return productDao.findByPriceRange(minPrice, maxPrice);
    }

    public Optional<Product> getProd(String id) {
        return productDao.getProduct(id);
    }

    public List<Product> pseudo() {
        Product a = new Product("1", "game a", "a game", 22.1, 1, 10);
        Product b = new Product("2", "game b", "b game", 22.1, 2, 10);
        Product c = new Product("3", "game c", "c game", 22.1, 3, 10);
        Product d = new Product("4", "game d", "d game", 22.1, 3, 10);
        List<Product> w = new ArrayList<>(Arrays.asList(a, b, c, d));
        return w;
    }
}
