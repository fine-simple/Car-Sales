package main.java.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.gui.CarCard;

public class CarCardDao implements Dao<CarCard> {

    @Override
    public void save(CarCard car) throws SQLException {
        Connection conn = H2.getConnection();
        PreparedStatement p = conn
                .prepareStatement("INSERT INTO Car (company, model, year, price, color, image_path) VALUES (?, ?, ?, ?, ?, ?)");
        p.setString(1, car.getCompany());
        p.setString(2, car.getModel());
        p.setInt(3, car.getYear());
        p.setInt(4, car.getPrice());
        p.setString(5, car.getColor());
        p.setString(6, car.getPic().getImage().getUrl());
        p.execute();

        conn.close();
    }

    @Override
    public CarCard get(long id) throws SQLException {
        Connection conn = H2.getConnection();
        PreparedStatement p = conn.prepareStatement("SELECT * FROM Car WHERE id=?");
        p.setLong(1, id);
        ResultSet rs = p.executeQuery();
        if (rs.next()) {
            CarCard car = new CarCard(rs.getString("company"), rs.getString("model"), rs.getInt("year"), rs.getInt("price"),
                    rs.getString("color"), rs.getString("image_path"));
            car.setId(rs.getLong("id"));
            return car;
        }

        return null;
    }

    @Override
    public void update(long id, CarCard car) throws SQLException {
        Connection conn = H2.getConnection();
        PreparedStatement p = conn
        .prepareStatement("UPDATE Car SET company=?, model=?, year=?, price=?, color=?, image_path=? WHERE id=?");
        p.setString(1, car.getCompany());
        p.setString(2, car.getModel());
        p.setInt(3, car.getYear());
        p.setInt(4, car.getPrice());
        p.setString(5, car.getColor());
        p.setString(6, car.getPic().getImage().getUrl());
        p.setLong(7, id);

        p.execute();
        conn.close();
    }

    @Override
    public void delete(CarCard car) throws SQLException {
        Connection conn = H2.getConnection();
        PreparedStatement p = conn.prepareStatement("DELETE FROM Car WHERE id=?");
        p.setLong(1, car.getId());

        p.execute();
        conn.close();

    }

}