package com.ngwingallah.inventory;

// This is a simple Java program.
// FileName : "HelloWorld.java".

import java.sql.Connection;
import java.sql.SQLException;

public class Main{
    public static void main(String[] args) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
    }
}
