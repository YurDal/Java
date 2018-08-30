package p2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;
import javax.swing.JFrame;

/**
 * Klassen används för att styra ett program som visar vägen mellan två orter på
 * en karta. Användaren kan begära en vägbeskrivning mellan två orter. Och
 * resultatet beror på vilken sökalgoritm som användaren anget att programmet
 * ska använda för att generera vägbeskrivningen.
 * 
 * @author Yurdaer Dalkic
 *
 */
public class P2Controller {
	private Graph<String> graph = new Graph<String>();
	private MapView map;
	private ArrayList<Place> places;
	static Viewer app;
	private TreeMap<String, Road> roads;

	public P2Controller() {

	}

	/**
	 * Konstruktor som skapar listor av vägar, platser och kartan.
	 * 
	 * @param mapFile
	 *            kartan som visas på fönstret.
	 * @param pos1
	 *            kordinater på  hörnet.
	 * @param pos2
	 *            kordinater på  hörnet.
	 * @param placeFile
	 *            sökväg till filen som innehåller platser
	 * @param roadFile
	 *            sökväg till filen som innehåller platser på kartan
	 */
	public P2Controller(String mapFile, Position pos1, Position pos2, String placeFile, String roadFile) {
		app = new Viewer(this);
		places = P2Controller.readPlaces(placeFile);
		TreeMap<String, Road> current = readRoads(roadFile);
		roads = current;
		map = new MapView(mapFile, pos1.getLongitude(), pos1.getLatitude(), pos2.getLongitude(), pos2.getLatitude());
		ArrayList<Road> roadList = new ArrayList<Road>();
		Iterator<Road> values = roads.values().iterator();
		while (values.hasNext())
			roadList.add(values.next());
		makeGraph(places, roads);

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // stängbart
		frame.setPreferredSize(app.getPreferredSize());
		frame.setTitle("P2");
		frame.setResizable(true);
		frame.setVisible(true);
		frame.pack();
		frame.add(app);
		showMap();

	}

	/**
	 * Metoden läser filen och sätter innehållat av filen till en ArrayList
	 * 
	 * @param filename
	 *            är sökvägen till den listan som innehåller platser.
	 * @return En ArrayList som innehåller plats indfo.
	 */
	public static ArrayList<Place> readPlaces(String filename) {
		ArrayList<Place> places = new ArrayList<Place>();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"));
			String[] parts;
			double longitude, latitude;
			String text = br.readLine();
			while (text != null) {
				parts = text.split(" ");
				longitude = Double.parseDouble(parts[2]);
				latitude = Double.parseDouble(parts[1]);
				places.add(new Place(parts[0], longitude, latitude));
				text = br.readLine();
			}
			br.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		return places;
	}

	/**
	 * Metoden läser filen och sätter innehållat av filen till en ArrayList
	 * 
	 * @param filename
	 *            är sökvägen till den listan som innehåller platser.
	 * @return En ArrayList som innehåller plats indfo.
	 */
	public static TreeMap<String, Road> readRoads(String filename) {
		TreeMap<String, Road> res = new TreeMap<String, Road>();
		ArrayList<Position> path;
		String[] parts;
		String text;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"));
			text = br.readLine();
			while (text != null) {
				path = new ArrayList<Position>();
				parts = text.split(",");
				for (int i = 3; i < parts.length; i += 2) {
					path.add(new Position(Double.parseDouble(parts[i]), Double.parseDouble(parts[i + 1])));
				}
				int i = 0;
				res.put(parts[0] + "-" + parts[1], new Road(parts[0], parts[1], Integer.parseInt(parts[2]), path));
				text = br.readLine();
			}
			br.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		return res;
	}

	public void showMap() {
		app.Map.add(map);

	}

	public void makeGraph(ArrayList<Place> places, TreeMap<String, Road> roads) {
		Iterator<Road> values = roads.values().iterator();
		Road road;
		for (Place place : places) {
			graph.addVertex(place.getName());
		}
		while (values.hasNext()) {
			road = values.next();
			graph.addEdge(road.getFrom(), road.getTo(), road.getCost());
		}
	}

	/**
	 * Strategi vid sökning på djupet testa olika vägar tills man kommit till
	 * målet
	 * 
	 * @param from
	 *            start adress
	 * @param to
	 *            destination adress
	 */
	public void deepsearch(String from, String to) {
		ArrayList<Edge<String>> path;
		ArrayList<Road> roadList = new ArrayList<Road>();

		if (graph.containsVertex(from)) {
			path = GraphSearch.depthFirstSearch(graph, from, to);
			app.text.setText(null);
			for (Edge<String> edge : path) {
				roadList.add(roads.get(edge.getFrom() + "-" + edge.getTo()));

				app.text.append(edge.getFrom() + " -> " + edge.getTo() + " cost: " + edge.getWeight() + "\n");
			}
			map.showRoads(roadList);

		}
	}

	/**
	 * Strategi vid sökning på bredden är att utforska alla närliggande noder.
	 * Och därefter utforska deras närligande noder osv. På det viset breder
	 * sökningen ut sig från startnoden.
	 * 
	 *@param from
	 *            start adress
	 * @param to
	 *            destination adress
	 */
	public void breadthsearch(String from, String to) {
		ArrayList<Edge<String>> path;
		ArrayList<Road> roadList = new ArrayList<Road>();
		if (graph.containsVertex(from)) {
			path = GraphSearch.breadthFirstSearch(graph, from, to);
			app.text.setText(null);

			for (Edge<String> edge : path) {
				roadList.add(roads.get(edge.getFrom() + "-" + edge.getTo()));

				app.text.append(edge.getFrom() + " -> " + edge.getTo() + " cost: " + edge.getWeight() + "\n");
			}
			map.showRoads(roadList);

		}
	}
/**
 * Metoden visar den kortaste vegen mellan noder.
 * @param from
 * @param to
 */
	public void dijkstra(String from, String to) {
		ArrayList<Road> roadList = new ArrayList<Road>();
		if (graph.containsVertex(from)) {
			app.text.setText(null);

			for (Edge<String> edge : GraphSearch.dijkstraSearch(graph, from, to)) {
				roadList.add(roads.get(edge.getFrom() + "-" + edge.getTo()));
				app.text.append(edge.getFrom() + " -> " + edge.getTo() + " cost: " + edge.getWeight() + "\n");
			}
			map.showRoads(roadList);
		}

	}

}
