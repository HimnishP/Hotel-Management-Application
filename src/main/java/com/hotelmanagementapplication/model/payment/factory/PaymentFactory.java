package com.hotelmanagementapplication.model.payment.factory;

import com.hotelmanagementapplication.model.payment.CreditCardPayment;
import com.hotelmanagementapplication.model.payment.DebitCardPayment;

public class PaymentFactory {
    /**
     * Creates the payment based on it's type
     *
     * @param type           type of payment
     * @param cardNumber     the card number
     * @param cardHolderName name of cardholder
     * @param expirationDate expiration date on the card
     * @param securityCode   the security code on the card
     * @param amount         amount they need to pay for their booking
     * @return the type of payment that will be made.
     */
    public static PaymentMethod createPayment(String type, int userId, String cardNumber, String cardHolderName, String expirationDate, String securityCode, double amount) {
        return switch (type.toLowerCase()) {
            case "debit" ->
                    new DebitCardPayment(userId, amount, cardNumber, cardHolderName, expirationDate, securityCode);
            case "credit" ->
                    new CreditCardPayment(userId, amount, cardNumber, cardHolderName, expirationDate, securityCode);
            default -> throw new IllegalArgumentException("Invalid payment type: " + type);
        };
    }
}
