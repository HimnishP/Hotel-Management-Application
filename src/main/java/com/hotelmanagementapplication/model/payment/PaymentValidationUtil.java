package com.hotelmanagementapplication.model.payment;

public class PaymentValidationUtil {

    public static boolean isValidCardNumber(String cardNumber) {
        return cardNumber != null && cardNumber.matches("\\d{16}"); // 16-digit number
    }

    public static boolean isValidExpirationDate(String expirationDate) {
        return expirationDate != null && expirationDate.matches("\\d{2}/\\d{2}"); // MM/YY format
    }

    public static boolean isValidSecurityCode(String securityCode) {
        return securityCode != null && securityCode.matches("\\d{3}"); // 3-digit code
    }
}
