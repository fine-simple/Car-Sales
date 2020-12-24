package main.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import main.java.component.Client;

public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		Client.array.add(new Client("fullName", "email", "password"));
		// Start Login Page
		Parent root = FXMLLoader.load(getClass().getResource("../gui/fxml/login.fxml"));
		Scene scene = new Scene(root);

		// Stage Settings
		primaryStage.setResizable(false);
		primaryStage.getIcons().add(new Image("https://raw.githubusercontent.com/v01dc0d3/Car-Sales/main/src/main/gui/assets/icon.png"));
		primaryStage.setTitle("Car Sales System");

		// Display window
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}