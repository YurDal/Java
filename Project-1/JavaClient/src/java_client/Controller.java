package java_client;

import java.io.IOException;

import javax.swing.JOptionPane;

/**
 * 
 * @author Yurdaer Dalkic
 *
 *         Kontroll klassen som kontrollerar andra klasser och innehåller all
 *         logik för hela systemet.. Innehåller metoder som visar vad som ska
 *         hända när man trycker på olika knappar och metoder som värderar som
 *         togs emot från olika server.
 */
public class Controller {

	private ViewerConnect viewerAccount;
	private RadarPanel radarPanel = new RadarPanel();
	private Viewer viewer;
	private TCPClient TCPClien;
	private UDPClient UdpClient;
	private String IPadress;
	private String TCPport;
	private String UDPport;
	private Integer[] command = { 0, 0, 0, 0 };
	private String stringCommand;
	private boolean readyToPlay = false;
	private boolean radarState = false;
	private String UserName = "driver";
	private String password = "pass";
	private String[] radar;

	/**
	 * Konstruktor som skapar ett nyt fönster som man kan mata in IP adressen
	 * och port nummer av Server.
	 */
	public Controller() {
		viewerAccount = new ViewerConnect(this);
	}

	/**
	 * Metoden anropas när man vill att programmet ansluta till servern. Metoden
	 * kontrollerar IP, TCPport och UDPPort fält om dem är tom föröker ansluta
	 * TCP server och UDP server. Om programmet har anslutning till TCP server
	 * stängs ner fönstret och öppnas ett nyt fönster som använderen kan styra
	 * bilen.
	 * 
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	public void connect() throws NumberFormatException, IOException {
		this.IPadress = viewerAccount.getIP();
		this.TCPport = viewerAccount.getPort();
		this.UDPport = viewerAccount.getUDPPort();

		// Användaren måste mata in IP och Port nummer för att kunna ansluta
		// till server

		if (IPadress.length() == 0 || TCPport.length() == 0 || UDPport.length() == 0) {
			fail();
		}
		//
		else {
			// anslutar rill TCP och UDP server
			TCPClien = new TCPClient(viewerAccount.getIP(), Integer.parseInt(viewerAccount.getPort()), this);
			UdpClient = new UDPClient(this, viewerAccount.getIP(), Integer.parseInt(viewerAccount.getUDPPort()));
			// Kontrollerar anslutningen till server
			if (TCPClien.isOnline()) {
				viewerAccount.dispose();
				viewer = new Viewer(this, radarPanel);
				TCPClien.start();
				UdpClient.sendInfo();
				UdpClient.start();
			}

		}
	}

	/**
	 * Visar ett fellmeddalende som informerar användraren om Ip nummer eller
	 * port nummer har inte angivits.
	 */
	public void fail() {
		JOptionPane.showMessageDialog(null, "Enter IP adress and Ports number");
	}

	/**
	 * Metoden tar emot en sträng och skickar den till klassern Viewer för att
	 * skriva ut den i fönster.
	 * 
	 * @param info
	 *            sträng som ska skrivas ut.
	 */
	public void addMessage(String info) {

		viewer.addInfo(info);
	}

	/**
	 * Metoden ökar värdet första positionen i int arrey (command) som
	 * representerar bilen hastighet framåt. Om bilen kör bakåt dvs trädje
	 * positionen i command större än 0 minskar hastigheten bakåt. Värdet i
	 * första positionen kan variera mellan -255___+255 och den ökar med +155 i
	 * första gången och +100 andra gången. Efter ändringen i int arrey
	 * (command) skrivas den ut i GUI, skickas till server och hastighet värde
	 * sättas i speedLabel.
	 */
	public void forward() {

		if (command[0] >= 0 && command[0] < 255 && command[2] == 0) {
			if (command[0] == 155) {
				command[0] += 100;
			}
			if (command[0] == 0) {
				command[0] += 155;
			}

			stringCommand = intTostring(command);
			TCPClien.sendMessage(stringCommand);
			addMessage(stringCommand.substring(3));
			viewer.setSpeed(command[0]);

		}
		// om hastigheten är på max framåt
		if (command[0] == 255 && command[2] == 0) {
			stringCommand = intTostring(command);
			TCPClien.sendMessage(stringCommand);
			addMessage(stringCommand.substring(3));
			viewer.setSpeed(command[0]);
		}
		// om bilen kör bakåt
		if (command[2] >= 0 && command[2] <= 255 && command[0] == 0) {
			if (command[2] == 155) {
				command[2] -= 155;
			} else {
				command[2] -= 100;
			}
			stringCommand = intTostring(command);
			TCPClien.sendMessage(stringCommand);
			addMessage(stringCommand.substring(3));
			viewer.setSpeed(-command[2]);
		}

	}

	/**
	 * Metoden ökar värdet tredje positionen i int arrey (command) som
	 * representerar bilen hastighet bakåt.Om bilen kör framåt dvs första
	 * positionen i command större än 0 minskar hastigheten framåt.Värdet i
	 * tredje positionen kan variera mellan -255___+255 och den ökar med +155 i
	 * första gången och +100 andra gången. Efter ändringen i int arrey
	 * (command) skrivas den ut i GUI, skickas till server och hastighet värde
	 * sättas i speedLabel.
	 */
	public void back() {
		// om hastigheten på max bakåt
		if (command[2] == 255 && command[0] == 0) {
			stringCommand = intTostring(command);
			TCPClien.sendMessage(stringCommand);
			addMessage(stringCommand.substring(3));
			viewer.setSpeed(-command[2]);
		}

		if (command[2] >= 0 && command[2] < 255 && command[0] == 0) {
			if (command[2] == 0) {
				command[2] += 155;
			} else {
				command[2] += 100;
			}

			stringCommand = intTostring(command);
			TCPClien.sendMessage(stringCommand);
			addMessage(stringCommand.substring(3));
			viewer.setSpeed(-command[2]);

		}

		// om bilen kör framåt
		if (command[0] >= 0 && command[0] <= 255 && command[2] == 0) {
			if (command[0] == 155) {
				command[0] -= 155;
			} else {
				command[0] -= 100;
			}
			stringCommand = intTostring(command);
			TCPClien.sendMessage(stringCommand);
			addMessage(stringCommand.substring(3));
			viewer.setSpeed(command[0]);

		}
	}

	/**
	 * Metoden ändrar värdet i andra och fjärde positionen i int arrey (command)
	 * som representerar svängning. Värderna kan vara "-1", "0" eller "+1"
	 * metoden svänger bilen till vänster genom att sätta en 1 i fjärde
	 * positionen och sätta en 0 i andra posotionen. Efter ändringen i int arrey
	 * (command) omvandlas den till en sträng och strängen skickas till server
	 * och skrivas den ut i fönster.
	 */
	public void Left() {

		command[3] = 1;
		command[1] = 0;
		stringCommand = intTostring(command);
		TCPClien.sendMessage(stringCommand);
		addMessage(stringCommand.substring(3));

	}

	/**
	 * Metoden ändrar värdet i andra och fjärde positionen i int arrey (command)
	 * som representerar svängning. Värderna kan variera mellan "-1", "0" eller
	 * "+1" metoden svänger bilen till höger genom att sätta en 0 i fjärde
	 * positionen och sätta en 1 i andra posotionen. Efter ändringen i int arrey
	 * (command)omvandlas den till en sträng och strängen skrivas ut i GUI,
	 * skickas till server.
	 */
	public void Right() {

		command[3] = 0;
		command[1] = 1;
		stringCommand = intTostring(command);
		TCPClien.sendMessage(stringCommand);
		addMessage(stringCommand.substring(3));
	}

	/**
	 * Metoden stoppar bilen genom att tilldela alla värder till 0 i int arrey
	 * command. Sedan skickas dem nya värderna till server, skrivas de i GUI och
	 * sättas ny hastighet värde i speedLabel.
	 */
	public void stop() {

		command[0] = 0;
		command[1] = 0;
		command[2] = 0;
		command[3] = 0;
		stringCommand = intTostring(command);
		TCPClien.sendMessage(stringCommand);
		addMessage(stringCommand.substring(3));
		viewer.setSpeed(command[0]);

	}

	/**
	 * Metoden tilldelar värderna till 0 i andra och fjärde positionen i int
	 * arreyen command för att stoppa svänging. Sedan skickas de nya värderna
	 * till server och skrivas de i GUI.
	 */
	public void straight() {
		command[1] = 0;
		command[3] = 0;
		stringCommand = intTostring(command);
		TCPClien.sendMessage(stringCommand);
		addMessage(stringCommand.substring(3));
	}

	/**
	 * Metoden tar emot en integer array och sätter kolon mellan varje element
	 * och arreyen omvandlas till sträng. I början av sträng läggas till "dx "
	 * .Integer array returneras i sträng formen.
	 * 
	 * @param array
	 *            Integer array som ska omvandlas till sträng.
	 * @return res strängen som returneras
	 */
	public String intTostring(Integer[] array) {
		String res = "dx " + array[0] + ":" + array[1] + ":" + array[2] + ":" + array[3];
		return res;

	}

	/**
	 * Metoden stänger ner anslutningen till server, stänger ner fönster
	 * (Viewer) och öppnar första fönster (ViewerAccount).
	 */
	public void close() {

		TCPClien.close();
		TCPClien.interrupt();
		TCPClien = null;
		viewer.dispose();
		viewer = null;
		viewerAccount = new ViewerConnect(this);

	}

	/**
	 * Metoden returnerar tillståndet av instansvaribeln "readyToPlay".
	 * Variabeln tilldelas till true om bilen är ansluten till server annars den
	 * är false.
	 * 
	 * @return en boolean
	 */
	public boolean isReadyToPlay() {
		return readyToPlay;
	}

	/**
	 * Metoden ändrar tillståndet av instansvariabeln "readyToPlay". Variabeln
	 * tilldelas till true om bilen är ansluten till server annars den är false.
	 * 
	 * @param readyToPlay
	 */
	public void setReadyToPlay(boolean readyToPlay) {
		this.readyToPlay = readyToPlay;
	}

	/**
	 * Metoden tar emot en sträng som skickas av server eller andra klienter.
	 * Metoden innehåller if satser som säger vad som ska göras med dem stränger
	 * som togs emot.
	 * 
	 * @param line
	 *            stängen som kom från server
	 */
	public void listen(String line) {
		// skickar användernamn och lösenordet till server.
		if (line.startsWith("username:password")) {

			TCPClien.sendMessage("name:" + UserName + ":" + password);

		}
		// arduino client är inte ansluen till server
		else if (line.startsWith("false")) {
			if (isReadyToPlay()) {
				setReadyToPlay(false);
				clearSpeed();
				addMessage("Waiting for aruino client...");
			}

		}
		// arduino client ansluten till server
		else if (line.startsWith("true")) {
			setReadyToPlay(true);
			addMessage("Ready to play...");
			addMessage("Kör försiktigt...");
		}
		// server stänger ner anslutningen
		else if (line.startsWith("exit") || line == "close") {
			close();
		}
		// nya värde tog emots som ska skicka till radarPanel
		else if (line.startsWith("radar")) {
			radar = line.split(":");
			radarPanel.add(Integer.parseInt(radar[1]), Integer.parseInt(radar[2]));
			addMessage(line);

		} else {
			addMessage(line);
		}

	}

	/**
	 * Metoden anropas om tappas anslutningen mellan bil och sever. Metoden
	 * tilldelar alla värde i command till noll och uppdaterar speedlabel.
	 */
	private void clearSpeed() {
		command[0] = 0;
		command[1] = 0;
		command[2] = 0;
		command[3] = 0;
		viewer.setSpeed(command[0]);
	}

	/**
	 * Metoden rensar radarPanel.
	 */
	public void clearRadar() {
		radarPanel.clear();
	}

	/**
	 * Metoden tar emot en sträng som representerar sögvägen av ny bild som ska
	 * visas i fönstrer och skickar den sögvägen till Viewer klassen.
	 * 
	 * @param imagePath
	 *            sökvägen av ny bilden.
	 */
	public void changeImage(String imagePath) {
		viewer.changePath(imagePath);
	}

	/**
	 * Metoden skickar en sträng till server för att starta eller stoppa radar
	 * och skriver ut den i fönstret.
	 */
	public void startStopRadar() {
		if (radarState == false) {
			TCPClien.sendMessage("dstart radar");
			addMessage("start radar");
			viewer.sellectTab(0);
			radarState = true;
		} else {
			TCPClien.sendMessage("dstop radar");
			addMessage("stop radar");
			radarState = false;
		}
	}

	/**
	 * Metoden skickar en sträng til server för att starta cameran och strängen
	 * skrivas i fönstret..
	 */
	public void startCamera() {
		if (readyToPlay) {
			TCPClien.sendMessage("dstart camera");
			addMessage("start camera");
		}
	}

}
