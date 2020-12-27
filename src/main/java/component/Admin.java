package main.java.component;

public class Admin extends User {

	public static Admin admin = new Admin("admin", "admin@admin.com", "admin");

	Admin(String fullName, String email, String password) {
		super(fullName, email, password);
	}

	public void add(Car car) {
		Car.array.add(car);
	}

	public void delete(Car car) {
		Car.array.remove(car);
	}

	public void edit(int ID, Car car) {
		Car.array.set(ID - 1, car);

	}
}