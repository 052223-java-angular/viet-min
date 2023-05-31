/**
 * A service class that provides business logic for products.
 * It uses a ProductDAO object to perform CRUD operations on the products table.
 */
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

    /**
     * Retrieves a list of all the products from the products table.
     * It uses the productDao to find all the products and returns them as a list.
     * @return A list of Product objects representing all the products in the database.
     */
    public List<Product> allProducts() {
        //return pseudo();
        return productDao.findAll();
    }

    /**
     * Retrieves a list of products that have a specific name from the products table.
     * It uses the productDao to find the products that have the same name as the parameter.
     * If no products are found, it throws a ProductNotFoundException.
     * @param name The name of the products to be retrieved.
     * @return A list of Product objects that have the given name. 
     * @throws ProductNotFoundException If no products are found with the given name. 
     */
    public Product byName(String name) throws ProductNotFoundException {
        Optional<Product> prodOpt = productDao.findByName(name);
        //return pseudo();
        return prodOpt.orElseThrow(ProductNotFoundException::new);
    }

    /**
     * Retrieves a list of products that belong to a specific category from the products table.
     * It uses the productDao to find the products that have the same category id as the parameter.
     * @param category The id of the category whose products are to be retrieved.
     * @return A list of Product objects that belong to the given category. 
     */
    public List<Product> byCategory(int category) {
        //return pseudo();
        return productDao.findByCategory(category);
    }

    /**
     * Retrieves a list of products that have a price within a specific range from the products table.
     * It uses the productDao to find the products that have a price between the minPrice and maxPrice parameters.
     * @param minPrice The lower bound of the price range (inclusive).
     * @param maxPrice The upper bound of the price range (inclusive).
     * @return A list of Product objects that have a price within the given range. 
     */
    public List<Product> byPrice(double minPrice, double maxPrice) {
        //return pseudo();
        return productDao.findByPriceRange(minPrice, maxPrice);
    }

    /**
     * Retrieves an optional product object that has a specific id from the products table.
     * It uses the productDao to find the product that has the same id as the parameter.
     * @param id The id of the product to be retrieved.
     * @return An optional Product object that has the given id. 
     */
    public Optional<Product> getProd(String id) {
        return productDao.getProduct(id);
    }

    /**
     * Updates the stock of a specific product in the products table.
     * It uses the productDao to set the stock of the product that has the same id as the parameter to the given quantity. 
     * @param id The id of the product whose stock is to be updated.
     * @param quantity The new quantity of the product to be set in the table. 
     */
    public void setStock(String id, int quantity) {
        productDao.setStock(id, quantity);
    }
}