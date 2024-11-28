package com.hotelmanagementapplication.controller.utildatabase;

import com.hotelmanagementapplication.model.payment.CreditCardPayment;
import com.hotelmanagementapplication.model.payment.DebitCardPayment;
import com.hotelmanagementapplication.model.payment.Payment;
import com.hotelmanagementapplication.model.room.DoubleBed;
import com.hotelmanagementapplication.model.room.Room;
import com.hotelmanagementapplication.model.room.SingleBed;
import com.hotelmanagementapplication.model.room.Status;
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
    public static Connection connect() {
        try {
            Connection conn = DriverManager.getConnection(PATH_DATABASE);
            try (Statement stmt = conn.createStatement()) {
                stmt.execute("PRAGMA foreign_keys = ON;"); // ensures that when FK is deleted, it will be deleted on all tables
            }
            return conn;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to the database.", e);
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
     * Drops table
     * @param tableName The table
     */
    public static void dropTable(String tableName) {
        executeUpdate("DROP TABLE IF EXISTS "+tableName);
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
    public static <T> List<T> executeQuery(String sql, ResultSetProcessor<T> processor, Object... params) {
        READ_LOCK.lock();
        List<T> resultList = new ArrayList<>();
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set the parameters dynamically
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    resultList.add(processor.process(rs));
                }
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

    /**
     * Method will map payment object
     *
     * @param rs Result set
     * @return Payment object
     * @throws SQLException SQL error
     */
    public static Payment mapPayment(ResultSet rs) throws SQLException {
        Payment payment = new Payment();
        // Map payment fields
        payment.setPaymentId(rs.getInt("paymentId"));
        payment.setAmount(rs.getDouble("amount"));
        payment.setPaymentDate(rs.getString("paymentDate"));
        //Map user fields
        User user = DatabaseUtil.mapUser(rs);
        payment.setUserId(user.getUserId());
        return payment;
    }

    /**
     * Method will map debit card payment
     *
     * @param rs Result Set
     * @return DebitCard object
     * @throws SQLException SQL Error
     */
    public static DebitCardPayment mapDebitCardPayment(ResultSet rs) throws SQLException {
        DebitCardPayment payment = new DebitCardPayment();
        // Map Payment fields
        payment.setPaymentId(rs.getInt("paymentId"));
        payment.setAmount(rs.getDouble("amount"));
        payment.setPaymentDate(rs.getString("paymentDate"));
        // Map DebitCardPayment-specific fields
        payment.setDebitCardNumber(rs.getString("debitCardNumber"));
        payment.setCardHolderName(rs.getString("cardHolderName"));
        payment.setExpirationDate(rs.getString("expirationDate"));
        payment.setSecurityCode(rs.getString("securityCode"));
        return payment;
    }

    /**
     * Method will map credit card payment
     *
     * @param rs Result Set
     * @return CreditCard object
     * @throws SQLException SQL Error
     */
    public static CreditCardPayment mapCreditCardPayment(ResultSet rs) throws SQLException {
        CreditCardPayment payment = new CreditCardPayment();
        // Map Payment fields
        payment.setPaymentId(rs.getInt("paymentId"));
        payment.setAmount(rs.getDouble("amount"));
        payment.setPaymentDate(rs.getString("paymentDate"));
        // Map CreditCardPayment-specific fields
        payment.setCreditCardNumber(rs.getString("creditCardNumber"));
        payment.setCardHolderName(rs.getString("cardHolderName"));
        payment.setExpirationDate(rs.getString("expirationDate"));
        payment.setSecurityCode(rs.getString("securityCode"));
        return payment;
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
// For the Room ----------------------------------------------------
    /**
     * Method will return Room object
     *
     * @param rs The ResultSet
     * @return Room object
     * @throws SQLException When SQL error occurs
     */
    public static Room mapRoom(ResultSet rs) throws SQLException {
        // Map common fields for the User class
        int roomId = rs.getInt("room_Id");
        double roomPrice = rs.getDouble("room_price");
        Status roomStatus = Status.valueOf(rs.getString("room_status"));

        return new Room(roomId, roomPrice, roomStatus);

    }

    /**
     * Method will return DoubleBed object
     *
     * @param rs The ResultSet
     * @return DoubleBed object
     * @throws SQLException When SQL error occurs
     */
    public static DoubleBed mapDoubleBed(ResultSet rs) throws SQLException {
        Room room = mapRoom(rs);
        return new DoubleBed(room.getPrice(), room.getStatus());
    }
//
    /**
     * Method will return SingleBed object
     *
     * @param rs The ResultSet
     * @return SingleBed object
     * @throws SQLException When SQL error occurs
     */
    public static SingleBed mapSingleBed(ResultSet rs) throws SQLException {
        Room room = mapRoom(rs);
        return new SingleBed(room.getPrice(), room.getStatus());
    }


}
