package main.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Start Login Page
		Parent root = FXMLLoader.load(getClass().getResource("../gui/fxml/admin_page.fxml"));
		Scene scene = new Scene(root);

		// Stage Settings
		primaryStage.setResizable(false);
		primaryStage.getIcons().add(new Image("main/gui/assets/icon.png"));
		primaryStage.setTitle("Car Sales System");

		// Display window
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}