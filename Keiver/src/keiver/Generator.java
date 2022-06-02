package keiver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * 
 * @author isanchez
 * @author croldan
 * @version 1.0 Fase Beta
 *
 */
public class Generator {
	private boolean caps;
	private boolean numbers;
	private boolean nonAlphChar;
	private static ArrayList<String> chars = new ArrayList<String>();
	private static String password = "";

	/**
	 * Constructor del generador
	 * 
	 * @param caps        (boolean para saber si quiere mayusculas en su contraseña)
	 * @param numbers     (boolean para saber si quiere numeros en su contraseña)
	 * @param nonAlphChar (boolean para saber si quiere caracteres no alfanumericos
	 *                    en su contraseña)
	 */
	public Generator(boolean caps, boolean numbers, boolean nonAlphChar) {
		this.caps = caps;
		this.numbers = numbers;
		this.nonAlphChar = nonAlphChar;
	}

	/**
	 * Generador de contraseñas
	 * 
	 * @param generator Le pasamos el generador
	 * @return Devuelve la contraseña
	 */
	public static String generatePass(Generator generator) {

		// Variables
		int numChars = 10;
		int counter = 0;
		String letter[] = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
				"s", "t", "u", "v", "w", "x", "y", "z" };
		String number[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		String special[] = { "!", "#", "@", "*", "-", "+", "_", "?", "$", "&" };
		Random rdm = new Random();

		// En caso de true se generara un caracter no alfanumerico
		if (generator.isNonAlphChar() == true) {
			chars.add(special[rdm.nextInt(10)]);
			counter++;
		}

		// En caso de true se generaran dos numeros
		if (generator.isNumbers() == true) {
			chars.add(number[rdm.nextInt(10)]);
			chars.add(number[rdm.nextInt(10)]);
			counter = counter + 2;
		}

		// En caso de true se generaran dos mayusculas
		if (generator.isCaps() == true) {
			chars.add(letter[rdm.nextInt(26)].toUpperCase());
			chars.add(letter[rdm.nextInt(26)].toUpperCase());
			counter = counter + 2;
		}

		// Rellenamos el resto de huecos de la contraseña con minusculas
		for (int i = 0; i < (numChars - counter); i++) {
			chars.add(letter[rdm.nextInt(26)]);
		}

		// Mezclamos toda la generacion de caracteres
		Collections.shuffle(chars);

		// Metemos los caracteres a un string
		for (int i = 0; i < numChars; i++) {
			password += chars.get(i);
		}

		return password;
	}

	/**
	 * Getter de las mayusculas
	 * 
	 * @return (devuelve si quiere o no mayusculas)
	 */
	public boolean isCaps() {
		return caps;
	}

	/**
	 * Setter de las mayusculas
	 * 
	 * @param caps (si quiere o no mayusculas)
	 */
	public void setCaps(boolean caps) {
		this.caps = caps;
	}

	/**
	 * Getter de los numeros
	 * 
	 * @return (devuelve si quiere o no numeros)
	 */
	public boolean isNumbers() {
		return numbers;
	}

	/**
	 * Setter de los numeros
	 * 
	 * @param numbers (si quiere o no numeros)
	 */
	public void setNumbers(boolean numbers) {
		this.numbers = numbers;
	}

	/**
	 * Getter de los caracteres no alfanumericos
	 * 
	 * @return (devuelve si quiere o no caracteres no alfanumericos)
	 */
	public boolean isNonAlphChar() {
		return nonAlphChar;
	}

	/**
	 * Setter de los caracteres no alfanumericos
	 * 
	 * @param nonAlphChar (si quiere o no caracteres no alfanumericos)
	 */
	public void setNonAlphChar(boolean nonAlphChar) {
		this.nonAlphChar = nonAlphChar;
	}

	/**
	 * Getter de la contraseña
	 * 
	 * @return (devuelve la contraseña)
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Setter de la contraseña
	 * 
	 * @param password (contraseña)
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
