package main.java.controller;

import java.io.IOException;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import main.gui.java.CarCard;
import main.java.component.Cars;

public class AdminPage extends Marketplace {

    private static AdminPage instance = null;
        
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

    @FXML
    private void goToAdd(Event e) {
        CarAdd.getInstance().loadScene(e);
    }

    @FXML
    private void deleteCars(Event e) {
        for (int i = 0; i < Cars.getArray().size(); i++) {
            CarCard car = Cars.getArray().get(i);
            if(car.isSelected()) {
                car.remove();
                i--;
            }
        }
    }

    void addCustomOption(CarCard car) {
        Button edit = new Button("Edit");

        edit.setOnAction(e -> {
            CarEdit.setActiveCar(car);
            CarEdit.getInstance().loadScene(e);
        });
        if(car.getContainer().getChildren().size() > 2)
            car.getContainer().getChildren().remove(2);
        car.getContainer().getChildren().add(edit);
    }

	public static AdminPage getInstance() {
        if(instance == null)
            instance = new AdminPage();
		return instance;
    }
}