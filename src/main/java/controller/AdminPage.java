package main.java.controller;

import java.io.IOException;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.java.component.Car;

public class AdminPage {

    private static AdminPage instance = null;
    
    private Scene scene = null;
    
    @FXML
    private TextField search;
    @FXML
    private VBox list;
    
    @FXML
    public void loadScene(Event e) {
        
        // Get the Stage from Event Called
        Stage stageTheEventBelongsTo = (Stage) ((Node) e.getSource()).getScene().getWindow();
        
        if(scene == null) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("../../gui/fxml/admin_page.fxml"));
                scene = new Scene(root);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        stageTheEventBelongsTo.setScene(scene);
    }

    @FXML
    private void goToEdit(Event e) {
        CarEdit.getInstance().loadScene(e);
    }

    @FXML
    private void goToAdd(Event e) {
        CarAdd.getInstance().loadScene(e);
    }

    @FXML
    private void deleteCars(Event e) {
        for (int i = 0; i < Car.array.size(); i++) {
            if(Car.array.get(i).isSelected()) {
                Car.array.get(i).buy();
                i--;
            }
        }
    }

    @FXML
    private void logout() {

    }

    @FXML
    private void search(Event e){
        if(search.getText().length()==0) {
            Car.array.forEach(car -> {
                list.getChildren().remove(car.getContainer());
            });
            Car.array.forEach(car -> {
                list.getChildren().add(car.getContainer());
            });
        }
        else{
            Car.array.forEach(car -> {
                list.getChildren().remove(car.getContainer());
            });
            for(int i=0;i<Car.array.size();i++) {
                if (isNumeric(search.getText())){
                    if(search.getText().length()>4){
                        if(Integer.parseInt(search.getText()) == Car.array.get(i).getPrice()){
                            list.getChildren().add(Car.array.get(i).getContainer());
                        }
                    }
                    else{
                        if(Integer.parseInt(search.getText()) == Car.array.get(i).getDate()){
                            list.getChildren().add(Car.array.get(i).getContainer());
                        }
                    }
                }
                else if (Car.array.get(i).getName().toLowerCase().contains(search.getText().toLowerCase()) ) {
                    list.getChildren().add(Car.array.get(i).getContainer());
                }
            }

        }
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

	public static AdminPage getInstance() {
        if(instance == null)
            instance = new AdminPage();
		return instance;
    }
}