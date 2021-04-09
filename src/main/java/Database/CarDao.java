package main.java.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import main.java.component.Car;

public class CarDao implements Dao<Car> {

    @Override
    public void save(Car t) throws SQLException {
        Connection conn = H2.getConnection();
        PreparedStatement p = conn.prepareStatement("INSERT INTO Car () VALUES ()");
        
    }

    @Override
    public Car get(long id) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void update(long id, Car t) throws SQLException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(Car t) throws SQLException {
        // TODO Auto-generated method stub
        
    }
    
}
