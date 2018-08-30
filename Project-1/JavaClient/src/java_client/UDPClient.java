package java_client;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import javax.imageio.ImageIO;

/**
 * 
 * @author Louay Khalil - Yurdaer Dalkic
 *         Klassen har ansvar att ta emot och
 *         skicka datagram paket till UDP server.
 *
 */
public class UDPClient extends Thread {
	private int imageNumber = 1;
	private Controller controller;
	private InetAddress address;
	private DatagramSocket socket;
	private DatagramPacket packet;
	private byte[] imageBuff = new byte[60000];
	private BufferedImage img;
	private String IPadress;
	private int PortNumber;

	/**
	 * Konstruktor skapas med 4 parameter. En referens till controller klassen,
	 * IP adressen av udp server och port nummer av udp server. I slutet av
	 * konsruktor anropas metoden som skicka en paket till server som innehåller
	 * en sträng för att udp server ska veta vilken klient ska ta emot bilder.
	 * 
	 * @param controller
	 * @param IP
	 *            nummer av server
	 * @param Port
	 *            nummer av server
	 * @throws IOException
	 */
	public UDPClient(Controller controller, String IP, int Port) throws IOException {
		this.controller = controller;
		this.IPadress = IP;
		this.PortNumber = Port;
		address = InetAddress.getByName(IPadress);
		socket = new DatagramSocket();
		sendInfo();
	}

	/**
	 * Metoden innehåller en while loop som lysnar på udp server och tar emot
	 * paketer. Paketer innehåller byte array och omvandlas den till en bild
	 * file. Bilden sparas och sökvägen av bilden skickas till conroller.
	 */
	public void run() {
		while (true)

		{

			try {
				byte[] newBuff = new byte[60000];
				packet = new DatagramPacket(newBuff, newBuff.length);
				socket.receive(packet);
				imageBuff = packet.getData();
				InputStream in = new ByteArrayInputStream(imageBuff);
				img = ImageIO.read(in);
				ImageIO.write(img, "jpg", new File("src/files/" + imageNumber + ".jpg"));
				controller.changeImage("src/files/" + imageNumber + ".jpg");
				imageNumber++;
			} catch (Exception e) {
				return;
			}
		} // slutet av while sats
	}// slutet av metoden

	/**
	 * Metoden skickar en paket som innehåller en sträng till server. Paketet
	 * skickas för att infermore vilken klient ska ta emot bilderna.
	 * 
	 * @throws IOException
	 */
	public void sendInfo() throws IOException {
		byte[] buf = new String("java").getBytes();
		packet = new DatagramPacket(buf, buf.length, address, PortNumber);
		socket.send(packet);
	}
}
