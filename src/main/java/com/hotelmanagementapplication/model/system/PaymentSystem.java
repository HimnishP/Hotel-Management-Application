package com.hotelmanagementapplication.model.system;

import com.hotelmanagementapplication.controller.screens.DatabaseController;
import com.hotelmanagementapplication.model.payment.CreditCardPayment;
import com.hotelmanagementapplication.model.payment.DebitCardPayment;
import com.hotelmanagementapplication.model.payment.Payment;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class PaymentSystem {
    private final Map<Integer, Payment> paymentMap; // UserId : Payment
    private final Map<Integer, CreditCardPayment> cardPaymentMap; // PaymentId : Credit
    private final Map<Integer, DebitCardPayment> debitCardPaymentMap;// PaymentID : Debit
    private final ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();

    public PaymentSystem() {
        paymentMap = DatabaseController.selectPayments()
                .stream()
                .filter(payment -> payment != null && payment.getUserId() > 0)
                .collect(Collectors.toMap(
                        Payment::getUserId,
                        payment -> payment,
                        (existingPayment, newPayment) -> newPayment
                ));
        cardPaymentMap = DatabaseController.selectCreditCardPayments()
                .stream()
                .filter(payment -> payment != null)
                .collect(Collectors.toMap(
                        CreditCardPayment::getPaymentId,
                        payment -> payment
                ));
        debitCardPaymentMap = DatabaseController.selectDebitCardPayments()
                .stream()
                .filter(payment -> payment != null)
                .collect(Collectors.toMap(
                        DebitCardPayment::getPaymentId,
                        payment -> payment
                ));
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
            paymentMap.put(payment.getUserId(), payment);
            return null;
        });
    }

    /**
     * Lists all payments.
     *
     * @return a list of all payments
     */
    public Future<List<Payment>> getAllPayments() {
        return EXECUTOR_SERVICE.submit(() -> paymentMap.values().stream().toList());
    }

    /**
     * Lists all credit card payments.
     *
     * @return a list of credit card payments
     */
    public Future<List<CreditCardPayment>> getAllCreditCardPayments() {
        return EXECUTOR_SERVICE.submit(() -> cardPaymentMap.values().stream().toList());
    }

    /**
     * Lists all debit card payments.
     *
     * @return a list of debit card payments
     */
    public Future<List<DebitCardPayment>> getAllDebitCardPayments() {
        return EXECUTOR_SERVICE.submit(() -> debitCardPaymentMap.values().stream().toList());
    }

    /**
     * Finds a payment by paymentId.
     *
     * @param paymentId the paymentId of the payment to find
     * @return the payment
     */
    public Future<Payment> getPaymentById(int paymentId) {
        return EXECUTOR_SERVICE.submit(() -> {
            if (!paymentExistsAsync(paymentId).get()) {
                throw new NoSuchElementException("ERROR: Payment with id " + paymentId + " does not exist!");
            }
            return paymentMap.get(paymentId);
        });
    }

    /**
     * Checks if a payment exists by paymentId.
     *
     * @param paymentId the paymentId of the payment
     * @return true if the payment exists, false otherwise
     */
    public Future<Boolean> paymentExistsAsync(int paymentId) {
        return EXECUTOR_SERVICE.submit(() -> paymentMap.containsKey(paymentId));
    }

    /**
     * Counts the total payments.
     *
     * @return the total number of payments
     */
    public Future<Integer> getPaymentCount() {
        return EXECUTOR_SERVICE.submit(paymentMap::size);
    }

    /**
     * Lists all paymentIds.
     *
     * @return a list of paymentIds
     */
    public Future<List<Integer>> listPaymentIds() {
        return EXECUTOR_SERVICE.submit(() -> paymentMap.keySet().stream().toList());
    }

    /**
     * Method will check if there is an existing payment by userId.
     *
     * @param userId the userId to search for payments
     * @return a list of matching payments
     */
    public Future<List<Payment>> findPaymentsByUserId(int userId) {
        return EXECUTOR_SERVICE.submit(() -> paymentMap.values().stream()
                .filter(payment -> payment.getUserId() == userId)
                .toList());
    }

    /**
     * Validates and returns the payment type based on the paymentId.
     *
     * @param paymentId the paymentId to check
     * @return the type of the payment (CreditCardPayment, DebitCardPayment)
     */
    public Future<String> validateAndReturnPaymentType(int paymentId) {
        return EXECUTOR_SERVICE.submit(() -> {
            return paymentMap.get(paymentId) != null ? (paymentMap.get(paymentId) instanceof CreditCardPayment ? "CreditCardPayment" : "DebitCardPayment") : null;
        });
    }
}
