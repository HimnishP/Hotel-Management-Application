package com.hotelmanagementapplication.model.payment;

import lombok.*;

import java.time.LocalDate;

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

    public DebitCardPayment(int paymentId, PaymentMethod paymentMethod, double amount, LocalDate paymentDate) {
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
        if (debitCardNumber != null && cardHolderName != null && expirationDate != null && securityCode != null) {
            System.out.println(amount + " is processed for your debit card.");
            return true;
        }
        return false;
    }


}
