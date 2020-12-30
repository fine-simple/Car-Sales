package main.java.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import main.gui.java.CarCard;

public class CarEdit extends CarMod implements Initializable {

	private static CarEdit instance = null;

	private static CarCard activeCar = null;

	@Override
	public void loadScene(Event e) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../../gui/fxml/edit_car.fxml"));

			// Get the Stage from Event Called
			Stage stageTheEventBelongsTo = (Stage) ((Node) e.getSource()).getScene().getWindow();
			stageTheEventBelongsTo.setScene(new Scene(root));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public static CarEdit getInstance() {
		if (instance == null)
			instance = new CarEdit();
		return instance;
	}

	public static void setActiveCar(CarCard activeCar) {
		CarEdit.activeCar = activeCar;
	}

	@FXML
	public void editBtnPressed(Event e) {
		
		if(validateAll()) {
			// edit activeCar
			activeCar.setCompany(getCompany().getText());
			activeCar.setModel(getModel().getText());
			activeCar.setPrice(Integer.parseInt(getPrice().getText()));
			activeCar.setYear(Integer.parseInt(getYear().getText()));

			// Notify to edited car
			Alert alert = new Alert(AlertType.INFORMATION, "Car edited successfully", ButtonType.OK);
			alert.show();

			// Go AdminPage
			AdminPage.getInstance().loadScene(e);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		getCompany().setText(activeCar.getCompany());
		getModel().setText(activeCar.getModel());
		getYear().setText(String.valueOf(activeCar.getYear()));
		getColor().setText(activeCar.getColor());
		getPrice().setText(String.valueOf(activeCar.getPrice()));
	}

}