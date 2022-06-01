package keiver;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

//Librerias para impedir que la aplicación se abra dos veces
import it.sauronsoftware.junique.AlreadyLockedException;
import it.sauronsoftware.junique.JUnique;

public class MainKeiver extends Application {
	public static void main(String[] args) {

		String appId = "myapplicationid";
		boolean alreadyRunning;
		try {
			JUnique.acquireLock(appId);
			alreadyRunning = false;
		} catch (AlreadyLockedException e) {
			alreadyRunning = true;
			Logs.appendToFile(e);
		}
		if (!alreadyRunning) {
			launch(args);
		} else {
			System.exit(1);
		}
	}

	@Override
	public void start(Stage stage) throws Exception {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
			Scene scene = new Scene(root);
			stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
			stage.setTitle("Keiver - Key Saver");
			stage.setResizable(false);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			Logs.appendToFile(e);
			e.printStackTrace();

		}
	}

}
