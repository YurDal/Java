package p2;

import java.util.*;

/** 
 * Klassens metoder och variabler anv�nds i
 *Exercise 2b. Klassan ninneh�lla tidsinformation 
 * @author  yurdaer
 */

public class Time {              //Instansvarianler g�r hela klassen     
	private int hour;
	private int minute;
	private int second;


	
	public Time(){
		Calendar cal = Calendar.getInstance();      //Konstruktor med parameter
		this.hour=cal.get(Calendar.HOUR_OF_DAY);
		this.minute=cal.get(Calendar.MINUTE);
		this.second=cal.get(Calendar.SECOND);

	}

	public int getHour(){              
		return this.hour;
	}
	public int getMinute(){
		return this.minute;
	}
	public int getSecond(){
		return this.second;
	}
	public String toString(){   //Metoden returnerar ett String objeckt
		return this.hour+":"+this.minute+":"+this.second;
	}
	public void update(){                    // Metoden anv�nder Klassen Calender f�r att ta reda p� dagens datum
		Calendar cal = Calendar.getInstance();
		this.hour=cal.get(Calendar.HOUR_OF_DAY);
		this.minute=cal.get(Calendar.MINUTE);
		this.second=cal.get(Calendar.SECOND);
	}
}