package main.java.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.java.component.Client;

public class Register {

    @FXML
    private TextField fullName, email;
    @FXML
    private PasswordField password;

    @FXML
    public static void loadScene(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(Register.class.getResource("../../gui/fxml/register.fxml"));

        // Get the Stage from Event Called
        Stage stageTheEventBelongsTo = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stageTheEventBelongsTo.setScene(new Scene(root));
    }

    @FXML
    private void goToLogin(ActionEvent e) throws IOException {
        Login.loadScene(e);
    }

    private boolean validateName() {
        String regexName = "\\p{Upper}(\\p{Lower}+\\s?)";
        String patternName = "(" + regexName + "){2,3}";

        if (fullName.getText().matches(patternName))
            return true;
        else
            return false;
    }

    private boolean validateEmail() {
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

        if(email.getText().matches(regex))
            return true;
        else
            return false;
    }

    private boolean validatePassword() {
        if(password.getText().length() >= 6)
            return true;
        else
            return false;
    }

    @FXML
    private void register(ActionEvent e) throws IOException {

        Alert error = new Alert(AlertType.ERROR, "", ButtonType.CANCEL);

        if(!validateName()) {
            error.setHeaderText("Full Name Error");
            error.setContentText("Please Make Sure you enter each word capitalized with maximum 3 words");
            error.show();
        }
        else if(!validateEmail()) {
            error.setHeaderText("Email Error");
            error.setContentText("Please Make Sure you enter valid email format");
            error.show();
        }
        else if(!validatePassword()) {
            error.setHeaderText("Password Error");
            error.setContentText("Please Make Sure you enter at least 6 characters in password");
            error.show();
        }
        else {
            //Add to users database
            Client.array.add(new Client(fullName.getText(), email.getText(), password.getText()));
            
            //Notify to added user
            Alert alert = new Alert(AlertType.INFORMATION, "User Added Successfully", ButtonType.OK);
            alert.show();

            //Go Marketplace
            Marketplace.loadScene(e);
        }
    }
}