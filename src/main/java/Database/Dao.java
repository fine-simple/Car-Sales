package main.java.Database;

import java.sql.SQLException;

public interface Dao<T> {
    
    void save(T t) throws SQLException;
    
    T get(long id) throws SQLException;
    
    void update(long id, T t) throws SQLException;
    
    void delete(T t) throws SQLException;
}