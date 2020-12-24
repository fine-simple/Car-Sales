package main.java.component;

//Omar Khaled
public abstract class Vehicle {
	private String name,model,color;
	private int date,price;

	Vehicle(String name,String model ,int date ,int price,String color)
    {
        this.name=name;
        this.model=model;
        this.date=date;
        this.price=price;
        this.color=color;
    }
    Vehicle(String name,String model ,int date ,int price)
    {
        this(name,model,date,price,"Not Defined");
    }
}
