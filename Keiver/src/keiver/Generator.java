package keiver;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
public class Generator {
	private int digits;
	private boolean caps;
	private boolean numbers;
	private boolean nonAlphChar;
	private static ArrayList<String> chars = new ArrayList<String>();
	private static String password = "";

	public Generator(int digits, boolean caps, boolean numbers, boolean nonAlphChar) {
		this.digits = digits;
		this.caps = caps;
		this.numbers = numbers;
		this.nonAlphChar = nonAlphChar;
	}

	public static String generatePass(Generator generator) {
		
		int numChars = generator.getDigits();
		int counter = 0;
		String letter[] = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
		String number[] = {"0","1","2","3","4","5","6","7","8","9"};
		String special[] = {"!","#","@","*","-","+","_","?","$","&"};
		Random rdm = new Random();
		
		if (generator.isNonAlphChar() == true) {
			chars.add(special[rdm.nextInt(10)]);
			counter++;
		}
		
		if (generator.isNumbers() == true) {
			chars.add(number[rdm.nextInt(10)]);
			chars.add(number[rdm.nextInt(10)]);
			counter = counter + 2;
		}
		
		if (generator.isCaps() == true) {
			chars.add(letter[rdm.nextInt(26)].toUpperCase());
			chars.add(letter[rdm.nextInt(26)].toUpperCase());
			counter = counter + 2;
		} 
		
		for (int i = 0; i < (numChars-counter); i++) {
			chars.add(letter[rdm.nextInt(26)]);
		}

		Collections.shuffle(chars);
		
		for (int i = 0; i < numChars; i++) {
			password += chars.get(i);
		}
				
		return password;
	}

	public int getDigits() {
		return digits;
	}

	public void setDigits(int digits) {
		this.digits = digits;
	}

	public boolean isCaps() {
		return caps;
	}

	public void setCaps(boolean caps) {
		this.caps = caps;
	}

	public boolean isNumbers() {
		return numbers;
	}

	public void setNumbers(boolean numbers) {
		this.numbers = numbers;
	}

	public boolean isNonAlphChar() {
		return nonAlphChar;
	}

	public void setNonAlphChar(boolean nonAlphChar) {
		this.nonAlphChar = nonAlphChar;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
}
