package main.java.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import main.java.model.User;

public class UserDao implements Dao<User> {

    @Override
    public void save(User user) {
        Connection conn = H2.getInstance().getConnection();
        try {
            PreparedStatement p = conn
                    .prepareStatement("INSERT INTO User (first_name, last_name, email, password) VALUES (?,?,?,?)");

            p.setString(1, user.getFirstName());
            p.setString(2, user.getLastName());
            p.setString(3, user.getEmail());
            p.setString(4, user.getPassword());
            p.execute();

        } catch (SQLException e) {
            throw new RuntimeException("Error saving user into database");
        }
        if (user.isAdmin())
            addToAdmins(user);
        H2.getInstance().closeConnection();
    }

    private void addToAdmins(User user) {
        Connection conn = H2.getInstance().getConnection();
        try {
            PreparedStatement p = conn.prepareStatement("INSERT INTO Admin VALUES (?)");
            p.setLong(1, user.getSsn());
            p.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error Adding user to admins");
        }
    }

    @Override
    public User get(long id) {
        Connection conn = H2.getInstance().getConnection();
        User user = new User();
        try {
            PreparedStatement p = conn
                    .prepareStatement("SELECT * FROM User LEFT JOIN Admin ON User.ssn = Admin.ssn WHERE User.ssn=?");
            p.setLong(1, id);

            ResultSet rs = p.executeQuery();

            if (!rs.next()) {
                H2.getInstance().closeConnection();
                return null;
            }
            user.setSsn(rs.getLong("ssn"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setAdmin(rs.getBoolean("admin.ssn"));
        } catch (SQLException e) {
            throw new RuntimeException("Error Getting user from database");
        }

        H2.getInstance().closeConnection();
        return user;
    }

    public User get(String email) {
        Connection conn = H2.getInstance().getConnection();
        User user = new User();
        try {
            PreparedStatement p = conn
                    .prepareStatement("SELECT * FROM User LEFT JOIN Admin ON User.ssn = Admin.ssn WHERE User.email=?");
            p.setString(1, email);
            ResultSet rs = p.executeQuery();
            if (!rs.next())
                return null;
            user.setSsn(rs.getLong("ssn"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setAdmin(rs.getBoolean("admin.ssn"));
            return user;
        } catch (SQLException e) {
            throw new RuntimeException("Error Getting user from database");
        } finally {
            H2.getInstance().closeConnection();
        }
    }

    @Override
    public void update(User user) {
        Connection conn = H2.getInstance().getConnection();
        try {
            PreparedStatement p = conn
                    .prepareStatement("UPDATE User set first_name=?, last_name=?, email=?, password=? WHERE ssn=?");

            p.setString(1, user.getFirstName());
            p.setString(2, user.getLastName());
            p.setString(3, user.getEmail());
            p.setString(4, user.getPassword());
            p.setLong(5, user.getSsn());

            p.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating user in database");
        }

        H2.getInstance().closeConnection();
    }

    @Override
    public void delete(User user) {
        Connection conn = H2.getInstance().getConnection();
        try {
            PreparedStatement p = conn.prepareStatement("DELETE FROM User WHERE ssn=?");
            p.setLong(1, user.getSsn());
            p.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting user from database");
        } finally {
            H2.getInstance().closeConnection();
        }
    }

    @Override
    public List<User> getAll() {
        Connection conn = H2.getInstance().getConnection();
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement p = conn.prepareStatement("SELECT * FROM User LEFT JOIN Admin ON User.ssn = Admin.ssn");

            ResultSet rs = p.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setSsn(rs.getLong("ssn"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setAdmin(rs.getBoolean("Admin.ssn"));

                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all users from database");
        } finally {
            H2.getInstance().closeConnection();
        }
        return users;
    }
}