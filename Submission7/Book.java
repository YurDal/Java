package project;





/**
 * Class Book which extends the class Media 
 *
 * Apart from extending the class Media it has certain methods only for books
 * 
 * @author Grupp3
 * 
 */
public class Book extends Media {
	private String author;

	
	
	
	/**
	 * The constructor creates a Media object with inromation put in as parameters
	 * Gives the variable for author a value
	 * 
	 * 
	 * @param mediaID
	 * @param author
	 * @param title
	 * @param year
	 */
	public Book(String mediaID, String author, String title, String year) {
		super(mediaID, title, year);
		this.author = author;
	}

	
	
	/**
	 * Gets the name of the author
	 * 
	 * @return String author
	 * 
	 */
	public String getAuthor() {
		return author;
	}

	
	
	
	
	/**
	 * Sets the name of the author
	 * 
	 * @param String author
	 * 
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	
	
	
	
	
	/**
	 * Method to print out all the information about the book
	 * 
	 * @return String toString
	 * 
	 */
	public String toString() {

		String state;
		if(isBarrowed()==true){
			state = "Busy";
		}
		else{
			state="Available";
		}
		String res = getId() + ",   Title : " + getTitle() + ",   Year " + getYear() + ",   Author : " + author +",   State : "+state ;
		return res;

	}

}
