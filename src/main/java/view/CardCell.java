package main.java.view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class CardCell {
    @FXML
    private HBox container;
    @FXML
    private Label heading;
    @FXML
    private Label details;
    @FXML
    private Button action;
    @FXML
    private ImageView pic;

    public CardCell() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../gui/fxml/card_cell.fxml"));
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Button getAction() {
        return action;
    }

    public Label getDetails() {
        return details;
    }

    public Label getHeading() {
        return heading;
    }

    public ImageView getPic() {
        return pic;
    }

    public HBox getContainer() {
        return container;
    }
}