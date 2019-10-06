package edu.usm.cos420.antenatal.view.impl;

import java.awt.Dimension;
import java.awt.Font;
import java.time.format.DateTimeFormatter;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.usm.cos420.antenatal.controller.VisitController;
import edu.usm.cos420.antenatal.domain.Visit;

public class VisitDetailsView extends JFrame {

	/**
	 * View for displaying patient details
	 */
	private static int WIDTH = 600;
	private static int HEIGHT = 700;
//	private static int maxColumns = 1;
	private static int TEXTFIELD_WIDTH = 300;
	private static int TEXTFIELD_HEIGHT = 30;
	private static final long serialVersionUID = 2840735252655654744L;

	public VisitDetailsView(Long visitID) {
		VisitController controller = new VisitController();
		createPatientDetailsView(visitID, controller);
	}

	/**
	 * Creates the view to display visit info for the selected patient visit
	 */
	public static void createPatientDetailsView(Long visitID, VisitController controller) {

		Visit visit = controller.getVisit(visitID);
		
		// Set up JFrame
		JFrame visitDetailsView = new JFrame("Visit Details");
		Dimension maxSize = new Dimension(TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

		if (visit != null) {
			
			/* Label for when the visit exists in the system */
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			String formattedDate = visit.getDateOfVisit().format(formatter);
			JLabel topLabel = new JLabel("Date of Visit: " + formattedDate);
			topLabel.setFont((new Font("Serif", Font.PLAIN, 22)));
			topLabel.setAlignmentX(CENTER_ALIGNMENT);
			panel.add(topLabel);

			/* Visit ID */
			JLabel IDLabel = new JLabel("Visit ID: " + visit.getVisitID());
			IDLabel.setAlignmentX(CENTER_ALIGNMENT);
			panel.add(IDLabel);
			
			// Is Pregnant
			JLabel pregnantLabel = new JLabel("Pregnant: " + visit.getPregnantStatus());
			pregnantLabel.setAlignmentX(CENTER_ALIGNMENT);
			panel.add(pregnantLabel);
		

			/* Gestation */
			JLabel gestLabel = new JLabel("Gestation: " + visit.getGestation() + " months");
			gestLabel.setAlignmentX(CENTER_ALIGNMENT);
			panel.add(gestLabel);
			if(!visit.getPregnantStatus().equalsIgnoreCase("Yes")) {
				gestLabel.setVisible(false);
			}

			/* Weight */
			JLabel weightLabel = new JLabel("Weight: " + visit.getWeight());
			weightLabel.setAlignmentX(CENTER_ALIGNMENT);
			panel.add(weightLabel);
			
			/* Remarks */
			JLabel remarksLabel = new JLabel("Remarks: " + visit.getRemarks());
			remarksLabel.setAlignmentX(CENTER_ALIGNMENT);
			panel.add(remarksLabel);

			// HIV
//			JLabel hivLabel = new JLabel("HIV: " + patient.getAilments().get("hiv"));
//			hivLabel.setAlignmentX(CENTER_ALIGNMENT);
//			panel.add(hivLabel);

			// Preeclampsia
//			JLabel preLabel = new JLabel("Preeclampsia: " + patient.getAilments().get("pe"));
//			preLabel.setAlignmentX(CENTER_ALIGNMENT);
//			panel.add(preLabel);

			// Syphilis
//			JLabel syphilisLabel = new JLabel("Syphilis: " + patient.getAilments().get("syphilis"));
//			syphilisLabel.setAlignmentX(CENTER_ALIGNMENT);
//			panel.add(syphilisLabel);

			// Anemia
//			JLabel anemiaLabel = new JLabel("Anemia: " + patient.getAilments().get("anemia"));
//			anemiaLabel.setAlignmentX(CENTER_ALIGNMENT);
//			panel.add(anemiaLabel);

		}

		/* Back button */
		JButton backButton = new JButton("Back");
		backButton.setAlignmentX(CENTER_ALIGNMENT);
		backButton.addActionListener((e) -> {
			visitDetailsView.dispose();
		});
		panel.add(backButton);

		visitDetailsView.setSize(WIDTH, HEIGHT);
		visitDetailsView.setDefaultCloseOperation(EXIT_ON_CLOSE);
		visitDetailsView.setVisible(true);
		visitDetailsView.add(panel);
		visitDetailsView.setLocationRelativeTo(null);
	}

}
