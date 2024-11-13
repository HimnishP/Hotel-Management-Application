package com.hotelmanagementapplication.model.payment.factory;

import com.hotelmanagementapplication.model.payment.CreditCardPayment;
import com.hotelmanagementapplication.model.payment.DebitCardPayment;

public class PaymentFactory {
    /**
     * creates the payment based on it's type
     * @param type type of payment
     * @param cardNumber the card number
     * @param cardHolderName name of cardholder
     * @param expirationDate expiration date on the card
     * @param securityCode the security code on the card
     * @param amount amount they need to pay for their booking
     * @return the type of payment that will be made.
     */
    public static PaymentMethod createPayment(String type, String cardNumber, String cardHolderName, String expirationDate, String securityCode, double amount) {
        if (type.equalsIgnoreCase("debit")) {
            return new DebitCardPayment(cardNumber, cardHolderName, expirationDate, securityCode, amount);
        } else if (type.equalsIgnoreCase("credit")) {
            return new CreditCardPayment(cardNumber, cardHolderName, expirationDate, securityCode, amount);
        }
        throw new IllegalArgumentException("Invalid payment type: " + type);
    }
}
