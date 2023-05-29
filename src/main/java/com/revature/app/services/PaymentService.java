package com.revature.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.revature.app.models.Cart;
import com.revature.app.models.CartItem;
import com.revature.app.models.Product;
import com.revature.app.utils.custom_datastucture.Pair;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PaymentService {
    private final ProductService productService;
    
    public String pay(String cardNumber, String expirtionDate, String securityCode, Optional<Cart> cartOpt) {
        List<CartItem> cartItemList = cartOpt.get().getItems();
        List<Pair> purchased = new ArrayList<>();
        for(CartItem cartItem : cartItemList){
            Optional<Product> product = productService.getProd(cartItem.getProduct_id());
            
            if(product.get().getStock() < cartItem.getQuantity()){
                for(Pair pair : purchased){
                    productService.setStock(pair.getId(), product.get().getStock() - pair.getCount());
                }
                return "We do not have enough [" + product.get().getName() + "] in stock! Change quantity to " + product.get().getStock() + " or lower.";
            }else{
                productService.setStock(product.get().getId(), product.get().getStock() - cartItem.getQuantity());
                Pair pInfo = new Pair(product.get().getId(), cartItem.getQuantity());
                purchased.add(pInfo);
            }
        }
        return "Thank you for your purchase!";
    }

    public boolean isValidCardNumber(String cardNumber) {
        String visa = "^4[0-9]{12}(?:[0-9]{3})?$";
        String masterCard = "^(?:5[1-5][0-9]{2}|222[1-9]|22[3-9][0-9]|2[3-6][0-9]{2}|27[01][0-9]|2720)[0-9]{12}$";
        String americanExpress = "^3[47][0-9]{13}$";
        String discover = "^6(?:011|5[0-9]{2})[0-9]{12}$";
        return (cardNumber.matches(visa) || cardNumber.matches(masterCard) || cardNumber.matches(americanExpress) || cardNumber.matches(discover));
    }

    public boolean isValidExpirationDate(String expirationDate) {
        String exp = "^(0[1-9]|1[0-2])/?([0-9]{4}|[0-9]{2})$";
        return expirationDate.matches(exp);
    }

    public boolean isValidSecurityCode(String securityCode) {
        String sec = "^[0-9]{3,4}$";
        return securityCode.matches(sec);
    }
    
}
