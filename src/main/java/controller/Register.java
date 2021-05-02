package main.java.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.java.Database.H2;
import main.java.model.User;

public class Register implements Controller {

	private static Register instance;
	private Scene scene;
	private Stage stageTheEventBelongsTo;
	Alert alert = new Alert(AlertType.INFORMATION, "", ButtonType.OK);;

	@FXML
	private TextField firstName, lastName, email;
	@FXML
	private PasswordField password;

	@Override
	@FXML
	public void loadScene(Event e) {

		stageTheEventBelongsTo = (Stage) ((Node) e.getSource()).getScene().getWindow();
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../../gui/fxml/register.fxml"));
			scene = new Scene(root);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// Get the Stage from Event Called
		stageTheEventBelongsTo.setScene(scene);

		alert.initOwner(stageTheEventBelongsTo);
		alert.initModality(Modality.APPLICATION_MODAL);
	}

	@FXML
	private void goToLogin(ActionEvent e) {
		Login.getInstance().loadScene(e);
	}

	@FXML
	private void performRegister(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER)
			register(e);
	}

	private String trimeName(String name) {
		char[] trimmed = name.toCharArray();

		trimmed[0] = Character.toUpperCase(trimmed[0]);

		if (name.length() < 2)
			return name;

		for (int i = 1; i < trimmed.length; i++) {
			trimmed[i] = Character.toLowerCase(trimmed[i]);
		}

		return String.valueOf(trimmed);
	}

	private boolean validateName() {

		firstName.setText(firstName.getText().strip());
		lastName.setText(lastName.getText().strip());

		String patternName = "^[a-zA-Z]+$";
		if (!firstName.getText().matches(patternName)) {
			alert.setContentText("Invalid First Name");
			alert.setHeaderText("");
			alert.show();
			return false;
		} else if (!lastName.getText().matches(patternName)) {
			alert.setContentText("Invalid Last Name");
			alert.setHeaderText("");
			alert.show();
			return false;
		}

		firstName.setText(trimeName(firstName.getText()));
		lastName.setText(trimeName(lastName.getText()));

		return true;
	}

	private boolean validateEmail() {
		String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

		if (!email.getText().matches(regex)) {
			alert.setContentText("Enter Valid Email Address");
			alert.setHeaderText("");
			alert.show();
			return false;
		}

		if (H2.getInstance().userOperations().get(email.getText()) != null) {
			alert.setContentText("User Already Exists");
			alert.setHeaderText("");
			alert.show();
			return false;
		}

		return true;
	}

	private boolean validatePassword() {
		if (password.getText().length() >= 6)
			return true;
		alert.setContentText("Password must be of 6 length at least");
		alert.setHeaderText("");
		alert.show();
		return false;
	}

	@FXML
	private void register(Event e) {
		if (validateName() && validateEmail() && validatePassword()) {
			User user = new User(firstName.getText(), lastName.getText(), email.getText(), password.getText());
			// Add to users database
			H2.getInstance().userOperations().save(user);

			// Notify to added user
			alert.setContentText("User Added Successfully");
			alert.setHeaderText("");
			alert.show();

			// Go Marketplace
			Marketplace.setActiveUser(user);
			Marketplace.getInstance().loadScene(e);
		}
	}

	// Singleton
	public static Register getInstance() {
		if (instance == null)
			instance = new Register();

		return instance;
	}
}