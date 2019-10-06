package edu.usm.cos420.antenatal.view.impl;

import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainView extends JFrame {
	/**
	 * Main view for the program
	 */
	private static int WIDTH = 600;
	private static int HEIGHT = 700;
	private static final long serialVersionUID = 2840735252655654044L;

	public static void main(String[] args) {
		MainView mainView = new MainView();
	}

	public MainView() {
		createMainView();
	}

	/**
	 * Creates the main view
	 */
	public static void createMainView() {
		JFrame mainView = new JFrame("Antenatal Care");
		JLabel welcomeLabel = new JLabel("Antenatal Care");
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		//JButton addPatient = new JButton("Add a new patient");
		JButton searchButton = new JButton("Search for a patient");
		JButton alertButton = new JButton("View Patient Alerts");

		/* Switches to the add patient view */
		/*addPatient.addActionListener( (e) -> {
		        mainView.dispose();
		        PatientView patientView = new PatientView();
		    });*/
		
		welcomeLabel.setFont((new Font("Serif", Font.PLAIN, 36)));
		welcomeLabel.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(welcomeLabel);
        //addPatient.setAlignmentX(CENTER_ALIGNMENT);
		alertButton.setAlignmentX(CENTER_ALIGNMENT);

		/* Switch to the search view */
		searchButton.addActionListener((e) -> {

			SearchForUserView v = new SearchForUserView();
		});

		/* Switch to the alert patient view */
		alertButton.addActionListener((e) -> {
			AlertView alertView = new AlertView();
		});

		searchButton.setAlignmentX(CENTER_ALIGNMENT);
		//panel.add(addPatient);
		panel.add(searchButton);
		panel.add(alertButton);

		mainView.setSize(WIDTH, HEIGHT);
		mainView.add(panel);
		mainView.setVisible(true);
		mainView.setDefaultCloseOperation(EXIT_ON_CLOSE);
		mainView.setLocationRelativeTo(null);
	}

}
