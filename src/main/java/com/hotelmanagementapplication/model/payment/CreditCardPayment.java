package com.hotelmanagementapplication.model.payment;

import com.hotelmanagementapplication.model.payment.factory.PaymentMethod;
import com.hotelmanagementapplication.model.payment.utility.PaymentValidationUtil;
import com.hotelmanagementapplication.model.user.User;
import lombok.*;

import java.text.DateFormat;

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

    public CreditCardPayment(int paymentId, double amount, String paymentDate, DateFormat dateFormat, int userId, String creditCardNumber, String cardHolderName, String expirationDate, String securityCode) {
        super(paymentId, amount, paymentDate, dateFormat, userId);
        this.creditCardNumber = creditCardNumber;
        this.cardHolderName = cardHolderName;
        this.expirationDate = expirationDate;
        this.securityCode = securityCode;
    }

    public CreditCardPayment(String creditCardNumber, String cardHolderName, String expirationDate, String securityCode) {
        this.creditCardNumber = creditCardNumber;
        this.cardHolderName = cardHolderName;
        this.expirationDate = expirationDate;
        this.securityCode = securityCode;
    }

    public CreditCardPayment(int userId, double amount, String creditCardNumber, String cardHolderName, String expirationDate, String securityCode) {
        super(userId, amount);
        this.creditCardNumber = creditCardNumber;
        this.cardHolderName = cardHolderName;
        this.expirationDate = expirationDate;
        this.securityCode = securityCode;
    }

    public CreditCardPayment(int paymentId, double amount, String paymentDate, int userId, String creditCardNumber, String cardHolderName, String expirationDate, String securityCode) {
        super(paymentId, amount, paymentDate, userId);
        this.creditCardNumber = creditCardNumber;
        this.cardHolderName = cardHolderName;
        this.expirationDate = expirationDate;
        this.securityCode = securityCode;
    }

    public CreditCardPayment(double amount, String creditCardNumber, String cardHolderName, String expirationDate, String securityCode) {
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
        if (PaymentValidationUtil.isValidCardNumber(creditCardNumber) && PaymentValidationUtil.isValidExpirationDate(expirationDate) && PaymentValidationUtil.isValidSecurityCode(securityCode) && PaymentValidationUtil.isValidAmount(amount)) {
            System.out.println(amount + " is processed for your debit card.");
            return true;
        }
        System.out.println("Payment validation failed for debit card.");
        return false;
    }

}
