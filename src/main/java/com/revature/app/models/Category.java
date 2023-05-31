/**
 * A class that represents a category of products in an online store.
 * It has an id and a name.
 */
package com.revature.app.models;

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
public class Category {
    /**
     * The id of the category.
     */
    private int id;
    /**
     * The name of the category.
     */
    private String name;
}