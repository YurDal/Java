package p5;

import java.util.Random;

/**
 * 
 * @author Yurdaer Dalkic 
 *         Klassen representerar en tärningsspelare.
 */
public class OrdinaryPlayer extends Player {
	Dice dice;

	/**
	 * 
	 * @param name
	 *        Konstruktor om tar emot en String som Players namn
	 * 
	 */
	public OrdinaryPlayer(String name) {
		super(name);

	}

	/**
	 * 
	 * @param name
	 * @param dice
	 *        Konstruktor som tar emot en String som namn och en Dice objekt
	 *        som sätts i denna klassen.
	 */
	public OrdinaryPlayer(String name, Dice dice) {
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
	 * @return dice 
	 *         Metoden returnerar en Dice objekt.
	 */
	public Dice getDice() {
		return dice;

	}

	/**
	 * Metoden kastar en tärning och
	 * returnerar ett (int)värde som resultat
	 */
	public int throwDice() {
		Random rand = new Random();
		int sides = dice.getSides();
		int last = rand.nextInt(sides) + 1;
		return last;

	}
}
