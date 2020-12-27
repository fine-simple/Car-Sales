package main.java.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.java.component.Car;

public class Marketplace implements Initializable {

    @FXML
    private VBox list;

    @FXML
    public static void loadScene(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(Marketplace.class.getResource("../../gui/fxml/marketplace.fxml"));

        // Get the Stage from Event Called
        Stage stageTheEventBelongsTo = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stageTheEventBelongsTo.setScene(new Scene(root));
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Car.array.forEach(car -> {
            list.getChildren().add(car.container);
        });
    }
}