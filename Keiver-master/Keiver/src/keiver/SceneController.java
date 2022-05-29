package keiver;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SceneController {
	public TextField userName;
	public PasswordField password;
	private Stage stage;
	private Scene scene;

	public void login(ActionEvent actionEvent) {
		ConnectionBBDD myConnectionClass = new ConnectionBBDD();
		Connection connection = myConnectionClass.getConnection();
		try {
			Statement statement = connection.createStatement();
			String sql = "SELECT FROM user WHERE username = '" + userName.getText() + "'AND password = '"
					+ password.getText() + "'";
			ResultSet resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {

			}
		} catch (SQLException e) {
			e.printStackTrace();
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
