package main.java.component;

public class Client extends User{
     public static int clientsNumber=0;
    public Client(String FN, String E, String PW) {
        super(FN, E, PW);
        clientsNumber++;
    }
    void buyCar(int carNumber){

    }
}
