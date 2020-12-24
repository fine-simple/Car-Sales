package main.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.component.Car;
import main.java.component.Marketplace;

public class Main extends Application {
	public static void main(String[] args) {
		Car c1 = new Car("Kia", "asd", 100000, 2010, "Red");
		Car c2 = new Car("Hyundai", "Alentra", 200000, 2012, "White");
		Car c3 = new Car("Bmw", "x6", 300000, 2018, "Black");
		Marketplace.arrayOfCars.add(c1);
		Marketplace.arrayOfCars.add(c2);
		Marketplace.arrayOfCars.add(c3);
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