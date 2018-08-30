package p5;

import java.util.Random;

/**
 * 
 * @author Yurdaer Dalkic 
 * Klassen som fuskar lite vid tärningsspel
 */
public class Cheater extends Player {

	Dice dice;

	/**
	 * 
	 * @param name
	 *            Om konstruktor används ska tärningen vara 6-sidig
	 */
	public Cheater(String name) {
		super(name);

	}

	/**
	 * 
	 * @param name
	 * @param dice
	 *            Konstruktor som tar emot en String som namn och en Dice objekt
	 *            som sätts i denna klassen.
	 */
	public Cheater(String name, Dice dice) {
		super(name);
		this.dice = new SimpleDice();

	}

	/**
	 * 
	 * @param dice
	 *        Metoden tar emot Dice objekt som sätts i denna klassen.
	 */
	public void setDice(Dice dice) {
		this.dice = dice;

	}

	/**
	 * 
	 * @return
	 *       Metoden returnerar en Dice objekt.
	 */
	public Dice getDice() {
		return dice;

	}

	/**
	 *    Metoden kastar en tärning och fuskar med resultaten, returnerar ett
	 *    (int)värde som resultat
	 */
	public int throwDice() {
		Random rand = new Random();
		int cheat = 0;
		int first = rand.nextInt(dice.getSides()) + 1;  //Kastar tärningen
		int change = rand.nextInt(2);                   //Cheater-objektet ska ange en prick för mycket vid ca hälften av kasten
		if (change == 0 && first != dice.getSides()) {  //Om tärningen visar inte max antal prickar och change är noll, fuska
			cheat = first + 1;
		} else {
			cheat = first;                              // Annars spela rent
		}
		return cheat;                                   

	}
}
