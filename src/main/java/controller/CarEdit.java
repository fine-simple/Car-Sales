package main.java.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CarEdit {
    
    private static CarEdit instance = null;
    private Scene scene = null;
    
    @FXML
    public void loadScene(Event e) {
        
        Stage stageTheEventBelongsTo = (Stage) ((Node) e.getSource()).getScene().getWindow();
        
        if(scene == null) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("../../gui/fxml/edit_car.fxml"));
                scene = new Scene(root);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        
        // Get the Stage from Event Called
        stageTheEventBelongsTo.setScene(scene);
    }

    @FXML
    private void goBack(Event e) {
        AdminPage.getInstance().loadScene(e);
    }

    public static CarEdit getInstance() {
        if(instance == null)
            instance = new CarEdit();
        return instance;
    }
}