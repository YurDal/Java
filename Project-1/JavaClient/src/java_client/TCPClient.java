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
 *         Klassen har ansvar för anslutning till server.
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
	 * en sträng som representerar IP adressen till server och en int som
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
	 * Metoden bygger en förbindelse mellan socket och Server. Om förbindelsen
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
	 * Metoden returnerar tillståndet av varibaeln socketState. socketState är
	 * true om klassen är ansluten till server annars false.
	 * 
	 * @return boolean
	 */
	public boolean isOnline() {
		return socketState;

	}

	/**
	 * Metoden lysnar på server. Datan som tas emot skickas till controller. Om
	 * datan kan inte läsas anropas metoden close för att stänga ner kopplingen
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
	 * Metoden skickar en sträng till server.
	 * 
	 * @param message
	 *            Strängen som ska skickas till server.
	 */
	public void sendMessage(String message) {
		out.println(message);
	}

	/**
	 * Metoden stänger anslutningen till server.
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
