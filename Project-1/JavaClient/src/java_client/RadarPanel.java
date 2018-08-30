package java_client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 * 
 * @author Yurdaer Dalkic
 *         Klassen representerar en radar panel. Klassen har int
 *         array distance och angle. Linjer som ska ritas panellen ber�knas
 *         enligt v�rderna som sparas i de h�r int arrayer. I arreyen distance
 *         sparas avst�nd v�rder, i arreyen angle sparas vinkel v�rder. Man kan
 *         �ndra l�ngden av radie som representerar max avst�nd. Linjerna b�rjar
 *         ritas med f�rgen gr�nt tills de n�r avst�nd v�rde i arreyen avst�nd
 *         eller radie. D�refter linj�rna fors�tter ritas med f�rjen r�d. R�d
 *         f�rgen representerar objekt som radar hittat.
 */
public final class RadarPanel extends JPanel {
	private static final long serialVersionUID = -5606894491901340966L;
	private final int XCenter = 250;
	private final int YCenter = 400;
	private final int Radie = 230;
	private int[] distance = new int[181];
	private int[] angle = new int[181];

	private Graphics2D original;

	public RadarPanel() {

		this.setDoubleBuffered(true);
		this.setOpaque(false);

	}

	/**
	 * Metoden ritar schablonen och skriver n�gra rader som informerar vad olika
	 * f�rger btyder och vad �r l�ngden av radien..
	 * 
	 */
	public void paint(Graphics g) {
		original = (Graphics2D) g;
		original.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		original.setColor(Color.GREEN);
		original.drawArc(18, YCenter - 232, 462, 462, 0, 180);
		original.drawArc(132, YCenter - 118, 235, 235, 0, 180);
		original.drawLine(18, YCenter, 480, YCenter);
		original.setFont(new Font("default", Font.BOLD, 16));
		original.drawString("0", 10, YCenter + 15);
		original.drawString("90", 245, YCenter - 240);
		original.drawString("180", 465, YCenter + 15);
		original.drawLine(XCenter, YCenter, 480, YCenter);
		original.drawString("30", (int) (XCenter - (Math.cos(Math.toRadians(30)) * Radie)) - 20,
				(int) (YCenter - (Math.sin(Math.toRadians(30)) * Radie)) - 5);
		original.drawString("60", (int) (XCenter - (Math.cos(Math.toRadians(60)) * Radie) - 10),
				(int) (YCenter - (Math.sin(Math.toRadians(120)) * Radie)) - 10);
		original.drawString("120", (int) (XCenter - (Math.cos(Math.toRadians(120)) * Radie)),
				(int) (YCenter - (Math.sin(Math.toRadians(120)) * Radie)) - 10);
		original.drawString("150", (int) (XCenter - (Math.cos(Math.toRadians(150)) * Radie)),
				(int) (YCenter - (Math.sin(Math.toRadians(150)) * Radie)) - 5);
		g.drawLine((int) (XCenter - (Math.cos(Math.toRadians(30)) * Radie)),
				(int) (YCenter - (Math.sin(Math.toRadians(30)) * Radie)), XCenter, YCenter);
		g.drawLine((int) (XCenter - (Math.cos(Math.toRadians(60)) * Radie)),
				(int) (YCenter - (Math.sin(Math.toRadians(60)) * Radie)), XCenter, YCenter);
		g.drawLine((int) (XCenter - (Math.cos(Math.toRadians(120)) * Radie)),
				(int) (YCenter - (Math.sin(Math.toRadians(120)) * Radie)), XCenter, YCenter);
		g.drawLine((int) (XCenter - (Math.cos(Math.toRadians(150)) * Radie)),
				(int) (YCenter - (Math.sin(Math.toRadians(150)) * Radie)), XCenter, YCenter);
		g.drawLine((int) (XCenter - (Math.cos(Math.toRadians(90)) * Radie)),
				(int) (YCenter - (Math.sin(Math.toRadians(90)) * Radie)), XCenter, YCenter);
		g.drawRect(10, 20, 12, 12);
		g.drawString("Available", 30, 32);
		g.fillRect(10, 20, 12, 12);
		g.setColor(Color.red);
		g.drawRect(10, 55, 12, 12);
		g.drawString("Object", 30, 66);
		g.fillRect(10, 55, 12, 12);
		g.setColor(Color.green);
		g.drawString("Radian : 200 cm", 10, 100);
		line(g);
	}

	/**
	 * Metoden tar emot en Graphics objekt och fors�tter ritar linjer enligt
	 * v�rderna som sparas i int arreyer angle och distance. Metoden inneh�ller
	 * for sats som g�r genom alla v�rde i int arreyer. Om int arreyers l�ngt
	 * inte lika med noll tar v�rdet i postion noll i angle och
	 * distance.Ber�knas riktningen av linjer enligt vinkel v�rde soparas i
	 * angle. Om distance v�rde st�rre �n radie ber�knas riktingen och ritas
	 * gr�n linje. Om distance mindre �n radie ber�knas riktingen och ritas en
	 * gr�n linje som �r lika l�ngt som distance v�rde, sedan fors�tts en r�d
	 * linje i samma riktining tills total l�ngd blir lika l�ngd med radie.
	 * Samma operation g�rs f�r alla v�rde som finns i int arreyer.
	 * 
	 * @param g
	 *            grapich objekt som nya linjer ritas
	 */
	public void line(Graphics g) {

		if (distance.length != 0 && angle.length != 0) {
			for (int i = 0; i < angle.length; i++) {
				// om l�ngden �r mindre �n radie
				if (distance[i] < Radie && distance[i] != 0) {

					g.drawLine((int) (XCenter - (Math.cos(Math.toRadians(angle[i])) * distance[i])),
							(int) (YCenter - (Math.sin(Math.toRadians(angle[i])) * distance[i])), XCenter, YCenter);
					g.setColor(Color.red);
					g.drawLine((int) (XCenter - (Math.cos(Math.toRadians(angle[i])) * distance[i])),
							(int) (YCenter - (Math.sin(Math.toRadians(angle[i])) * distance[i])),
							(int) (XCenter - (Math.cos(Math.toRadians(angle[i])) * Radie)),
							(int) (YCenter - (Math.sin(Math.toRadians(angle[i])) * Radie)));
					g.setColor(Color.green);
				}
				// om l�ngden �r st�rre �n radie
				if (distance[i] >= Radie) {

					g.drawLine((int) (XCenter - (Math.cos(Math.toRadians(angle[i])) * Radie)),
							(int) (YCenter - (Math.sin(Math.toRadians(angle[i])) * Radie)), XCenter, YCenter);
				}
			}
		}

	}

	/**
	 * Metoden rensar alla linjer som ritades i panellen genom att fylla angle
	 * och distance med nollor. Till slut anropas repaint metoden f�r att
	 * uppdatera panellen.
	 */
	public void clear() {
		for (int i = 0; i < 181; i++) {
			angle[i] = 0;
			distance[i] = 0;
		}
		this.repaint();

	}

	/**
	 * Metoden l�gger till nya v�rde i arreyer angle och distance. Metoden tar
	 * emot tv� parameter angle som representerar vinkel och distance som
	 * representerar avst�nd. I slutat av metoden anropas repaint metoden f�r
	 * att uppdatera panellen. Om avst�nd �r mindre �n 6 cm tilldelas den till
	 * ett v�rde som �r st�rre �n radie d�rf�r att sensoren kan m�ta mellan 5cm - 400 cm.
	 * 
	 * @param angle
	 *            vinkel v�rde som ska sparas i int arrey angle.
	 * @param distance
	 *            avst�nd v�rde som ska sparas i int array distance.
	 */
	public void add(int angle, int distance) {
		angle = 180 - angle;
		if (distance <= 5) {
			distance = 240;
		}
		this.angle[angle] = angle;
		this.distance[angle] = (int) (distance * 1.15);
		this.repaint();
	}

}
