package com.hotelmanagementapplication.controller.utildatabase;

import com.hotelmanagementapplication.model.payment.CreditCardPayment;
import com.hotelmanagementapplication.model.payment.DebitCardPayment;
import com.hotelmanagementapplication.model.payment.Payment;

import java.util.List;

import static com.hotelmanagementapplication.controller.utildatabase.DatabaseUtil.*;

public class PaymentDatabase {
    /**
     * Method will create payment table
     */
    public static void createTablePayment() {
        String sql = """
                CREATE TABLE IF NOT EXISTS Payment (
                    paymentId INTEGER PRIMARY KEY AUTOINCREMENT,
                    userId INTEGER NOT NULL,
                    amount REAL NOT NULL,
                    paymentDate TEXT NOT NULL,
                    paymentType TEXT NOT NULL CHECK(paymentType IN ('DebitCardPayment', 'CreditCardPayment')),
                    FOREIGN KEY (userId) REFERENCES User(userId) ON DELETE CASCADE
                );
                """;
        executeUpdate(sql);
    }

    /**
     * Create debit card table
     */
    public static void createTableDebitCardPayment() {
        String sql = """
                CREATE TABLE IF NOT EXISTS DebitCardPayment (
                    paymentId INTEGER PRIMARY KEY,
                    debitCardNumber TEXT NOT NULL,
                    cardHolderName TEXT NOT NULL,
                    expirationDate TEXT NOT NULL,
                    securityCode TEXT NOT NULL,
                    FOREIGN KEY (paymentId) REFERENCES Payment(paymentId) ON DELETE CASCADE
                );
                """;
        executeUpdate(sql);
    }

    /**
     * Create debit card table
     */
    public static void createTableCreditCardPayment() {
        String sql = """
                 CREATE TABLE IF NOT EXISTS CreditCardPayment (
                     paymentId INTEGER PRIMARY KEY,
                     creditCardNumber TEXT NOT NULL,
                     cardHolderName TEXT NOT NULL,
                     expirationDate TEXT NOT NULL,
                     securityCode TEXT NOT NULL,
                     FOREIGN KEY (paymentId) REFERENCES Payment(paymentId) ON DELETE CASCADE
                 );               \s
                \s""";
        executeUpdate(sql);
    }

    /**
     * Inserts a payment into the Payment table.
     *
     * @param userId      The ID of the user making the payment.
     * @param amount      The payment amount.
     * @param paymentDate The payment date.
     * @param paymentType The type of payment (e.g., "DebitCardPayment" or "CreditCardPayment").
     * @return The generated payment ID.
     */
    public static int insertPayment(int userId, double amount, String paymentDate, String paymentType) {
        String sql = "INSERT INTO Payment(userId, amount, paymentDate, paymentType) VALUES(?, ?, ?, ?)";
        return executeInsert(sql, userId, amount, paymentDate, paymentType);
    }

    /**
     * Inserts a payment into the Payment table.
     *
     * @param payment     The payment
     * @param paymentType The type of payment (e.g., "DebitCardPayment" or "CreditCardPayment").
     * @return The generated payment ID.
     */
    public static int insertPayment(Payment payment, String paymentType) {
        String sql = "INSERT INTO Payment(userId, amount, paymentDate, paymentType) VALUES(?, ?, ?, ?)";
        return insertPayment(payment.getUser().getUserId(), payment.getAmount(), payment.getPaymentDate(), paymentType);
    }

    /**
     * Inserts a debit card payment into the DebitCardPayment table.
     *
     * @param paymentId       The ID of the payment (referencing the Payment table).
     * @param debitCardNumber The debit card number.
     * @param cardHolderName  The name on the card.
     * @param expirationDate  The card expiration date.
     * @param securityCode    The card security code.
     */
    public static void insertDebitCardPayment(int paymentId, String debitCardNumber, String cardHolderName, String expirationDate, String securityCode) {
        String sql = "INSERT INTO DebitCardPayment(paymentId, debitCardNumber, cardHolderName, expirationDate, securityCode) VALUES(?, ?, ?, ?, ?)";
        executeInsert(sql, paymentId, debitCardNumber, cardHolderName, expirationDate, securityCode);
    }

    /**
     * Inserts a debit card payment into the DebitCardPayment table.
     *
     * @param debit The debit card payment
     */
    public static void insertDebitCardPayment(DebitCardPayment debit) {
        insertDebitCardPayment(debit.getPaymentId(), debit.getDebitCardNumber(), debit.getCardHolderName(), debit.getExpirationDate(), debit.getSecurityCode());
    }

    /**
     * Inserts a credit card payment into the CreditCardPayment table.
     *
     * @param paymentId        The ID of the payment (referencing the Payment table).
     * @param creditCardNumber The credit card number.
     * @param cardHolderName   The name on the card.
     * @param expirationDate   The card expiration date.
     * @param securityCode     The card security code.
     */
    public static void insertCreditCardPayment(int paymentId, String creditCardNumber, String cardHolderName, String expirationDate, String securityCode) {
        String sql = "INSERT INTO CreditCardPayment(paymentId, creditCardNumber, cardHolderName, expirationDate, securityCode) VALUES(?, ?, ?, ?, ?)";
        executeInsert(sql, paymentId, creditCardNumber, cardHolderName, expirationDate, securityCode);
    }

    /**
     * Inserts a credit card payment into the CreditCardPayment table.
     *
     * @param credit The credit payment
     */
    public static void insertCreditCardPayment(CreditCardPayment credit) {
        insertCreditCardPayment(credit.getPaymentId(), credit.getCreditCardNumber(), credit.getCardHolderName(), credit.getExpirationDate(), credit.getSecurityCode());
    }

    /**
     * Retrieves all payments with their basic details.
     *
     * @return a list of Payment objects.
     */
    public static List<Payment> selectPayments() {
        String sql = """
                        SELECT p.*, u.userId, u.firstName, u.lastName, u.email, u.phoneNum, u.password
                        FROM Payment p
                        JOIN User u ON p.userId = u.userId
                """;
        return executeQuery(sql, (rs) -> {
            String paymentType = rs.getString("paymentType");
            Payment payment = DatabaseUtil.mapPayment(rs);
            if ("DebitCardPayment".equals(paymentType)) {
                return selectDebitCardPayment(payment.getPaymentId());
            } else if ("CreditCardPayment".equals(paymentType)) {
                return selectCreditCardPayment(payment.getPaymentId());
            }
            return payment;
        });
    }

    /**
     * Retrieves all debit card payments.
     *
     * @return a list of DebitCardPayment objects.
     */
    public static List<DebitCardPayment> selectDebitCardPayments() {
        String sql = """
                SELECT p.*, u.userId, u.firstName, u.lastName, u.email, u.phoneNum, u.password,
                       d.debitCardNumber, d.cardHolderName, d.expirationDate, d.securityCode
                FROM Payment p
                JOIN User u ON p.userId = u.userId
                JOIN DebitCardPayment d ON p.paymentId = d.paymentId
                """;
        return executeQuery(sql, DatabaseUtil::mapDebitCardPayment);
    }

    /**
     * Retrieves all credit card payments.
     *
     * @return a list of CreditCardPayment objects.
     */
    public static List<CreditCardPayment> selectCreditCardPayments() {
        String sql = """
                SELECT p.*, u.userId, u.firstName, u.lastName, u.email, u.phoneNum, u.password,
                       c.creditCardNumber, c.cardHolderName, c.expirationDate, c.securityCode
                FROM Payment p
                JOIN User u ON p.userId = u.userId
                JOIN CreditCardPayment c ON p.paymentId = c.paymentId
                """;

        return executeQuery(sql, DatabaseUtil::mapCreditCardPayment);
    }

    /**
     * Retrieves a specific debit card payment by payment ID.
     *
     * @param paymentId The ID of the payment.
     * @return A DebitCardPayment object.
     */
    public static DebitCardPayment selectDebitCardPayment(int paymentId) {
        String sql = """
                SELECT p.*, u.userId, u.firstName, u.lastName, u.email, u.phoneNum, u.password,
                       d.debitCardNumber, d.cardHolderName, d.expirationDate, d.securityCode
                FROM Payment p
                JOIN User u ON p.userId = u.userId
                JOIN DebitCardPayment d ON p.paymentId = d.paymentId
                WHERE p.paymentId = ?
                """;
        return executeQuery(sql, DatabaseUtil::mapDebitCardPayment, paymentId).stream().findFirst().orElse(null);
    }

    /**
     * Retrieves a specific credit card payment by payment ID.
     *
     * @param paymentId The ID of the payment.
     * @return A CreditCardPayment object.
     */
    public static CreditCardPayment selectCreditCardPayment(int paymentId) {
        String sql = """
                SELECT p.*, u.userId, u.firstName, u.lastName, u.email, u.phoneNum, u.password,
                       c.creditCardNumber, c.cardHolderName, c.expirationDate, c.securityCode
                FROM Payment p
                JOIN User u ON p.userId = u.userId
                JOIN CreditCardPayment c ON p.paymentId = c.paymentId
                WHERE p.paymentId = ?
                """;
        return executeQuery(sql, DatabaseUtil::mapCreditCardPayment, paymentId).stream().findFirst().orElse(null);
    }
}
