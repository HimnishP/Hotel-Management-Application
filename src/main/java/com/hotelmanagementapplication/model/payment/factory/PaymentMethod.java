package com.hotelmanagementapplication.model.payment.factory;

public interface PaymentMethod {
    boolean validatePayment(double amount);
}
