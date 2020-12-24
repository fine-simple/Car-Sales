package main.java.component;

import java.util.ArrayList;

public class Client extends User{
    public static int clientsNumber=0;

    public static ArrayList<Client> array = new ArrayList<>();

    public Client(String fullName, String email, String password) {
        super(fullName, email, password);
        clientsNumber++;
    }

    void buyCar(int carNumber){

    }
}