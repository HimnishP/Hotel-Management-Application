package com.hotelmanagementapplication.controller;

import com.hotelmanagementapplication.controller.utildatabase.UserDatabase;
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
}
