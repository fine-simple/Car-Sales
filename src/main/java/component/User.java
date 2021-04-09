package main.java.component;

public class User {
	private static User activeUser;
	
	private long id;
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
		this.id = id;
	}

	public User(long id){
		this.id = id;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public static User getActiveUser() {
		return activeUser;
	}

	public static void setActiveUser(User activeUser) {
		User.activeUser = activeUser;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

}
