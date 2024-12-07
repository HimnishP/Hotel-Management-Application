package com.hotelmanagementapplication.model.payment.factory;

import com.hotelmanagementapplication.model.payment.CreditCardPayment;
import com.hotelmanagementapplication.model.payment.DebitCardPayment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentFactoryTest {

    @Test
    void createPayment1() {
        PaymentMethod debitPayment = PaymentFactory.createPayment(
                "debit", 101, "1234567812345678", "John Doe", "12/25", "123", 150.00
        );

        assertTrue(debitPayment instanceof DebitCardPayment);
        assertEquals(101, ((DebitCardPayment) debitPayment).getUserId());
        assertEquals(150.00, ((DebitCardPayment) debitPayment).getAmount());
    }

    @Test
    void createPayment2() {
        PaymentMethod creditPayment = PaymentFactory.createPayment(
                "credit", 102, "8765432187654321", "Jane Smith", "11/26", "456", 300.00
        );
        assertTrue(creditPayment instanceof CreditCardPayment);
        assertEquals(102, ((CreditCardPayment) creditPayment).getUserId());
        assertEquals(300.00, ((CreditCardPayment) creditPayment).getAmount());
    }

    @Test
    void createPayment3() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                PaymentFactory.createPayment("cash", 103, "0000000000000000", "Invalid User", "01/01", "000", 50.00)
        );
        assertEquals("Invalid payment type: cash", exception.getMessage());
    }

    @Test
    void createPayment4() {
        PaymentMethod debitPayment = PaymentFactory.createPayment(
                "debit", 104, "1111222233334444", "Alice Johnson", "10/27", "789", 0.00
        );
        assertTrue(debitPayment instanceof DebitCardPayment);
        assertEquals(104, ((DebitCardPayment) debitPayment).getUserId());
        assertEquals(0.00, ((DebitCardPayment) debitPayment).getAmount());
    }

}