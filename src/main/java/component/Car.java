package main.java.component;

import java.util.ArrayList;

public class Car {
	private String name, model, color;
	private int date, price;
	public static ArrayList<Car> cars;

	public Car(String name, String model, int date, int price, String color) {
		this.name = name;
		this.model = model;
		this.color = color;
		this.date = date;
		this.price = price;

	}

	public Car(String name, String model, int date, int price) {
		this(name, model, date, price, "Not Defined");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
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
	
}
