package com.hotelmanagementapplication.model.payment.utility;

public class PaymentValidationUtil {

    /**
     * check if the card has a valid card number
     *
     * @param cardNumber the card number
     * @return true if it is a valid card number, or false if it's not
     */
    public static boolean isValidCardNumber(String cardNumber) {
        return cardNumber != null && cardNumber.matches("\\d{16}"); // 16-digit number
    }

    /**
     * checks if the expiration date is valid
     *
     * @param expirationDate the date when the card expires
     * @return true if the expiration date is valid, or false if it's not.
     */
    public static boolean isValidExpirationDate(String expirationDate) {
        return expirationDate != null && expirationDate.matches("\\d{2}/\\d{2}"); // MM/YY format
    }

    /**
     * checks if the security code is 3 digits
     *
     * @param securityCode the security code.
     * @return true if it is 3 digits.
     */
    public static boolean isValidSecurityCode(String securityCode) {
        return securityCode != null && securityCode.matches("\\d{3}"); // 3-digit code
    }

    /**
     * checks if the amount to pay is a valid number, which is more than 0.
     *
     * @param amount the amount to pay.
     * @return true if it is a positive number.
     */
    public static boolean isValidAmount(double amount) {
        return amount > 0;
    }

}
