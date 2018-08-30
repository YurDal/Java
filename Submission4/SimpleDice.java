package p5;

import java.util.Random;

/**
 * 
 * @author Yurdaer Dalkic
 * Klassen SimpleDice representerar en tärning med minst en sida.
 *
 */
public class SimpleDice implements Dice {
	private int sides;

	/**
	 * Om denna konstruktor används ska tärningen vara 6-sidig
	 */
	public SimpleDice() {
		this(6);
	}

	/**
	 * @param sides
	 * En konstruktor som tar emot antalet sidor som parameter
	 */
	public SimpleDice(int sides) {
		if (sides <= 0) {
			throw new NegativeSidesException("Tärningen måste vara minst en sida" + sides);
		} else {
			this.sides = sides;
		}
	}

	@Override
	public int throwDice() {
		Random rand = new Random();
		return rand.nextInt(sides) + 1;

	}

	@Override
	public int getSides() {
		return sides;
	}

}
