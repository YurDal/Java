package p3;
/**
 * Klassen sköter spelet, t.ex.
* ordnar med nytt spel (vid klick på Nytt spel)
* jämför spelarens val med datorns val och delar ut poäng
* avsluta spelet då en spelare nått 3 poäng
* avslutar programmet (vid klick på Avsluta).
 * 
 * @author yurdaer
 *
 */

public class SSPController {
	private SSPPlayer player;
	private SSPViewer viewer;         //Instansvariabler för att känna andra klasser
	private SSPUserInput userInput;

	private final int Rock = 0;       //För att kunna representera  valen sten, sax och påse i programmet          
	private final int Bag = 2;        //Kodas dessa med heltal
	private final int Socissors = 1;

	private int humanScore;
	private int compScore;

	public SSPController(SSPPlayer player, SSPViewer viewer) {   
		this.player = player;
		this.viewer = viewer;
	}

	public void setUserInput(SSPUserInput userInput) {
		this.userInput = userInput;
	}

	public void setUserInput(int choise) {                                              //Med detta kod om en av spelare uppnår till 3 poäng
		if ((humanScore == 3 || compScore == 3) && !(choise == 3 || choise == 4)) {     //Spelen ska återställas om använderen trycker button3(nytt spel)
			if (humanScore == 3) {                                                      
				viewer.setUpLine("GRATTIS! DU VANN");                                   
			} else if (compScore == 3) {
				viewer.setUpLine("KANSKE NÄSTA GÅNG :(");
			}
			userInput.disableButtons();             //dimmas butonger och visas information om vinnaren identitet
		}

		else if (choise == 3) {             
			humanScore = 0;
			compScore = 0;

			viewer.setUpLine("Först till 3 vinner");
			viewer.setHumanChose("");
			viewer.setCompChose("");
			viewer.setHumanScr(0);
			viewer.setCompScr(0);
		} else if (choise == 4) {                   //Programen ska stängas om anvendären trycker på knappen "Avsluta"
			System.exit(0);
		} else {
			int compInput = player.newChoice();     //Koden tar reda på datorspelarens val
			action(choise, compInput);

		}

	}

	public void action(int choise, int compInput) {   //Metoden jämför spelarens val med datorns val och delar ut poäng
		if (choise == Rock && compInput == Rock) {
			viewer.setCompChose("STEN");
			viewer.setHumanChose("STEN");
		} else if (choise == Bag && compInput == Bag) {
			viewer.setCompChose("PÅSE");
			viewer.setHumanChose("PÅSE");
		} else if (choise == Socissors && compInput == Socissors) {
			viewer.setCompChose("SAX");
			viewer.setHumanChose("SAX");
		}
		if (choise == Rock && compInput == Bag) {
			viewer.setCompChose("PÅSE");
			viewer.setHumanChose("STEN");
			compScore++;
		}
		if (choise == Rock && compInput == Socissors) {
			viewer.setCompChose("SAX");
			viewer.setHumanChose("STEN");
			humanScore++;
		}
		if (choise == Bag && compInput == Rock) {
			viewer.setCompChose("STEN");
			viewer.setHumanChose("PÅSE");
			humanScore++;
		}
		if (choise == Bag && compInput == Socissors) {
			viewer.setCompChose("SAX");
			viewer.setHumanChose("PÅSE");
			compScore++;
		}
		if (choise == Socissors && compInput == Bag) {
			viewer.setCompChose("PÅSE");
			viewer.setHumanChose("SAX");
			humanScore++;
		}
		if (choise == Socissors && compInput == Rock) {
			viewer.setCompChose("STEN");
			viewer.setHumanChose("SAX");
			compScore++;
		}
		viewer.setHumanScr(humanScore);        //Med hjälp av set motoder returerar nya poäng till SSPViewer
		viewer.setCompScr(compScore);

	}

}
