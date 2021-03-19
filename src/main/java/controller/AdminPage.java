package main.java.controller;

import java.io.IOException;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import main.gui.java.CarCard;
import main.java.component.Car;

public class AdminPage extends Marketplace {

	private static AdminPage instance = null;

	@Override
	@FXML
	public void loadScene(Event e) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../../gui/fxml/admin_page.fxml"));
			// Get the Stage from Event Called
			Stage stageTheEventBelongsTo = (Stage) ((Node) e.getSource()).getScene().getWindow();
			stageTheEventBelongsTo.setScene(new Scene(root));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@FXML
	private void goToAdd(Event e) {
		CarAdd.getInstance().loadScene(e);
	}

	@FXML
	private void deleteCars(Event e) {
		for (int i = 0; i < Car.getArray().size(); i++) {
			CarCard car = Car.getArray().get(i);
			if (car.isSelected()) {
				car.remove();
				i--;
			}
		}
	}

	//// Changes the (Buy) button to (Edit) button
	@Override
	void setCustomBtn(CarCard car) {
		Button edit = car.getCustomBtn();

		edit.setText("Edit");

		edit.setOnAction(e -> {
			CarEdit.setActiveCar(car);
			CarEdit.getInstance().loadScene(e);
		});
	}

	//// Singleton
	public static AdminPage getInstance() {
		if (instance == null)
			instance = new AdminPage();
		return instance;
	}
}