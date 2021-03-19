package main.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import main.gui.java.CarCard;
import main.java.component.Car;
import main.java.component.Client;

public class Main extends Application {
	public static void main(String[] args) {
		// Sample Data
		Client.getArray().add(new Client("fullName", "email", "password"));
		Car.getArray().add(new CarCard("Tesla", "Model X", 2019, 1927600, "White", "main/gui/assets/tesX.jpg"));
		Car.getArray().add(new CarCard("Suzuki", "Alto", 2020, 123900, "Red", "main/gui/assets/suzA.jpg"));
		Car.getArray().add(new CarCard("Lada", "Granta", 2021, 153000, "Silver", "main/gui/assets/ladG.jpg"));
		Car.getArray().add(new CarCard("Proton", "Saga", 2021, 188900, "Black", "main/gui/assets/proS.jpg"));
		Car.getArray().add(new CarCard("DFSK", "Glory 330", 2020, 169900, "Red", "main/gui/assets/dfsk.jpg"));
		Car.getArray().add(new CarCard("Renault", "Logan", 2021, 174000, "Beige", "main/gui/assets/renL.jpg"));
		Car.getArray().add(new CarCard("Nissan", "Sunny", 2021, 180900, "Gold", "main/gui/assets/nisS.jpg"));
		Car.getArray().add(new CarCard("Geely", "Emgrand", 2019, 189000, "Blue", "main/gui/assets/geeE.jpg"));

		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Start Login Page

		Parent root = FXMLLoader.load(getClass().getResource("../gui/fxml/login.fxml"));
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