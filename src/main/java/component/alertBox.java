package main.java.component;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class alertBox {
    public static void display(String purchased){
        Stage window=new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Purchased");
        Label l1=new Label("Congratulations for thr new car.");
        window.setMaxWidth(250);
        Button button1= new Button();
        button1.setText("Hope seeing you again");
        button1.setOnAction(e -> window.close());
        StackPane layout = new StackPane();
        Scene alertScene=new Scene(layout);
        window.show();
    }
}
