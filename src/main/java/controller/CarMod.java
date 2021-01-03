package main.java.controller;

import java.io.File;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public abstract class CarMod {
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
	private ImageView carImageView;

	@FXML
	public abstract void loadScene(Event e);

	@FXML
	void goBack(Event e) {
		AdminPage.getInstance().loadScene(e);
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
		String RegaxModel = "^[A-Za-z][A-Za-z0-9]*";

		if (model.getText().matches(RegaxModel))
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

	boolean validateAll() {
		Alert error = new Alert(AlertType.ERROR, "", ButtonType.CANCEL);

		if (!validateCompany()) {
			error.setHeaderText("Company Name Error");
			error.setContentText("Please Make Sure you enter each word capitalized with maximum 3 words");
		}

		else if (!validateModel()) {
			error.setHeaderText("Model Name Error");
			error.setContentText("Please Make Sure you enter valid model format");
		}

		else if (!validateDate()) {
			error.setHeaderText("Date Error");
			error.setContentText("Please Make Sure you enter a valid date(date between 1980 and 2020)");
		}

		else if (!validatePrice()) {
			error.setHeaderText("Price Error");
			error.setContentText("Please Make Sure you enter a valid price");
		} else {
			return true;
		}

		error.show();
		return false;
	}

	public TextField getCompany() {
		return company;
	}

	public TextField getModel() {
		return model;
	}

	public TextField getYear() {
		return year;
	}

	public TextField getColor() {
		return color;
	}

	public TextField getPrice() {
		return price;
	}

	public ImageView getCarImageView() {
		return carImageView;
	}

	@FXML
	public void getImageFile(Event e) {
		MouseEvent eMouse = (MouseEvent) e;

		if(eMouse.getButton() == MouseButton.PRIMARY) {
			FileChooser imageFile = new FileChooser();
			imageFile.getExtensionFilters()
					.addAll(new ExtensionFilter("Images", "*.jpg", "*.jpeg", "*.png", "*.bmp", "*.gif"));
			File file = imageFile.showOpenDialog(((Scene) ((Node) e.getSource()).getScene()).getWindow());
			Image image = new Image(file.toURI().toString(), 150, 100 , false, false);
			carImageView.setImage(image);
		}
	}
	
}