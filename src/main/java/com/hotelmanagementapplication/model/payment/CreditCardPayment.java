package com.hotelmanagementapplication.model.payment;

public class CreditCardPayment implements PaymentMethod {
    private String creditCardNumber;
    private String cardHolderName;
    private String expirationDate;
    private String securityCode;

    public CreditCardPayment(String creditCardNumber, String cardHolderName, String expirationDate, String securityCode) {
        this.creditCardNumber = creditCardNumber;
        this.cardHolderName = cardHolderName;
        this.expirationDate = expirationDate;
        this.securityCode = securityCode;
    }

    @Override
    public boolean validatePayment() {
        return false;
    }

    @Override
    public void processPayment(double amount) {

    }
}
