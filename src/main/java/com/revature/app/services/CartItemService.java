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

    public String add(String product_id, int count, Cart cart) {
        Optional<Product> productOpt = productService.getProd(product_id);
        if (productOpt.isEmpty()) {
            System.out.println("Product not found");
            //product not found exception
            
        }
        
        for(CartItem cartItem : cart.getItems()){
            if(cartItem.getProduct_id() == product_id){
                return modify(product_id, cartItem.getQuantity() + count);
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
    

    public String modify(String product_id, int count) {
        Optional<Product> productOpt = productService.getProd(product_id);
        if (productOpt.isEmpty()) {
            System.out.println("Product not found");
        }
        if(productOpt.get().getStock() < count){
            return "not enough in stock, only " + productOpt.get().getStock() + " copies left!";
        }else if(count < 0){
            return "cannot be negative, please enter a number between 0 and " + productOpt.get().getStock();
        }
        cartItemDAO.update(product_id, count);
        return "quantity updated";
    }

    public void remove(String id) {
        cartItemDAO.delete(id);
    }

    public List<CartItem> getCartItemByCartId(String cart_id) {
        return cartItemDAO.findByCartId(cart_id);
    }


    public void clearByCartId(String id) {
        cartItemDAO.deleteByCartId(id);
    }
    
}
