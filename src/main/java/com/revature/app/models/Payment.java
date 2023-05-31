/**
 * A class that represents a payment method for an online store.
 * It has an id and a payment number that corresponds to the credit card information.
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

public class Payment {
    /**
     * The id of the payment method.
     */
    private String id;
    /**
     * The payment number that corresponds to the credit card information.
     */
    private int payment_num; //cc info
}