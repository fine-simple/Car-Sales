package main.java.component;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class Car {
	private final Label name = new Label(), details = new Label();
	private final Font heading1 = new Font("System Bold", 20.0), heading2 = new Font("System Bold", 14.0);
	private final VBox detailsBox = new VBox(name, details);
	private final ImageView pic = new ImageView();
	private final Button buy = new Button("Buy");
	public final HBox container = new HBox(pic, detailsBox, buy);
	private final Image image;
	
	private int date, price, id;
	private String model, color;

	public static ArrayList<Car> array = new ArrayList<>();
	
	
	private void setupGUI() {
		//set left margin to 15 for each element
		detailsBox.setPrefSize(260, 100);
		
		//set Font size
		name.setFont(heading1);
		details.setFont(heading2);
		details.setText("ID : " + id);
		details.setText(details.getText() + '\n' + "Model : " + model);
		details.setText(details.getText() + '\n' + "Color : " + color);
		details.setText(details.getText() + '\n' + "Price : " + price + " EGP");
		
		//car picture
		pic.setImage(this.image);
		pic.setFitWidth(150);
		pic.setFitHeight(100);

		//Container
		container.setPadding(new Insets(5,5,5,5));
		container.setSpacing(10);
		container.setAlignment(Pos.CENTER);
		//Button
		buy.setPrefSize(80, 40);
		buy.setOnAction(e -> buy());
	}

	public Car(String name, String model, int date, int price, String color) {
		this.name.setText(name);
		this.model = model;
		this.color = color;
		this.date = date;
		this.price = price;
		this.id = array.toArray().length + 1;
		this.image = new Image("main/gui/assets/Volvo_XC40_2018.jpg");
		setupGUI();
	}

	public Car(String name, String model, int date, int price) {
		this(name, model, date, price, "Not Defined");
	}

	public String getName() {
		return name.getText();
	}

	public void setName(String name) {
		this.name.setText(name);;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	private void buy() {
		VBox parent = (VBox) container.getParent();
		parent.getChildren().remove(container);
		array.remove(this);
	}
}