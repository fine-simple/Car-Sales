package main.java.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Login {

    @FXML
    private TextField email;
    @FXML
    private PasswordField password;

    @FXML
    private void goToRegister(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../gui/fxml/register.fxml"));

        // Get the Stage from Event Called
        Stage stageTheEventBelongsTo = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stageTheEventBelongsTo.setScene(new Scene(root));
        stageTheEventBelongsTo.setTitle("Register");
    }

    @FXML
    private void login() {
        
    }
}