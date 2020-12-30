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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.gui.java.CarCard;

public class CarEdit implements Initializable {

	private static CarEdit instance = null;

	private static CarCard activeCar = null;

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

	@FXML
	private void goBack(Event e) {
		AdminPage.getInstance().loadScene(e);
	}

	public static CarEdit getInstance() {
		if (instance == null)
			instance = new CarEdit();
		return instance;
	}

	public static void setActiveCar(CarCard activeCar) {
		CarEdit.activeCar = activeCar;
	}

	private boolean validateCompany() {
		String RegaxName = "\\p{Upper}(\\p{Lower}+\\s?)";
		String patternName = "(" + RegaxName + "){1,3}";
		if (company.getText().matches(patternName))
			return true;
		else if (company.getText().isEmpty())
			return false;
		else
			return false;
	}

	private boolean validateModel() {
		String RegaxModel = "\\p{Upper}(\\p{Lower}+\\s?)";
		String patternModel = "(" + RegaxModel + "){1,2}";
		if (model.getText().matches(patternModel))
			return true;
		else if (model.getText().isEmpty())
			return false;
		else
			return false;

	}

	private boolean validateDate() {

		if (Integer.parseInt(year.getText()) > 2020 || Integer.parseInt(year.getText()) < 1980)
			return false;
		else if (year.getText().isEmpty())
			return false;
		else
			return true;

	}

	private boolean validatePrice() {
		try {
			Integer.parseInt(price.getText());
			if (Integer.parseInt(price.getText()) < 0)
				return false;
			else
				return true;
		} catch (Exception e) {
			return false;
		}

	}

	@FXML
	public void editBtnPressed(Event e) {
		Alert error = new Alert(AlertType.ERROR, "", ButtonType.CANCEL);

		if (!validateCompany()) {
			error.setHeaderText("Company Name Error");
			error.setContentText("Please Make Sure you enter each word capitalized with maximum 3 words");
			error.show();
		}

		else if (!validateModel()) {
			error.setHeaderText("Model Name Error");
			error.setContentText("Please Make Sure you enter each word capitalized with maximum 3 words");
			error.show();
		}

		else if (!validateDate()) {
			error.setHeaderText("Date Error");
			error.setContentText("Please Make Sure you enter a valid date(date between 1980 and 2020)");
			error.show();
		}

		else if (!validatePrice()) {
			error.setHeaderText("Price Error");
			error.setContentText("Please Make Sure you enter a valid price");
			error.show();
		} else {
			// edit activeCar
			activeCar.setCompany(company.getText());
			activeCar.setModel(model.getText());
			activeCar.setPrice(Integer.parseInt(price.getText()));
			activeCar.setYear(Integer.parseInt(year.getText()));

			// Notify to edited car
			Alert alert = new Alert(AlertType.INFORMATION, "Car edited successfully", ButtonType.OK);
			alert.show();

			// Go AdminPage
			AdminPage.getInstance().loadScene(e);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		company.setText(activeCar.getCompany());
		model.setText(activeCar.getModel());
		year.setText(String.valueOf(activeCar.getYear()));
		color.setText(activeCar.getColor());
		price.setText(String.valueOf(activeCar.getPrice()));
	}

}