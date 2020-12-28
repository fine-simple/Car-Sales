package main.java.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import main.java.component.Client;

public class Login implements Controller {
    
    private static Login instance = null;
    private Scene scene = null;

    @FXML
    private TextField email;
    @FXML
    private PasswordField password;

    public static Login getInstance() {
        if(instance == null)
            instance = new Login();
        return instance;
    }

    @FXML
    public void loadScene(Event e) {
        
        Stage stageTheEventBelongsTo = (Stage) ((Node) e.getSource()).getScene().getWindow();
        if(scene == null) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("../../gui/fxml/login.fxml"));
                scene = new Scene(root);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
        stageTheEventBelongsTo.setScene(scene);
    }
    @FXML
    private void performLogin(KeyEvent e) throws IOException {
        if(e.getCode() == KeyCode.ENTER) {
            login((Event)e);
        }
    }

    @FXML
    private void goToRegister(ActionEvent e) throws IOException {
        Register.getInstance().loadScene(e);
    }

    @FXML
    private void login(Event e) throws IOException {
        
        if(email.getText().equals(main.java.component.Admin.admin.getEmail()) && password.getText().equals(main.java.component.Admin.admin.getPassword())) {
            main.java.controller.Admin.loadScene(e);
        }
        else {
            for(Client client: Client.array) {
                if(client.getEmail().equals(email.getText()) && client.getPassword().equals(password.getText())) {
                    Marketplace.getInstance().loadScene(e);
                    return;
                }
            }

            Alert alert = new Alert(javafx.scene.control.Alert.AlertType.INFORMATION, "User Not Found", ButtonType.CANCEL);
            alert.show();
        }
    }
}