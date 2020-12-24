package main.java.component;

public class User {
	private String fullName;
	private String email;
	private String password;

	User(String FN, String E, String PW) {
		fullName = FN;
		email = E;
		password = PW;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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

}
