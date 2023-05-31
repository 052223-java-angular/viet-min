/**
 * A service class that provides business logic for payments.
 * It uses a ProductService object to validate and update product information.
 */
package com.revature.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.core.config.yaml.YamlConfiguration;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import com.revature.app.models.Cart;
import com.revature.app.models.CartItem;
import com.revature.app.models.Product;
import com.revature.app.utils.custom_datastucture.Pair;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PaymentService {
    private final ProductService productService;
    
    /**
     * Processes the payment for the cart items using the given card information.
     * It checks the stock of each product in the cart and updates it using the productService.
     * If any product has insufficient stock, it restores the stock of the previously purchased products and returns an error message.
     * Otherwise, it returns a success message after completing the payment.
     * @param cardNumber The card number to be used for the payment.
     * @param expirtionDate The expiration date of the card.
     * @param securityCode The security code of the card.
     * @param cartOpt An optional Cart object that contains the cart items to be paid for.
     * @return A string message indicating the result of the payment operation. 
     */
    public String pay(String cardNumber, String expirtionDate, String securityCode, Optional<Cart> cartOpt) {
        List<CartItem> cartItemList = cartOpt.get().getItems();
        List<Pair> purchased = new ArrayList<>();
        for(CartItem cartItem : cartItemList){
            Optional<Product> product = productService.getProd(cartItem.getProduct_id());
            
            if(product.get().getStock() < cartItem.getQuantity()){
                for(Pair pair : purchased){
                    productService.setStock(pair.getId(), productService.getProd(pair.getId()).get().getStock() + pair.getCount());
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

    /**
     * Validates a given card number using regular expressions for different card types.
     * It checks if the card number matches the patterns for Visa, MasterCard, American Express or Discover cards.
     * @param cardNumber The card number to be validated.
     * @return A boolean value indicating whether the card number is valid or not. 
     */
    public boolean isValidCardNumber(String cardNumber) {
        String visa = "^4[0-9]{12}(?:[0-9]{3})?$";
        String masterCard = "^(?:5[1-5][0-9]{2}|222[1-9]|22[3-9][0-9]|2[3-6][0-9]{2}|27[01][0-9]|2720)[0-9]{12}$";
        String americanExpress = "^3[47][0-9]{13}$";
        String discover = "^6(?:011|5[0-9]{2})[0-9]{12}$";
        return (cardNumber.matches(visa) || cardNumber.matches(masterCard) || cardNumber.matches(americanExpress) || cardNumber.matches(discover));
    }

    /**
     * Validates a given expiration date using a regular expression and a date comparison.
     * It checks if the expiration date matches the pattern of MM/yyyy and if it is not before the current date.
     * @param expirationDate The expiration date to be validated.
     * @return A boolean value indicating whether the expiration date is valid or not. 
     */
    public boolean isValidExpirationDate(String expirationDate) {
        String exp = "^(0[1-9]|1[0-2])/?([0-9]{4}|[0-9]{2})$";
        
        return expirationDate.matches(exp) && !isExpired(expirationDate);
    }

    /**
     * Validates a given security code using a regular expression.
     * It checks if the security code matches the pattern of 3 or 4 digits.
     * @param securityCode The security code to be validated.
     * @return A boolean value indicating whether the security code is valid or not. 
     */
    public boolean isValidSecurityCode(String securityCode) {
        String sec = "^[0-9]{3,4}$";
        return securityCode.matches(sec);
    }

    /**
     * Checks if a given expiration date is before the current date.
     * It parses the expiration date as a YearMonth object and compares it with the current date.
     * @param expirationDate The expiration date to be checked.
     * @return A boolean value indicating whether the expiration date is expired or not. 
     */
    public boolean isExpired(String expirationDate){
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MM/yyyy");
        YearMonth ym = YearMonth.parse(expirationDate, pattern);
        LocalDate curDate = LocalDate.now();
        return ym.isBefore(YearMonth.from(curDate));
    }
    
}