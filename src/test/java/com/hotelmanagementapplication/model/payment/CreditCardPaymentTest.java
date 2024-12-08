package com.hotelmanagementapplication.model.payment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreditCardPaymentTest {

    @Test
    void validatePayment1() {
        CreditCardPayment creditPayment = new CreditCardPayment(101, 150.00, "1234567812345678", "John Doe", "12/25", "123");
        assertTrue(creditPayment.validatePayment(150.00), "Validation should pass with valid card details.");
    }

    @Test
    void validatePayment2() {
        CreditCardPayment creditPayment = new CreditCardPayment(101, 150.00, "invalidcardnumber", "John Doe", "12/25", "123");
        assertFalse(creditPayment.validatePayment(150.00), "Validation should fail with an invalid card number.");
    }

    @Test
    void validatePayment3() {
        CreditCardPayment creditPayment = new CreditCardPayment(101, 150.00, "1234567812345678", "John Doe", "invalid-date", "123");
        assertFalse(creditPayment.validatePayment(150.00), "Validation should fail with an invalid expiration date.");
    }

    @Test
    void validatePayment4() {
        CreditCardPayment creditPayment = new CreditCardPayment(101, 150.00, "1234567812345678", "John Doe", "12/25", "invalid-code");
        assertFalse(creditPayment.validatePayment(150.00), "Validation should fail with an invalid security code.");
    }

    @Test
    void validatePayment5() {
        CreditCardPayment creditPayment = new CreditCardPayment(101, 0.00, "1234567812345678", "John Doe", "12/25", "123");
        assertFalse(creditPayment.validatePayment(0.00), "Validation should fail with an amount of zero.");
    }

}