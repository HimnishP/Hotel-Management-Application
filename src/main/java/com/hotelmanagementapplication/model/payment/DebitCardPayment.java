package com.hotelmanagementapplication.model.payment;

import com.hotelmanagementapplication.model.payment.factory.PaymentMethod;
import com.hotelmanagementapplication.model.payment.utility.PaymentValidationUtil;
import lombok.*;

@NoArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class DebitCardPayment extends Payment implements PaymentMethod {
    private String debitCardNumber;
    private String cardHolderName;
    private String expirationDate;
    private String securityCode;

    public DebitCardPayment(String debitCardNumber, String cardHolderName, String expirationDate, String securityCode, double amount) {
        super(amount);
        this.debitCardNumber = debitCardNumber;
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
        if (PaymentValidationUtil.isValidCardNumber(debitCardNumber) && PaymentValidationUtil.isValidExpirationDate(expirationDate) && PaymentValidationUtil.isValidSecurityCode(securityCode)) {
            System.out.println(amount + " is processed for your debit card.");
            return true;
        }
        System.out.println("Payment validation failed for debit card.");
        return false;
    }
}
