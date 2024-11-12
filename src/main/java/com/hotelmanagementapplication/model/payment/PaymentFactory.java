package com.hotelmanagementapplication.model.payment;

public class PaymentFactory {
    /**
     * creates a payment type depending on the customer's selection type.
     *
     * @param type the type of payment the customer wants to pay.
     * @return the payment type based on the selection.
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
