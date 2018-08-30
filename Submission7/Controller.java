package project;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;



/**
 * Controller-class where all the logic of the spoftware is
 * Once started it will create a Viewer object to be able to sign in
 * Some logics behind the signing in, exiting, creating an account or deleting an account
 * Other logics to get data from and to text files
 * 
 * @author
 * 
 */
public class Controller {
	private HashtableOH<String, Media> mediaTable;
	private static BinarySearchTree<String, ArrayList> loans;
	private static BinarySearchTree<String, Member> memberTree;
	private static ArrayList<String> allLoans;

	private Viewer viewer;
	private ViewAccount account;
	private Member member;
	
	
	
	/**
	 * The Constructor with 3 parameters
	 * Creates the window to sign in
	 * Gets data from 3 text files, for members, medias and loans
	 * 
	 * @param memberFile
	 * @param mediaFile
	 * @param loansFile
	 */
	public Controller(String memberFile, String mediaFile, String loansFile) {
		
		viewer = new Viewer(this);
		memberTree = readMember(memberFile);
		mediaTable = readMedia(mediaFile);
		loans = readLoans(loansFile);
		allLoans = readAllLoans();
		
	}
	


	
	/**
	 * Used to get the member id and sign in with it
	 * Condition to have the member id stored or otherwise try again
	 * 
	 * @return void
	
	 */
	public void signin() {
		String id = viewer.getID();
		if (memberTree.contains(id)) {
			member = memberTree.get(id);
			account = new ViewAccount(this, member.getName());
			viewer.getFrame().dispose();
		} else {
			viewer.tryAgain();
		}
	}

	
	
	
	/**
	 * Used to get the media id and search for the media connected to the id
	 * Condition to have the media id stored or otherwise item will not be found
	 * 
	 * @return void
	 * 
	 */
	public void search() {
		String id = account.getSearchID();
		Media res;
		if (mediaTable.containsKey(id)) {
			res = mediaTable.get(id);
			account.getList().clear();
			account.getList().addElement(res.toString());
		} else {
			account.itemNotFound();
		}
	}

	
	
	
	/**
	 * Used to get the frame opened and dispose it
	 * Will open a new Viewer-window to sign in again
	 * 
	 * @return void
	 * 
	 */
	public void signout() {
		account.frame2.dispose();
		viewer = new Viewer(this);
	}

	
	
	
	/**
	 * Gets the list of media id¨s for all the media objects borrowed of the member
	 * Prints out as a list all the media objects borrowed
	 * 
	 * @return void
	 * 
	 */
	public void showBorrowed() {
		ArrayList<String> l = loans.get( member.getMemberID() );
		account.getList().clear();
		Media m;

		for (int i = 0; i < l.size(); i++ ) {
			m = mediaTable.get( l.get(0) );
			account.getList().addElement(m.toString());
		}
	}

	
	
	
	/**
	 * Gets the selected item from the list shown to return
	 * Gets all the the medias borrowed by this member and checks if the selected item is borrowed by this member
	 * Delets the media from the list of borrowed items for this member
	 * 
	 * Re-writes the lists of borrowed items to the text file
	 * 
	 * @return void
	 * 
	 */
	public void returns() {
		String selected = account.getJList().getSelectedValue().toString();
		String mediaID = selected.substring(0, 6);
		Media m = mediaTable.get( mediaID );
		ArrayList<String> l = loans.get( member.getMemberID() );
		
		for ( int i = 0; i < l.size(); i++) {
			if (l.get(i).equals( mediaID ) ) {
				m.setBarrowed(false);

				account.getList().clear();
				account.getList().addElement( m.getTitle() + " - is now returned!");

				l.remove(i);
				i = l.size();
				loans.put(member.getMemberID(), l);
				allLoans.remove( allLoans.indexOf( mediaID )  );
			}
		}
		reWriteLoans();
	}

	
	
	
	
	/**
	 * Gets the selected item from the list shown to borrow
	 * Checks if this media is borrowed by any member since before
	 * 
	 * 
	 * Re-writes the lists of borrowed items to the text file
	 * 
	 * @return void
	 * 
	 */
	public void borrow() {
		String selected = account.getJList().getSelectedValue().toString();
		String mediaID = selected.substring(0, 6);

			if ( allLoans.indexOf( mediaID ) != -1 ) {
				account.itemAlreadyBorrowed();
			} else {
				Media m = mediaTable.get(mediaID);
				m.setBarrowed(true);
 				member.addToList(m);
 				account.getList().clear();
 				account.getList().addElement( m.getTitle() + " - is now borrowed!");
 				loans.get(member.getMemberID()).addLast(mediaID);
 				allLoans.add( mediaID );
			}	
			reWriteLoans();
	}
	
	
	
	
	/**
	 * Private method to be used through other public methods in this class Controller
	 * It will re-write the data of loans to the text filee.
	 * 
	 * 
	 * Re-writes the lists of borrowed items to the text file
	 * 
	 * @return void
	 * 
	 */
	private void reWriteLoans() {
		try
		{
			String filename= "C:/Loans.txt";
			FileWriter clear = new FileWriter(filename);
			clear.write("");
			clear.close();
			
			List<String> keys = loans.keys();
			
			for (int j = 0; j < keys.size(); j++) {
				ArrayList<String> l = loans.get( keys.get(j) );
				FileWriter wr = new FileWriter(filename,true);
				wr.write( keys.get(j) );
				wr.close();

				for (int i = 0; i < l.size(); i++) {
					FileWriter fw = new FileWriter(filename,true); //the true will append the new data
					fw.write( ";" + l.get(i) );//appends the string to the file
					fw.close();
				}
				
			    FileWriter dw = new FileWriter(filename,true); 
			    dw.write(";" + "\r\n");
			    dw.close();
			}
		}
		catch(IOException ioe)
		{
			System.err.println("IOException: " + ioe.getMessage());
		}
		
	}
	
	
	
	
	/**
	 * Used to create an account of membership at the library
	 * Checks if  enetered id number is or is not stored in the system since before
	 * 
	 * 
	 * Re-writes the lists of members to the text file and the text file for the loans
	 * 
	 * @return void
	 * 
	 */
	public void createAccount() {
		String newPersonnummer = JOptionPane.showInputDialog("Mata in personnummer:");
		if ( memberTree.contains(newPersonnummer) ) {
			JOptionPane.showMessageDialog(null, "Ditt personnummer finns redan hos oss sedan tidigare: " + memberTree.get(newPersonnummer).getName());
		}
		else {
			String newNamn = JOptionPane.showInputDialog("Mata in namn:");
			String newTelefon = JOptionPane.showInputDialog("Mata in telefonnummer:");
			try
			{
				String filename= "C:/Lantagare.txt";
				FileWriter fw = new FileWriter(filename,true); //the true will append the new data
				fw.write(newPersonnummer + ";" + newNamn + ";" + newTelefon + "\r\n");//appends the string to the file
				fw.close();
				loans.put( newPersonnummer,  new ArrayList<String>() );
				reWriteLoans();
			}
			catch(IOException ioe)
			{
				System.err.println("IOException: " + ioe.getMessage());
			}
		}
	}
	
	
	
	
	/**
	 * Used to delete an account of membership at the library
	 * Checks if  enetered id number is or is not stored in the system since before
	 * 
	 * 
	 * Re-writes the lists of members to the text file and the text file for the loans
	 * 
	 * @return void
	 * 
	 */
	public void delete() {
		String newPersonnummer = JOptionPane.showInputDialog("Mata in personnummer:");
		if ( memberTree.contains(newPersonnummer) ) {
			memberTree.remove(newPersonnummer);
			try
			{
				String filename= "C:/Lantagare.txt";
				FileWriter clear = new FileWriter(filename);
				clear.write("");
				clear.close();
				List<String> keys = memberTree.keys();
				for (int i = 0; i < memberTree.size(); i++) {
					FileWriter fw = new FileWriter(filename,true); //the true will append the new data
					fw.write( memberTree.get( keys.get(i) ) + "\r\n" );//appends the string to the file
					fw.close();
					loans.remove( newPersonnummer );
					reWriteLoans();
				}
			}
			catch(IOException ioe)
			{
				System.err.println("IOException: " + ioe.getMessage());
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Tyv�rr, vi kunde ej hitta ditt personnummer");
		}
	}


	
	
	/**
	 * Private meethod to get data from member data text file
	 * Will return a tree of all the members
	 * Uses the file name as a parameter as a String
	 * 
	 * Reads all the data from a text file
	 * 
	 * @return BinarySearchTree<String, Member> readMember
	 * @param String filename
	 */
	private static BinarySearchTree<String, Member> readMember(String filename) {
		BinarySearchTree<String, Member> res = new BinarySearchTree<String, Member>();
		ArrayList<Member> path = new ArrayList<Member>();
		String[] parts;
		String text;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"));
			text = br.readLine();
			while (text != null) {
				parts = text.split(";");
				path.add(new Member(parts[0], parts[1], parts[2]));
				text = br.readLine();
			}

			for (int i = 0; i < path.size(); i++) {
				res.put(path.get(i).getMemberID(), path.get(i));
			}
			br.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		return res;
	}

	
	
	/**
	 * Private meethod to get data from media data text file
	 * Will return a hash table of all the medias
	 * Uses the file name as a parameter as a String
	 * 
	 * Reads all the data from a text file
	 * 
	 * @return HashtableOH<String, Media> readMedia
	 * @param String filename
	 */
	private static HashtableOH<String, Media> readMedia(String filename) {
		HashtableOH<String, Media> res = null;
		ArrayList<Media> path = new ArrayList<Media>();
		String[] parts;
		String text;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"));
			text = br.readLine();
			while (text != null) {
				parts = text.split(";");
				if (parts[0].equals("Bok")) {
					path.add(new Book(parts[1], parts[2], parts[3], parts[4]));
				}
				if (parts[0].equals("Dvd")) {
					String actors[] = new String[parts.length - 4];
					int index = 0;
					for (int i = 4; i < parts.length; i++) {
						actors[index] = parts[i];
						index++;
					}
					path.add(new DVD(parts[1], parts[2], parts[3], actors));
				}
				text = br.readLine();
			}
			res = new HashtableOH<String, Media>(path.size());
			for (int i = 0; i < path.size(); i++) {
				res.put(path.get(i).getId(), path.get(i));
			}
			br.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		return res;
	}
	
	
	
	
	/**
	 * Private meethod to get data from loans data text file
	 * Will return a tree of all the members with their loans
	 * Uses the file name as a parameter as a String
	 * 
	 * Reads all the data from a text file
	 * 
	 * @return BinarySearchTree<String, ArrayList> readLoans
	 * @param String filename
	 */
	private static BinarySearchTree<String, ArrayList> readLoans(String filename) {
		BinarySearchTree<String, ArrayList> res = new BinarySearchTree<String, ArrayList>();
		ArrayList<String> path = new ArrayList<String>();
		String[] parts;
		String text;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"));
			text = br.readLine();
			while (text != null) {
				parts = text.split(";");
				for (int i = 1; i < parts.length; i++) {
					path.add( parts[i] );
				}
				res.put(parts[0], path);
				path = new ArrayList<String>();
				text = br.readLine();
			}
			br.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		return res;
	}
	
	
	
	
	
	/**
	 * Private meethod to get data from loans data text file
	 * Will return an arraylist of all the loans without the members
	 * 
	 * 
	 * Reads all the data from previous methods for memberTree and loans
	 * 
	 * @return ArrayList<String> readAllLoans
	 * 
	 */
	private static ArrayList<String> readAllLoans() {
		ArrayList<String> theList = new ArrayList<String>();
		List<String> keys = memberTree.keys();
		
		for (int i = 0; i < loans.size();  i++) {
			ArrayList<String> l = loans.get(  keys.get(i)  );
			for (int j = 0; j < l.size(); j++ ) {
				theList.add( l.get(j) );
			}
		}
		return theList;
	}


}
