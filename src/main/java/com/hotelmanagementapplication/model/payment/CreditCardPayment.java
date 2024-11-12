package com.hotelmanagementapplication.model.payment;

import com.hotelmanagementapplication.model.payment.factory.PaymentMethod;
import com.hotelmanagementapplication.model.payment.utility.PaymentValidationUtil;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class CreditCardPayment extends Payment implements PaymentMethod {
    private String creditCardNumber;
    private String cardHolderName;
    private String expirationDate;
    private String securityCode;

    public CreditCardPayment(String creditCardNumber, String cardHolderName, String expirationDate, String securityCode, double amount) {
        super(amount);
        this.creditCardNumber = creditCardNumber;
        this.cardHolderName = cardHolderName;
        this.expirationDate = expirationDate;
        this.securityCode = securityCode;
    }

    /**
     * Method validates the payment by making sure all the information is taken
     *
     * @param amount the amount
     * @return if the payment is valid and processed.
     */
    @Override
    public boolean validatePayment(double amount) {
        if (PaymentValidationUtil.isValidCardNumber(creditCardNumber) && PaymentValidationUtil.isValidExpirationDate(expirationDate) && PaymentValidationUtil.isValidSecurityCode(securityCode)) {
            System.out.println(amount + " is processed for your debit card.");
            return true;
        }
        System.out.println("Payment validation failed for debit card.");
        return false;
    }
}
