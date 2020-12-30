package main.gui.java;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import main.java.component.Cars;

public class CarCard extends Car {
    private final Label company = new Label(), details = new Label();

	private final Font heading1 = new Font("System Bold", 20.0), heading2 = new Font("System Bold", 14.0);
    
    private final VBox detailsBox = new VBox(company, details);
    
    private final ImageView pic = new ImageView();
        
    private final HBox container = new HBox(pic, detailsBox);
    
    private boolean selected = false;

    private void setGUI(String imagePath) {
         //set left margin to 15 for each element
		detailsBox.setPrefSize(260, 100);
		
		//set Font size
        this.company.setFont(heading1);
        this.company.setText(super.getCompany());
		details.setFont(heading2);
		details.setText("ID : " + (Cars.getArray().size() + 1));
		details.setText(details.getText() + '\n' + "Model : " + super.getModel());
		details.setText(details.getText() + '\n' + "Color : " + super.getColor());
		details.setText(details.getText() + '\n' + "Price : " + String.format("%,d", super.getPrice()) + " EGP");
		
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

    public CarCard(String company, String model, int year, int price, String imagePath, String color) {
        super(company, model, year, price, color);
        setGUI(imagePath);
    }

    public CarCard(String company, String model, int year, int price, String imagePath) {
        super(company, model, year, price);
        setGUI(imagePath);
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
        Cars.getArray().remove(this);
        VBox parent = (VBox) container.getParent();
		parent.getChildren().remove(container);
    }

    public int getIndex() {
        return Cars.getArray().indexOf(this);
    }
    
	public HBox getContainer() {
		return container;
    }

}