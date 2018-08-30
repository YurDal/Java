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
 *         array distance och angle. Linjer som ska ritas panellen beräknas
 *         enligt värderna som sparas i de här int arrayer. I arreyen distance
 *         sparas avstånd värder, i arreyen angle sparas vinkel värder. Man kan
 *         ändra längden av radie som representerar max avstånd. Linjerna börjar
 *         ritas med färgen grönt tills de når avständ värde i arreyen avstånd
 *         eller radie. Därefter linjärna forsätter ritas med färjen röd. Röd
 *         färgen representerar objekt som radar hittat.
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
	 * Metoden ritar schablonen och skriver några rader som informerar vad olika
	 * färger btyder och vad är längden av radien..
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
	 * Metoden tar emot en Graphics objekt och forsätter ritar linjer enligt
	 * värderna som sparas i int arreyer angle och distance. Metoden innehåller
	 * for sats som går genom alla värde i int arreyer. Om int arreyers längt
	 * inte lika med noll tar värdet i postion noll i angle och
	 * distance.Beräknas riktningen av linjer enligt vinkel värde soparas i
	 * angle. Om distance värde större än radie beräknas riktingen och ritas
	 * grön linje. Om distance mindre än radie beräknas riktingen och ritas en
	 * grön linje som är lika långt som distance värde, sedan forsätts en röd
	 * linje i samma riktining tills total längd blir lika långd med radie.
	 * Samma operation görs för alla värde som finns i int arreyer.
	 * 
	 * @param g
	 *            grapich objekt som nya linjer ritas
	 */
	public void line(Graphics g) {

		if (distance.length != 0 && angle.length != 0) {
			for (int i = 0; i < angle.length; i++) {
				// om längden är mindre än radie
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
				// om längden är större än radie
				if (distance[i] >= Radie) {

					g.drawLine((int) (XCenter - (Math.cos(Math.toRadians(angle[i])) * Radie)),
							(int) (YCenter - (Math.sin(Math.toRadians(angle[i])) * Radie)), XCenter, YCenter);
				}
			}
		}

	}

	/**
	 * Metoden rensar alla linjer som ritades i panellen genom att fylla angle
	 * och distance med nollor. Till slut anropas repaint metoden för att
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
	 * Metoden lägger till nya värde i arreyer angle och distance. Metoden tar
	 * emot två parameter angle som representerar vinkel och distance som
	 * representerar avstånd. I slutat av metoden anropas repaint metoden för
	 * att uppdatera panellen. Om avstånd är mindre än 6 cm tilldelas den till
	 * ett värde som är större än radie därför att sensoren kan mäta mellan 5cm - 400 cm.
	 * 
	 * @param angle
	 *            vinkel värde som ska sparas i int arrey angle.
	 * @param distance
	 *            avstånd värde som ska sparas i int array distance.
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
