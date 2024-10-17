package com.hotelmanagementapplication.model.payment;

public interface PaymentMethod {

    boolean validatePayment();
    void processPayment(double amount);
}
