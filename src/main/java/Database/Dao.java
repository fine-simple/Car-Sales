package main.java.Database;

import java.util.List;

public interface Dao<T> {

    void save(T t);

    T get(long id);

    void update(T t);

    void delete(T t);

    List<T> getAll();
}