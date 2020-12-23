package main.java.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Register {

    @FXML
    private void goToLogin(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../gui/fxml/login.fxml"));
        
        //Get the Stage from Event Called
        Stage stageTheEventBelongsTo = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stageTheEventBelongsTo.setScene(new Scene(root));
        stageTheEventBelongsTo.setTitle("Login");
    }
}