package com.hotelmanagementapplication.model.payment.factory;

import com.hotelmanagementapplication.model.payment.CreditCardPayment;
import com.hotelmanagementapplication.model.payment.DebitCardPayment;

import java.util.Locale;

public class PaymentFactory {
    /**
     * Creates a payment type depending on the customer's selection type.
     *
     * @param type The type of payment the customer wants to pay.
     * @return The payment type based on the selection.
     */
    public static PaymentMethod createPayment(String type, String cardNumber, String cardHolderName, String expirationDate, String securityCode, double amount) {
        return switch (type.toLowerCase()) {
            case "debit" -> new DebitCardPayment(cardNumber, cardHolderName, expirationDate, securityCode, amount);
            case "credit" -> new CreditCardPayment(cardNumber, cardHolderName, expirationDate, securityCode, amount);
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }
}
