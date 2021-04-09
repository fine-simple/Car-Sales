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
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import main.java.Database.UserDao;
import main.java.component.User;

public class Login implements Controller {

	private static Login instance = null;
	private Scene scene = null;

	@FXML
	private TextField email;
	@FXML
	private PasswordField password;

	@Override
	@FXML
	public void loadScene(Event e) {

		Stage stageTheEventBelongsTo = (Stage) ((Node) e.getSource()).getScene().getWindow();
		if (scene == null) {
			try {
				Parent root = FXMLLoader.load(getClass().getResource("../../gui/fxml/login.fxml"));
				scene = new Scene(root);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}
		stageTheEventBelongsTo.setScene(scene);
	}

	@FXML
	private void performLogin(KeyEvent e) throws IOException {
		if (e.getCode() == KeyCode.ENTER) {
			login(e);
		}
	}

	@FXML
	private void goToRegister(ActionEvent e) throws IOException {
		Register.getInstance().loadScene(e);
	}

	@FXML
	private void login(Event e) {
		// Logging as admin validation
		try {
			User u = UserDao.getInstance().get(email.getText());
			if(password.getText().equals(u.getPassword())) {
				if (u.isAdmin()) {
					AdminPage.getInstance().loadScene(e);
				} else {
					Marketplace.getInstance().loadScene(e);
				}
				
				return;
			} else {
				Alert alert = new Alert(javafx.scene.control.Alert.AlertType.INFORMATION, "Wrong Password",
					ButtonType.CANCEL);
				alert.show();
			}

		} catch (SQLException e1) {
			Alert alert = new Alert(javafx.scene.control.Alert.AlertType.INFORMATION, "User Not Found",
					ButtonType.CANCEL);
			alert.show();
		}
	}

	//// Singleton
	public static Login getInstance() {
		if (instance == null)
			instance = new Login();
		return instance;
	}
}