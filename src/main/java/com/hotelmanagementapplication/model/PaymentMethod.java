package com.hotelmanagementapplication.model;

public interface PaymentMethod {

    boolean validatePayment();
    void processPayment(double amount);
}
