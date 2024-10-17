package com.hotelmanagementapplication.model.payment;

public class DebitCardPayment implements PaymentMethod {
    private String debitCardNumber;
    private String cardHolderName;
    private String expirationDate;
    private String securityCode;

    public DebitCardPayment(String debitCardNumber, String cardHolderName, String expirationDate, String securityCode) {
        this.debitCardNumber = debitCardNumber;
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
