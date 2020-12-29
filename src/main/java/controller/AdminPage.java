package main.java.controller;

import java.io.IOException;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdminPage {

    private static AdminPage instance = null;
    
    private Scene scene = null;
    
    @FXML
    public void loadScene(Event e) {
        
        // Get the Stage from Event Called
        Stage stageTheEventBelongsTo = (Stage) ((Node) e.getSource()).getScene().getWindow();
        
        if(scene == null) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("../../gui/fxml/admin_page.fxml"));
                scene = new Scene(root);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        stageTheEventBelongsTo.setScene(scene);
    }

    @FXML
    private void goToEdit(Event e) {
        CarEdit.getInstance().loadScene(e);
    }

    @FXML
    private void goToAdd(Event e) {
        CarAdd.getInstance().loadScene(e);
    }

    @FXML
    private void deleteCars(Event e) {
        
    }

    @FXML
    private void logout() {

    }

	public static AdminPage getInstance() {
        if(instance == null)
            instance = new AdminPage();
		return instance;
    }
}