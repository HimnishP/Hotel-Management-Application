package com.hotelmanagementapplication.model.payment;

import lombok.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
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
