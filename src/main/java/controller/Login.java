package main.java.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.java.component.Client;

public class Login {

    @FXML
    private TextField email;
    @FXML
    private PasswordField password;


    @FXML
    public static void loadScene(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(Login.class.getResource("../../gui/fxml/login.fxml"));

        // Get the Stage from Event Called
        Stage stageTheEventBelongsTo = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stageTheEventBelongsTo.setScene(new Scene(root));
    }

    @FXML
    private void goToRegister(ActionEvent e) throws IOException {
        Register.loadScene(e);
    }

    @FXML
    private void login(ActionEvent e) throws IOException {
        
        if(email.getText().equals(main.java.component.Admin.admin.getEmail()) && password.getText().equals(main.java.component.Admin.admin.getPassword())) {
            main.java.controller.Admin.loadScene(e);
        }
        else {
            for(Client client: Client.array) {
                if(client.getEmail().equals(email.getText()) && client.getPassword().equals(password.getText())) {
                    Marketplace.loadScene(e);
                    return;
                }
            }

            Alert alert = new Alert(javafx.scene.control.Alert.AlertType.INFORMATION, "User Not Found", ButtonType.CANCEL);
            alert.show();
        }
    }
}