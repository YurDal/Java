package p2;

import javax.swing.ImageIcon;

/**
 * Klassen startar programmet Race
 * 
 * @author TSROAX
 */
public class StartRace { // /Volumes/Home/TSROAX/
	public static void main(String[] args) {
		PaintWindow window = new PaintWindow(
				new ImageIcon("C:/Users/yurdaer/Desktop/MAH/Obj-Programering/Uppgifter/P2/background.jpg"));
		Car c1 = new Car(new ImageIcon("C:/Users/yurdaer/Desktop/MAH/Obj-Programering/Uppgifter/P2/CarBlue.GIF"));
		Car c2 = new Car(new ImageIcon("C:/Users/yurdaer/Desktop/MAH/Obj-Programering/Uppgifter/P2/CarRed.GIF"));
		Race race = new Race(window, c1, c2);
		race.action();
		if (args.length > 0) {
			PaintWindow.pause(2000);
			window.dispose();
		}
	}
}
