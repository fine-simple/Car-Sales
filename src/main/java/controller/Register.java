package main.java.controller;

import java.io.IOException;
import java.sql.SQLException;

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
import javafx.stage.Stage;
import main.java.Database.UserDao;
import main.java.component.User;

public class Register implements Controller {

	private static Register instance = null;
	private Scene scene = null;

	@FXML
	private TextField fullName, email;
	@FXML
	private PasswordField password;

	@Override
	@FXML
	public void loadScene(Event e) {

		Stage stageTheEventBelongsTo = (Stage) ((Node) e.getSource()).getScene().getWindow();
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../../gui/fxml/register.fxml"));
			scene = new Scene(root);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// Get the Stage from Event Called
		stageTheEventBelongsTo.setScene(scene);
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

	private boolean validateName() {
		String regexName = "\\p{Upper}(\\p{Lower}+\\s?)";
		String patternName = "(" + regexName + "){2,3}";

		if (fullName.getText().matches(patternName))
			return true;
		else
			return false;
	}

	private boolean validateEmail() {
		String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

		if (email.getText().matches(regex)) {
			try {
				UserDao.getInstance().get(email.getText());
				return false;
			} catch (SQLException e) {
				return true;
			}
		} else
			return false;
	}

	private boolean validatePassword() {
		if (password.getText().length() >= 6)
			return true;
		else
			return false;
	}

	@FXML
	private void register(Event e) {

		Alert error = new Alert(AlertType.ERROR, "", ButtonType.CANCEL);

		if (!validateName()) {
			error.setHeaderText("Full Name Error");
			error.setContentText("Please Make Sure you enter each word capitalized with maximum 3 words");
			error.show();
		} else if (!validateEmail()) {
			error.setHeaderText("Email Error");
			error.setContentText(
					"Please Make Sure you enter valid email format\n" + "Make sure you are not already registered");
			error.show();
		} else if (!validatePassword()) {
			error.setHeaderText("Password Error");
			error.setContentText("Please Make Sure you enter at least 6 characters in password");
			error.show();
		} else {
			// Add to users database
			String[] splittedFullName = fullName.getText().split(" ");
			String firstName = splittedFullName[0];
			String lastName = splittedFullName[1];

			try {
				UserDao.getInstance().save(new User(firstName, lastName, email.getText(), password.getText()));
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			// Notify to added user
			Alert alert = new Alert(AlertType.INFORMATION, "User Added Successfully", ButtonType.OK);
			alert.show();

			// Go Marketplace
			Marketplace.getInstance().loadScene(e);
		}
	}

	//// Singleton
	public static Register getInstance() {
		if (instance == null)
			instance = new Register();

		return instance;
	}
}