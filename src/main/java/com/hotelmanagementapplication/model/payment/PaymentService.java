package com.hotelmanagementapplication.model.payment;

public class PaymentService {
    /**
     * processes the payment of the customer.
     *
     * @param type   type of payment
     * @param amount the payment amount.
     */
    public void processPayment(String type, String cardNumber, String cardHolderName, String expirationDate, String securityCode, double amount) {
        PaymentMethod paymentMethod = PaymentFactory.createPayment(type, cardNumber, cardHolderName, expirationDate, securityCode, amount);
        paymentMethod.validatePayment(amount);
    }
}
