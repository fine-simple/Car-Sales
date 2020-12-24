package main.java.component;

public class Admin extends User {
	Admin(String FN, String E, String PW) {
		super(FN, E, PW);
	}

	public void add(Car car) {
		Car.cars.add(car);
	}

	public void delete(Car car) {
		Car.cars.remove(car);
	}

	public void edit(int ID, Car car) {
		Car.cars.set(ID - 1, car);

	}
}
