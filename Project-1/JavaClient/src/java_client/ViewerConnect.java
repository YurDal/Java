package java_client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
import java.awt.*;

/**
 * 
 * @author Yurdaer Dalkic
 *         Klassen representerar en GUI som man kan mata in
 *         servern IP adress och port nummer. GUI inneh�ller en knapp som
 *         anv�nds f�r att ansluta till server.
 */
public class ViewerConnect implements ActionListener {
	private JFrame frame = new JFrame("Mazumba");
	private JPanel main = new JPanel();
	private JLabel ipLabel = new JLabel();
	private JLabel TCPportLabel = new JLabel();
	private JTextField ipField = new JTextField();
	private JTextField TCPportField = new JTextField();
	private JLabel UDPportLabel = new JLabel();
	private JTextField UDPportField = new JTextField();
private Picture picture;
	private JButton connectButton = new JButton();
	private Controller controller;

	/**
	 * Konstruktor som skapar en Jframe och s�tter alla komponenter in i den.
	 * Konstruktor anropas med en referens till klassen Controller.
	 * 
	 * @param controller
	 */
	public ViewerConnect(Controller controller) {
	 this.picture = new Picture("src/files/background2.jpg");
		this.controller = controller;
		main.setLayout(null);
		ipLabel.setForeground(Color.white);
		ipLabel.setText("IP ADRESS");
		TCPportLabel.setText("TCP PORT");
		TCPportLabel.setForeground(Color.white);
		UDPportLabel.setText("UDP PORT");
		UDPportLabel.setForeground(Color.white);
		ipField.setText("localhost");
		TCPportField.setText("443");
		UDPportField.setText("44344");
		connectButton.setText("Connect");
		ipField.setBounds(280, 50, 100, 25);
		ipLabel.setBounds(170, 50, 100, 25);
		TCPportField.setBounds(280, 90, 100, 25);
		TCPportLabel.setBounds(170, 90, 100, 25);
		UDPportField.setBounds(280, 130, 100, 25);
		UDPportLabel.setBounds(170, 130, 100, 25);
		connectButton.setBounds(200, 180, 100, 25);
		picture.add(ipField);
		picture.add(ipLabel);
		picture.add(TCPportField);
		picture.add(TCPportLabel);
		picture.add(UDPportField);
		picture.add(UDPportLabel);
		picture.add(connectButton);
		frame.setSize(800, 500);
		frame.setLocation(350, 180);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.add(picture);
		main.setOpaque(false);
		connectButton.addActionListener(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Metoden �ndrar tiltle i JFrame som inneh�ller komponenter.
	 * 
	 * @param str�ngen
	 *            som ska vara title.
	 */
	public void setText(String res) {
		frame.setTitle(res);
	}

	/**
	 * Returnerar en str�ng som representerar IP nummer som finns i ipField.
	 * 
	 * @return en str�ng.
	 */
	public String getIP() {
		return ipField.getText();
	}

	/**
	 * Returnerar en str�ng som representerar Port nummer som finns i ipField.
	 * 
	 * @return en str�ng.
	 */
	public String getPort() {
		return TCPportField.getText();
	}
	
	public String getUDPPort() {
		return UDPportField.getText();
	}

	/**
	 * Metoden rensar och st�nger f�nstret (GUI).
	 */
	public void dispose() {
		frame.dispose();
	}

	/**
	 * Visar ett fellmeddalende som informerar anv�ndaren om anslutning till
	 * server misslyckades.
	 */
	public void error() {
		JOptionPane.showMessageDialog(null, "Can not connect to server");
	}

	

	/**
	 * Action Listener som s�ger vad som ska h�nda n�r man trycker p� knappen.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == connectButton) {
			try {
				controller.connect();
			} catch (NumberFormatException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}


}
