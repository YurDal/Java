package p5;

/**
 * 
 * @author Yurdaer Dalkic
 *
 */
public class TestDice {
	/**
	 * 
	 * @param result
	 *            Metoden skriver ut resultaten i fönstret
	 */
	public static void printResult(int[] result) {
		for (int i = 0; i < result.length; i++) {
			TextWindow.println(String.format("%-4s%10d", (i + 1), result[i]));
		}
	}

	/**
	 * 
	 * @param dice
	 *            tas emot som antalet sidor som tärningen har
	 * @param nbrOfThrows
	 *            tas emot som antalet prickar som tärningen visar vid kastet
	 */
	public static void test(Dice dice, int nbrOfThrows) {
		int[] res = new int[dice.getSides()];
		for (int i = 0; i < nbrOfThrows; i++) {
			int antalPrickar = dice.throwDice();
			res[antalPrickar - 1]++;
		}
		TestDice.printResult(res);
	}

	/**
	 * 
	 * @param player
	 * @param nbrOfThrows
	 *        Genom att anropa denna metod kan du avgöra om en Player ger korrekta resultat
     *        vid anrop till spelarens throwDice-metod.
	 */
	public static void test(Player player, int nbrOfThrows) {
		Dice dice;
		if (player instanceof OrdinaryPlayer) {              //Kontrollerar om Player-objektet som levereras är  en OrdinaryPlayer
			OrdinaryPlayer op = (OrdinaryPlayer) player;     // Typkonverterar Player-objektet till referens till OrdinaryPlayer  
			dice = op.getDice();                             //Errhållar referens till tärningsobjektet
			int[] res = new int[dice.getSides()];
			for (int i = 0; i < nbrOfThrows; i++) {
				int antalPrickar = dice.throwDice();
				res[antalPrickar - 1]++;

			}
			TestDice.printResult(res);

		} else if (player instanceof Cheater) {              //Kontrollerar om Player-objektet som levereras är  en Cheater
			Cheater che = (Cheater) player;                  //Typkonverterar Player-objektet till referens till Cheater  
			dice = che.getDice();                            //Errhållar referens till tärningsobjektet
			int[] res = new int[dice.getSides()];
			for (int i = 0; i < nbrOfThrows; i++) {
				int antalPrickar = dice.throwDice();
				res[antalPrickar - 1]++;

			}
			TestDice.printResult(res);

		}

	}

}
