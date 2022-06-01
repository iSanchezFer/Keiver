package keiver;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class Record {
	private StringProperty title;
	private StringProperty username;
	private StringProperty email;
	private StringProperty password;
	private StringProperty app;
	private StringProperty description;
	private boolean favorite;

	public Record() {

	}

	public Record(String title, String username, String email, String password, String app, String description,
			boolean favorite) {
		this.title = new SimpleStringProperty(title);
		this.username = new SimpleStringProperty(username);
		this.email = new SimpleStringProperty(email);
		this.password = new SimpleStringProperty(password);
		this.app = new SimpleStringProperty(app);
		this.description = new SimpleStringProperty(description);
		this.favorite = favorite;
	}

	public boolean isFavorite() {
		return favorite;
	}

	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}

	public String getTitle() {
		return title.get();
	}

	public StringProperty titleProperty() {
		return title;
	}

	public void setTitle(String title) {
		this.title.set(title);
	}

	public String getUsername() {
		return username.get();
	}

	public StringProperty usernameProperty() {
		return username;
	}

	public void setUsername(String username) {
		this.username.set(username);
	}

	public String getEmail() {
		return email.get();
	}

	public StringProperty emailProperty() {
		return email;
	}

	public void setEmail(String email) {
		this.email.set(email);
	}

	public String getPassword() {
		return password.get();
	}

	public StringProperty passwordProperty() {
		return password;
	}

	public void setPassword(String password) {
		this.password.set(password);
	}

	public String getApp() {
		return app.get();
	}

	public StringProperty appProperty() {
		return app;
	}

	public void setApp(String app) {
		this.app.set(app);
	}

	public String getDescription() {
		return description.get();
	}

	public StringProperty descriptionProperty() {
		return description;
	}

	public void setDescription(String description) {
		this.description.set(description);
	}

}