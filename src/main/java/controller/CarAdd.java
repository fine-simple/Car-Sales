package main.java.controller;

import java.io.IOException;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import main.java.gui.CarCard;
import main.java.component.Car;

public class CarAdd extends CarMod {

	private static CarAdd instance = null;
	private Scene scene = null;

	@Override
	@FXML
	public void loadScene(Event e) {
		Stage stageTheEventBelongsTo = (Stage) ((Node) e.getSource()).getScene().getWindow();
		if (scene == null) {
			try {
				Parent root = FXMLLoader.load(getClass().getResource("../../gui/fxml/add_car.fxml"));
				scene = new Scene(root);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		// Get the Stage from Event Called
		stageTheEventBelongsTo.setScene(scene);
	}

	//// adds the (ADD) button and send alert

	@FXML
	private void addBtnPressed(Event e) {
		if (validateAll()) {
			Car.getArray()
					.add(new CarCard(getCompany().getText(), getModel().getText(),
							Integer.parseInt(getYear().getText()), Integer.parseInt(getPrice().getText()),
							getColor().getText(), getCarImageView().getImage().getUrl()));

			//// Notify that the car was added successfully
			Alert alert = new Alert(AlertType.INFORMATION, "Car Added successfully", ButtonType.OK);
			alert.show();

			// Go back to AdminPage
			AdminPage.getInstance().loadScene(e);
		}
	}

	//// Singleton
	public static CarAdd getInstance() {
		if (instance == null)
			instance = new CarAdd();
		return instance;
	}
}