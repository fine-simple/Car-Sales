package main.java.component;

import java.util.ArrayList;

public class Client extends User{

    private static ArrayList<Client> array = new ArrayList<>();

    public Client(String fullName, String email, String password) {
        super(fullName, email, password);
    }

    public static ArrayList<Client> getArray() {
        return array;
    }
}