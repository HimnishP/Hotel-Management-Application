package com.hotelmanagementapplication.model.payment;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
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
    public boolean validatePayment(double amount) {
        if (creditCardNumber != null && cardHolderName != null && expirationDate != null && securityCode != null) {
            System.out.println(amount + " is processed for your credit card.");
            return true;
        }
        return false;
    }


}
