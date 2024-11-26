package com.hotelmanagementapplication.model.system;

import com.hotelmanagementapplication.controller.DatabaseController;
import com.hotelmanagementapplication.model.payment.CreditCardPayment;
import com.hotelmanagementapplication.model.payment.DebitCardPayment;
import com.hotelmanagementapplication.model.payment.Payment;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class PaymentSystem {
    private final Map<Integer, Payment> paymentMap;
    private final Map<Integer, CreditCardPayment> cardPaymentMap;
    private final Map<Integer, DebitCardPayment> debitCardPaymentMap;
    private final ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();

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

    /**
     * Adds a payment to the system.
     *
     * @param payment the payment to add
     * @return the Future of the operation
     */
    public Future<Void> addPayment(Payment payment) {
        return EXECUTOR_SERVICE.submit(() -> {
            if (payment instanceof CreditCardPayment) {
                DatabaseController.insertPayment(payment, "CreditCardPayment");
                DatabaseController.insertCreditCardPayment((CreditCardPayment) payment);
            } else if (payment instanceof DebitCardPayment) {
                DatabaseController.insertPayment(payment, "DebitCardPayment");
                DatabaseController.insertDebitCardPayment((DebitCardPayment) payment);
            } else {
                throw new IllegalArgumentException("Invalid payment type");
            }
            paymentMap.put(payment.getUser().getUserId(), payment);
            return null;
        });
    }
}
