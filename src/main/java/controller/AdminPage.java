package main.java.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;
import main.java.Database.H2;
import main.java.model.Car;
import main.java.view.CardView;
import main.java.view.Cardable;

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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		actionText = "Edit";
		super.initialize(arg0, arg1);
	}

	@Override
	void action(Event e, CardView cardView) {
		CarMod.setActiveCar((Car) cardView.getItem());
		CarMod.getInstance().loadScene(e);
	}

	@FXML
	private void goToAdd(Event e) {
		CarMod.getInstance().loadScene(e);
	}

	@FXML
	private void deleteCars(Event e) {
		for (Cardable card : list.getSelectionModel().getSelectedItems()) {
			Car car = (Car) card;
			list.getItems().remove(car);
			H2.getInstance().carOperations().delete(car);
		}
	}

	// Singleton
	public static AdminPage getInstance() {
		if (instance == null)
			instance = new AdminPage();
		return instance;
	}
}