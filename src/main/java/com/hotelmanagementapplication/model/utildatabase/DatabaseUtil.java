package com.hotelmanagementapplication.model.utildatabase;

import java.sql.*;

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
}
