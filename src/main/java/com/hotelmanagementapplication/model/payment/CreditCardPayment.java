package com.hotelmanagementapplication.model.payment;

import lombok.*;

import java.time.LocalDate;

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

    public CreditCardPayment(int paymentId, PaymentMethod paymentMethod, double amount, LocalDate paymentDate) {
        super(paymentId, paymentMethod, amount, paymentDate);
    }


    /**
     * it validates the payment by making sure all the information is taken
     *
     * @param amount the amount
     * @return if the payment is valid and processed.
     */
    @Override
    public boolean validatePayment(double amount) {
        if (creditCardNumber != null && cardHolderName != null && expirationDate != null && securityCode != null) {
            System.out.println(amount + " is processed for your credit card.");
            return true;
        }
        return false;
    }


}
