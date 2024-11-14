package com.hotelmanagementapplication.model.utildatabase;

import com.hotelmanagementapplication.controller.l10n_i18n.ScreenHandler;

import java.sql.*;
import java.util.ResourceBundle;

public class DatabaseUtil {
    private static String path = "jdbc:sqlite:./src/main/resources/database/database.db";
    /**
     * establishes a connection with SQLite Database
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

    public static void createTableUser() {
        String sql =
                """
                        CREATE TABLE IF NOT EXISTS User (
                            userId INTEGER PRIMARY KEY AUTOINCREMENT,
                            firstName TEXT NOT NULL,
                            lastName TEXT NOT NULL,
                            email TEXT UNIQUE NOT NULL,
                            phoneNum TEXT,
                            password TEXT NOT NULL
                        );
                        """;
        try (Connection conn = connect();
             Statement statement = conn.createStatement()) {
            statement.execute(sql);
            System.out.println("Table created successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Insert data into the User table.
     *
     * @param firstName The first name of the user.
     * @param lastName  The last name of the user.
     * @param email     The email address of the user (unique).
     * @param phoneNum  The phone number of the user.
     * @param password  The password of the user.
     */
    public static void insertUser(String firstName, String lastName, String email, String phoneNum, String password) {
        String sql = "INSERT INTO User(firstName, lastName, email, phoneNum, password) VALUES(?, ?, ?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, email);
            pstmt.setString(4, phoneNum);
            pstmt.setString(5, password);
            pstmt.executeUpdate();
            System.out.println("User data inserted successfully.");
        } catch (SQLException e) {
            System.out.println("Error inserting user: " + e.getMessage());
        }
    }

    /**
     * reads the users as plaintext from the user table
     *
     * @return the users as plaintext from user table
     */
    public static String selectUsers() {
        String sql = "SELECT * FROM User";
        StringBuilder builder = new StringBuilder();
        ResourceBundle resourceBundle = ScreenHandler.getResourceBundle();
        try (Connection conn = connect();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                builder.append(formatUserDetails(rs, resourceBundle));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    private static String formatUserDetails(ResultSet rs, ResourceBundle resourceBundle) throws SQLException {
        String userIDLabel = resourceBundle.getString("user_id");
        String firstNameLabel = resourceBundle.getString("first_name");
        String lastNameLabel = resourceBundle.getString("last_name");
        String emailLabel = resourceBundle.getString("email");
        String phoneLabel = resourceBundle.getString("phone_number");
        String passwordLabel = resourceBundle.getString("password");

        int userID = rs.getInt("userId");
        String firstName = rs.getString("firstName");
        String lastName = rs.getString("lastName");
        String email = rs.getString("email");
        String phone = rs.getString("phoneNum");
        String password = rs.getString("password");

        return String.format("%s%d, %s%s, %s%s, %s%s, %s%s, %s%s%n",
                userIDLabel, userID,
                firstNameLabel, firstName,
                lastNameLabel, lastName,
                emailLabel, email,
                phoneLabel, phone,
                passwordLabel, password);
    }

    /**
     * Method will create manager table
     */
    public static void createTableManager() {
        String sql =
                """
                        CREATE TABLE IF NOT EXISTS Manager (
                            userId INTEGER PRIMARY KEY,
                            FOREIGN KEY (userId) REFERENCES User(userId) ON DELETE CASCADE
                        );
                        """;
        try (Connection conn = connect();
             Statement statement = conn.createStatement()) {
            statement.execute(sql);
            System.out.println("Manager table created successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method will create customer table
     */
    public static void createTableCustomer() {
        String sql =
                """
                        CREATE TABLE IF NOT EXISTS Customer (
                            userId INTEGER PRIMARY KEY,
                            FOREIGN KEY (userId) REFERENCES User(userId) ON DELETE CASCADE
                        );
                        """;
        try (Connection conn = connect();
             Statement statement = conn.createStatement()) {
            statement.execute(sql);
            System.out.println("Customer table created successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
