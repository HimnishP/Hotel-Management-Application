package com.hotelmanagementapplication.model.payment;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Payment {
    private int paymentId;
    private static int paymentIdIncrement = 0;
    private PaymentMethod paymentMethod;
    private double amount;
    private LocalDate paymentDate;

    public Payment(PaymentMethod paymentMethod, double amount, LocalDate paymentDate) {
        this.paymentId = ++paymentIdIncrement;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }
}
