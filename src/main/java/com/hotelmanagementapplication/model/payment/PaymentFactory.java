package com.hotelmanagementapplication.model.payment;

public class PaymentFactory {
    /**
     * creates a payment type depending on the customer's selection type.
     *
     * @param type the type of payment the customer wants to pay.
     * @return the payment type based on the selection.
     */
    public static PaymentMethod createPayment(String type) {
        if (type.equalsIgnoreCase("debit")) {
            return new DebitCardPayment();
        } else if (type.equalsIgnoreCase("credit")) {
            return new CreditCardPayment();
        }
        throw new IllegalArgumentException("Invalid payment type: " + type);
    }
}
