package com.revature.app.services;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PaymentService {
    public static boolean pay(String cardNumber, String expirtionDate, String securityCode) {
        return true;
    }

    public static boolean isValidCardNumber(String cardNumber) {
        String visa = "^4[0-9]{12}(?:[0-9]{3})?$";
        String masterCard = "^(?:5[1-5][0-9]{2}|222[1-9]|22[3-9][0-9]|2[3-6][0-9]{2}|27[01][0-9]|2720)[0-9]{12}$";
        String americanExpress = "^3[47][0-9]{13}$";
        String discover = "^6(?:011|5[0-9]{2})[0-9]{12}$";
        return (cardNumber.matches(visa) || cardNumber.matches(masterCard) || cardNumber.matches(americanExpress) || cardNumber.matches(discover));
    }

    public static boolean isValidExpirationDate(String expirationDate) {
        String exp = "^(0[1-9]|1[0-2])/?([0-9]{4}|[0-9]{2})$";
        return expirationDate.matches(exp);
    }

    public static boolean isValidSecurityCode(String securityCode) {
        String sec = "^[0-9]{3,4}$";
        return securityCode.matches(sec);
    }
    
}
