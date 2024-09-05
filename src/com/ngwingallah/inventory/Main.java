package com.ngwingallah.inventory;


import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

public class Main{
    public static void main(String[] args) throws SQLException {
        //Connect to the database
        Connection connection = DatabaseConnection.getConnection();

        //Display GUI
        SwingUtilities.invokeLater(() -> {
            HomeWindow homeWindow = new HomeWindow();
            homeWindow.setVisible(true);
        });
    }
}
