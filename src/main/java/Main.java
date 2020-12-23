package main.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Set GUI FXML file
        Parent root = FXMLLoader.load(getClass().getResource("../gui/fxml/login.fxml"));
        Scene scene = new Scene(root);
        
        //Stage Settings
        primaryStage.setTitle("Login");
        primaryStage.setResizable(false);
        
        //Display window
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}