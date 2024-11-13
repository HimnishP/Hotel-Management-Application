package com.hotelmanagementapplication.model.databaseutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtil {
    private static String path = "jdbc:sqlite:./src/main/resources/database/database.db";

    /**
     * connects to the SQLiteDatabase
     *
     * @return the connection made with SQLite Database.
     */
    private static Connection connect() {
        String url = path;

        Connection conn;
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    /**
     * creates a new table.
     */
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
            System.out.println("Table created Successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
