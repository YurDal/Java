package p3;

/**
 * Klassen visar spel information
 * 
 */


import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class SSPViewer extends JPanel {      

	private JPanel leftPanel = new JPanel();     //Paneller som ska innehålla flera labeller
	private JPanel rightPanel = new JPanel();    //

	private JPanel leftGrid = new JPanel(new GridLayout(3, 1, 0, 20));   //Paneller ska placeras med hjlp av GridLayout  
	private JPanel rightGrid = new JPanel(new GridLayout(3, 1, 0, 20));  

	private JLabel upLine = new JLabel("Först till 3 vinner!", SwingConstants.CENTER);   //Label som visar information
	private JLabel lblHuman = new JLabel("Human", SwingConstants.CENTER);                
	private JLabel lblComp = new JLabel("Computer", SwingConstants.CENTER);
	private JLabel lblHumanScr = new JLabel("0", SwingConstants.CENTER);      
	private JLabel lblCompScr = new JLabel("0", SwingConstants.CENTER);
	private JLabel lblHumanChose = new JLabel("", SwingConstants.CENTER);
	private JLabel lblCompChose = new JLabel("", SwingConstants.CENTER);

	private Font font = new Font("SansSerif", Font.PLAIN, 20);      //Font för användaren val, poäng identitet
	private Font font2 = new Font("SansSerif", Font.PLAIN, 25);     //Font för info label

	public SSPViewer() {
		setPreferredSize(new Dimension(400, 200));
		setBackground(new Color(173, 255, 47));
		setLayout(new BorderLayout());

		upLine.setFont(font2);         // Deklaration av fonter
		lblHuman.setFont(font);
		lblComp.setFont(font);
		lblHumanScr.setFont(font);       
		lblCompScr.setFont(font);
		lblHumanChose.setFont(font);
		lblCompChose.setFont(font);

		rightGrid.add(lblComp);       //Labeller av datorspelarens placeras i höger panell
		rightGrid.add(lblCompScr);
		rightGrid.add(lblCompChose);

		leftGrid.add(lblHuman);
		leftGrid.add(lblHumanScr);     //   Labeller av anveändaren placeras i vänstra panell
		leftGrid.add(lblHumanChose);

		leftPanel.setBackground(new Color(173, 255, 47));
		rightPanel.setBackground(new Color(173, 255, 47));

		leftGrid.setBackground(new Color(173, 255, 47));
		rightGrid.setBackground(new Color(173, 255, 47));

		leftPanel.add(leftGrid);
		leftPanel.setBorder(new EmptyBorder(20, 0, 0, 80));     //EmptyBorder används för att förbättra placeringen
		rightPanel.add(rightGrid);
		rightPanel.setBorder(new EmptyBorder(20, 80, 0, 0));

		add(upLine, BorderLayout.NORTH);
		add(leftPanel, BorderLayout.EAST);      //Paneller placeras i huvudpanel
		add(rightPanel, BorderLayout.WEST);

	}

	public void setUpLine(String text) {             //Set metoder används för att få ändra information från SSPController classen
		this.upLine.setText(text);
	}

	public void setCompScr(int score) {
		this.lblCompScr.setText(Integer.toString(score));
	}

	public void setHumanScr(int score) {
		this.lblHumanScr.setText(Integer.toString(score));
	}

	public void setHumanChose(String text) {
		this.lblHumanChose.setText(text);
	}

	public void setCompChose(String text) {
		this.lblCompChose.setText(text);
	}

}
