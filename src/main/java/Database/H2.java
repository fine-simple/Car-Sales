package main.java.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * H2
 */
public class H2 {
    private final static String url = "jdbc:h2:./database";
    private final static String user = "admin";
    private final static String password = "VGF9DW8z5AGUpZ7oa3";
    //Singleton Pattern
    private static H2 instance = null;
    
    private H2(){

    }
    
    public static H2 getInstance(){
        if(instance == null)
            instance = new H2();
        return instance;
    }

    public void excute(String query) throws SQLException {
        Connection conn = DriverManager.getConnection(url, user, password);
        Statement st = conn.createStatement();
        st.execute(query);
    }

    public ResultSet excuteQuery (String query) throws SQLException {
        Connection conn = DriverManager.getConnection(url, user, password);
        Statement st = conn.createStatement();
        return st.executeQuery(query);
    }

}