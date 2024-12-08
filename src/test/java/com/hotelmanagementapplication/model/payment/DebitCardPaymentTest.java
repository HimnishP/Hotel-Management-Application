package com.hotelmanagementapplication.model.payment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DebitCardPaymentTest {
    @Test
    void validatePayment1() {
        DebitCardPayment debitPayment = new DebitCardPayment(101, 150.00, "1234567812345678", "John Doe", "12/25", "123");
        assertTrue(debitPayment.validatePayment(150.00), "Validation should pass with valid card details.");
    }

    @Test
    void validatePayment2() {
        DebitCardPayment debitPayment = new DebitCardPayment(101, 150.00, "invalidcardnumber", "John Doe", "12/25", "123");
        assertFalse(debitPayment.validatePayment(150.00), "Validation should fail with an invalid card number.");
    }

    @Test
    void validatePayment3() {
        DebitCardPayment debitPayment = new DebitCardPayment(101, 150.00, "1234567812345678", "John Doe", "invalid-date", "123");
        assertFalse(debitPayment.validatePayment(150.00), "Validation should fail with an invalid expiration date.");
    }

    @Test
    void validatePayment4() {
        DebitCardPayment debitPayment = new DebitCardPayment(101, 150.00, "1234567812345678", "John Doe", "12/25", "invalid-code");
        assertFalse(debitPayment.validatePayment(150.00), "Validation should fail with an invalid security code.");
    }

    @Test
    void validatePayment5() {
        DebitCardPayment debitPayment = new DebitCardPayment(101, 0.00, "1234567812345678", "John Doe", "12/25", "123");
        assertFalse(debitPayment.validatePayment(0.00), "Validation should fail with an amount of zero.");
    }
}