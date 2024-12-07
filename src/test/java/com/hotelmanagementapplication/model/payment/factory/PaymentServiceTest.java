package com.hotelmanagementapplication.model.payment.factory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceTest {

    PaymentService process = new PaymentService();

    @Test
    void processPayment1() {
        assertDoesNotThrow(() -> {
            process.processPayment(
                    "debit", 201, "1234567812345678", "John Doe", "12/25", "123", 100.00
            );
        });
    }

    @Test
    void processPayment2() {
        assertDoesNotThrow(() -> {
            process.processPayment(
                    "credit", 202, "8765432187654321", "Jane Smith", "11/26", "456", 200.00
            );
        });
    }
    @Test
    void processPayment3() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            process.processPayment(
                    "cash", 203, "0000000000000000", "Invalid User", "01/01", "000", 50.00
            );
        });
        assertEquals("Invalid payment type: cash", exception.getMessage());
    }
    @Test
    void processPayment4() {
        assertDoesNotThrow(() -> {
            process.processPayment(
                    "debit", 205, "1111222233334444", "Alice Johnson", "10/27", "789", 0.00
            );
        });
    }
}