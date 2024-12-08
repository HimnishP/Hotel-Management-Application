package com.hotelmanagementapplication.model.payment.utility;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentValidationUtilTest {

    // Test case for a valid card number
    @Test
    void isValidCardNumber1() {
        assertTrue(PaymentValidationUtil.isValidCardNumber("1234567812345678"));
    }

    // Test case for an invalid card number (too short)
    @Test
    void isValidCardNumber2() {
        assertFalse(PaymentValidationUtil.isValidCardNumber("12345678"));
    }

    // Test case for an invalid card number (contains non-digit)
    @Test
    void isValidCardNumber3() {
        assertFalse(PaymentValidationUtil.isValidCardNumber("123456781234567A"));
    }

    // Test case for an invalid card number (null value)
    @Test
    void isValidCardNumber4() {
        assertFalse(PaymentValidationUtil.isValidCardNumber(null));
    }

    // Test case for an invalid card number (too long)
    @Test
    void isValidCardNumber5() {
        assertFalse(PaymentValidationUtil.isValidCardNumber("12345678123456789"));
    }

    // Test case for a valid expiration date
    @Test
    void isValidExpirationDate1() {
        assertTrue(PaymentValidationUtil.isValidExpirationDate("12/25"));
    }

    // Test case for an invalid expiration date (missing slash)
    @Test
    void isValidExpirationDate2() {
        assertFalse(PaymentValidationUtil.isValidExpirationDate("1225"));
    }

    // Test case for an invalid expiration date (full year format)
    @Test
    void isValidExpirationDate4() {
        assertFalse(PaymentValidationUtil.isValidExpirationDate("12/2025"));
    }

    // Test case for a valid security code
    @Test
    void isValidSecurityCode1() {
        assertTrue(PaymentValidationUtil.isValidSecurityCode("123"));
    }

    // Test case for an invalid security code (too short)
    @Test
    void isValidSecurityCode2() {
        assertFalse(PaymentValidationUtil.isValidSecurityCode("12"));
    }

    // Test case for an invalid security code (too long)
    @Test
    void isValidSecurityCode3() {
        assertFalse(PaymentValidationUtil.isValidSecurityCode("1234"));
    }

    // Test case for an invalid security code (contains letter)
    @Test
    void isValidSecurityCode4() {
        assertFalse(PaymentValidationUtil.isValidSecurityCode("12A"));
    }

    // Test case for an invalid security code (empty string)
    @Test
    void isValidSecurityCode5() {
        assertFalse(PaymentValidationUtil.isValidSecurityCode(""));
    }

    // Test case for a valid amount
    @Test
    void isValidAmount1() {
        assertTrue(PaymentValidationUtil.isValidAmount(100.00));
    }

    // Test case for an invalid amount (zero)
    @Test
    void isValidAmount2() {
        assertFalse(PaymentValidationUtil.isValidAmount(0));
    }

    // Test case for an invalid amount (negative value)
    @Test
    void isValidAmount3() {
        assertFalse(PaymentValidationUtil.isValidAmount(-50.00));
    }

}