/**
 * A service class that provides business logic for cart items.
 * It uses a CartItemDAO object to perform CRUD operations on the cart items table.
 * It also uses a ProductService object to validate and retrieve product information.
 */
package com.revature.app.services;

import java.util.List;
import java.util.Optional;

import com.revature.app.daos.CartItemDAO;
import com.revature.app.models.Cart;
import com.revature.app.models.CartItem;
import com.revature.app.models.Product;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CartItemService {

    private final CartItemDAO cartItemDAO;
    private final ProductService productService;

    /**
     * Adds a new cart item to the cart with the given product id and quantity.
     * If the product id is not valid or the quantity is negative or exceeds the stock, it returns an error message.
     * If the cart already contains a cart item with the same product id, it calls the modify method to update the quantity.
     * Otherwise, it creates a new CartItem object and saves it using the cartItemDAO.
     * @param product_id The id of the product to be added to the cart.
     * @param count The quantity of the product to be added to the cart.
     * @param cart The cart object that contains the cart items.
     * @return A string message indicating the result of the operation.
     */
    public String add(String product_id, int count, Cart cart) {
        Optional<Product> productOpt = productService.getProd(product_id);
        if (productOpt.isEmpty()) {
            System.out.println("Product not found"); 
        }
        for(CartItem cartItem : cart.getItems()){
            if(cartItem.getProduct_id().equals(product_id) ){
                return modify(product_id, cartItem.getId(), cartItem.getQuantity() + count);
            }
        }
        if(productOpt.get().getStock() < count){
            return "not enough in stock, only " + productOpt.get().getStock() + " copies left!";
        }else if(count < 0){
            return "cannot be negative, please enter a number between 0 and " + productOpt.get().getStock();
        }
        CartItem cartItem = new CartItem(productOpt.get().getName(), cart.getId(), product_id, count, productOpt.get().getPrice());
        cartItemDAO.save(cartItem);
        return "successfully added to cart";
    }
    

    /**
     * Modifies an existing cart item with the given id and quantity.
     * If the product id is not valid or the quantity is negative or exceeds the stock, it returns an error message.
     * Otherwise, it updates the quantity of the cart item using the cartItemDAO.
     * @param product_id The id of the product associated with the cart item.
     * @param id The id of the cart item to be modified.
     * @param count The new quantity of the product to be set for the cart item.
     * @return A string message indicating the result of the operation.
     */
    public String modify(String product_id, String id, int count) {
        Optional<Product> productOpt = productService.getProd(product_id);
        if (productOpt.isEmpty()) {
            System.out.println("Product not found");
        }
        if(productOpt.get().getStock() < count){
            return "not enough in stock, only " + productOpt.get().getStock() + " copies left!";
        }else if(count < 0){
            return "cannot be negative, please enter a number between 0 and " + productOpt.get().getStock();
        }
        cartItemDAO.update(id, count);
        return "quantity updated";
    }

    /**
     * Removes an existing cart item with the given id from the cart items table.
     * It uses the cartItemDAO to delete the cart item by its id.
     * @param id The id of the cart item to be removed.
     */
    public void remove(String id) {
        cartItemDAO.delete(id);
    }

    /**
     * Retrieves a list of cart items that belong to a specific cart by its id.
     * It uses the cartItemDAO to find all the cart items that have the same cart id as the parameter.
     * @param cart_id The id of the cart whose items are to be retrieved.
     * @return A list of CartItem objects that belong to the given cart. 
     */
    public List<CartItem> getCartItemByCartId(String cart_id) {
        return cartItemDAO.findByCartId(cart_id);
    }


    /**
     * Clears all the cart items that belong to a specific cart by its id.
     * It uses the cartItemDAO to delete all the cart items that have the same cart id as the parameter.
     * @param id The id of the cart whose items are to be cleared.
     */
    public void clearByCartId(String id) {
        cartItemDAO.deleteByCartId(id);
    }
    
}