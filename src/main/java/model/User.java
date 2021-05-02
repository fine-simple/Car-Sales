package main.java.model;

public class User {
	private long ssn;
	private String firstName, lastName;
	private String email;
	private String password;

	private boolean isAdmin;

	public User(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public User(long id, String firstName, String lastName, String email, String password) {
		this(firstName, lastName, email, password);
		this.ssn = id;
	}

	public User() {
		ssn = 0;
		firstName = "";
		lastName = "";
		email = "";
		password = "";
		isAdmin = false;
	}

	public User(long ssn) {
		this.ssn = ssn;
	}

	public User(long id, String firstName, String lastName, String email, String password, boolean isAdmin) {
		this(firstName, lastName, email, password);
		this.isAdmin = isAdmin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public long getSsn() {
		return ssn;
	}

	public void setSsn(long ssn) {
		this.ssn = ssn;
	}

}
