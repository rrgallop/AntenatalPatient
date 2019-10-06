package edu.usm.cos420.antenatal.view.impl;

import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.PatientDao;
import domain.GenericPatient;
import edu.usm.cos420.antenatal.controller.AntenatalPatientController;
import edu.usm.cos420.antenatal.dao.domain.AntenatalPatientDao;
import edu.usm.cos420.antenatal.service.impl.ValidationService;
import services.PatientSearch;


public class SearchForUserView extends JFrame {
	/**
	 * View to search for a user
	 */
	private static final long serialVersionUID = -3553212210695554861L;
	private static int WIDTH = 600;
	private static int HEIGHT = 700;
	private static int textColumns = 1;
	private static int TEXTFIELD_WIDTH = 250;
	private static int TEXTFIELD_HEIGHT = 30;

	public static void main(String[] args) {
		SearchForUserView v = new SearchForUserView();

	}

	public SearchForUserView() {
		createSearchView();
	}

	public static void createSearchView() {
		Dimension maxSize = new Dimension(TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		JFrame searchView = new JFrame("Search for a user");
		JPanel panel = new JPanel();

		/* Welcome label */
		JLabel welcomeLabel = new JLabel("Enter Patient ID");
		welcomeLabel.setFont((new Font("Serif", Font.PLAIN, 36)));
		welcomeLabel.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(welcomeLabel);

		/* Text input */
		JTextField userIDField = new JTextField(textColumns);
		userIDField.setMaximumSize(maxSize);
		userIDField.setToolTipText("Enter a patient id like 1000,1001...");
		panel.add(userIDField);

		/* Search button */
		JButton searchButton = new JButton("Search");
		searchButton.setAlignmentX(CENTER_ALIGNMENT);

		searchButton.addActionListener((e) -> {
			AntenatalPatientController controller = new AntenatalPatientController();
			if (!ValidationService.isUserIDValid(userIDField)) {
				JOptionPane.showMessageDialog(searchView, "Please enter a valid patient ID", "Error",
						JOptionPane.ERROR_MESSAGE, null);
			}else {

				String input = userIDField.getText().trim();
				AntenatalPatientDao apDao = new AntenatalPatientDao();
				PatientDao pDao = new PatientDao();
				PatientSearch apsearch = new PatientSearch((PatientDao)apDao);
				PatientSearch gpsearch = new PatientSearch(new PatientDao());
				ArrayList<GenericPatient> ap = apsearch.idSearch(Long.parseLong(input));
				ArrayList<GenericPatient> gp = gpsearch.idSearch(Long.parseLong(input));
				userIDField.setText("");
		
				if(!ap.isEmpty()) new PatientDetailsView(ap.get(0));
				else if(!gp.isEmpty()) new PatientDetailsView(gp.get(0));
				
			}		
		});
		panel.add(searchButton);

		/* Cancel button */
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener((e) -> {
			searchView.dispose();
		});
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		cancelButton.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(cancelButton);

		/* Add panel to JFrame */
		searchView.setSize(WIDTH, HEIGHT);
		searchView.add(panel);
		searchView.setVisible(true);
		searchView.setDefaultCloseOperation(EXIT_ON_CLOSE);
		searchView.setLocationRelativeTo(null);
	}

}