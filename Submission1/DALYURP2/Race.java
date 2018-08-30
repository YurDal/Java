package p2;

import java.awt.Color;
import java.awt.Font;

import java.util.Random;
/**
 * Klassen stimulerar en tävling mellan två racerbilar
 * @author yurdaer
 *
 */

public class Race {                //Instansvariabler för klassen
	private PaintWindow window;
	private Car car1;
	private Car car2;

	public Race(PaintWindow window, Car car1, Car car2) {     //Konstruktor med parameter
		this.window = window;
		this.car1 = car1;
		this.car2 = car2;
	}

	public void action() {              //Programen startas genom anrop till metoden action
		window.showImage(car1.getImage(), 700, 230);
		window.showImage(car2.getImage(), 700, 430);
		window.line(0, 250, 800, 250, Color.DARK_GRAY, 80);
		window.line(0, 450, 800, 450, Color.DARK_GRAY, 80);
		window.line(30, 280, 30, 220, Color.WHITE, 20);
		window.line(30, 480, 30, 420, Color.WHITE, 20);

		Random rand = new Random();
		int mål = 30;
		int x1 = 700;
		int x2 = 700;
		while (x1 >= mål && x2 >= mål) {
			x1 -= rand.nextInt(10) + 4;
			window.showImage(car1.getImage(), x1, 230);
			x2 -= rand.nextInt(10) + 4;
			window.showImage(car2.getImage(), x2, 430);
			PaintWindow.pause(30);

			if (x1 > mål && x2 <= mål) {
				window.showImage(new Text("Vinner: RED", new Font("SansSerif", Font.PLAIN, 36), Color.WHITE), 50, 550);

			} else if (x1 <= mål && x2 > mål) {
				window.showImage(new Text("Vinner: BLUE", new Font("SansSerif", Font.PLAIN, 36), Color.WHITE), 50, 150);
			} else if (x1 <= mål && x2 <= mål) {
				window.showImage(new Text("Vinner: ?", new Font("SansSerif", Font.PLAIN, 36), Color.WHITE), 50, 330);
			}

		}

	}

}
