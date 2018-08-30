package p2;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
/**
 * Klassen representerar fönstrer där använderen kan välja olika platser och ölika söknings metoder
 * @author Yurdaer Dalkic
 *
 */
public class Viewer extends JPanel implements ActionListener {
	private P2Controller controller;
	private JPanel pnlNorth = new JPanel(new BorderLayout());
	private JPanel pnlSouth = new JPanel(new BorderLayout());
	private JPanel pnlSouthWest = new JPanel(new GridLayout(3, 1));
	private JPanel pnlSouthEast = new JPanel(new GridLayout(3, 1));
	private JPanel pnlSouthWest1 = new JPanel(new BorderLayout());
	private JPanel pnlSouthWest2 = new JPanel(new BorderLayout());
	private JPanel pnlSouthWest3 = new JPanel(new BorderLayout());
	private ButtonGroup group = new ButtonGroup();
	protected JTextArea text = new JTextArea();
	private JTabbedPane tabbed = new JTabbedPane();
	private JLabel lblFrom = new JLabel(" Från ");
	private JLabel lblTo = new JLabel(" To     ");
	protected JRadioButton djup = new JRadioButton("Sökning på djupet");
	protected JRadioButton bred = new JRadioButton("Sökning på bredden");
	protected JRadioButton djkstar = new JRadioButton("Dijkstra");
	protected JButton Search = new JButton("Sök");
	protected JComboBox<String> boxFrån = new JComboBox(ListToString(controller.readPlaces("C:/places.txt")));
	protected JComboBox<String> boxTo = new JComboBox(ListToString(controller.readPlaces("C:/places.txt")));
	protected JPanel Map = new JPanel();
/*
 * Konstruktor
 */
	public Viewer(P2Controller controller) {
		this.controller = controller;
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(700, 730));
		add(pnlNorth, BorderLayout.NORTH);
		add(pnlSouth, BorderLayout.SOUTH);
		pnlSouth.setPreferredSize(new Dimension(700, 80));

		pnlSouthEast.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		pnlNorth.add(tabbed);
		tabbed.add("Karta", Map);

		text.setPreferredSize(new Dimension(600, 450));
		tabbed.add("Text", text);
		pnlSouth.add(pnlSouthWest, BorderLayout.CENTER);
		pnlSouth.add(pnlSouthEast, BorderLayout.EAST);
		pnlSouthWest.add(pnlSouthWest1);
		pnlSouthWest.add(pnlSouthWest2);
		pnlSouthWest.add(pnlSouthWest3);

		pnlSouthEast.add(djup);
		pnlSouthEast.add(bred);
		pnlSouthEast.add(djkstar);
		group.add(djup);
		group.add(bred);
		group.add(djkstar);
		pnlSouthEast.setBorder(BorderFactory.createTitledBorder("Sökaltarnative"));

		pnlSouthWest3.add(Search, BorderLayout.CENTER);
		pnlSouthWest1.add(lblFrom, BorderLayout.WEST);
		pnlSouthWest1.add(boxFrån, BorderLayout.CENTER);
		pnlSouthWest2.add(lblTo, BorderLayout.WEST);
		pnlSouthWest2.add(boxTo, BorderLayout.CENTER);
		djup.setSelected(true);
		Search.addActionListener(this);
		djup.addActionListener(this);
		bred.addActionListener(this);
		djkstar.addActionListener(this);

	}
/**
 * Metoden omvandlarArrayListan till sen Strän
 * @param list
 * @return
 */
	public String[] ListToString(ArrayList<Place> list) {
		String[] array = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			array[i] = list.get(i).getName();
		}
		return array;

	}
/**
 * Returnerar den valda elementen i CompoBox
 * @return Yurdaer Dalkic
 */
	public String getfrom() {
		return boxFrån.getSelectedItem().toString();
	}
/**
 *  Returnerar den valda elementen i CompoBox

 */
	public String getto() {
		return boxTo.getSelectedItem().toString();
	}
/**
 * Metodentar innehåller listener för Viewer obj.
 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Search) {
			if (djup.isSelected()) {
				controller.deepsearch(getfrom(), getto());

				System.out.println("3");

			}
			if (bred.isSelected()) {
				controller.breadthsearch(getfrom(), getto());

			}
			if (djkstar.isSelected()) {
				controller.dijkstra(getfrom(), getto());

			}
		}

	}

}
