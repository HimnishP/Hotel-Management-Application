package com.hotelmanagementapplication.model.utildatabase;

import com.hotelmanagementapplication.controller.l10n_i18n.ScreenHandler;

import java.sql.*;
import java.util.ResourceBundle;

public class DatabaseUtil {
    private static String path = "jdbc:sqlite:./src/main/resources/database/database.db";

    /**
     * Establishes a connection with SQLite Database
     *
     * @return the database connection
     */
    private static Connection connect() {
        String url = path;
        Connection conn;
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

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
                    password TEXT NOT NULL
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
     * Insert data into the User table and method will return the generated ID
     *
     * @param firstName The first name of the user.
     * @param lastName  The last name of the user.
     * @param email     The email address of the user (unique).
     * @param phoneNum  The phone number of the user.
     * @param password  The password of the user.
     */
    public static int insertUser(String firstName, String lastName, String email, String phoneNum, String password) {
        String sql = "INSERT INTO User(firstName, lastName, email, phoneNum, password) VALUES(?, ?, ?, ?, ?)";
        return executeInsert(sql, firstName, lastName, email, phoneNum, password);
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
     * reads the users as plaintext from the user table
     *
     * @return the users as plaintext from user table
     */
    public static String selectUsers() {
        String sql = "SELECT * FROM User";
        return executeQuery(sql, rs -> formatUserDetails(rs, ScreenHandler.getResourceBundle()));
    }

    /**
     * Method will join the PKs to retrieve manager data
     * @return the manager data
     */
    public static String selectManagers() {
        String sql = """
        SELECT * FROM User u
        JOIN Manager m ON u.userId = m.userId
                                               \s""";
        return executeQuery(sql, rs -> formatUserDetails(rs, ScreenHandler.getResourceBundle()));
    }

    /**
     * Method will join the PKs to retrieve customer data
     * @return the customer data
     */
    public static String selectCustomers() {
        String sql = """
        SELECT * FROM User u
        JOIN Customer c ON u.userId = c.userId
                                                               \s""";
        return executeQuery(sql, rs -> formatUserDetails(rs, ScreenHandler.getResourceBundle()));
    }

    /**
     * Helper method for methods requiring executing update ( create table)
     *
     * @param sql    SQL statement
     * @param params Parameters
     */
    private static void executeUpdate(String sql, Object... params) {
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            pstmt.executeUpdate();
            System.out.println("SQL executed successfully: " + sql);
        } catch (SQLException e) {
            throw new RuntimeException("Error executing SQL: " + sql, e);
        }
    }

    /**
     * Helper method for executing insert
     *
     * @param sql    SQL Statement
     * @param params The parameters
     * @return The generated key
     */
    private static int executeInsert(String sql, Object... params) {
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            pstmt.executeUpdate();
            ResultSet keys = pstmt.getGeneratedKeys();
            if (keys.next()) {
                return keys.getInt(1); // Return the generated key
            }
            System.out.println("Data inserted successfully: " + sql);
        } catch (SQLException e) {
            throw new RuntimeException("Error executing insert: " + sql, e);
        }
        return -1;
    }

    @FunctionalInterface
    interface ResultSetProcessor {
        String process(ResultSet rs) throws SQLException;
    }

    /**
     * Helper method for executing queries (selecting)
     *
     * @param sql       SQL statement
     * @param processor ResultSet
     * @return The result
     */
    private static String executeQuery(String sql, ResultSetProcessor processor) {
        StringBuilder builder = new StringBuilder();
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                builder.append(processor.process(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error executing query: " + sql, e);
        }
        return builder.toString();
    }

    /**
     * Formats user details retrieved from the database as a localized string.
     *
     * @param rs             The ResultSet containing user data from the database.
     * @param resourceBundle The ResourceBundle containing localized labels for user attributes.
     * @return A formatted string containing the user details with localized labels.
     * @throws SQLException If an error occurs while accessing the ResultSet.
     */
  private static String formatUserDetails(ResultSet rs, ResourceBundle resourceBundle) throws SQLException {
    // Localize strings
    String userIDLabel = resourceBundle.getString("user_id");
    String firstNameLabel = resourceBundle.getString("first_name");
    String lastNameLabel = resourceBundle.getString("last_name");
    String emailLabel = resourceBundle.getString("email");
    String phoneLabel = resourceBundle.getString("phone_number");
    String passwordLabel = resourceBundle.getString("password");
    // Retrieve user data from the ResultSet
    int userID = rs.getInt("userId");
    String firstName = rs.getString("firstName");
    String lastName = rs.getString("lastName");
    String email = rs.getString("email");
    String phone = rs.getString("phoneNum");
    String password = rs.getString("password");
    // Format and return the user details as a string
    return String.format("%s%d, %s%s, %s%s, %s%s, %s%s, %s%s%n",
            userIDLabel, userID,
            firstNameLabel, firstName,
            lastNameLabel, lastName,
            emailLabel, email,
            phoneLabel, phone,
            passwordLabel, password);
}
