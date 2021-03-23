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
import main.java.gui.CarCard;
import main.java.component.Car;

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
        Car.getArray().forEach(car -> {
            car.updateDetails();
            list.getChildren().add(car.getContainer());
            setCustomBtn(car);
        });

    }

    void setCustomBtn(CarCard car) {
        Button buy = car.getCustomBtn();
        
        buy.setText("Buy");
        
        buy.setOnAction(e -> {
            Alert confirmBox = new Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION, "Are you sure you want to buy this car?");
            if(confirmBox.showAndWait().get() == ButtonType.OK) {
                car.remove();
                new Alert(AlertType.INFORMATION, "Car Bought Successfully").show();
            }
        });
    }

    @FXML
    private void search(Event e){
        if(searchTextBox.getText().length()==0) {   //Clear when field is empty
            list.getChildren().clear();
            Car.getArray().forEach(car -> {
                list.getChildren().add((car.getContainer()));
            });
        }
        else{
            Car.getArray().forEach(car -> {       //Clear when start typing
                list.getChildren().remove(car.getContainer());
            });
            for(int i=0;i<Car.getArray().size();i++) {
                if (isNumeric(searchTextBox.getText())){
                    if(searchTextBox.getText().length()>4){     //Search by price
                        if(Integer.parseInt(searchTextBox.getText()) == Car.getArray().get(i).getPrice()){
                            list.getChildren().add(Car.getArray().get(i).getContainer());
                        }
                    }
                    else{    //Search by year
                        if(Integer.parseInt(searchTextBox.getText()) == Car.getArray().get(i).getYear()){
                            list.getChildren().add(Car.getArray().get(i).getContainer());
                        }
                    }
                }
                else if (Car.getArray().get(i).getCompany().toLowerCase().contains(searchTextBox.getText().toLowerCase()) ) {  //Search by name
                    list.getChildren().add(Car.getArray().get(i).getContainer());
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