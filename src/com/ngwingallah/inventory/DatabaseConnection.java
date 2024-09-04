package com.ngwingallah.inventory;

import javax.swing.*;
import java.sql.*;


public class DatabaseConnection {
    private static final String url = "jdbc:sqlite:/home/ngwing-allah/InventoryDatabase.db";

    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
            System.out.println("Connected to database: " + connection.getMetaData().getURL());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection closed.");
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
                e.printStackTrace(); // Print stack trace for debugging
            }
        }
    }
}