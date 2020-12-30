package main.java.component;

public class Admin extends User {

	public static Admin admin = new Admin("admin", "admin@admin.com", "admin");

	Admin(String fullName, String email, String password) {
		super(fullName, email, password);
	}
}