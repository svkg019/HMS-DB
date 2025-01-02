package com.hms.driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:E:\\Programs\\eclipse-workspace\\MySQLiteDB";
    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL);
                System.out.println("Connection to SQLite has been established.");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection to SQLite has been closed.");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
