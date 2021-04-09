package main.java.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * H2
 */
public class H2 {
    public static final  String url = "jdbc:h2:./h2Data";
    public static final String user = "admin";
    public static final String password = "VGF9DW8z5AGUpZ7oa3";
    
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException("Error Connecting to database");
        }
    }
}