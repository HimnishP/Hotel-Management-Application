package com.hotelmanagementapplication.controller;

import com.hotelmanagementapplication.controller.utildatabase.PaymentDatabase;
import com.hotelmanagementapplication.controller.utildatabase.UserDatabase;
import com.hotelmanagementapplication.model.payment.CreditCardPayment;
import com.hotelmanagementapplication.model.payment.DebitCardPayment;
import com.hotelmanagementapplication.model.payment.Payment;
import com.hotelmanagementapplication.model.user.Customer;
import com.hotelmanagementapplication.model.user.Manager;
import com.hotelmanagementapplication.model.user.User;

import java.util.List;

public class DatabaseController {
    /**
     * Create the user table
     */
    public static void createTableUser() {
        UserDatabase.createTableUser();
    }

    /**
     * Create the manager table
     */
    public static void createTableManager() {
        UserDatabase.createTableManager();
    }

    /**
     * Create the customer table
     */
    public static void createTableCustomer() {
        UserDatabase.createTableCustomer();
    }

    /**
     * Insert a user into the user table
     *
     * @param user     The user object to be inserted
     * @param userType The type of user (e.g., Manager, Customer)
     * @return The generated userId
     */
    public static int insertUser(User user, String userType) {
        return UserDatabase.insertUser(user, userType);
    }

    /**
     * Insert a manager into the manager table based on the userId
     *
     * @param userId The userId of the manager
     */
    public static void insertManager(int userId) {
        UserDatabase.insertManager(userId);
    }

    /**
     * Insert a customer into the customer table based on the userId
     *
     * @param userId The userId of the customer
     */
    public static void insertCustomer(int userId) {
        UserDatabase.insertCustomer(userId);
    }

    /**
     * Select all users from the user table
     *
     * @return List of users (Manager, Customer, or Generic)
     */
    public static List<User> selectUsers() {
        return UserDatabase.selectUsers();
    }

    /**
     * Select all managers from the database
     *
     * @return List of managers
     */
    public static List<Manager> selectManagers() {
        return UserDatabase.selectManagers();
    }

    /**
     * Select all customers from the database
     *
     * @return List of customers
     */
    public static List<Customer> selectCustomers() {
        return UserDatabase.selectCustomers();
    }

    /**
     * Remove a user from the database based on their userId
     *
     * @param userId The userId of the user to be removed
     */
    public static void removeUser(int userId) {
        UserDatabase.removeUser(userId);
    }

    /**
     * Remove a user from the database based on the user object
     *
     * @param user The user object to be removed
     */
    public static void removeUser(User user) {
        UserDatabase.removeUser(user);
    }

    /**
     * Update a user's information in the database
     *
     * @param id   The userId of the user to be updated
     * @param user The user object containing updated information
     */
    public void updateUser(int id, User user) {
        UserDatabase.alterUser(id, user);
    }

    /**
     * Method will alter user table
     *
     * @param user User to be altered
     */
    public static void alterUser(int id, User user) {
        UserDatabase.alterUser(id, user);
    }

    /**
     * Creates the Payment table in the database.
     * This table holds basic payment information for each payment.
     */
    public void createTablePayment() {
        PaymentDatabase.createTablePayment();
    }

    /**
     * Creates the DebitCardPayment table in the database.
     * This table holds specific details for debit card payments.
     */
    public void createTableDebitCardPayment() {
        PaymentDatabase.createTableDebitCardPayment();
    }

    /**
     * Creates the CreditCardPayment table in the database.
     * This table holds specific details for credit card payments.
     */
    public void createTableCreditCardPayment() {
        PaymentDatabase.createTableCreditCardPayment();
    }

    /**
     * Inserts a payment into the Payment table.
     *
     * @param payment     The payment object containing payment details.
     * @param paymentType The type of the payment (e.g., "DebitCardPayment" or "CreditCardPayment").
     * @return The generated payment ID.
     */
    public int insertPayment(Payment payment, String paymentType) {
        return PaymentDatabase.insertPayment(payment, paymentType);
    }

    /**
     * Inserts a debit card payment into the DebitCardPayment table.
     * This method takes a debit card payment object and inserts the details into the database.
     *
     * @param debit The DebitCardPayment object containing payment details.
     */
    public void insertDebitCardPayment(DebitCardPayment debit) {
        PaymentDatabase.insertDebitCardPayment(debit);
    }

    /**
     * Inserts a credit card payment into the CreditCardPayment table.
     * This method takes a credit card payment object and inserts the details into the database.
     *
     * @param credit The CreditCardPayment object containing payment details.
     */
    public void insertCreditCardPayment(CreditCardPayment credit) {
        PaymentDatabase.insertCreditCardPayment(credit);
    }

    /**
     * Retrieves a list of all payments from the Payment table.
     * This method retrieves all payment entries, including both debit and credit card payments.
     *
     * @return A list of Payment objects containing basic payment details.
     */
    public List<Payment> selectPayments() {
        return PaymentDatabase.selectPayments();
    }

    /**
     * Retrieves a list of all debit card payments.
     * This method retrieves all debit card payment records from the database.
     *
     * @return A list of DebitCardPayment objects containing debit card payment details.
     */
    public List<DebitCardPayment> selectDebitCardPayments() {
        return PaymentDatabase.selectDebitCardPayments();
    }

    /**
     * Retrieves a list of all credit card payments.
     * This method retrieves all credit card payment records from the database.
     *
     * @return A list of CreditCardPayment objects containing credit card payment details.
     */
    public List<CreditCardPayment> selectCreditCardPayments() {
        return PaymentDatabase.selectCreditCardPayments();
    }

    /**
     * Retrieves a specific debit card payment based on the payment ID.
     * This method fetches details of a single debit card payment by its unique payment ID.
     *
     * @param paymentId The ID of the debit card payment to retrieve.
     * @return The DebitCardPayment object corresponding to the payment ID.
     */
    public DebitCardPayment selectDebitCardPayment(int paymentId) {
        return PaymentDatabase.selectDebitCardPayment(paymentId);
    }

    /**
     * Retrieves a specific credit card payment based on the payment ID.
     * This method fetches details of a single credit card payment by its unique payment ID.
     *
     * @param paymentId The ID of the credit card payment to retrieve.
     * @return The CreditCardPayment object corresponding to the payment ID.
     */
    public CreditCardPayment selectCreditCardPayment(int paymentId) {
        return PaymentDatabase.selectCreditCardPayment(paymentId);
    }
}
