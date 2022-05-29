package keiver;

import java.sql.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 * @author isanchez
 *
 */
public class Test extends Application {
	private TextArea ta = new TextArea();
	private Button btShowJobs = new Button("Show Records");
	private ComboBoxBase<String> cboTableName = new ComboBox<>();
	private Statement stmt;

	@Override
	public void start(Stage primaryStage) {
// establish the database connection
		initializeDB();
// display the JOB Data
		btShowJobs.setOnAction(e -> showData());
		HBox hBox = new HBox(10);
		hBox.getChildren().addAll(new Label("Table Name"), cboTableName, btShowJobs);
		hBox.setAlignment(Pos.CENTER);
		BorderPane bpane = new BorderPane();
		bpane.setCenter(new ScrollPane(ta));
		bpane.setTop(hBox);
		Scene scene = new Scene(bpane, 420, 180);
		primaryStage.setTitle("Display JOB Information");
		primaryStage.setScene(scene);
		primaryStage.show();
	}// end method start

	private void initializeDB() {
		try {
// Add code that does the following
// Create a connection to your Oracle database using the orcluser account
			Connection myConnection = DriverManager.getConnection("jdbc:mysql://192.168.56.106:3306/Keiver", "test",
					"1234");
// Use the connection to create a statement
			stmt = myConnection.createStatement();
// Use the Database MetaData to generate a resultSet based on tables that
// contain the word job
			DatabaseMetaData myDBMD = myConnection.getMetaData();
			ResultSet myResultSet = myDBMD.getTables(null, null, "Us%", new String[] { "TABLE" });
// Add the returned table names to the comboBox, selecting the first item
			while (myResultSet.next()) {
				((ComboBox<String>) cboTableName).getItems().add(myResultSet.getString(3));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} // end try catch
	}// end method initializeDB

	private void showData() {
		ta.clear();
		String tableName = cboTableName.getValue();
		try {
// Create query that will select from the chosen table name
			ResultSet myResultSet = stmt.executeQuery("Select * FROM " + tableName);
// Create a ResultSet object to hold the data from the executed query.
// Use the MetaData from the ResultSet to append the column names to the text
			ResultSetMetaData register = myResultSet.getMetaData();
// Use a while loop to display the values of the returned data to the text
			String tc = "";
			for (int i = 1; i <= register.getColumnCount(); i++) {
				tc += " " + register.getColumnName(i);
			}
			tc += "\n";
			while (myResultSet.next()) {
				for (int i = 1; i <= register.getColumnCount(); i++) {
					tc += " " + myResultSet.getString(i);
				}
				tc += "\n";
			}
			ta.setText(tc);
		} catch (SQLException e) {
			e.printStackTrace();
		} // end try catch
	}// end method showData

	public static void main(String[] args) {
		launch(args);
	}
}
// end method main