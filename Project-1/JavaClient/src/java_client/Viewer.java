package java_client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;
import javax.swing.*;

/**
 * 
 * @author Yurdaer Dalkic
 * 
 *         Klassen representerar en GUI som best�r av fyra delar. En panel f�r
 *         radar f�nster, en panel f�r bild, en textf�lt f�r visar information
 *         om anslutnong till server, data som kom fr�n server och data som som
 *         skickas till server, och en textf�lt som inneh�ller instruktioner om
 *         hur anv�ndaren ska styra bilen.
 *
 */
public class Viewer implements KeyListener {
	private Controller controller;
	private JPanel background = new Picture("src/files/background1.jpg");
	private JFrame frame = new JFrame();
	private JPanel pnlRadar = new JPanel();
	private JLabel speedLabel = new JLabel();
	private JTextArea messageArea = new JTextArea(10, 40);
	private JTextArea Info = new JTextArea();
	private JScrollPane scroll = new JScrollPane(messageArea);
	private JTabbedPane tabbed = new JTabbedPane();
	private JPanel image = new Picture("src/files/icon.png");
	private long lastTimePressed = 0;
	private JButton exitButton = new JButton("X");

	/**
	 * Konstruktor tar emot tv� parameter. En referens till klassen kontroller
	 * och referens till klassen RadarPanel. Konstruktor skapar en Jframe och
	 * s�tter andra paneller och textf�ltdet i den JFrame. JFrame skapas som
	 * fullsk�rm med en backrund bild.
	 * 
	 * @param controller
	 */
	public Viewer(Controller controller, RadarPanel radarPanel) {
		this.controller = controller;
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		int centerX = (gd.getDisplayMode().getWidth()) / 2 - 250;
		// tas bor titel bar
		frame.setUndecorated(true);
		frame.setLayout(null);
		pnlRadar.setLayout(null);
		pnlRadar.setBounds(800, 0, 600, 500);
		pnlRadar.setBackground(Color.black);
		Info.setBackground(Color.black);
		Info.setForeground(Color.YELLOW);
		Info.setFont(new Font("default", Font.PLAIN, 20));
		// information om vad som h�nder n�r man trycker p� olika knappar
		Info.setText("\n" + "        W & UP ==> DRIVE FORWARD" + "\n" + "\n" + "         S & DOWN ==> DRIVE BACK" + "\n"
				+ "\n" + "         A & LEFT ==> TURN LEFT" + "\n" + "\n" + "         D & RIGHT ==> TURN RIGHT" + "\n"
				+ "\n" + "         R ==> START & STOP RADAR " + "\n" + "\n" + "         ENTER ==> START CAMERA" + "\n"
				+ "\n" + "         SPACE ==> STOP CAR");
		Info.setEditable(false);
		messageArea.setForeground(Color.yellow);
		messageArea.setBackground(Color.black);
		messageArea.setEditable(false);
		scroll.setBounds(centerX - 50, 540, 600, 180);
		speedLabel.setBounds(centerX + 195, 485, 110, 35);
		speedLabel.setForeground(Color.orange);
		speedLabel.setBorder(BorderFactory.createRaisedBevelBorder());
		speedLabel.setText("Speed   % 0");
		speedLabel.setHorizontalAlignment(SwingConstants.CENTER);
		speedLabel.setVerticalAlignment(SwingConstants.CENTER);
		radarPanel.setBounds(50, 0, 500, 425);
		radarPanel.setBackground(Color.black);
		pnlRadar.add(radarPanel);
		tabbed.setBounds(centerX - 50, 30, 600, 509);
		tabbed.add(pnlRadar, 0);
		tabbed.add(image, 1);
		tabbed.add(Info, 2);
		tabbed.setTitleAt(1, "Camera");
		tabbed.setTitleAt(0, "Radar");
		tabbed.setTitleAt(2, "Manual");
		exitButton.setBackground(new Color(10, 10, 10, 50));
		exitButton.setForeground(Color.RED);
		exitButton.setOpaque(false);
		exitButton.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		exitButton.setFont(new Font("default", Font.BOLD, 25));
		// om amn trycker p� knappen programmet ska st�ng ner
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		frame.setTitle("Mazumba");
		frame.setVisible(true);
		frame.setResizable(false);
		// fullsk�rm
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		background.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		background.setOpaque(false);
		exitButton.setBounds(frame.getWidth() - 60, 10, 40, 40);
		frame.add(exitButton);
		frame.add(speedLabel);
		frame.add(tabbed);
		frame.add(scroll);
		frame.add(background);
		frame.setFocusable(true);
		frame.requestFocusInWindow();
		frame.addKeyListener(this);

	}

	/**
	 * Metoden tar emot str�ng och skriver ut den i textf�lted i en ny rad.
	 * Varje g�ng metoden anropas den nya st�ngen skrivs ut h�gst up i
	 * textf�ltet. Om str�ngen b�rjar med bokstaven "d", f�rsta bokstaven tas
	 * bort fr�n str�ngen.
	 * 
	 * @param info
	 *            som ska skrivas ut i textf�lted.
	 */
	public void addInfo(String info) {
		if (info.startsWith("d")) {
			info = info.substring(1);
		}
		messageArea.setText(info + "\n" + messageArea.getText());

	}

	/**
	 * Metoden st�nger ner JFrame som inneh�ller alla komponenter.
	 */
	public void dispose() {
		frame.dispose();
	}

	/**
	 * Metoden tar emot en int v�rde och delar den till 2.55 f�r att omvandla
	 * v�rdet till i formen av procent. Den nya int v�rde skrivs i speedlabel
	 * och den representerar hastigheten av bilen i formen av procent.
	 * 
	 * @param speed
	 *            str�ngen som ska visas i f�nstret.
	 */
	public void setSpeed(int speed) {
		int speedLast = (int) (speed / 2.55);
		String res = "Speed  % " + speedLast;
		this.speedLabel.setText(res);
	}

	/**
	 * Metoden som best�mmer vad som ska h�nde n�r man trycker p� olika knappar.
	 * Varje g�ng anv�ndaren trycker p� en av de valda knapparna kontrolleras
	 * metoden isRaeadyToPlay om den returnerar true och tiden av senaste
	 * anropning och om skilnaden �r st�rre �n 350 d� anropas olika metoder som
	 * kopplad till olika knappar. Dessa regler g�ller f�r alla knappar f�rutom
	 * knappen "C","ENTER" och "R".
	 */
	public void keyPressed(KeyEvent id) {

		if (controller.isReadyToPlay()) {
			// Anropas en metod f�r att k�ra bilen fram�t.
			if (id.getKeyCode() == KeyEvent.VK_W || id.getKeyCode() == KeyEvent.VK_UP) {
				if (System.currentTimeMillis() - lastTimePressed > 350) {
					controller.forward();
					lastTimePressed = System.currentTimeMillis();
				}
			}
			// Anropas en metod f�r att sv�nga bilen till h�ger.
			if (id.getKeyCode() == KeyEvent.VK_D || id.getKeyCode() == KeyEvent.VK_RIGHT) {
				if (System.currentTimeMillis() - lastTimePressed > 350) {
					controller.Right();
					lastTimePressed = System.currentTimeMillis();
				}
			}
			// Anropas en metod f�r att k�ra bilen bak�t.
			if (id.getKeyCode() == KeyEvent.VK_S || id.getKeyCode() == KeyEvent.VK_DOWN) {
				if (System.currentTimeMillis() - lastTimePressed > 350) {
					controller.back();
					lastTimePressed = System.currentTimeMillis();
				}
			}
			// Anropas metoden som sv�nger bilen till v�nster.
			if (id.getKeyCode() == KeyEvent.VK_A || id.getKeyCode() == KeyEvent.VK_LEFT) {
				if (System.currentTimeMillis() - lastTimePressed > 350) {
					controller.Left();
					lastTimePressed = System.currentTimeMillis();
				}
			}
			// Anropas en motod som stoppar bilen.
			if (id.getKeyCode() == KeyEvent.VK_SPACE) {
				if (System.currentTimeMillis() - lastTimePressed > 350) {
					controller.stop();
					lastTimePressed = System.currentTimeMillis();
				}
			}
			// startar radarn
			if (id.getKeyCode() == KeyEvent.VK_R) {
				controller.startStopRadar();
			}
			if (id.getKeyCode() == KeyEvent.VK_ENTER) {
				controller.startCamera();

			}
			// Metoden som rensar radar f�nster anropas.
			if (id.getKeyCode() == KeyEvent.VK_C) {
				controller.clearRadar();

			}
		}
	}

	/**
	 * Metoden anropas n�r anv�ndaren sl�pper en av valda knappar
	 * (A,LEFT,D,RIGHT). Metoden straight anropas f�r att k�ra bilen rakt.
	 */
	public void keyReleased(KeyEvent e) {

		if (controller.isReadyToPlay()) {
			if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_D
					|| e.getKeyCode() == KeyEvent.VK_RIGHT) {
				controller.straight();
			}
		}
	}

	public void keyTyped(KeyEvent arg0) {

	}

	/**
	 * Metoden tar emot en st�ng som representerar s�kv�gen av en bild. Den
	 * gamla bild tas bort fr�n f�nster och den nya bilden visas.
	 * 
	 * @param path
	 */
	public void changePath(String path) {
		image = null;
		image = new Picture(path);
		tabbed.remove(1);
		tabbed.add(image, 1);
		tabbed.setTitleAt(1, "Camera");
		tabbed.setSelectedIndex(1);

	}

	/**
	 * Metoden byter valda f�nster som visas i JTabbedPane (tabbed) enligt int
	 * v�rde som tas emot.
	 * 
	 * @param index
	 *            f�nster nummer som ska v�ljas och visas.
	 */
	public void sellectTab(int index) {
		tabbed.setSelectedIndex(index);
	}

}