package com.ngwingallah.inventory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {
    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            String url = "jdbc:sqlite:InventoryDatabase.db";
            connection = DriverManager.getConnection(url);
            System.out.println("Connection to Inventory Database has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
