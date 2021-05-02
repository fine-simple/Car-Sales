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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;
import main.java.Database.H2;
import main.java.model.Car;
import main.java.model.User;
import main.java.view.CardView;
import main.java.view.Cardable;

public class Marketplace implements Initializable, Controller {

    private static Marketplace instance = null;
    String actionText = "Buy";

    @FXML
    TextField searchTextBox;

    @FXML
    ListView<Cardable> list;

    private static User activeUser;

    public static User getActiveUser() {
        return activeUser;
    }

    public static void setActiveUser(User activeUser) {
        Marketplace.activeUser = activeUser;
    }

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
        // Add data to listview from database
        list.getItems().setAll(H2.getInstance().carOperations().getAvailable());

        initializeCellFactory();
    }

    void initializeCellFactory() {
        // Set GUI of each car in the list
        list.setCellFactory(new Callback<ListView<Cardable>, ListCell<Cardable>>() {
            @Override
            public ListCell<Cardable> call(ListView<Cardable> arg0) {
                CardView cardView = new CardView(actionText);
                cardView.setOnAction(e -> {
                    action(e, cardView);
                });
                return cardView;
            }
        });
    }

    void action(Event e, CardView cardView) {
        Alert confirmBox = new Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION,
                "Are you sure you want to buy this car?");
        if (confirmBox.showAndWait().get() == ButtonType.OK) {
            H2.getInstance().carOperations().buy((Car) cardView.getItem(), activeUser);
            list.getItems().remove(cardView.getIndex());

            new Alert(AlertType.INFORMATION, "Car Bought Successfully").show();
        }
    }

    @FXML
    private void search(Event e) {
        if (searchTextBox.getText().strip().isBlank()) {
            list.getItems().setAll(H2.getInstance().carOperations().getAvailable());
        } else {
            list.getItems().setAll(H2.getInstance().carOperations().search(searchTextBox.getText()));
        }
    }

    @FXML
    private void logout(Event e) {
        activeUser = null;
        Login.getInstance().loadScene(e);
    }

    public static Marketplace getInstance() {
        if (instance == null)
            instance = new Marketplace();
        return instance;
    }
}