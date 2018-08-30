package p3;
/**
 * Klassen innehåller en Random metod
 * för att leverera nya val till SSPController
 */

import java.util.Random;

public class SSPPlayer {
	public int newChoice() {
		int compInput;
		Random random = new Random();
		compInput = random.nextInt(3);

		return compInput;

	}

}
