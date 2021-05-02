package main.java.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.model.Car;
import main.java.model.User;

public class CarDao implements Dao<Car> {
    @Override
    public void save(Car car) {
        Connection conn = H2.getInstance().getConnection();
        PreparedStatement p;
        try {
            p = conn.prepareStatement(
                    "INSERT INTO Car (company, model, year, price, color, image_path) VALUES (?, ?, ?, ?, ?, ?)");
            p.setString(1, car.getCompany());
            p.setString(2, car.getModel());
            p.setInt(3, car.getYear());
            p.setInt(4, car.getPrice());
            p.setString(5, car.getColor());
            p.setString(6, car.getImagePath());
            p.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Car Already Exists");
        }

        H2.getInstance().closeConnection();
    }

    @Override
    public Car get(long id) {
        Connection conn = H2.getInstance().getConnection();
        ResultSet rs;
        Car c = new Car();
        try {
            PreparedStatement p = conn.prepareStatement("SELECT * FROM Car WHERE id=?");
            p.setLong(1, id);
            rs = p.executeQuery();
            if (!rs.next()) {
                H2.getInstance().closeConnection();
                return null;
            }
            c.setId(rs.getLong("id"));
            c.setCompany(rs.getString("company"));
            c.setModel(rs.getString("model"));
            c.setPrice(rs.getInt("price"));
            c.setYear(rs.getInt("year"));
            c.setColor(rs.getString("color"));
            c.setImagePath(rs.getString("image_path"));
        } catch (SQLException e) {
            throw new RuntimeException("Error getting car from database");
        }

        H2.getInstance().closeConnection();
        return c;
    }

    @Override
    public void update(Car car) {
        Connection conn = H2.getInstance().getConnection();
        try {
            PreparedStatement p = conn.prepareStatement(
                    "UPDATE Car SET company=?, model=?, year=?, price=?, color=?, image_path=? WHERE id=?");
            p.setString(1, car.getCompany());
            p.setString(2, car.getModel());
            p.setInt(3, car.getYear());
            p.setInt(4, car.getPrice());
            p.setString(5, car.getColor());
            p.setString(6, car.getImagePath());

            p.setLong(7, car.getId());

            p.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating car to database");
        }

        H2.getInstance().closeConnection();
    }

    @Override
    public void delete(Car car) {
        Connection conn = H2.getInstance().getConnection();
        try {
            PreparedStatement p = conn.prepareStatement("DELETE FROM Car WHERE id=?");
            p.setLong(1, car.getId());

            p.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting car from database");
        }
        H2.getInstance().closeConnection();
    }

    @Override
    public List<Car> getAll() {
        Connection conn = H2.getInstance().getConnection();
        List<Car> cars = new ArrayList<>();
        try {
            PreparedStatement p = conn.prepareStatement("SELECT * FROM Car");
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                Car c = new Car();
                c.setId(rs.getLong("id"));
                c.setCompany(rs.getString("company"));
                c.setModel(rs.getString("model"));
                c.setPrice(rs.getInt("price"));
                c.setYear(rs.getInt("year"));
                c.setColor(rs.getString("color"));
                c.setImagePath(rs.getString("image_path"));

                cars.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all cars");
        }
        H2.getInstance().closeConnection();
        return cars;
    }

    public List<Car> getAvailable() {
        Connection conn = H2.getInstance().getConnection();
        List<Car> cars = new ArrayList<>();
        try {
            PreparedStatement p = conn.prepareStatement("SELECT * FROM Car WHERE user_ssn IS NULL");
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                Car c = new Car();
                c.setId(rs.getLong("id"));
                c.setCompany(rs.getString("company"));
                c.setModel(rs.getString("model"));
                c.setPrice(rs.getInt("price"));
                c.setYear(rs.getInt("year"));
                c.setColor(rs.getString("color"));
                c.setImagePath(rs.getString("image_path"));

                cars.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all cars");
        }
        H2.getInstance().closeConnection();
        return cars;
    }

    public void buy(Car car, User user) {
        Connection conn = H2.getInstance().getConnection();
        try {
            PreparedStatement p = conn.prepareStatement("UPDATE Car SET user_ssn=? WHERE id=?");
            p.setLong(1, user.getSsn());
            p.setLong(2, car.getId());

            p.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error Performing Transaction");
        }

        H2.getInstance().closeConnection();
    }

    public List<Car> search(String keyword) {
        Connection conn = H2.getInstance().getConnection();
        List<Car> cars = new ArrayList<>();
        try {
            PreparedStatement p = conn.prepareStatement(
                    "SELECT * FROM Car WHERE company LIKE ? OR model LIKE ? OR color LIKE ?  OR price LIKE ? OR year LIKE ?");

            p.setString(1, '%' + keyword + '%');
            p.setString(2, '%' + keyword + '%');
            p.setString(3, '%' + keyword + '%');
            p.setString(4, '%' + keyword + '%');
            p.setString(5, '%' + keyword + '%');

            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                Car c = new Car();
                c.setId(rs.getLong("id"));
                c.setCompany(rs.getString("company"));
                c.setModel(rs.getString("model"));
                c.setPrice(rs.getInt("price"));
                c.setYear(rs.getInt("year"));
                c.setColor(rs.getString("color"));
                c.setImagePath(rs.getString("image_path"));

                cars.add(c);
            }
        } catch (SQLException e) {
            // throw new RuntimeException("Error searching cars");
            e.printStackTrace();
        }
        return cars;
    }
}