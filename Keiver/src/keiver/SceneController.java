package keiver;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.BooleanStringConverter;

public class SceneController {
	public Button registerButton, modifyPassword;
	public Label login_label;

	public Label register_label, changepass_label;

	public TextField username_login;

	public PasswordField password_login;

	public TextField username_register;

	public TextField email_register;

	private static String username_loginbbdd = "";
	private static String password_loginbbdd = "";

	public PasswordField password_register1;

	public PasswordField password_register2;
	private static Stage stage;
	private static Scene scene;

	public void loginButtonOnAction(ActionEvent e) {

		if (username_login.getText().isBlank() == false && password_login.getText().isBlank() == false) {
			if (login() == true) {
				try {
					switchToTableView(e);
				} catch (IOException e1) {
					Logs.appendToFile(e1);
					e1.printStackTrace();
				}
			}

		} else {
			login_label.setText("Please enter your credentials");
		}
	}

	public boolean login() {
		ConnectionBBDD databaseConnection = new ConnectionBBDD();
		Connection connectionDB = databaseConnection.getConnection();
		username_loginbbdd = username_login.getText();
		password_loginbbdd = password_login.getText();
		try {
			PreparedStatement pst = connectionDB
					.prepareStatement("SELECT count(*) FROM user WHERE username_acc = ? AND password_acc =  ? ");
			pst.setString(1, username_loginbbdd);
			pst.setString(2, password_loginbbdd);
			ResultSet resultSet = pst.executeQuery();
			while (resultSet.next()) {
				if (resultSet.getInt(1) == 1) {
					login_label.setText("Welcome");
					return true;
				} else {
					login_label.setText("Wrong credentials");
					username_login.setText("");
					password_login.setText("");
					username_login.requestFocus();
					return false;
				}
			}
		} catch (SQLException e) {
			Logs.appendToFile(e);
			System.out.println(e.getMessage());
			System.out.println("Cause" + e.getCause());
		}
		return false;

	}

	public void registerButtonOnAction(ActionEvent e) {
		boolean credentials = false;
		boolean checkUser = false;
		boolean checkEmail = false;
		boolean checkPassword = false;

		if (username_register.getText().isBlank() == false && email_register.getText().isBlank() == false
				&& password_register1.getText().isBlank() == false && password_register2.getText().isBlank() == false) {
			String passwordRegister = password_register1.getText();
			String confirmPassword = password_register2.getText();
			checkUser = checkUser();
			checkEmail = checkEmail();
			checkPassword = checkPassword(passwordRegister, confirmPassword);

			if (checkPassword && checkEmail && checkUser) {
				credentials = true;
				registerUser();
			} else if (!checkEmail || !checkUser) {
				register_label.setText("The credentails are already used.");
			}

			if (credentials) {
				Stage stage = (Stage) registerButton.getScene().getWindow();
				stage.close();
			}

		} else {
			login_label.setText("Enter your credentials");
		}
	}

	public PasswordField new_password;
	public PasswordField confirm_newpass;
	public PasswordField current_password;

	public void changeUserPass(ActionEvent e) {
		boolean credentials = false;
		boolean checkPassword = false;

		if (current_password.getText().isBlank() == false && new_password.getText().isBlank() == false
				&& confirm_newpass.getText().isBlank() == false) {
			String currentPass = current_password.getText();
			String newpassword = new_password.getText();
			String confirmnewpass = confirm_newpass.getText();

			checkPassword = checkPassword(newpassword, confirmnewpass);
			if ((checkPassword == true) && (currentPass.equals(password_loginbbdd))
					&& !newpassword.equals(password_loginbbdd)) {
				credentials = true;
				updateUser();

			} else {
				changepass_label.setText("Wrong credentials");
			}
			if (credentials) {
				Stage stage = (Stage) modifyPassword.getScene().getWindow();
				stage.close();
			}

		} else {
			changepass_label.setText("Fill all the fields ");
		}
	}

	private void updateUser() {
		ConnectionBBDD databaseConnection = new ConnectionBBDD();
		Connection connectionDB = databaseConnection.getConnection();
		String newpassword = new_password.getText();

		try {
			PreparedStatement pst = connectionDB
					.prepareStatement("UPDATE user set password_acc = ? WHERE idUser_acc = ? ");
			pst.setString(1, newpassword);
			pst.setInt(2, getIdUserAccount());
			pst.executeUpdate();
			password_loginbbdd = newpassword;

		} catch (SQLException e) {
			Logs.appendToFile(e);
			System.out.println(e.getMessage());
			System.out.println("Cause" + e.getCause());
		}

	}

	private boolean checkUser() {
		ConnectionBBDD databaseConnection = new ConnectionBBDD();
		Connection connectionDB = databaseConnection.getConnection();
		try {
			Statement usercheck = connectionDB.createStatement();
			ResultSet rs = usercheck
					.executeQuery("SELECT * FROM user WHERE username_acc = '" + username_register.getText() + "' ");
			if (rs.next()) {
				return false;
			}
		} catch (SQLException e) {
			Logs.appendToFile(e);
			e.printStackTrace();
		}
		return true;

	}

	private boolean checkEmail() {
		ConnectionBBDD databaseConnection = new ConnectionBBDD();
		Connection connectionDB = databaseConnection.getConnection();
		try {
			Statement emailcheck = connectionDB.createStatement();
			ResultSet rs = emailcheck
					.executeQuery("SELECT * FROM user WHERE email_acc = '" + email_register.getText() + "' ");
			if (rs.next()) {
				return false;
			}
		} catch (SQLException e) {
			Logs.appendToFile(e);
			e.printStackTrace();
		}

		return true;

	}

	private boolean checkPassword(String password, String confirmPassword) {

		if (password.equals(confirmPassword)) {
			return true;
		} else {
			return false;
		}

	}

	private void registerUser() {
		ConnectionBBDD databaseConnection = new ConnectionBBDD();
		Connection connectionDB = databaseConnection.getConnection();
		String username = username_register.getText();
		String email = email_register.getText();
		String password = password_register1.getText();

		String insertFields = "INSERT INTO user (username_acc, email_acc, password_acc) VALUES('";
		String insertValues = username + "','" + email + "','" + password + "')";
		String insertToRegister = insertFields + insertValues;

		try {

			Statement statement = connectionDB.createStatement();
			statement.executeUpdate(insertToRegister);
			register_label.setText("Usuario creado");

		} catch (SQLException e) {
			Logs.appendToFile(e);
			System.out.println(e.getMessage());
			System.out.println("Cause" + e.getCause());
		}
	}

	public void switchToRegistration(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Registration.fxml"));
		Parent root = fxmlLoader.load();
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setResizable(false);
		stage.setOpacity(1);
		stage.setTitle("Keiver - User Account");
		stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
		stage.setScene(new Scene(root, 463, 469));
		stage.showAndWait();
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

	public void switchToModifyRecords(ActionEvent event) throws IOException {
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

	public static final String MainStyle = "keiver/style.css";

	public void switchToTableView(ActionEvent event) throws IOException {
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		BorderPane border = new BorderPane();
		HBox hbox1 = new HBox();
		HBox hbox2 = new HBox();
		HBox hbox3 = new HBox();
		Button addRecords = new Button("Add Records");
		addRecords.setId("addRecords");
		Button applyChanges = new Button("Modify Records");
		applyChanges.setId("applyChanges");
		Button modifyUser = new Button("Modify User");
		modifyUser.setId("modifyUser");
		Button deleteRecord = new Button("Delete Records");
		deleteRecord.setId("deleteRecord");
		border.setLeft(hbox1);
		border.setCenter(hbox2);
		border.setRight(hbox3);
		border.setBottom(myTable);
		hbox1.getChildren().addAll(addRecords);
		hbox2.getChildren().addAll(applyChanges, modifyUser);
		hbox3.getChildren().addAll(deleteRecord);
		scene = new Scene(border);
		scene.getStylesheets().add(MainStyle);
		addRecords.prefWidth(25);
		applyChanges.prefWidth(25);
		deleteRecord.prefWidth(25);
		modifyUser.prefWidth(25);
		modifyUser.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					switchToUserAccount(event);
				} catch (IOException e) {
					Logs.appendToFile(e);
					e.printStackTrace();
				}

			}
		});
		addRecords.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					switchToAddRecords(event);
					switchToTableView(event);
				} catch (IOException e) {
					Logs.appendToFile(e);
					e.printStackTrace();
				}

			}
		});
		applyChanges.setOnAction(new EventHandler<ActionEvent>() {
			Record changes = myTable.getSelectionModel().getSelectedItem();

			@Override
			public void handle(ActionEvent event) {
				try {
					switchToModifyRecords(event);
				} catch (IOException e) {
					Logs.appendToFile(e);
					e.printStackTrace();
				}

			}
		});
		deleteRecord.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {

					switchToDeleteRecords(event);
				} catch (IOException e) {
					Logs.appendToFile(e);
					e.printStackTrace();
				}

			}
		});
		stage.setWidth(736);
		stage.setResizable(false);
		stage.setScene(scene);
		showTable();
		stage.show();
		Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
		double x = bounds.getMinX() + (bounds.getWidth() - scene.getWidth()) * 0.5;
		double y = bounds.getMinY() + (bounds.getHeight() - scene.getHeight()) * 0.5;
		stage.setX(x);
		stage.setY(y);

	}

	private TableView<Record> myTable = new TableView<Record>();
	static Record tmp;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void showTable() {
		ObservableList<Record> data = FXCollections.observableArrayList();
		try {
			ConnectionBBDD databaseConnection = new ConnectionBBDD();
			Connection myConnection = databaseConnection.getConnection();
			Statement myStatement = myConnection.createStatement();
			ResultSet rs = myStatement.executeQuery("select * from record where idUser = " + getIdUserAccount());
			while (rs.next()) {
				Record rec = new Record(rs.getString("title"), rs.getString("username"), rs.getString("email"),
						rs.getString("password"), rs.getString("app"), rs.getString("description"),
						rs.getBoolean("favorite"));
				tmp = new Record(rs.getString("title"), rs.getString("username"), rs.getString("email"),
						rs.getString("password"), rs.getString("app"), rs.getString("description"),
						rs.getBoolean("favorite"));
				data.add(rec);
			}

			TableColumn title_column = new TableColumn("Title");
			TableColumn username_column = new TableColumn("Username");
			TableColumn email_column = new TableColumn("Email");
			TableColumn password_column = new TableColumn("Password");
			TableColumn app_column = new TableColumn("App");
			TableColumn description_column = new TableColumn("Description");
			TableColumn favorite_column = new TableColumn("Favorite");
			TableColumn edit_column = new TableColumn("Edit");
			myTable.setEditable(true);

			title_column.setCellValueFactory(new PropertyValueFactory<Record, String>("title"));
			title_column.setCellFactory(TextFieldTableCell.forTableColumn());
			title_column.setOnEditCommit(new EventHandler<CellEditEvent<Record, String>>() {

				@Override
				public void handle(CellEditEvent<Record, String> event) {
					Record record = event.getRowValue();
					record.setTitle(event.getNewValue());

				}
			});

			username_column.setCellValueFactory(new PropertyValueFactory<Record, String>("username"));
			username_column.setCellFactory(TextFieldTableCell.forTableColumn());
			username_column.setOnEditCommit(new EventHandler<CellEditEvent<Record, String>>() {

				@Override
				public void handle(CellEditEvent<Record, String> event) {
					Record record = event.getRowValue();
					record.setUsername(event.getNewValue());

				}
			});

			email_column.setCellValueFactory(new PropertyValueFactory<Record, String>("email"));
			email_column.setCellFactory(TextFieldTableCell.forTableColumn());
			email_column.setOnEditCommit(new EventHandler<CellEditEvent<Record, String>>() {

				@Override
				public void handle(CellEditEvent<Record, String> event) {
					Record record = event.getRowValue();
					record.setEmail(event.getNewValue());

				}
			});

			password_column.setCellValueFactory(new PropertyValueFactory<Record, String>("password"));
			password_column.setCellFactory(TextFieldTableCell.forTableColumn());
			password_column.setOnEditCommit(new EventHandler<CellEditEvent<Record, String>>() {

				@Override
				public void handle(CellEditEvent<Record, String> event) {
					Record record = event.getRowValue();
					record.setPassword(event.getNewValue());

				}
			});

			app_column.setCellValueFactory(new PropertyValueFactory<Record, String>("app"));
			app_column.setCellFactory(TextFieldTableCell.forTableColumn());
			app_column.setOnEditCommit(new EventHandler<CellEditEvent<Record, String>>() {

				@Override
				public void handle(CellEditEvent<Record, String> event) {
					Record record = event.getRowValue();
					record.setApp(event.getNewValue());

				}
			});

			description_column.setCellValueFactory(new PropertyValueFactory<Record, String>("description"));
			description_column.setCellFactory(TextFieldTableCell.forTableColumn());
			description_column.setOnEditCommit(new EventHandler<CellEditEvent<Record, String>>() {

				@Override
				public void handle(CellEditEvent<Record, String> event) {
					Record record = event.getRowValue();
					record.setDescription(event.getNewValue());

				}
			});

			favorite_column.setCellValueFactory(new PropertyValueFactory<Record, Boolean>("favorite"));
			favorite_column.setCellFactory(TextFieldTableCell.forTableColumn(new BooleanStringConverter()));
			favorite_column.setOnEditCommit(new EventHandler<CellEditEvent<Record, Boolean>>() {

				@Override
				public void handle(CellEditEvent<Record, Boolean> event) {
					Record record = event.getRowValue();
					record.setFavorite(event.getNewValue());

				}
			});
			Callback<TableColumn<Record, String>, TableCell<Record, String>> cellFactory = param -> {
				final TableCell<Record, String> cell = new TableCell<Record, String>() {
					@Override
					public void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);

						if (empty) {
							setGraphic(null);
							setText(null);
						} else {
							final Button editButton = new Button();
							ImageView imageview = new ImageView("keiver/edit.png");
							imageview.setFitHeight(15);
							imageview.setFitWidth(20);
							editButton.setGraphic(imageview);
							editButton.setPadding(Insets.EMPTY);
							editButton.setOnAction(event -> {
								Record rec = getTableView().getItems().get(getIndex());

							});

							setGraphic(editButton);
							setText(null);

						}

					}
				};
				cell.setAlignment(Pos.CENTER);
				return cell;
			};

			edit_column.setCellFactory(cellFactory);

			title_column.prefWidthProperty().bind(myTable.widthProperty().multiply(0.10));
			title_column.setResizable(false);
			username_column.prefWidthProperty().bind(myTable.widthProperty().multiply(0.10));
			username_column.setResizable(false);
			email_column.prefWidthProperty().bind(myTable.widthProperty().multiply(0.18));
			email_column.setResizable(false);
			password_column.prefWidthProperty().bind(myTable.widthProperty().multiply(0.16));
			password_column.setResizable(false);
			app_column.prefWidthProperty().bind(myTable.widthProperty().multiply(0.10));
			description_column.prefWidthProperty().bind(myTable.widthProperty().multiply(0.21));
			description_column.setResizable(false);
			favorite_column.prefWidthProperty().bind(myTable.widthProperty().multiply(0.08));
			favorite_column.setResizable(false);

			edit_column.prefWidthProperty().bind(myTable.widthProperty().multiply(0.06));
			edit_column.setResizable(false);

			myTable.getColumns().addAll(title_column, username_column, email_column, password_column, app_column,
					description_column, favorite_column, edit_column);
			myTable.setItems(data);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Not Working
	public void deleteRecords() {

		myTable.getItems().removeAll(myTable.getSelectionModel().getSelectedItem());
		try {
			ConnectionBBDD databaseConnection = new ConnectionBBDD();
			Connection connectionDB = databaseConnection.getConnection();
			PreparedStatement pst = connectionDB.prepareStatement(
					"DELETE FROM record WHERE user set password_acc = ? WHERE idUser = ? AND title = ? AND username = ? AND email = ? AND password = ? AND app = ? AND description = ? ");

			pst.setInt(1, getIdUserAccount());
			pst.setString(2, tmp.getTitle());
			pst.setString(3, tmp.getUsername());
			pst.setString(4, tmp.getEmail());
			pst.setString(5, tmp.getPassword());
			pst.setString(6, tmp.getApp());
			pst.setString(7, tmp.getDescription());
			pst.executeUpdate();
		} catch (SQLException e) {
			Logs.appendToFile(e);
		}
	}

	public void updateRecord(ActionEvent event) throws ClassNotFoundException, SQLException {
		// TODO
	}

	public int getIdUserAccount() {
		try {
			ConnectionBBDD databaseConnection = new ConnectionBBDD();
			Connection connectionDB = databaseConnection.getConnection();
			PreparedStatement pst;

			pst = connectionDB.prepareStatement("SELECT * FROM user WHERE username_acc = ? AND password_acc =  ? ");
			pst.setString(1, username_loginbbdd);
			pst.setString(2, password_loginbbdd);
			ResultSet getID = pst.executeQuery();
			if (getID.next()) {
				int idUseraccount = getID.getInt("idUser_acc");
				return idUseraccount;
			}
		} catch (SQLException e) {
			Logs.appendToFile(e);
			e.printStackTrace();
		}
		return -1;
	}

	@FXML
	public TextField app_record;

	@FXML
	public TextArea description_record;

	@FXML
	public TextField email_record;

	@FXML
	public PasswordField password_record;

	@FXML
	public Button saveRecord;

	@FXML
	public TextField title_record;

	@FXML
	public TextField username_record;

	@FXML
	void addRecords(ActionEvent event) {
		String title = title_record.getText();
		String username = username_record.getText();
		String email = email_record.getText();
		String password = password_record.getText();
		String app = app_record.getText();
		String description = description_record.getText();

		if (title.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty() || app.isEmpty()
				|| description.isEmpty()) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Please Fill All DATA");
			alert.showAndWait();

		} else {
			insert();
			Stage stage = (Stage) saveRecord.getScene().getWindow();
			stage.close();

		}

	}

	private void insert() {

		try {
			ConnectionBBDD databaseConnection = new ConnectionBBDD();
			Connection connectionDB = databaseConnection.getConnection();
			String queryToAddRecords = "INSERT INTO record( idUser, title, username, email, password, app, description, favorite) VALUES (?,?,?,?,?,?,?, 0)";
			PreparedStatement pst = connectionDB.prepareStatement(queryToAddRecords);
			pst.setInt(1, getIdUserAccount());
			pst.setString(2, title_record.getText());
			pst.setString(3, username_record.getText());
			pst.setString(4, email_record.getText());
			pst.setString(5, password_record.getText());
			pst.setString(6, app_record.getText());
			pst.setString(7, description_record.getText());
			pst.execute();
		} catch (SQLException ex) {
			Logs.appendToFile(ex);
		}

	}

}
