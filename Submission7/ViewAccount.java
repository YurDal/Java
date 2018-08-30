package project;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;



/**
 * ViewAccount is GUI-class viewing the second window to once signed in 
 * to search for a media
 * to borrow a media
 * to return a media
 * to show borrowed
 *
 * The class extends JPanel and implements Actionlistener
 * 
 * @author
 * 
 */
public class ViewAccount extends JPanel implements ActionListener {
	protected JFrame frame2 = new JFrame();
	private Controller controller;
	private JPanel signedIn = new JPanel();
	private JTextField searchID = new JTextField();
	private DefaultListModel listModel = new DefaultListModel();
	private DefaultListModel listBorrowed = new DefaultListModel();
	private JList result = new JList(listModel);

	private JButton searching = new JButton();
	private JButton borrowing = new JButton();
	private JButton returning = new JButton();
	private JButton borrowed = new JButton();
	private JButton exiting2 = new JButton();

	
	/**
	 * The Constructor with 2 parameters
	 * Creates the window with all the buttons
	 * Puts ActionListener to the buttons
	 * 
	 * @param Controller controller, String id
	 * 
	 */
	public ViewAccount(Controller controller, String id) {
		frame2.setTitle("Library Account" + " - " + id);
		this.controller = controller;
		searching.setText("Search");
		borrowing.setText("Borrow");
		returning.setText("Return");
		borrowed.setText("Borrowed");
		exiting2.setText("Sign out");

		searching.setPreferredSize(new Dimension(100, 25));
		borrowing.setPreferredSize(new Dimension(100, 25));
		returning.setPreferredSize(new Dimension(100, 25));
		borrowed.setPreferredSize(new Dimension(100, 25));
		exiting2.setPreferredSize(new Dimension(100, 25));

		searchID.setPreferredSize(new Dimension(205, 25));
		result.setPreferredSize(new Dimension(680, 370));
		result.setFixedCellWidth(680);
		

		signedIn.add(searchID);
		signedIn.add(searching);
		signedIn.add(borrowing);
		signedIn.add(returning);
		signedIn.add(borrowed);

		signedIn.add(result);
		signedIn.add(exiting2);

		signedIn.setPreferredSize(new Dimension(100, 120));

		frame2.setSize(700, 500);
		frame2.setResizable(false);


		frame2.add(signedIn, BorderLayout.CENTER);
		frame2.setLocation(300, 100);
		frame2.setVisible(true);

		searching.addActionListener(this);
		exiting2.addActionListener(this);
		borrowing.addActionListener(this);
		borrowed.addActionListener(this);
		returning.addActionListener(this);
	}

	
	
	
	/**
	 * Terminates the actual window and returns to a new view-window
	 * 
	 * @return void
	 * 
	 */
	public void exit() {
		Viewer signingIn = new Viewer(this.controller);
		frame2.dispose();
	}
	
	
	

	/**
	 * Returns the frame used for the window
	 * 
	 * @return JFrame frame2
	 * 
	 */
	public JFrame getFrame() {
		return frame2;
	}
	
	
	
	
	/**
	 * Returns the list used to represent all the media-items
	 * 
	 * @return JList result
	 * 
	 */
	public JList getJList() {
		return result;
	}
	
	
	
	
	/**
	 * Returns the actual list where the items are stored
	 * 
	 * @return DefaultListModel listModel
	 * 
	 */
	public DefaultListModel getList() {
		return listModel;
	}

	
	

	/**
	 * Returns the media id put in to search for a media
	 * 
	 * @return String getText
	 * 
	 */
	public String getSearchID() {
		return searchID.getText();
	}

	
	
	
	/**
	 * Prints out item not found in the DefaultListModel
	 * 
	 * @return void
	 * 
	 */
	public void itemNotFound() {
		listModel.clear();
		listModel.addElement("Item not found...");
	}

	
	
	
	/**
	 * Prints out item already borrowed in the DefaultListModel
	 * 
	 * @return void
	 * 
	 */
	public void itemAlreadyBorrowed() {
		listModel.clear();
		listModel.addElement("Item already borrowed...");
	}

	
	

	/**
	 * Prints out item now borrowed in the DefaultListModel
	 * 
	 * @param String title
	 * 
	 */
	public void itemBorrowed(String title) {
		listModel.clear();
		listModel.addElement("Item now borrowed..." + "\n" + title);
	}
	
	
	
	
	
	/**
	 * Prints out item now returned in the DefaultListModel
	 * 
	 * @param String ID
	 * 
	 */
	public void itemReturned(String ID) {
		listModel.clear();
		listModel.addElement("Item now returned..." + "\n" + ID);
	}
	
	
	
	
	

	/**
	 * ActionListener conditions for what happens in case a button i spressed
	 * Listens for:
	 * borrow-button
	 * search-button
	 * exit-button
	 * return-button
	 * borrowed-button
	 * 
	 * @param EctionEvent e
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == borrowing) {
			controller.borrow();
		}
		
		if (e.getSource() == searching) {
			controller.search();
		}

		if (e.getSource() == exiting2) {
			controller.signout();
		}

		if (e.getSource() == returning) {
			controller.returns();

		}

		if (e.getSource() == borrowed) {
			controller.showBorrowed();
		}

	}

	
}
