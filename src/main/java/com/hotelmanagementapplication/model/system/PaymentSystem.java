package com.hotelmanagementapplication.model.system;

import com.hotelmanagementapplication.controller.DatabaseController;
import com.hotelmanagementapplication.model.payment.CreditCardPayment;
import com.hotelmanagementapplication.model.payment.DebitCardPayment;
import com.hotelmanagementapplication.model.payment.Payment;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class PaymentSystem {
    private final Map<Integer, Payment> paymentMap;
    private final Map<Integer, CreditCardPayment> cardPaymentMap;
    private final Map<Integer, DebitCardPayment> debitCardPaymentMap;
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    public PaymentSystem() {
        paymentMap = DatabaseController.selectPayments()
                .stream()
                .collect(Collectors.toMap(payment -> payment.getUser().getUserId(), payment -> payment));
        cardPaymentMap = DatabaseController.selectCreditCardPayments()
                .stream()
                .collect(Collectors.toMap(CreditCardPayment::getPaymentId, payment -> payment));
        debitCardPaymentMap = DatabaseController.selectDebitCardPayments()
                .stream()
                .collect(Collectors.toMap(DebitCardPayment::getPaymentId, payment -> payment));
    }
}
