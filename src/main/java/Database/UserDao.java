package main.java.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.component.User;

public class UserDao implements Dao<User> {
    //Singleton
    private static UserDao instance;

    private UserDao() {

    }

    public static UserDao getInstance() {
        if(instance == null) {
            instance = new UserDao();
        }

        return instance;
    }
    @Override
    public void save(User user) throws SQLException {
        Connection conn = H2.getConnection();
        PreparedStatement p = conn
                .prepareStatement("INSERT INTO User (first_name, last_name, email, password) VALUES (?,?,?,?)");

        p.setString(1, user.getFirstName());
        p.setString(2, user.getLastName());
        p.setString(3, user.getEmail());
        p.setString(4, user.getPassword());

        p.execute();
        conn.close();
    }

    @Override
    public User get(long id) throws SQLException {
        Connection conn = H2.getConnection();
        PreparedStatement p = conn
                .prepareStatement("SELECT * FROM User WHERE ssn=?");
        p.setLong(1, id);

        ResultSet rs = p.executeQuery();
        
        if (rs.next()) {
            User user = new User(rs.getLong("ssn"),rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"), rs.getString("password"));
            user.setAdmin(isAdmin(user.getId()));
            conn.close();
            return user;
        }

        conn.close();
        return null;
    }

    public User get(String email) throws SQLException {
        Connection conn = H2.getConnection();
        PreparedStatement p = conn
                .prepareStatement("SELECT * FROM User WHERE email=?");
        p.setString(1, email);

        ResultSet rs = p.executeQuery();
        
        rs.next();
        User user = new User(rs.getLong("ssn"),rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"), rs.getString("password"));
        user.setAdmin(isAdmin(user.getId()));

        conn.close();
        return user;
    }
    
    @Override
    public void update(long id, User user) throws SQLException {
        Connection conn = H2.getConnection();
        PreparedStatement p = conn.prepareStatement("UPDATE User set first_name=?, last_name=?, email=?, password=? WHERE id=?");

        p.setString(1, user.getFirstName());
        p.setString(2, user.getLastName());
        p.setString(3, user.getEmail());
        p.setString(4, user.getPassword());
        p.setLong(5, user.getId());

        p.execute();
        conn.close();
    }

    @Override
    public void delete(User user) throws SQLException {
        Connection conn = H2.getConnection();
        PreparedStatement p = conn.prepareStatement("DELETE FROM User WHERE id=?");

        p.setLong(1, user.getId());
        
        p.execute();
        conn.close();
    }

    private boolean isAdmin(long id) throws SQLException {
        Connection conn = H2.getConnection();
        PreparedStatement p = conn
                .prepareStatement("SELECT * FROM Admin WHERE ssn=?");
        p.setLong(1, id);

        ResultSet rs = p.executeQuery();
        
        return rs.next();
    }
}