package main.java.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.java.component.Car;

public class Marketplace implements Initializable, Controller {

    private static Marketplace instance = null;
    private Scene scene = null;
    @FXML
    private TextField search;
    @FXML
    private VBox list;

    @FXML
    public void loadScene(Event e) {
        Stage stageTheEventBelongsTo = (Stage) ((Node) e.getSource()).getScene().getWindow();

        if(scene == null) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("../../gui/fxml/marketplace.fxml"));
                // Get the Stage from Event Called
                scene = new Scene(root);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        stageTheEventBelongsTo.setScene(scene);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Car.array.forEach(car -> {
            list.getChildren().add(car.getContainer());
        });
    }
    @FXML
    private void search(Event e){
        Car.array.forEach(car -> {
            list.getChildren().remove(car.getContainer());
        });
        for(int i=0;i<Car.array.size();i++){
            if(Car.array.get(i).getName().equals(search.getText())) {
                list.getChildren().add(Car.array.get(i).getContainer());

            }
        }
    }
    public static Marketplace getInstance() {
        if(instance == null)
            instance = new Marketplace();
        return instance;
    }
}