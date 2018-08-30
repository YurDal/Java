package p3;

/**
 * Klassen innehåller 5 stycken JButton som
 * låter användaren välja STEN/SAX/PÅSE eller Nytt spel eller att Avsluta programmet
 * 
 */



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class SSPUserInput extends JPanel {

	private SSPController controller;

	private JPanel firstLine = new JPanel();         //Label som ska innehålla Sten, Sax och Påse butoner
	private JButton btnBag = new JButton("PÅSE");
	private JButton btnRock = new JButton("STEN");
	private JButton btnScissors = new JButton("SAX");
	private JButton btnNewGame = new JButton("Nytt Spel");
	private JButton btnClose = new JButton("Avsluta");

	private Font btnFont = new Font("SansSerif", Font.PLAIN, 20);    //Font för butoner

	public SSPUserInput(SSPController controller) {      //För att hantera SSPUserInput-objektet från SSPController-objektet
                                                        //används en konstruktorn och SSPController använder en referens till sig själv.
		this.controller = controller;
		setBackground(new Color(64, 224, 208));

		setPreferredSize(new Dimension(300, 250));
		btnNewGame.setPreferredSize(new Dimension(2500, 25));
		btnClose.setPreferredSize(new Dimension(250, 25));     
		btnBag.setPreferredSize(new Dimension(100, 25));      //Kod anger storlek till butoner
		btnRock.setPreferredSize(new Dimension(100, 25));
		btnScissors.setPreferredSize(new Dimension(100, 25));

		btnNewGame.setFont(btnFont);
		btnClose.setFont(btnFont);
		btnBag.setFont(btnFont);               //Kod som anger fonten till buttoner
		btnRock.setFont(btnFont);
		btnScissors.setFont(btnFont);

		btnNewGame.setBackground(new Color(139, 0, 139));
		btnClose.setBackground(new Color(155, 0, 0));  
		btnRock.setBackground(new Color(105, 105, 105));        //Kod som anger färgen till butoner
		btnScissors.setBackground(new Color(255, 20, 147));
		btnBag.setBackground(new Color(255, 255, 0));

		firstLine.add(btnRock);
		firstLine.add(btnScissors);       //Sten, Sax och Påse buttoner placeras i panel
		firstLine.add(btnBag);

		add(firstLine);
		add(btnNewGame);             //Paneller skapas i huvudpanel 
		add(btnClose);

		ButtonListener listener = new ButtonListener();
		btnNewGame.addActionListener(listener);
		btnClose.addActionListener(listener);
		btnRock.addActionListener(listener);            // Kopplingen mellan lysnare och komponenter 
		btnBag.addActionListener(listener);
		btnScissors.addActionListener(listener);

	}

	private class ButtonListener implements ActionListener {          //Implementering av lyssnaren i en inre klass:
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == btnRock) {
				controller.setUserInput(0);
			} else if (e.getSource() == btnScissors) {
				controller.setUserInput(1);                   
			} else if (e.getSource() == btnBag) {
				controller.setUserInput(2);
			} else if (e.getSource() == btnNewGame) {
				controller.setUserInput(3);
			} else if (e.getSource() == btnClose) {
				controller.setUserInput(4);
			}
		}
	}

	public void enableButtons() {         //Metoden dimmar butoner
		btnRock.setEnabled(true);
		btnBag.setEnabled(true);
		btnScissors.setEnabled(true);
	}

	public void disableButtons() {       //Metoden aktiveras butoner
		btnRock.setEnabled(false);
		btnBag.setEnabled(false);
		btnScissors.setEnabled(false);
	}

}
