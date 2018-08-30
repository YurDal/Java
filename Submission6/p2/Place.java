package p2;

public class Place {
    private String name;
    private Position position;
/**
 * Klassen representerar en plats.
 * @param name
 * @param longitude
 * @param latitude
 */
    public Place(String name, double longitude, double latitude) {
        this.name = name;
        this.position = new Position(longitude, latitude);
    }
/**
 * Returnerar platsens namn
 * @return namn
 */
    public String getName() {
        return name;
    }
/**
 * Returnerar platsen position
 * @return postitionen
 */
    public Position getPosition() {
        return position;
    }
/**
 * Omnavdlar res info
 */
    public String toString() {
        return name;
    }
}
