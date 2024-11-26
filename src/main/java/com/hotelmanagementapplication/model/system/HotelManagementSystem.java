package com.hotelmanagementapplication.model.system;

import com.hotelmanagementapplication.model.payment.CreditCardPayment;
import com.hotelmanagementapplication.model.payment.DebitCardPayment;
import com.hotelmanagementapplication.model.payment.Payment;
import com.hotelmanagementapplication.model.user.Customer;
import com.hotelmanagementapplication.model.user.Manager;
import com.hotelmanagementapplication.model.user.User;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class HotelManagementSystem {
    private static HotelManagementSystem instance;
    private final UserSystem userSystem;
    private final PaymentSystem paymentSystem;

    private HotelManagementSystem() {
        userSystem = new UserSystem();
        paymentSystem = new PaymentSystem();
    }

    public static HotelManagementSystem getInstance() {
        if (instance == null) {
            synchronized (HotelManagementSystem.class) {
                if (instance == null) {
                    instance = new HotelManagementSystem();
                }
            }
        }
        return instance;
    }

    /**
     * Adds a user to the system asynchronously.
     *
     * @param user the user to add
     */
    public void addUser(User user) {
        userSystem.addUser(user);
    }

    /**
     * Removes a user by ID and returns the removed user.
     *
     * @param userId the ID of the user to remove
     * @return the removed user
     */
    public User removeUser(int userId) {
        try {
            return userSystem.removeUser(userId).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Failed to remove user with ID " + userId, e);
        }
    }

    /**
     * Retrieves a user by ID.
     *
     * @param userId the ID of the user to retrieve
     * @return the user
     */
    public User getUserById(int userId) {
        try {
            return userSystem.getUserById(userId).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Failed to retrieve user with ID " + userId, e);
        }
    }

    /**
     * Retrieves a list of all users.
     *
     * @return the list of all users
     */
    public List<User> getAllUsers() {
        try {
            return userSystem.getAllUsers().get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Failed to retrieve all users", e);
        }
    }

    /**
     * Retrieves a list of all managers.
     *
     * @return the list of all managers
     */
    public List<Manager> getAllManagers() {
        try {
            return userSystem.getAllManagers().get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Failed to retrieve all managers", e);
        }
    }

    /**
     * Retrieves a list of all customers.
     *
     * @return the list of all customers
     */
    public List<Customer> getAllCustomers() {
        try {
            return userSystem.getAllCustomers().get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Failed to retrieve all customers", e);
        }
    }

    /**
     * Updates a user asynchronously.
     *
     * @param user the user with updated information
     * @param id the id of the user
     */
    public void updateUser(int id, User user) {
        userSystem.updateUser(id, user);
    }

    /**
     * Finds users by name.
     *
     * @param name the name to search for
     * @return the list of matching users
     */
    public List<User> findUsersByName(String name) {
        try {
            return userSystem.findUsersByName(name).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Failed to find users by name: " + name, e);
        }
    }

    /**
     * Checks if a user exists by ID.
     *
     * @param userId the ID of the user
     * @return true if the user exists, false otherwise
     */
    public boolean userExists(int userId) {
        try {
            return userSystem.userExistsAsync(userId).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Failed to check if user exists with ID " + userId, e);
        }
    }

    /**
     * Retrieves the total user count.
     *
     * @return the total number of users
     */
    public int getUserCount() {
        try {
            return userSystem.getUserCount().get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Failed to retrieve user count", e);
        }
    }

    /**
     * Lists all user IDs.
     *
     * @return the list of user IDs
     */
    public List<Integer> listUserIds() {
        try {
            return userSystem.listUserIds().get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Failed to list user IDs", e);
        }
    }

    /**
     * Method will check if there is an existing user based on email and password
     *
     * @param email    The email
     * @param password The password
     * @return The type of user found
     */
    public String validateExistingCustomer(String email, String password) {
        try {
            return userSystem.validateAndReturnUserType(email, password).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Adds a payment (either CreditCard or DebitCard) to the system.
     * This method is executed asynchronously.
     *
     * @param payment the payment object to be added
     */
    public void addPayment(Payment payment) {
        paymentSystem.addPayment(payment);
    }

    /**
     * Retrieves all payments in the system.
     * This method is executed asynchronously and returns a list of all payments.
     *
     * @return a list of all payments
     */
    public List<Payment> getAllPayments() {
        try {
            return paymentSystem.getAllPayments().get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Failed to retrieve all payments", e);
        }
    }

    /**
     * Retrieves all credit card payments in the system.
     * This method is executed asynchronously and returns a list of credit card payments.
     *
     * @return a list of all credit card payments
     */
    public List<CreditCardPayment> getAllCreditCardPayments() {
        try {
            return paymentSystem.getAllCreditCardPayments().get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Failed to retrieve all credit card payments", e);
        }
    }

    /**
     * Retrieves all debit card payments in the system.
     * This method is executed asynchronously and returns a list of debit card payments.
     *
     * @return a list of all debit card payments
     */
    public List<DebitCardPayment> getAllDebitCardPayments() {
        try {
            return paymentSystem.getAllDebitCardPayments().get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Failed to retrieve all debit card payments", e);
        }
    }

    /**
     * Retrieves a payment by its payment ID.
     * This method is executed asynchronously and throws a `NoSuchElementException`
     * if the payment with the given ID does not exist.
     *
     * @param paymentId the payment ID to search for
     * @return the payment object
     */
    public Payment getPaymentById(int paymentId) {
        try {
            return paymentSystem.getPaymentById(paymentId).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Failed to retrieve payment by ID " + paymentId, e);
        }
    }

    /**
     * Finds all payments associated with a given user ID.
     * This method is executed asynchronously and returns a list of payments.
     *
     * @param userId the user ID to search for payments
     * @return a list of payments for the given user ID
     */
    public List<Payment> findPaymentsByUserId(int userId) {
        try {
            return paymentSystem.findPaymentsByUserId(userId).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Failed to retrieve payments for user ID " + userId, e);
        }
    }

    /**
     * Validates and returns the payment type (either "CreditCardPayment" or "DebitCardPayment")
     * based on the given payment ID. This method is executed asynchronously.
     *
     * @param paymentId the payment ID to check
     * @return the payment type as a string ("CreditCardPayment" or "DebitCardPayment")
     */
    public String validateAndReturnPaymentType(int paymentId) {
        try {
            return paymentSystem.validateAndReturnPaymentType(paymentId).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Failed to validate payment type for ID " + paymentId, e);
        }
    }

    /**
     * Counts the total number of payments in the system.
     * This method is executed asynchronously.
     *
     * @return the total number of payments
     */
    public int getPaymentCount() {
        try {
            return paymentSystem.getPaymentCount().get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Failed to retrieve payment count", e);
        }
    }

    /**
     * Lists all payment IDs in the system.
     * This method is executed asynchronously.
     *
     * @return a list of all payment IDs
     */
    public List<Integer> listPaymentIds() {
        try {
            return paymentSystem.listPaymentIds().get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Failed to list payment IDs", e);
        }
    }
}
