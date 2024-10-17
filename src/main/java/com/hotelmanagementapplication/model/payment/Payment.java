package com.hotelmanagementapplication.model.payment;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class Payment {
    private PaymentMethod paymentMethod;
    private double amount;
    private LocalDate paymentDate;
    private HashMap<String, List<Payment>> customerPurchases;

    public Payment(PaymentMethod paymentMethod, double amount, LocalDate paymentDate) {
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }
}
