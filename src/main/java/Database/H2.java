package main.java.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * H2
 */
public class H2 {
    public static final String url = "jdbc:h2:./h2Data";
    public static final String user = "admin";
    public static final String password = "VGF9DW8z5AGUpZ7oa3";
    private static H2 instance;
    private CarDao carDao;
    private UserDao userDao;
    Connection conn;

    private H2() {

    }

    public static H2 getInstance() {
        if (instance == null)
            instance = new H2();
        return instance;
    }

    public Connection getConnection() {
        try {
            conn = DriverManager.getConnection(url, user, password);
            return conn;
        } catch (SQLException e) {
            throw new RuntimeException("Error Connecting to database");
        }
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException("Error Closing Connection with database");
        }
    }

    public CarDao carOperations() {
        if (carDao == null)
            carDao = new CarDao();
        return carDao;
    }

    public UserDao userOperations() {
        if (userDao == null)
            userDao = new UserDao();
        return userDao;
    }
}