package com.hotelmanagementapplication.model.payment;

public interface PaymentMethod {
    boolean validatePayment(double amount);
}
