package com.hotelmanagementapplication.model.payment;

import com.hotelmanagementapplication.controller.l10n_i18n.LocaleSingleton;
import com.hotelmanagementapplication.model.user.User;
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
    private User user;

    public Payment(User user, double amount) {
        this.user = user;
        this.paymentId = ++paymentIdIncrement;
        this.amount = amount;
        this.paymentDate = dateFormat.format(new Date());
    }
}
