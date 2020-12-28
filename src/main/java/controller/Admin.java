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

public class Admin {

    @FXML
    public static void loadScene(Event e) throws IOException {
        Parent root = FXMLLoader.load(Admin.class.getResource("../../gui/fxml/admin_page.fxml"));

        // Get the Stage from Event Called
        Stage stageTheEventBelongsTo = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stageTheEventBelongsTo.setScene(new Scene(root));
    }

    @FXML
    private void goToSearch(ActionEvent e) throws IOException {
        CarSearch.loadScene(e);
    }

    @FXML
    private void goToEdit(ActionEvent e) throws IOException {
        CarEdit.loadScene(e);
    }

    @FXML
    private void goToAdd(ActionEvent e) throws IOException {
        CarAdd.loadScene(e);
    }

    @FXML
    private void goToDelete(ActionEvent e) throws IOException {
        CarDelete.loadScene(e);
    }
}