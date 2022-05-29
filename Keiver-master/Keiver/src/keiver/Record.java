package keiver;

public class Record {
	private String title;
	private String userName;
	private String email;
	private String password;
	private String app;
	private String description;
	private boolean favorite;

	public Record(String title, String userName, String email, String password, String app, String description,
			boolean favorite) {
		this.title = title;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.app = app;
		this.description = description;
		this.favorite = favorite;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isFavorite() {
		return favorite;
	}

	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}
}
