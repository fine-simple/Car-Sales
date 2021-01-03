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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.gui.java.CarCard;
import main.java.component.Cars;

public class Marketplace implements Initializable, Controller {

    private static Marketplace instance = null;

    @FXML
    private TextField searchTextBox;
    @FXML
    private VBox list;

    @FXML
    public void loadScene(Event e) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../../gui/fxml/marketplace.fxml"));
            // Get the Stage from Event Called
            Stage stageTheEventBelongsTo = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stageTheEventBelongsTo.setScene(new Scene(root));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Cars.getArray().forEach(car -> {
            car.updateDetails();
            list.getChildren().add(car.getContainer());
            addCustomOption(car);
        });

    }

    void addCustomOption(CarCard car) {
        Button buy = new Button("Buy");
        
        buy.setOnAction(e -> {
            Alert confirmBox = new Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION, "Are you sure you want to buy this car?");
            if(confirmBox.showAndWait().get() == ButtonType.OK) {
                car.remove();
                new Alert(AlertType.INFORMATION, "Car Bought Successfully").show();
            }
        });

        if(car.getContainer().getChildren().size() > 2)
            car.getContainer().getChildren().remove(2);
        car.getContainer().getChildren().add(buy);
    }

    @FXML
    private void search(Event e){
        if(searchTextBox.getText().length()==0) {
            list.getChildren().clear();
            Cars.getArray().forEach(car -> {
                list.getChildren().add((car.getContainer()));
            });
        }
        else{
            Cars.getArray().forEach(car -> {
                list.getChildren().remove(car.getContainer());
            });
            for(int i=0;i<Cars.getArray().size();i++) {
                if (isNumeric(searchTextBox.getText())){
                    if(searchTextBox.getText().length()>4){
                        if(Integer.parseInt(searchTextBox.getText()) == Cars.getArray().get(i).getPrice()){
                            list.getChildren().add(Cars.getArray().get(i).getContainer());
                        }
                    }
                    else{
                        if(Integer.parseInt(searchTextBox.getText()) == Cars.getArray().get(i).getYear()){
                            list.getChildren().add(Cars.getArray().get(i).getContainer());
                        }
                    }
                }
                else if (Cars.getArray().get(i).getCompany().toLowerCase().contains(searchTextBox.getText().toLowerCase()) ) {
                    list.getChildren().add(Cars.getArray().get(i).getContainer());
                }
            }

        }
    }

    @FXML
    private void logout(Event e) {
        Login.getInstance().loadScene(e);
    }
    
    public static Marketplace getInstance() {
        if(instance == null)
            instance = new Marketplace();
        return instance;
    }

    private static boolean isNumeric(String str){
        try{
            Double.parseDouble(str);
            return true;
        }
        catch(NumberFormatException e){
            return false;
        }
    }
}