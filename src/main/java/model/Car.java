package main.java.model;

import main.java.view.Cardable;

public class Car implements Cardable {

	private int year, price;
	private long id;
	private String company, model, color, imagePath;

	public Car(String company, String model, int year, int price, String color) {
		this.company = company;
		this.model = model;
		this.color = color;
		this.year = year;
		this.price = price;
	}

	public Car(String company, String model, int year, int price, String color, String imagePath) {
		this(company, model, year, price, color);
		this.imagePath = imagePath;
	}

	public Car(String company, String model, int year, int price) {
		this(company, model, year, price, "Not Defined");
	}

	public Car() {
		id = 0;
		company = "NULL";
		model = "NULL";
		color = "NULL";
		year = 0;
		price = 0;
		imagePath = "main/gui/assets/no-image.gif";
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	@Override
	public String getPicPath() {
		return imagePath;
	}

	@Override
	public String getHeader1() {
		return company;
	}

	@Override
	public String getDetails() {
		String pairs = "";
		pairs += "Model: " + model;
		pairs += "\nColor: " + color;
		pairs += "\nYear: " + String.valueOf(year);
		pairs += "\nPrice: " + String.format("%,d EGP", price);
		return pairs;
	}
}