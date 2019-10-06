package edu.usm.cos420.antenatal.view.impl;

import java.awt.Dimension;
import java.awt.Font;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import domain.GenericPatient;
import edu.usm.cos420.antenatal.controller.AntenatalPatientController;
import edu.usm.cos420.antenatal.domain.AntenatalPatient;

public class PatientDetailsView extends JFrame {
	/**
	 * View for displaying patient details
	 */
	private static int WIDTH = 600;
	private static int HEIGHT = 700;
	private static int maxColumns = 1;
	private static int TEXTFIELD_WIDTH = 300;
	private static int TEXTFIELD_HEIGHT = 30;
	private static final long serialVersionUID = 2840735252655654044L;

	public PatientDetailsView(GenericPatient pat) {
		AntenatalPatientController controller = new AntenatalPatientController();
		createPatientDetailsView(pat, controller);
	}

	/**
	 * Creates the view to display patient info if the patient is already in the
	 * system. If the patient is not in the system, it will inform the user.
	 */
	public static void createPatientDetailsView(GenericPatient pat, AntenatalPatientController controller) {
		if(pat.getClass() == GenericPatient.class) {
			PatientView patview = new PatientView(pat);
		}
		else {
			AntenatalPatient patient = (AntenatalPatient) pat;

			// Set up JFrame
			JFrame patientDetailsView = new JFrame("Patient Details");
			Dimension maxSize = new Dimension(TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
			JPanel panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

			if (patient != null) {

				/* Label for when the patient exists in the system */
				JLabel topLabel = new JLabel("Patient Details");
				topLabel.setFont((new Font("Serif", Font.PLAIN, 36)));
				topLabel.setAlignmentX(CENTER_ALIGNMENT);
				panel.add(topLabel);

				/* Patient's Name */
				JLabel nameLabel = new JLabel("Name: " + patient.getFirstName() + " " + patient.getLastName());
				nameLabel.setAlignmentX(CENTER_ALIGNMENT);
				panel.add(nameLabel);

				/* Patient ID */
				JLabel IDLabel = new JLabel("ID: " + patient.getPatientId());
				IDLabel.setAlignmentX(CENTER_ALIGNMENT);
				panel.add(IDLabel);

				/* Address */
				JLabel addrLabel = new JLabel("Address: " + patient.getAddress());
				addrLabel.setAlignmentX(CENTER_ALIGNMENT);
				panel.add(addrLabel);

				/* Village */
				JLabel villageLabel = new JLabel("Village: " + patient.getVillage());
				villageLabel.setAlignmentX(CENTER_ALIGNMENT);
				panel.add(villageLabel);

				/* Gender */
				JLabel genderLabel = new JLabel("Gender: " + patient.getGender());
				genderLabel.setAlignmentX(CENTER_ALIGNMENT);
				panel.add(genderLabel);

				/* Age */
				JLabel ageLabel = new JLabel("Age: " + patient.getAge());
				ageLabel.setAlignmentX(CENTER_ALIGNMENT);
				panel.add(ageLabel);

				/* Date of Birth */
				JLabel dobLabel = new JLabel("Date of Birth: " + patient.getDateOfBirth());
				dobLabel.setAlignmentX(CENTER_ALIGNMENT);
				panel.add(dobLabel);

				/* Height */
				JLabel heightLabel = new JLabel("Height: " + patient.getHeight());
				heightLabel.setAlignmentX(CENTER_ALIGNMENT);
				panel.add(heightLabel);

				/* Weight */
				JLabel weightLabel = new JLabel("Weight: " + patient.getWeight());
				weightLabel.setAlignmentX(CENTER_ALIGNMENT);
				panel.add(weightLabel);

				/* Blood Type */
				JLabel btypeLabel = new JLabel("Blood Type: " + patient.getBloodType());
				btypeLabel.setAlignmentX(CENTER_ALIGNMENT);
				panel.add(btypeLabel);

				/* Is Pregnant */
				JLabel pregnantLabel = new JLabel("Pregnant: " + patient.getStatusPregnant());
				pregnantLabel.setAlignmentX(CENTER_ALIGNMENT);
				panel.add(pregnantLabel);

				/* Last Cycle */
				JLabel cycleLabel = new JLabel("Date of last menstrual cycle: " + patient.getLastCycle());
				cycleLabel.setAlignmentX(CENTER_ALIGNMENT);
				if(patient.getStatusPregnant().equalsIgnoreCase("Yes")) {
					panel.add(cycleLabel);
				}

				/* HIV */
				JLabel hivLabel = new JLabel("HIV: " + patient.getAilments().get("hiv"));
				hivLabel.setAlignmentX(CENTER_ALIGNMENT);
				panel.add(hivLabel);

				/* Preeclampsia */
				JLabel preLabel = new JLabel("Preeclampsia: " + patient.getAilments().get("pe"));
				preLabel.setAlignmentX(CENTER_ALIGNMENT);
				panel.add(preLabel);

				/* Syphilis */
				JLabel syphilisLabel = new JLabel("Syphilis: " + patient.getAilments().get("syphilis"));
				syphilisLabel.setAlignmentX(CENTER_ALIGNMENT);
				panel.add(syphilisLabel);

				/* Anemia */
				JLabel anemiaLabel = new JLabel("Anemia: " + patient.getAilments().get("anemia"));
				anemiaLabel.setAlignmentX(CENTER_ALIGNMENT);
				panel.add(anemiaLabel);

				/* Date of last visit */
				TreeMap<Long, LocalDate> visitHistory = patient.getVisitHistory();

				String text = "";
				if(visitHistory.isEmpty()) {
					text = "Date of last antenatal visit: (No visits on record)";
				}else {
					
					Long visitID = -1L;
					LocalDate visitDate = LocalDate.now();
					for(Map.Entry<Long,LocalDate> entry : visitHistory.entrySet()) {
						  Long temp = entry.getKey();
						  if(temp > visitID) {
							  visitID = temp;
							  visitDate = entry.getValue();
						  }
						}
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
					text = "Date of last antenatal visit: " + visitDate.format(formatter);
				}
				JLabel lastVisitLabel = new JLabel(text);
				lastVisitLabel.setAlignmentX(CENTER_ALIGNMENT);
				panel.add(lastVisitLabel);

				/* New Visit Button */
				JButton newVisitButton = new JButton("New Visit");
				newVisitButton.setAlignmentX(CENTER_ALIGNMENT);

				// On "New Visit" button press, go to new visit screen
				newVisitButton.addActionListener((e) -> {
					patientDetailsView.dispose();
					VisitView visitView = new VisitView(patient);
				});
				panel.add(newVisitButton);

				/* Visit History Button */
				JButton visitHistoryButton = new JButton("View Visit History");
				visitHistoryButton.setAlignmentX(CENTER_ALIGNMENT);

				// TODO: Transition to visit history screen instead of going back
				visitHistoryButton.addActionListener((e) -> {
					new VisitHistoryView(patient);
					//patientDetailsView.dispose();
				});

				panel.add(visitHistoryButton);

				/* Update Patient Info Button
			JButton updatePatientButton = new JButton("Update Patient Information");
			updatePatientButton.setAlignmentX(CENTER_ALIGNMENT);

			// TODO: Transition to update screen instead of going back
			updatePatientButton.addActionListener((e) -> {
				patientDetailsView.dispose();
			});
			panel.add(updatePatientButton);
				 */

			}

			/* New Search button */
			JButton newSearchButton = new JButton("New Search");
			newSearchButton.setAlignmentX(CENTER_ALIGNMENT);
			newSearchButton.addActionListener((e) -> {
				patientDetailsView.dispose();
			});
			panel.add(newSearchButton);

			patientDetailsView.setSize(WIDTH, HEIGHT);
			patientDetailsView.setDefaultCloseOperation(EXIT_ON_CLOSE);
			patientDetailsView.setVisible(true);
			patientDetailsView.add(panel);
			patientDetailsView.setLocationRelativeTo(null);
		}
	}
}
