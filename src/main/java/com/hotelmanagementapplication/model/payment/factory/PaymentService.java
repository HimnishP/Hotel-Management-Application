package com.hotelmanagementapplication.model.payment.factory;

public class PaymentService {
    /**
     * processes the payment of the customer.
     *
     * @param type   type of payment
     * @param amount the payment amount.
     */
    public void processPayment(String type, int userId, String cardNumber, String cardHolderName, String expirationDate, String securityCode, double amount) {
        PaymentMethod paymentMethod = PaymentFactory.createPayment(type, userId, cardNumber, cardHolderName, expirationDate, securityCode, amount);
        paymentMethod.validatePayment(amount);
    }
}
