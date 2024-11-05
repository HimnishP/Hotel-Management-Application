package com.hotelmanagementapplication.model.payment;

public class PaymentService {
    /**
     * processes the payment of the customer.
     *
     * @param type   type of payment
     * @param amount the payment amount.
     */
    public void processPayment(String type, double amount) {
        PaymentMethod paymentMethod = PaymentFactory.createPayment(type);
        paymentMethod.validatePayment(amount);
    }
}
