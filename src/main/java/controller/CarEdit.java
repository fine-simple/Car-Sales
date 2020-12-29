package main.java.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.java.component.Car;

public class CarEdit implements Initializable {

	private static Car activeCar = null;
	@FXML
	private TextField company;
	@FXML
	private TextField model;

	@FXML
	private TextField year;

	@FXML
	private TextField color;
	@FXML
	private TextField price;

	@FXML
	public static void loadScene(ActionEvent e) throws IOException {
		Parent root = FXMLLoader.load(CarEdit.class.getResource("../../gui/fxml/edit_car.fxml"));

		// Get the Stage from Event Called
		Stage stageTheEventBelongsTo = (Stage) ((Node) e.getSource()).getScene().getWindow();
		stageTheEventBelongsTo.setScene(new Scene(root));
	}

	@FXML
	private void goBack(ActionEvent e) throws IOException {
		Admin.loadScene(e);
	}

	public static void setActiveCar(Car activeCar) {
		CarEdit.activeCar = activeCar;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		company.setText(activeCar.getName());
		model.setText(activeCar.getModel());
		year.setText(activeCar.getDate() + "");
	}

}