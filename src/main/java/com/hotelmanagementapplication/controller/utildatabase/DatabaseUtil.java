package com.hotelmanagementapplication.controller.utildatabase;

import com.hotelmanagementapplication.model.user.Customer;
import com.hotelmanagementapplication.model.user.Manager;
import com.hotelmanagementapplication.model.user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DatabaseUtil {
    private final static String PATH_DATABASE = "jdbc:sqlite:./src/main/resources/database/database.db";
    private final static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final static ReentrantReadWriteLock.ReadLock READ_LOCK = lock.readLock();
    private final static ReentrantReadWriteLock.WriteLock WRITE_LOCK = lock.writeLock();

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
     * Helper method for methods requiring executing update (UPDATE,DELETE,ALTER,CREATE,DROP)
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
     * Helper method for executing INSERT
     *
     * @param sql    SQL Statement
     * @param params The parameters
     * @return The generated key
     */
    public static int executeInsert(String sql, Object... params) {
        WRITE_LOCK.lock();
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
            WRITE_LOCK.unlock();
        }
        return -1;
    }

    /**
     * Helper method for executing queries (SELECTING)
     *
     * @param sql       SQL statement
     * @param processor ResultSet
     * @return The list
     */
    public static <T> List<T> executeQuery(String sql, ResultSetProcessor<T> processor) {
        READ_LOCK.lock();
        List<T> resultList = new ArrayList<>();
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                resultList.add(processor.process(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error executing query: " + sql, e);
        } finally {
            READ_LOCK.unlock();
        }
        return resultList;
    }

    /**
     * Method will return user object
     *
     * @param rs The ResultSet
     * @return User object
     * @throws SQLException When SQL error occurs
     */
    public static User mapUser(ResultSet rs) throws SQLException {
        // Map common fields for the User class
        int userId = rs.getInt("userId");
        String firstName = rs.getString("firstName");
        String lastName = rs.getString("lastName");
        String email = rs.getString("email");
        String phoneNum = rs.getString("phoneNum");
        String password = rs.getString("password");
        return new User(userId, firstName, lastName, email, phoneNum, password);
    }

    /**
     * Method will return manager object
     *
     * @param rs The ResultSet
     * @return User object
     * @throws SQLException When SQL error occurs
     */
    public static Manager mapManager(ResultSet rs) throws SQLException {
        User user = mapUser(rs);
        return new Manager(user);
    }

    /**
     * Method will return customer object
     *
     * @param rs The ResultSet
     * @return User object
     * @throws SQLException When SQL error occurs
     */
    public static Customer mapCustomer(ResultSet rs) throws SQLException {
        User user = mapUser(rs);
        return new Customer(user);
    }

    @FunctionalInterface
    public interface ResultSetProcessor<T> {
        T process(ResultSet rs) throws SQLException;
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
