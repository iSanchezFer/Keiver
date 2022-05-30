package keiver;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SceneController {

	public Label login_label;

	public TextField username_login;

	public PasswordField password_login;

	public TextField username_register;

	public TextField email_register;

	public PasswordField password_register1;

	public PasswordField password_register2;
	private Stage stage;
	private Scene scene;

	public void login() {
		ConnectionBBDD databaseConnection = new ConnectionBBDD();
		Connection connectDB = databaseConnection.getConnection();
		String sql = "SELECT count(*) FROM user WHERE username_acc = '" + username_login.getText()
				+ "' AND password_acc = '" + password_login.getText() + "'";
		System.out.println("TOT OK");
		try {
			Statement myStatement = connectDB.createStatement();

			ResultSet resultSet = myStatement.executeQuery(sql);
			while (resultSet.next()) {
				if (resultSet.getInt(1) == 1) {
					login_label.setText("Welcome");
				} else {
					login_label.setText("Tas equivocao");
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public void loginButtonOnAction(ActionEvent e) {

		if (username_login.getText().isBlank() == false && password_login.getText().isBlank() == false) {

			login();
			try {
				switchToTableView(e);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else {
			login_label.setText("Please enter your credentials");
		}
	}

	public void switchToTableView(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Table.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
	}

	public void switchToRegistration(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Registration.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void switchToLogin(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void switchToDeleteRecords(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DeleteRecord.fxml"));
		Parent root = fxmlLoader.load();
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setResizable(false);
		stage.setOpacity(1);
		stage.setTitle("Keiver - Delete Record");
		stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
		stage.setScene(new Scene(root, 600, 450));
		stage.showAndWait();
	}

	public void switchToAddRecords(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreateRecord.fxml"));
		Parent root = fxmlLoader.load();
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setResizable(false);
		stage.setOpacity(1);
		stage.setTitle("Keiver - Create Record");
		stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
		stage.setScene(new Scene(root, 1000, 450));
		stage.showAndWait();
	}

	public void switchToUserAccount(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ModifyUser.fxml"));
		Parent root = fxmlLoader.load();
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setResizable(false);
		stage.setOpacity(1);
		stage.setTitle("Keiver - User Account");
		stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
		stage.setScene(new Scene(root, 600, 400));
		stage.showAndWait();
	}

	public void generatePassword(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Generator.fxml"));
		Parent root = fxmlLoader.load();
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setResizable(false);
		stage.setOpacity(1);
		stage.setTitle("Keiver - Generate Password");
		stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
		stage.setScene(new Scene(root, 465, 477));
		stage.showAndWait();

	}

}
