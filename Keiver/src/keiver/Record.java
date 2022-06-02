package keiver;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * 
 * @author isanchez
 * @author croldan
 * @version 1.0 Fase Beta
 *
 */
public class Record {
	// Variables
	private StringProperty title;
	private StringProperty username;
	private StringProperty email;
	private StringProperty password;
	private StringProperty app;
	private StringProperty description;
	private boolean favorite;

	/**
	 * Contructor vacio de record
	 */
	public Record() {

	}

	/**
	 * Contructor completo de record
	 * 
	 * @param title       (titulo del registro)
	 * @param username    (usuario del registro)
	 * @param email       (email del registro)
	 * @param password    (contraseña del registro)
	 * @param app         (aplicacion del registro)
	 * @param description (descripcion del registro)
	 * @param favorite    (si es o no favorito el registro)
	 */
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

	/**
	 * Getter de si es favorito
	 * 
	 * @return (devuelve si es o no favorito)
	 */
	public boolean isFavorite() {
		return favorite;
	}

	/**
	 * Setter de favorito
	 * 
	 * @param favorite (si es favorito o no)
	 */
	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}

	/**
	 * Getter del titulo
	 * 
	 * @return (titulo del registro)
	 */
	public String getTitle() {
		return title.get();
	}

	/**
	 * Getter especial del titulo
	 * 
	 * @return (titulo del registro)
	 */
	public StringProperty titleProperty() {
		return title;
	}

	/**
	 * Setter del titulo
	 * 
	 * @param title (titulo del registro)
	 */
	public void setTitle(String title) {
		this.title.set(title);
	}

	/**
	 * Getter del usuario
	 * 
	 * @return (usuario del registro)
	 */
	public String getUsername() {
		return username.get();
	}

	/**
	 * Getter especial del usuario
	 * 
	 * @return (usuario del registro)
	 */
	public StringProperty usernameProperty() {
		return username;
	}

	/**
	 * Setter del usuario
	 * 
	 * @param username (usuario del titulo)
	 */
	public void setUsername(String username) {
		this.username.set(username);
	}

	/**
	 * Getter del email
	 * 
	 * @return (email del registro)
	 */
	public String getEmail() {
		return email.get();
	}

	/**
	 * Getter especial del email
	 * 
	 * @return (email del registro)
	 */
	public StringProperty emailProperty() {
		return email;
	}

	/**
	 * Setter del email
	 * 
	 * @param email (email del registro)
	 */
	public void setEmail(String email) {
		this.email.set(email);
	}

	/**
	 * Getter de la contraseña
	 * 
	 * @return (contraseña del registro)
	 */
	public String getPassword() {
		return password.get();
	}

	/**
	 * Getter especial de la contraseña
	 * 
	 * @return (contraseña del registro)
	 */
	public StringProperty passwordProperty() {
		return password;
	}

	/**
	 * Setter de la contraseña
	 * 
	 * @param password (contraseña del registro)
	 */
	public void setPassword(String password) {
		this.password.set(password);
	}

	/**
	 * Getter de la aplicacion
	 * 
	 * @return (aplicacion del registro)
	 */
	public String getApp() {
		return app.get();
	}

	/**
	 * Getter especial de la aplicacion
	 * 
	 * @return (aplicacion del registro)
	 */
	public StringProperty appProperty() {
		return app;
	}

	/**
	 * Setter de la aplicacion
	 * 
	 * @param app (aplicacion del registro)
	 */
	public void setApp(String app) {
		this.app.set(app);
	}

	/**
	 * Getter de la descripcion
	 * 
	 * @return (descripcion del registro)
	 */
	public String getDescription() {
		return description.get();
	}

	/**
	 * Getter especial de la descripcion
	 * 
	 * @return (descripcion del registro)
	 */
	public StringProperty descriptionProperty() {
		return description;
	}

	/**
	 * Setter de la descripcion
	 * 
	 * @param description (descripcion del registroºº)
	 */
	public void setDescription(String description) {
		this.description.set(description);
	}

}