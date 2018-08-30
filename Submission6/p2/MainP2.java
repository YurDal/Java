package p2;

import javax.swing.SwingUtilities;

public class MainP2 {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Position mapLeftUp = new Position(12.527, 56.346);
				Position mapRightDown = new Position(14.596, 55.324);
				new P2Controller("C:/skane.jpg", mapLeftUp, mapRightDown, "C:/places.txt", "C:/roads.txt");
			}
		});
	}
}
