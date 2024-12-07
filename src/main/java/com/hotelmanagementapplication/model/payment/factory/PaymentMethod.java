package com.hotelmanagementapplication.model.payment.factory;

import lombok.Getter;
import lombok.Setter;


public interface PaymentMethod {
    boolean validatePayment(double amount);
}
