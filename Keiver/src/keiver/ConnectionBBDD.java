package keiver;

import java.sql.*;
import java.io.*;

/**
 * 
 * @author isanchez
 * @author croldan
 *
 */
public class ConnectionBBDD {

	private static String data[] = new String[3];

	public Connection connection;

	/**
	 * Metodo para crear la conexion con la base de datos
	 * 
	 * @return Devuelve la conexion o null en caso de error
	 */
	public Connection getConnection() {

		String end = "";
		int i = 0;
		try {

			// Archivo con los datos de conexion a la base de datos
			File dataFile = new File("src/keiver/bbdd_data.txt");

			BufferedReader file = new BufferedReader(new FileReader(dataFile));

			// Lectura y asignacion a variables de los datos del archivo de texto
			while ((end = file.readLine()) != null) {
				data[i] = end;
				i++;
			}

		} catch (FileNotFoundException e) {
			System.err.println("No se ha encontrado el archivo -> " + e.getMessage());
			Logs.appendToFile(e); // Escritura del error en el archivo de logs
		} catch (Exception e) {
			System.err.println("Error imprevisto");
			Logs.appendToFile(e); // Escritura del error en el archivo de logs
		}

		try {

			// Conexion a la base de datos
			Connection myConnection = DriverManager.getConnection(data[0], data[1], data[2]);

			System.out.println("Connection OK");

			return myConnection;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			Logs.appendToFile(e); // Escritura del error en el archivo de logs
			return null;
		}

	}
}
