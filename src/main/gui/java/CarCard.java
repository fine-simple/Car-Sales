package main.gui.java;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public abstract class CarCard {
    protected final Label name = new Label(), details = new Label();

	protected final Font heading1 = new Font("System Bold", 20.0), heading2 = new Font("System Bold", 14.0);
    
    protected final VBox detailsBox = new VBox(name, details);
    
    protected final ImageView pic = new ImageView();
    
    protected final Button buy = new Button("Buy");
    
    protected final HBox container = new HBox(pic, detailsBox, buy);
    
    private boolean selected = false;

    public CarCard(String name,String model, int price, String color , int id, String imagePath) {
        //set left margin to 15 for each element
		detailsBox.setPrefSize(260, 100);
		
		//set Font size
		this.name.setFont(heading1);
		details.setFont(heading2);
		details.setText("ID : " + id);
		details.setText(details.getText() + '\n' + "Model : " + model);
		details.setText(details.getText() + '\n' + "Color : " + color);
		details.setText(details.getText() + '\n' + "Price : " + String.format("%,d", price) + " EGP");
		
        //Car Picture
        this.pic.setImage(new Image(imagePath, 150, 100 ,false, false));
        //Container
		container.setPadding(new Insets(5,5,5,5));
		container.setSpacing(10);
		container.setAlignment(Pos.CENTER);
        container.setOnMouseEntered(e -> setColorEntered());
        container.setOnMouseExited(e -> setColorExited());
        container.setOnMouseClicked(e -> {
            setColorSelected();
        });

		//Button
        buy.setPrefSize(80, 40);
        buy.setOnAction(e -> buy());
    }

    protected abstract void buy();

    private void setColorSelected() {
        if(!selected) {
            container.setBackground(new Background(new BackgroundFill(Color.rgb(46, 49, 51), CornerRadii.EMPTY, Insets.EMPTY)));
            selected = true;
        }
        else {
            selected=false;
            setColorEntered();
        }
    }

    protected void setColorEntered() {
        if(!selected)
            container.setBackground(new Background(new BackgroundFill(Color.rgb(85, 92, 97), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    protected void setColorExited() {
        if(!selected)
            container.setBackground(Background.EMPTY);
    }

    public boolean isSelected() {
        return selected;
    }

    public void remove() {
        VBox parent = (VBox) container.getParent();
		parent.getChildren().remove(container);
    }

	public HBox getContainer() {
		return container;
    }
    
}