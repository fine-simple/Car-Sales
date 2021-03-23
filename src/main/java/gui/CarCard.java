package main.java.gui;

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
import main.java.component.Car;

public class CarCard extends Car {
    private final Label company = new Label(), details = new Label();

	private final Font heading1 = new Font("System Bold", 20.0), heading2 = new Font("System Bold", 14.0);
    
    private final VBox detailsBox = new VBox(company, details);
    
    private final ImageView pic = new ImageView();
    
    private final Button customBtn = new Button();

    private final HBox container = new HBox(pic, detailsBox, customBtn);
    
    private boolean selected = false;

    private void setGUI(String imagePath) {
         //set left margin to 15 for each element
		detailsBox.setPrefSize(260, 100);
		
		//set Font size
        this.company.setFont(heading1);
		details.setFont(heading2);
		updateDetails();
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
    }

    public void updateDetails() {
        this.company.setText(super.getCompany());
        details.setText("Model : " + super.getModel());
		details.setText(details.getText() + '\n' + "Color : " + super.getColor());
		details.setText(details.getText() + '\n' + "Price : " + String.format("%,d", super.getPrice()) + " EGP");
    }

    public CarCard(String company, String model, int year, int price, String color, String imagePath) {
        super(company, model, year, price, color);
        setGUI(imagePath);
    }

    public CarCard(String company, String model, int year, int price, String color) {
        super(company, model, year, price, color);
        setGUI("main/gui/assets/no-image.gif");
    }

    public CarCard(String company, String model, int year, int price) {
        super(company, model, year, price);
        setGUI("main/gui/assets/no-image.gif");
    }

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
        Car.getArray().remove(this);
        VBox parent = (VBox) container.getParent();
		parent.getChildren().remove(container);
    }

    public int getIndex() {
        return Car.getArray().indexOf(this);
    }
    
	public HBox getContainer() {
		return container;
    }

    public ImageView getPic() {
        return pic;
    }

    public Button getCustomBtn() {
        return customBtn;
    }

}