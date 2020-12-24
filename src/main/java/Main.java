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

		// Start Login Page
		Parent root = FXMLLoader.load(getClass().getResource("../gui/fxml/login.fxml"));
		Scene scene = new Scene(root);

		// Stage Settings
		primaryStage.setResizable(false);
		primaryStage.setTitle("Login");

		// Display window
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}