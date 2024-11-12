package com.hotelmanagementapplication.model.payment;

import com.hotelmanagementapplication.controller.l10n_i18n.LocaleSingleton;
import lombok.*;

import java.text.DateFormat;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Payment {
    private int paymentId;
    private static int paymentIdIncrement = 0;
    private double amount;
    private String paymentDate;
    private DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, LocaleSingleton.getInstance().getCurrentLocale()); // (e.g : en = 11/11/24) (e.g : fr = 2024-11-11)

    public Payment(double amount) {
        this.paymentId = ++paymentIdIncrement;
        this.amount = amount;
        this.paymentDate = dateFormat.format(new Date());
    }
}
