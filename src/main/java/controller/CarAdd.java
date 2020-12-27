package main.java.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CarAdd {
    
    @FXML
    public static void loadScene(ActionEvent e) throws IOException{
        Parent root = FXMLLoader.load(CarSearch.class.getResource("../../gui/fxml/add_car.fxml"));

        // Get the Stage from Event Called
        Stage stageTheEventBelongsTo = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stageTheEventBelongsTo.setScene(new Scene(root));
    }

    @FXML
    private void goBack(ActionEvent e) throws IOException {
        Admin.loadScene(e);
    }
}