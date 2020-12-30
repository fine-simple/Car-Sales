package main.java.component;

public class Car {
	
	private int year, price;
	private String company, model, color;

	public Car(String company, String model, int year, int price, String color) {
		this.company = company;
		this.model = model;
		this.color = color;
		this.year = year;
		this.price = price;
	}

	public Car(String company, String model, int year, int price) {
		this(company, model, year, price , "Not Defined");
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
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

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
}