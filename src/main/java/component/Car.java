package main.java.component;

import java.util.ArrayList;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.gui.java.CarCard;

public class Car extends CarCard{
	
	private int date, price;
	private String model, color;

	public static ArrayList<Car> array = new ArrayList<>();

	public Car(String name, String model, int date, int price,String imagePath, String color) {
		super(name, model, price, color, array.toArray().length +1, imagePath);
		this.name.setText(name);
		this.model = model;
		this.color = color;
		this.date = date;
		this.price = price;
	}

	public Car(String name, String model, int date, int price, String imagePath) {
		this(name, model, date, price, imagePath , "Not Defined");
	}

	public Car(String name, String model, int date, int price) {
		this(name, model, date, price, "main/gui/assets/no-image.gif");
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

	public HBox getContainer() {
		return container;
	}

	public void setPrice(int price) {
		this.price = price;
	}


	
	protected void buy() {
		VBox parent = (VBox) container.getParent();
		parent.getChildren().remove(container);
		array.remove(this);
	}
}