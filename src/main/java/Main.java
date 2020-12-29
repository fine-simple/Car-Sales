package main.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import main.java.component.Car;
import main.java.component.Client;

public class Main extends Application {
	public static void main(String[] args) {
		// Sample Data
		Client.array.add(new Client("fullName", "email", "password"));
		Car.array.add(new Car("Tesla", "S23", 32, 32, "main/gui/assets/Volvo_XC40_2018.jpg"));
		Car.array.add(new Car("Hundai", "7mada", 242, 3242342));
		Car.array.add(new Car("Tesla", "S24", 32, 32, "main/gui/assets/Volvo_XC40_2018.jpg"));
		Car.array.add(new Car("Tesla", "S25", 32, 32, "main/gui/assets/Volvo_XC40_2018.jpg"));
		Car.array.add(new Car("Tesla", "S26", 32, 32, "main/gui/assets/Volvo_XC40_2018.jpg"));
		Car.array.add(new Car("Tesla", "S27", 32, 32, "main/gui/assets/Volvo_XC40_2018.jpg"));
		Car.array.add(new Car("Tesla", "S28", 32, 32, "main/gui/assets/Volvo_XC40_2018.jpg"));

		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Start Login Page
		Parent root = FXMLLoader.load(getClass().getResource("../gui/fxml/edit_car.fxml"));
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