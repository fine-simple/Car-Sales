package main.java.controller;

import java.io.IOException;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.w3c.dom.Text;
import main.java.component.Car;

public class CarAdd {

    private static CarAdd instance = null;
    private Scene scene = null;
    
    @FXML
    public void loadScene(Event e) {
        Stage stageTheEventBelongsTo = (Stage) ((Node) e.getSource()).getScene().getWindow();
        if(scene == null) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("../../gui/fxml/add_car.fxml"));
                scene = new Scene(root);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        // Get the Stage from Event Called
        stageTheEventBelongsTo.setScene(scene);
    }

    public static CarAdd getInstance() {
        if(instance == null)
            instance = new CarAdd();
        return instance;
    }

    @FXML
    private void goBack(Event e) {
        AdminPage.getInstance().loadScene(e);
    }
    @FXML
    private TextField CarID;
    @FXML
    private TextField Company;
    @FXML
    private TextField Model;
    @FXML
    private TextField Year;
    @FXML
    private TextField Price;
    @FXML
    private TextField Color;
    //


    private void addCar()
    {
        Car.array.add(new Car (Company.getText(),Model.getText(),Integer.parseInt(Year.getText()),Integer.parseInt(Price.getText()),Color.getText() ));
    }
}