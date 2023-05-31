/**
 * A class that represents a product in an online store.
 * It has an id, a name, a description, a price, a category and a stock.
 */
package com.revature.app.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Product {
    /**
     * The id of the product.
     */
    private String id;
    /**
     * The name of the product.
     */
    private String name;
    /**
     * The description of the product.
     */
    private String description;
    /**
     * The price of the product per unit.
     */
    private double price;
    /**
     * The category of the product, represented by an integer.
     */
    private int category;
    /**
     * The stock of the product, indicating how many units are available.
     */
    private int stock;
}