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

//Main Class que controla la aplicacion
/**
 * @author isanchez
 * @author croldan
 * @version 1.0 Fase Beta
 *
 */
public class MainKeiver extends Application {
	public static void main(String[] args) {
		// Codigo para evitar que inicien la app 2 veces
		String appId = "myapplicationid";
		boolean alreadyRunning;
		try {
			JUnique.acquireLock(appId);
			alreadyRunning = false;
		} catch (AlreadyLockedException e) {
			alreadyRunning = true;
			Logs.appendToFile(e); // Errores a archivo de log
		}
		if (!alreadyRunning) {
			launch(args);
		} else {
			System.exit(1);
		}
	}

	/**
	 * Metodo de JavaFx para iniciar la aplicacion
	 */
	@Override
	public void start(Stage stage) throws Exception {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Login.fxml")); //Establecer el login como ventana
			Scene scene = new Scene(root);
			stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png"))); //Cargar el icono de la app
			stage.setTitle("Keiver - Key Saver");
			stage.setResizable(false);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			Logs.appendToFile(e);

		}
	}

}
