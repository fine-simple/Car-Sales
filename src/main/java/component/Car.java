package main.java.component;

public class Car {
	private String name, model, color;
	private int date, price;

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
}
