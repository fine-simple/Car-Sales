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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Register implements Initializable {
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
    
    }

    @FXML
    private TextField fullName, password, email;

    @FXML
    private void goToLogin(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../gui/fxml/login.fxml"));

        // Get the Stage from Event Called
        Stage stageTheEventBelongsTo = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stageTheEventBelongsTo.setScene(new Scene(root));
        stageTheEventBelongsTo.setTitle("Login");
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
        if(password.getText().length() > 6)
            return true;
        else
            return false;
    }

    @FXML
    private void register() {
        if(validateName() && validateEmail() && validatePassword()) {
            //Add to users database


            //Added notification
            Alert alert = new Alert(AlertType.INFORMATION, "User Added Successfully", ButtonType.OK);
            alert.show();
        }
        else {
            //Display Error
            Alert alert = new Alert(AlertType.ERROR, "Please Validate your information", ButtonType.CANCEL);
            alert.show();
        }
    }

}