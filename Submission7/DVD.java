package project;

/**
 * Class DVD which extends the class Media
 *
 * Apart from extending the class Media it has certain methods only for DVDs
 * 
 * @author Grupp3
 * 
 */
public class DVD extends Media {
	private String[] actors;

	/**
	 * The constructor creates a Media object with inromation put in as
	 * parameters Gives the variable for actors a value
	 * 
	 * @param mediaID
	 * @param title
	 * @param year
	 * @param actors
	 */
	public DVD(String mediaID, String title, String year, String[] actors) {
		super(mediaID, title, year);
		this.actors = actors;

	}

	/**
	 * Gets the name of the actors and outs them in an array of Strings
	 * 
	 * @return String[] actors
	 * 
	 */

	public String[] getActors() {
		return actors;
	}

	/**
	 * Sets the name of the actors
	 * 
	 * @param String[]
	 *            actors
	 * 
	 */
	public void setActors(String[] actors) {
		this.actors = actors;
	}

	/**
	 * Method to print out all the information about the DVD
	 * 
	 * @return String toString
	 * 
	 */
	public String toString() {
		String list = " ";
		for (int i = 0; i < actors.length; i++) {
			if (i < actors.length && i != 0)
				list += " - ";
			list += actors[i];
		}

		String state;
		if (isBarrowed() == true) {
			state = "Busy";
		} else {
			state = "Available";
		}
		String res = "Media ID : " + getId() + ",  Title : " + getTitle() + ",  Year " + getYear() + ",  Actors : "
				+ list + ",  State : " + state;
		return res;

	}

}
