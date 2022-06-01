package keiver;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

public class Logs {
	public static void appendToFile(Exception e) {
		try {
			Timestamp time = new Timestamp(System.currentTimeMillis());
			FileWriter myWriter = new FileWriter("src/keiver/exception.txt", true);
			myWriter.write(time + " " + e.getMessage() + "\n");
			myWriter.close();
		} catch (IOException e1) {
			System.out.println("An error occurred.");
		}

	}
}
