package Keiver;

import java.sql.*;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.geometry.*;

public class MainKeiver extends Application {
	private Button btLogin = new Button("Login");
	private Button btRegister = new Button("Register Free");

	public void start(Stage stage) {

		// Creating title name of the application
		Text keiver = new Text("Keiver");
		keiver.setId("keiver");

		// Creating label email and password
		Text email = new Text("Email");
		email.setId("email");
		Text password = new Text("Password");
		password.setId("password");

		// Creating Text Filed for email
		TextField fieldEmail = new TextField();

		// Creating Password Filed for password
		PasswordField fieldPassword = new PasswordField();

		//Creating a line
		Line line = new Line(50, 286, 350, 286);
		
		// Creating a Grid Pane
		GridPane gridPane = new GridPane();

		// Setting size for the pane
		gridPane.setMinSize(400, 400);

		// Setting the padding
		gridPane.setPadding(new Insets(10, 10, 10, 10));

		// Setting the vertical and horizontal gaps between the columns
		gridPane.setVgap(5);
		gridPane.setHgap(5);

		// Setting the Grid alignment
		gridPane.setAlignment(Pos.CENTER);

		// Arranging all the nodes in the grid
		gridPane.add(keiver, 1, 0);
		gridPane.add(email, 0, 7);
		gridPane.add(fieldEmail, 1, 7);
		gridPane.add(password, 0, 9);
		gridPane.add(fieldPassword, 1, 9);
		gridPane.add(btLogin, 1, 15);
		gridPane.add(btRegister, 1, 20);
		

		// Creating a scene object
		Group root = new Group(gridPane,line);
		Scene scene = new Scene(root);
		scene.getStylesheets().add("/styles/style.css");

		// Setting title to the Stage
		stage.setTitle("Keiver - Key Saver");

		// Adding scene to the stage
		stage.setScene(scene);

		// Displaying the contents of the stage
		stage.show();
	}

	public static void main(String args[]) {
		launch(args);
	}

}
