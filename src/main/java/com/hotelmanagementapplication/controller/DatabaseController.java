package com.hotelmanagementapplication.controller;

import com.hotelmanagementapplication.controller.utildatabase.DatabaseUtil;
import com.hotelmanagementapplication.model.user.Customer;
import com.hotelmanagementapplication.model.user.Manager;
import com.hotelmanagementapplication.model.user.User;

import java.util.List;

import static com.hotelmanagementapplication.controller.utildatabase.DatabaseUtil.*;

public class DatabaseController {
    /**
     * Method will create the user table
     */
    public static void createTableUser() {
        String sql = """
            CREATE TABLE IF NOT EXISTS User (
                userId INTEGER PRIMARY KEY AUTOINCREMENT,
                firstName TEXT NOT NULL,
                lastName TEXT NOT NULL,
                email TEXT UNIQUE NOT NULL,
                phoneNum TEXT,
                password TEXT NOT NULL,
                userType TEXT NOT NULL CHECK(userType IN ('Manager', 'Customer'))
            );
            """;
        executeUpdate(sql);
    }

    /**
     * Method will create manager table
     */
    public static void createTableManager() {
        String sql = """
                CREATE TABLE IF NOT EXISTS Manager (
                    userId INTEGER PRIMARY KEY,
                    FOREIGN KEY (userId) REFERENCES User(userId) ON DELETE CASCADE
                );
                """;
        executeUpdate(sql);
    }

    /**
     * Method will create customer table
     */
    public static void createTableCustomer() {
        String sql = """
                CREATE TABLE IF NOT EXISTS Customer (
                    userId INTEGER PRIMARY KEY,
                    FOREIGN KEY (userId) REFERENCES User(userId) ON DELETE CASCADE
                );
                """;
        executeUpdate(sql);
    }

    /**
     * Inserts data into user table
     * @param user The user
     * @param userType The user type
     * @return The generated key
     */
    public static int insertUser(User user, String userType) {
        String sql = "INSERT INTO User(firstName, lastName, email, phoneNum, password, userType) VALUES(?, ?, ?, ?, ?, ?)";
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String email = user.getEmail();
        String phoneNum = user.getPhoneNum();
        String password = user.getPassword();

        return executeInsert(sql, firstName, lastName, email, phoneNum, password, userType);
    }


    /**
     * Method will insert a manager based on the id generated by the insert method for users
     *
     * @param userId the userID
     */
    public static void insertManager(int userId) {
        String sql = "INSERT INTO Manager (userId) VALUES(?)";
        executeInsert(sql, userId);
        System.out.println("Manager data inserted successfully.");
    }

    /**
     * Method will insert a customer based on the id generated by the insert method for users
     *
     * @param userId the userID
     */
    public static void insertCustomer(int userId) {
        String sql = "INSERT INTO Customer(userId) VALUES(?)";
        executeInsert(sql, userId);
        System.out.println("Customer data inserted successfully.");
    }

    /**
     * Method will remove user from database
     *
     * @param userId The userId of the user
     */
    public static void removeUser(int userId) throws IllegalStateException {
        String sql = "DELETE FROM users WHERE userId = ?";
        try {
            executeUpdate(sql, userId);
            System.out.println("User with ID " + userId + " removed from the database.");
        } catch (RuntimeException e) {
            throw new IllegalStateException("Failed to remove user with ID: " + userId, e);
        }
    }

    /**
     * Method will remove user from database
     *
     * @param user The user to be removed
     */
    public static void removeUser(User user) throws IllegalStateException {
        int userId = user.getUserId();
        removeUser(userId);
    }

    /**
     * Method will alter user table
     *
     * @param user User to be altered
     */
    public static void alterUser(User user) {
        String sql = "UPDATE users SET first_name = ?, last_name = ?, email = ?, phone_num = ?, password = ? WHERE user_id = ?";
        executeUpdate(sql, user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhoneNum(), user.getPassword(), user.getUserId());
    }

    /**
     * reads the users as plaintext from the user table
     *
     * @return the users
     */
    public static List<User> selectUsers() {
        String sql = "SELECT * FROM User";
        return executeQuery(sql, (rs) -> {
            String userType = rs.getString("userType");
            User user = DatabaseUtil.mapUser(rs);
            if ("Manager".equals(userType)) {
                return new Manager(user);
            } else if ("Customer".equals(userType)) {
                return new Customer(user);
            }
            return user;
        });
    }

    /**
     * Method will join the PKs to retrieve manager data
     *
     * @return the managers
     */
    public static List<Manager> selectManagers() {
        String sql = """
                SELECT * FROM User u
                JOIN Manager m ON u.userId = m.userId
                """;
        return executeQuery(sql, DatabaseUtil::mapManager);
    }

    /**
     * Method will join the PKs to retrieve customer data
     *
     * @return the customers
     */
    public static List<Customer> selectCustomers() {
        String sql = """
                SELECT * FROM User u
                JOIN Customer c ON u.userId = c.userId
                """;
        return executeQuery(sql, DatabaseUtil::mapCustomer);
    }
}
