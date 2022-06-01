package keiver;

public class User {
	private String username_acc;
	private String email_acc;
	private String password_acc;

	public User(String username_acc, String email_acc, String password_acc) {
		this.username_acc = username_acc;
		this.email_acc = email_acc;
		this.password_acc = password_acc;
	}

	public String getUserName() {
		return username_acc;
	}

	public void setUserName(String userName) {
		this.username_acc = userName;
	}

	public String getEmail() {
		return email_acc;
	}

	public void setEmail(String email_acc) {
		this.email_acc = email_acc;
	}

	public String getPassword() {
		return password_acc;
	}

	public void setPassword(String password_acc) {
		this.password_acc = password_acc;
	}

}
