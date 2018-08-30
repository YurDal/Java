package project;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Viewer is GUI-class viewingthe first window to sign in 
 * or create an account
 * or delete an account
 * or just exit
 *
 * The class extends JPanel and implements Actionlistener
 * 
 * @author
 * 
 */
public class Viewer extends JPanel implements ActionListener {
	private JFrame frame = new JFrame("Library - Sign in");

	private JPanel signIn = new JPanel();
	private JLabel idEnter = new JLabel();
	private JLabel createDelete = new JLabel();

	private JButton signing = new JButton();
	private JButton exiting = new JButton();
	private JButton creating = new JButton();
	private JButton deleting = new JButton();
	
	private JTextField idNumber = new JTextField();
	private Controller controller;

	
	
	
	/**
	 * The constructor with Controller as parameter
	 * This constructor creates the window with the buttons
	 * and the text fields needed
	 * 
	 * @param Controller controller
	 * 
	 */
	public Viewer(Controller controller) {
		this.controller=controller;
		signing.setText("Sign in");
		exiting.setText("Exit");
		creating.setText("Create Account");
		deleting.setText("Delete Account");
		idEnter.setText("Please, enter your ID-number:");
		createDelete.setText("If not a member yet, sign up!");

		signing.setPreferredSize(new Dimension(100, 25));
		exiting.setPreferredSize(new Dimension(100, 25));
		//idEnter.setPreferredSize(new Dimension(205, 25));
		//createDelete.setPreferredSize(new Dimension(205, 25));
		idNumber.setPreferredSize(new Dimension(205, 25));
		creating.setPreferredSize(new Dimension(205, 25));
		signIn.setPreferredSize(new Dimension(300, 2100));

		signIn.add(idEnter);
		signIn.add(idNumber);
		signIn.add(signing);
		signIn.add(exiting);
		signIn.add(createDelete);
		signIn.add(creating);
		signIn.add(deleting);
		

		frame.setSize(300, 210);
		frame.setResizable(false);

		frame.add(signIn, BorderLayout.CENTER);
		frame.setLocation(100, 100);
		frame.setVisible(true);

		exiting.addActionListener(this);
		signing.addActionListener(this);
		creating.addActionListener(this);
		deleting.addActionListener(this);
	}


	
	
	/**
	 * Reads the input from the text field and returns a String
	 * Supposed to represent an ID-number
	 * 
	 * @return String
	 * 
	 */
	public String getID() {
		return idNumber.getText();
	}

	
	
	
	/**
	 * Getting the frame used to store all the buttons for the window
	 * Returns the frame
	 * 
	 * @return JFrame
	 * 
	 */
	public JFrame getFrame() {
		return frame;
	}


	
	
	/**
	 * Changes the label text telling to re-enter the ID-number
	 * 
	 * 
	 * @return void
	 * 
	 */
	public void tryAgain() {
		idEnter.setText("Wrong ID, please try again:");
	}



	
	/**
	 * ActionListener conditions in different cases depending on the 
	 * buttons clicked on
	 * Listens for: 
	 * exit-button
	 * sign in-button
	 * create account-button
	 * delete account-button
	 * 
	 * @return void
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == exiting) {
			System.exit(0);
		}

		if (e.getSource() == signing) {
			controller.signin();

		}
		
		if (e.getSource() == creating) {
			controller.createAccount();

		}
		
		if (e.getSource() == deleting) {
			controller.delete();

		}
	}

}
