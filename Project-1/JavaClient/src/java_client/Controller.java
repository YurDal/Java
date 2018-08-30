package java_client;

import java.io.IOException;

import javax.swing.JOptionPane;

/**
 * 
 * @author Yurdaer Dalkic
 *
 *         Kontroll klassen som kontrollerar andra klasser och inneh�ller all
 *         logik f�r hela systemet.. Inneh�ller metoder som visar vad som ska
 *         h�nda n�r man trycker p� olika knappar och metoder som v�rderar som
 *         togs emot fr�n olika server.
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
	 * Konstruktor som skapar ett nyt f�nster som man kan mata in IP adressen
	 * och port nummer av Server.
	 */
	public Controller() {
		viewerAccount = new ViewerConnect(this);
	}

	/**
	 * Metoden anropas n�r man vill att programmet ansluta till servern. Metoden
	 * kontrollerar IP, TCPport och UDPPort f�lt om dem �r tom f�r�ker ansluta
	 * TCP server och UDP server. Om programmet har anslutning till TCP server
	 * st�ngs ner f�nstret och �ppnas ett nyt f�nster som anv�nderen kan styra
	 * bilen.
	 * 
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	public void connect() throws NumberFormatException, IOException {
		this.IPadress = viewerAccount.getIP();
		this.TCPport = viewerAccount.getPort();
		this.UDPport = viewerAccount.getUDPPort();

		// Anv�ndaren m�ste mata in IP och Port nummer f�r att kunna ansluta
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
	 * Visar ett fellmeddalende som informerar anv�ndraren om Ip nummer eller
	 * port nummer har inte angivits.
	 */
	public void fail() {
		JOptionPane.showMessageDialog(null, "Enter IP adress and Ports number");
	}

	/**
	 * Metoden tar emot en str�ng och skickar den till klassern Viewer f�r att
	 * skriva ut den i f�nster.
	 * 
	 * @param info
	 *            str�ng som ska skrivas ut.
	 */
	public void addMessage(String info) {

		viewer.addInfo(info);
	}

	/**
	 * Metoden �kar v�rdet f�rsta positionen i int arrey (command) som
	 * representerar bilen hastighet fram�t. Om bilen k�r bak�t dvs tr�dje
	 * positionen i command st�rre �n 0 minskar hastigheten bak�t. V�rdet i
	 * f�rsta positionen kan variera mellan -255___+255 och den �kar med +155 i
	 * f�rsta g�ngen och +100 andra g�ngen. Efter �ndringen i int arrey
	 * (command) skrivas den ut i GUI, skickas till server och hastighet v�rde
	 * s�ttas i speedLabel.
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
		// om hastigheten �r p� max fram�t
		if (command[0] == 255 && command[2] == 0) {
			stringCommand = intTostring(command);
			TCPClien.sendMessage(stringCommand);
			addMessage(stringCommand.substring(3));
			viewer.setSpeed(command[0]);
		}
		// om bilen k�r bak�t
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
	 * Metoden �kar v�rdet tredje positionen i int arrey (command) som
	 * representerar bilen hastighet bak�t.Om bilen k�r fram�t dvs f�rsta
	 * positionen i command st�rre �n 0 minskar hastigheten fram�t.V�rdet i
	 * tredje positionen kan variera mellan -255___+255 och den �kar med +155 i
	 * f�rsta g�ngen och +100 andra g�ngen. Efter �ndringen i int arrey
	 * (command) skrivas den ut i GUI, skickas till server och hastighet v�rde
	 * s�ttas i speedLabel.
	 */
	public void back() {
		// om hastigheten p� max bak�t
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

		// om bilen k�r fram�t
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
	 * Metoden �ndrar v�rdet i andra och fj�rde positionen i int arrey (command)
	 * som representerar sv�ngning. V�rderna kan vara "-1", "0" eller "+1"
	 * metoden sv�nger bilen till v�nster genom att s�tta en 1 i fj�rde
	 * positionen och s�tta en 0 i andra posotionen. Efter �ndringen i int arrey
	 * (command) omvandlas den till en str�ng och str�ngen skickas till server
	 * och skrivas den ut i f�nster.
	 */
	public void Left() {

		command[3] = 1;
		command[1] = 0;
		stringCommand = intTostring(command);
		TCPClien.sendMessage(stringCommand);
		addMessage(stringCommand.substring(3));

	}

	/**
	 * Metoden �ndrar v�rdet i andra och fj�rde positionen i int arrey (command)
	 * som representerar sv�ngning. V�rderna kan variera mellan "-1", "0" eller
	 * "+1" metoden sv�nger bilen till h�ger genom att s�tta en 0 i fj�rde
	 * positionen och s�tta en 1 i andra posotionen. Efter �ndringen i int arrey
	 * (command)omvandlas den till en str�ng och str�ngen skrivas ut i GUI,
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
	 * Metoden stoppar bilen genom att tilldela alla v�rder till 0 i int arrey
	 * command. Sedan skickas dem nya v�rderna till server, skrivas de i GUI och
	 * s�ttas ny hastighet v�rde i speedLabel.
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
	 * Metoden tilldelar v�rderna till 0 i andra och fj�rde positionen i int
	 * arreyen command f�r att stoppa sv�nging. Sedan skickas de nya v�rderna
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
	 * Metoden tar emot en integer array och s�tter kolon mellan varje element
	 * och arreyen omvandlas till str�ng. I b�rjan av str�ng l�ggas till "dx "
	 * .Integer array returneras i str�ng formen.
	 * 
	 * @param array
	 *            Integer array som ska omvandlas till str�ng.
	 * @return res str�ngen som returneras
	 */
	public String intTostring(Integer[] array) {
		String res = "dx " + array[0] + ":" + array[1] + ":" + array[2] + ":" + array[3];
		return res;

	}

	/**
	 * Metoden st�nger ner anslutningen till server, st�nger ner f�nster
	 * (Viewer) och �ppnar f�rsta f�nster (ViewerAccount).
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
	 * Metoden returnerar tillst�ndet av instansvaribeln "readyToPlay".
	 * Variabeln tilldelas till true om bilen �r ansluten till server annars den
	 * �r false.
	 * 
	 * @return en boolean
	 */
	public boolean isReadyToPlay() {
		return readyToPlay;
	}

	/**
	 * Metoden �ndrar tillst�ndet av instansvariabeln "readyToPlay". Variabeln
	 * tilldelas till true om bilen �r ansluten till server annars den �r false.
	 * 
	 * @param readyToPlay
	 */
	public void setReadyToPlay(boolean readyToPlay) {
		this.readyToPlay = readyToPlay;
	}

	/**
	 * Metoden tar emot en str�ng som skickas av server eller andra klienter.
	 * Metoden inneh�ller if satser som s�ger vad som ska g�ras med dem str�nger
	 * som togs emot.
	 * 
	 * @param line
	 *            st�ngen som kom fr�n server
	 */
	public void listen(String line) {
		// skickar anv�ndernamn och l�senordet till server.
		if (line.startsWith("username:password")) {

			TCPClien.sendMessage("name:" + UserName + ":" + password);

		}
		// arduino client �r inte ansluen till server
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
			addMessage("K�r f�rsiktigt...");
		}
		// server st�nger ner anslutningen
		else if (line.startsWith("exit") || line == "close") {
			close();
		}
		// nya v�rde tog emots som ska skicka till radarPanel
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
	 * tilldelar alla v�rde i command till noll och uppdaterar speedlabel.
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
	 * Metoden tar emot en str�ng som representerar s�gv�gen av ny bild som ska
	 * visas i f�nstrer och skickar den s�gv�gen till Viewer klassen.
	 * 
	 * @param imagePath
	 *            s�kv�gen av ny bilden.
	 */
	public void changeImage(String imagePath) {
		viewer.changePath(imagePath);
	}

	/**
	 * Metoden skickar en str�ng till server f�r att starta eller stoppa radar
	 * och skriver ut den i f�nstret.
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
	 * Metoden skickar en str�ng til server f�r att starta cameran och str�ngen
	 * skrivas i f�nstret..
	 */
	public void startCamera() {
		if (readyToPlay) {
			TCPClien.sendMessage("dstart camera");
			addMessage("start camera");
		}
	}

}
