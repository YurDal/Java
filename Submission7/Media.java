package project;

/**
 * Represent a madia.
 * 
 * @author Grupp3 
 */
public abstract class Media {
	private String mediaID;
	private String title;
	private String year;
	private boolean barrowed;
/**
 * Konstrukts a new Media objekt
 * @param mediaID
 * @param title
 * @param year
 */
	public Media(String mediaID, String title, String year) {
		this.mediaID = mediaID;
		this.title = title;
		this.year = year;
		this.barrowed = false;
	}
/**
 * Sets a new ID
 * @param mediaID
 */
	public void setId(String mediaID) {
		this.mediaID = mediaID;

	}
/**
 * Returns ID
 * @return
 */
	public String getId() {
		return mediaID;
	}
/**
 * Compare två Media objekts
 */
	public boolean equals(Object obj) {
		Media media = (Media) obj;
		return mediaID.equals(media.getId());
	}
/**
 * Returns the title
 * @return
 */
	public String getTitle() {
		return title;
	}
/**
 * Sest a new title
 * @param title
 */
	public void setTitle(String title) {
		this.title = title;
	}
/**
 * Returns year
 * @return year
 */
	public String getYear() {
		return year;
	}
/**
 * Sets a new year
 * @param year
 */
	public void setYear(String year) {
		this.year = year;
	}
/**
 * Returns the state of the objekt
 * @return
 */
	public boolean isBarrowed() {
		return barrowed;
	}
/**
 * Sets a new state
 * @param barrowed
 */
	public void setBarrowed(boolean barrowed) {
		this.barrowed = barrowed;
	}
}