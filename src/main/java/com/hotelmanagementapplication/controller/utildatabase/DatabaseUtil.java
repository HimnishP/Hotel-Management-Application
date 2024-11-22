package com.hotelmanagementapplication.controller.utildatabase;

import java.sql.*;
import java.util.ResourceBundle;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DatabaseUtil {
    private final static String PATH_DATABASE = "jdbc:sqlite:./src/main/resources/database/database.db";
    private final static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final static ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    private final static ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    /**
     * Establishes a connection with SQLite Database
     *
     * @return the database connection
     */
    private static Connection connect() {
        try {
            return DriverManager.getConnection(PATH_DATABASE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Helper method for methods requiring executing update ( create table)
     *
     * @param sql    SQL statement
     * @param params Parameters
     */
    public static void executeUpdate(String sql, Object... params) {
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
    public static int executeInsert(String sql, Object... params) {
        writeLock.lock();
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
        } finally {
            writeLock.unlock();
        }
        return -1;
    }

    @FunctionalInterface
    public interface ResultSetProcessor {
        String process(ResultSet rs) throws SQLException;
    }

    /**
     * Helper method for executing queries (selecting)
     *
     * @param sql       SQL statement
     * @param processor ResultSet
     * @return The result
     */
    public static String executeQuery(String sql, ResultSetProcessor processor) {
        readLock.lock();
        StringBuilder builder = new StringBuilder();
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                builder.append(processor.process(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error executing query: " + sql, e);
        } finally {
            readLock.unlock();
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
    public static String formatUserDetails(ResultSet rs, ResourceBundle resourceBundle) throws SQLException {
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
}
