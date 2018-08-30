package java_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 * 
 * @author Yurdaer Dalkic - Louay Khalil
 * 
 *         Klassen har ansvar f�r anslutning till server.
 */
public class TCPClient extends Thread {
	private Controller controller;
	private BufferedReader in;
	private PrintWriter out;
	private String serverIP;
	private Socket socket;
	private boolean socketState;
	private int port;

	/**
	 * Konstruktorn tar emot tre paremeter en referens till klassen kontroller,
	 * en str�ng som representerar IP adressen till server och en int som
	 * representerar port nummer av server som ska anslutas.I slutet av
	 * kontroller anropas metoden connect.
	 * 
	 * @param ip
	 *            Ip adressen av Server
	 * @param port
	 *            Port nummer av Server
	 * @param controller
	 *            referens till kontroller klassen.
	 */
	public TCPClient(String ip, int port, Controller controller) {
		this.serverIP = ip;
		this.port = port;

		this.controller = controller;

		connect();

	}

	/**
	 * Metoden bygger en f�rbindelse mellan socket och Server. Om f�rbindelsen
	 * kan inte byggas visas en fel meddelande.
	 */
	public void connect() {

		try {
			socket = new Socket(serverIP, port);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			socketState = true;
		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Could not connect to server...");

		}

	}

	/**
	 * Metoden returnerar tillst�ndet av varibaeln socketState. socketState �r
	 * true om klassen �r ansluten till server annars false.
	 * 
	 * @return boolean
	 */
	public boolean isOnline() {
		return socketState;

	}

	/**
	 * Metoden lysnar p� server. Datan som tas emot skickas till controller. Om
	 * datan kan inte l�sas anropas metoden close f�r att st�nga ner kopplingen
	 * till server.
	 * 
	 */
	public void run() {
		String line = null;
		while (true) {

			try {
				line = in.readLine();
				if (line != null) {
					controller.listen(line);

				}
			} catch (IOException e) {
				controller.close();

			}

		}
	}

	/**
	 * Metoden skickar en str�ng till server.
	 * 
	 * @param message
	 *            Str�ngen som ska skickas till server.
	 */
	public void sendMessage(String message) {
		out.println(message);
	}

	/**
	 * Metoden st�nger anslutningen till server.
	 */
	public void close() {
		try {
			socket.shutdownInput();
			socket.shutdownOutput();
			socket.close();
			socket = null;
			socketState = false;

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Could not close the socket...Please restart!");

		}

	}

}
