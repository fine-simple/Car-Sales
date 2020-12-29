package main.java.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
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

    private static CarEdit instance = null;
    private Scene scene = null;

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
    public void loadScene(Event e) {
        
        Stage stageTheEventBelongsTo = (Stage) ((Node) e.getSource()).getScene().getWindow();
        
        if(scene == null) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("../../gui/fxml/edit_car.fxml"));
                scene = new Scene(root);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        
        // Get the Stage from Event Called
        stageTheEventBelongsTo.setScene(scene);
    }

	@FXML
	private void goBack(ActionEvent e) throws IOException {
		AdminPage.getInstance().loadScene(e);
	}
    
    public static CarEdit getInstance() {
        if(instance == null)
            instance = new CarEdit();
        return instance;
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