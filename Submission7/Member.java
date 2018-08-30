package project;

/**
 * Represents a member
 * 
 * @author Grupp3
 *
 */
public class Member {
	private ArrayList<Media> loan;
	private String memberID;
	private String name;
	private String phoneNumber;

	/**
	 * Konstrukts a new Member objekt
	 * 
	 * @param memberID
	 * @param name
	 * @param phoneNumber
	 */
	public Member(String memberID, String name, String phoneNumber) {
		this.memberID = memberID;
		this.name = name;
		this.phoneNumber = phoneNumber;
		loan = new ArrayList<Media>();
	}

	/**
	 * Adds a new objekt to the list of barrowed objekts
	 * 
	 * @param media
	 */
	public void addToList(Media media) {
		loan.addLast(media);
	}

	/**
	 * Delete objekt from the list
	 * 
	 * @param media
	 */
	public void deleteFromList(Media media) {
		loan.remove(loan.indexOf(media));
	}

	/**
	 * Returns the list of barrowed objekts
	 * 
	 * @return
	 */
	public ArrayList<Media> getList() {
		return loan;
	}

	/**
	 * Returns the ID
	 * @return ID
	 */
	public String getMemberID() {
		return memberID;
	}
/**
 * Sets a new ID
 * @param memberID
 */
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
/**
 * Returns the members name
 * @return name
 */
	public String getName() {
		return name;
	}
/**
 * Sets a new name
 * @param name
 */
	public void setName(String name) {
		this.name = name;
	}
/**
 * Returns the phone number
 * @return phoneNumber
 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
/**
 * Prints the list of barrowed Medias
 * @return list
 */
	public String printList() {
		String res = "";
		for (int i = 0; i < loan.size(); i++) {
			res += loan.get(i).toString();
			res += "\n";
		}
		return res;

	}
/**
 * Prints information of the member
 */
	public String toString() {
		String res = memberID + ";" + name + ";" + phoneNumber + "\n";
		return res;

	}

}
