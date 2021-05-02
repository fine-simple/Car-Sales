package main.java.controller;

import java.io.File;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import main.java.Database.H2;
import main.java.model.Car;

public class CarMod implements Controller, Initializable {
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
	private Button action;
	@FXML
	private Label title;

	private static Car activeCar;
	private static CarMod instance;

	public static CarMod getInstance() {
		if (instance == null)
			instance = new CarMod();
		return instance;
	}

	@Override
	public void loadScene(Event e) {
		// Get the Stage from Event Called
		Stage stageTheEventBelongsTo = (Stage) ((Node) e.getSource()).getScene().getWindow();
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../../gui/fxml/car_mod.fxml"));
			Scene scene = new Scene(root);
			stageTheEventBelongsTo.setScene(scene);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@FXML
	void goBack(Event e) {
		activeCar = null;
		company.setText("");
		model.setText("");
		color.setText("");
		price.setText("");
		year.setText("");
		carImageView.setImage(new Image("main/gui/assets/no-image.gif"));

		AdminPage.getInstance().loadScene(e);
	}

	public void addNewCar() {
		title.setText("Add");
		action.setOnAction(e -> {
			if (validateAll()) {
				Car car = new Car();
				car.setCompany(company.getText());
				car.setModel(model.getText());
				car.setColor(color.getText());
				car.setPrice(Integer.parseInt(price.getText()));
				car.setYear(Integer.parseInt(year.getText()));
				car.setImagePath(carImageView.getImage().getUrl());

				H2.getInstance().carOperations().save(car);

				// Notify that the car was added successfully
				Alert alert = new Alert(AlertType.INFORMATION, "Car Added successfully", ButtonType.OK);
				alert.show();

				goBack(e);
			}
		});
	}

	public void editCar() {
		title.setText("Edit");
		company.setText(activeCar.getCompany());
		model.setText(activeCar.getModel());
		year.setText(String.valueOf(activeCar.getYear()));
		color.setText(activeCar.getColor());
		price.setText(String.valueOf(activeCar.getPrice()));
		carImageView.setImage(new Image(activeCar.getImagePath()));
		action.setOnAction(e -> {
			if (validateAll()) {
				// Edit activeCar
				activeCar.setCompany(company.getText());
				activeCar.setModel(model.getText());
				activeCar.setPrice(Integer.parseInt(price.getText()));
				activeCar.setYear(Integer.parseInt(year.getText()));
				activeCar.setColor(color.getText());
				activeCar.setImagePath(carImageView.getImage().getUrl());

				H2.getInstance().carOperations().update(activeCar);

				// Notify that the car was edited successfully
				Alert alert = new Alert(AlertType.INFORMATION, "Car edited successfully", ButtonType.OK);
				alert.setHeaderText("");
				alert.show();

				goBack(e);
			}
		});
	}

	public static void setActiveCar(Car activeCar) {
		CarMod.activeCar = activeCar;
	}

	private boolean validateCompany() {
		String patternName = "^[A-Za-z]";
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

		if ((Integer.parseInt(year.getText()) > 2020) || (Integer.parseInt(year.getText()) < 1980))
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

	@FXML
	public void getImageFile(Event e) {
		MouseEvent eMouse = (MouseEvent) e;

		if (eMouse.getButton() == MouseButton.PRIMARY) {
			FileChooser imageFile = new FileChooser();
			imageFile.getExtensionFilters()
					.addAll(new ExtensionFilter("Images", "*.jpg", "*.jpeg", "*.png", "*.bmp", "*.gif"));
			File file = imageFile.showOpenDialog(((Node) e.getSource()).getScene().getWindow());
			Image image = new Image(file.toURI().toString(), 150, 100, false, false);
			carImageView.setImage(image);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (activeCar == null) {
			addNewCar();
		} else {
			editCar();
		}
	}

}