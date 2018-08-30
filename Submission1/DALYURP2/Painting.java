package p2;
/**
 * Klassen Painting inneh�ller variabler och metoder som anv�nds i 
 * Klassen Exercise2a
 * @author yurdaer
 *
 */
public class Painting {    //Instansvariabler f�r hela klassen
	private String title;
	private String painter;
	private int year;
	
	public Painting (String title, String painter, int year){  //Konstruktor som inneh�ller parameter
		this.title=title;
		this.painter=painter;
		this.year=year;
	}
	public String getTitle(){   
		return this.title;
	}
	public String getPainter(){
		return this.painter;
	}
	public int getYear(){
		return this.year;
	}
	public String toString(){           // Metoden toString returnerar ett String objeckt
		return this.title+" av "+this.painter+", "+this.year;
		
	}

}
