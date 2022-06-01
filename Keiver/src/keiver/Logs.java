package keiver;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

/**
 * @author isanchez
 * @author croldan
 * @version 1.0 Fase Beta
 *
 */

/**
 * Class que escribe los errores dentro de un archivo llamado exception Escribe
 * la data de cuando se ha producido el fallo
 *
 */
public class Logs {
	public static void appendToFile(Exception e) {
		try {
			Timestamp time = new Timestamp(System.currentTimeMillis()); // Añadir timestamp para saber que error sucedio
																		// en que momento
			FileWriter myWriter = new FileWriter("src/keiver/exception.txt", true);
			myWriter.write(time + " " + e.getMessage() + "\n");
			myWriter.close();
		} catch (IOException e1) {
			System.out.println("An error occurred.");
		}

	}
}
