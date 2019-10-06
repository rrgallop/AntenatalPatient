package edu.usm.cos420.antenatal.view.impl;

import java.awt.Dimension;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.TreeMap;

import edu.usm.cos420.antenatal.*;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import edu.usm.cos420.antenatal.controller.AntenatalPatientController;
import edu.usm.cos420.antenatal.controller.VisitController;
import edu.usm.cos420.antenatal.domain.AntenatalPatient;
import edu.usm.cos420.antenatal.service.impl.ValidationService;

public class VisitView extends JFrame {

	/**
	 * View for adding a new visit
	 */
	private static int WIDTH = 600;
	private static int HEIGHT = 700;
	private static int maxColumns = 1;
	private static int TEXTFIELD_WIDTH = 300;
	private static int TEXTFIELD_HEIGHT = 30;
	private static int REMARKS_HEIGHT = 150;
	private static final long serialVersionUID = 5056571675604314963L;
	private static AntenatalPatient thisPatient;

	public VisitView(AntenatalPatient patient) {
		thisPatient = patient;
		createVisitView();
	}

	public static void createVisitView() {

		JFrame addVisitView = new JFrame("Add a new visit");
		Dimension maxSize = new Dimension(TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

		JButton addVisitButton = new JButton("Add");
		JButton cancelButton = new JButton("Cancel");

		JLabel dateLabel = new JLabel("Date of Visit");
		JTextField date = new JTextField(maxColumns);

		LocalDate now = LocalDate.now();
		int month = now.getMonthValue();
		int day = now.getDayOfMonth();
		int year = now.getYear();
		date.setText(month + "/" + day + "/" + year);
		date.setEditable(false);
		date.setMaximumSize(maxSize);
		date.setAlignmentX(CENTER_ALIGNMENT);
		dateLabel.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(dateLabel);
		panel.add(date);

		JLabel nameLabel = new JLabel("Name");
		JTextField name = new JTextField(maxColumns);
		name.setMaximumSize(maxSize);
		name.setAlignmentX(CENTER_ALIGNMENT);
		name.setText(thisPatient.getFirstName() + " " + thisPatient.getLastName());
		name.setEditable(false);
		nameLabel.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(nameLabel);
		panel.add(name);

		/* Is Pregnant */
		JLabel pregnantLabel = new JLabel("Currently Pregnant");
		JRadioButton radioIsPregnant = new JRadioButton("Yes", true);
		JRadioButton radioNotPregnant = new JRadioButton("No");
		ButtonGroup pregnantGroup = new ButtonGroup();
		pregnantGroup.add(radioNotPregnant);
		pregnantGroup.add(radioIsPregnant);
		pregnantLabel.setAlignmentX(CENTER_ALIGNMENT);
		radioNotPregnant.setAlignmentX(CENTER_ALIGNMENT);
		radioIsPregnant.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(pregnantLabel);
		panel.add(radioNotPregnant);
		panel.add(radioIsPregnant);

		/**** Last Cycle ****/
		JLabel cycleLabel = new JLabel("Date of Last Menstrual Cycle");
		cycleLabel.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(cycleLabel);

		/* Month */
		String[] months = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
		JLabel monthLabel = new JLabel("Month");
		monthLabel.setAlignmentX(CENTER_ALIGNMENT);
		JComboBox monthDropdown2 = new JComboBox(months);
		monthDropdown2.setMaximumSize(monthDropdown2.getPreferredSize());
		monthDropdown2.setSelectedIndex(thisPatient.getLastCycle().getMonthValue()-1);
		monthDropdown2.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(monthLabel);
		panel.add(monthDropdown2);

		/* Day */
		String[] days = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15",
				"16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
		JLabel dayLabel = new JLabel("Day");
		dayLabel.setAlignmentX(CENTER_ALIGNMENT);
		JComboBox dayDropdown2 = new JComboBox(days);
		dayDropdown2.setMaximumSize(dayDropdown2.getPreferredSize());
		dayDropdown2.setSelectedIndex(thisPatient.getLastCycle().getDayOfMonth()-1);
		dayDropdown2.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(dayLabel);
		panel.add(dayDropdown2);

		/* Year */
		int stopYear = LocalDate.now().getYear() - 120;
		String[] years = populateYearArray(stopYear);
		JLabel yearLabel = new JLabel("Year");
		yearLabel.setAlignmentX(CENTER_ALIGNMENT);
		JComboBox yearDropdown2 = new JComboBox(years);
		yearDropdown2.setMaximumSize(yearDropdown2.getPreferredSize());
		yearDropdown2.setSelectedIndex(getYearIndex(years));
		yearDropdown2.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(yearLabel);
		panel.add(yearDropdown2);

		// Certify that RAM has been performed
		JCheckBox RAMButton = new JCheckBox("RAM assessment performed*");
		RAMButton.setMaximumSize(maxSize);
		RAMButton.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(RAMButton);

		/* Weight */
		JLabel weightLabel = new JLabel("Weight (kg)");
		JTextField weight = new JTextField(maxColumns);
		weight.setMaximumSize(maxSize);
		weight.setAlignmentX(CENTER_ALIGNMENT);
		weightLabel.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(weightLabel);
		panel.add(weight);

		/* According to the provided informational packets, all women should have 4 antenatal care visits,
		 * one before 4 months, then at 6 months, then at 8 months, then at 9 months.
		 * This seems like a good place to start for setting gestation
		 */
		JLabel gestationLabel = new JLabel("Gestation*");
		JRadioButton month4gestation = new JRadioButton("Before 4 months");
		JRadioButton month6gestation = new JRadioButton("6 Months");
		JRadioButton month8gestation = new JRadioButton("8 Months");
		JRadioButton month9gestation = new JRadioButton("9 Months");
		// Set a default selection and alignment
		month4gestation.setSelected(true);
		month4gestation.setMaximumSize(maxSize);
		month4gestation.setAlignmentX(CENTER_ALIGNMENT);
		month6gestation.setMaximumSize(maxSize);
		month6gestation.setAlignmentX(CENTER_ALIGNMENT);
		month8gestation.setMaximumSize(maxSize);
		month8gestation.setAlignmentX(CENTER_ALIGNMENT);
		month9gestation.setMaximumSize(maxSize);
		month9gestation.setAlignmentX(CENTER_ALIGNMENT);
		gestationLabel.setAlignmentX(CENTER_ALIGNMENT);
		// Group the buttons so clicking one deselects the last
		ButtonGroup gestationGroup = new ButtonGroup();
		panel.add(gestationLabel);
		gestationGroup.add(month4gestation);
		gestationGroup.add(month6gestation);
		gestationGroup.add(month8gestation);
		gestationGroup.add(month9gestation);
		// Add the buttons to the UI
		panel.add(month4gestation);
		panel.add(month6gestation);
		panel.add(month8gestation);
		panel.add(month9gestation);

		// HIV status
		JCheckBox HIVButton = new JCheckBox("Patient is HIV+");
		HIVButton.setMaximumSize(maxSize);
		HIVButton.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(HIVButton);

		// Syphilis status
		JCheckBox syphilisButton = new JCheckBox("Patient has syphilis");
		syphilisButton.setMaximumSize(maxSize);
		syphilisButton.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(syphilisButton);

		// Pre-eclampsia status
		JCheckBox peButton = new JCheckBox("Pre-eclampsia detected");
		peButton.setMaximumSize(maxSize);
		peButton.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(peButton);

		// Anemia status
		JCheckBox anemiaButton = new JCheckBox("Anemia detected");
		anemiaButton.setMaximumSize(maxSize);
		anemiaButton.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(anemiaButton);

		// Remarks field
		JLabel remarksLabel = new JLabel("Remarks");
		JTextArea remarks = new JTextArea("");
		remarks.setLineWrap(true); // words wrap around in the text box
		remarks.setWrapStyleWord(true);
		remarks.setMaximumSize(new Dimension(TEXTFIELD_WIDTH, REMARKS_HEIGHT));
		remarks.setAlignmentX(CENTER_ALIGNMENT);
		remarksLabel.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(remarksLabel);
		panel.add(remarks);

		addVisitButton.addActionListener((e) -> {
			/*
			 * TODO Convert fields to appropriate types and do appropriate validation rather
			 * than treating each as a string.
			 */

			VisitController visits = new VisitController();
			TreeMap<String,Boolean> ailments = thisPatient.getAilments();
			String visitDate = date.getText();
			String patientName = name.getText();
			String visitGestation = new String();
			if (month4gestation.isSelected()) {
				visitGestation = "4";
			}
			if (month6gestation.isSelected()) {
				visitGestation = "6";
			}
			if (month8gestation.isSelected()) {
				visitGestation = "8";
			}
			if (month9gestation.isSelected()) {
				visitGestation = "9";
			}

			ailments.put("hiv", HIVButton.isSelected());
			ailments.put("syphilis", syphilisButton.isSelected());
			ailments.put("anemia", anemiaButton.isSelected());
			ailments.put("pe", peButton.isSelected());

			// Determine which pregnancy radio button is selected
			String selectedPregnancy = "Unknown";
			if (radioIsPregnant.isSelected()) {
				selectedPregnancy = "Yes";
			} else if (radioNotPregnant.isSelected()) {
				selectedPregnancy = "No";
			}

			// Determine Date of Last Cycle
			String selectedMonth2 = monthDropdown2.getSelectedItem().toString();
			String selectedDay2 = dayDropdown2.getSelectedItem().toString();
			String selectedYear2 = yearDropdown2.getSelectedItem().toString();
			LocalDate lastCycle = LocalDate.now();
			boolean badCycle = false;

			try {
				lastCycle = LocalDate.parse(selectedYear2 + "-" + selectedMonth2 + "-" + selectedDay2);
			} catch (Exception badDate) {
				badCycle = true;
			}

			String visitRemarks = remarks.getText();
			/* TODO: validation should take place now that more fields are
			 * being properly filled in
			 * 
			 * Validation, checking that relevant fields are not empty. Remarks field can be
			 * empty, as it is an optional field.
			 */
			if (!ValidationService.areVisitViewFieldsEmpty(visitGestation, patientName, RAMButton)) {
				JOptionPane.showMessageDialog(addVisitView,
						"Please fill out all fields marked " + "with an asterisk (*).", "Error",
						JOptionPane.ERROR_MESSAGE, null);
			}else if (!ValidationService.isWeightValid(weight)) {
				JOptionPane.showMessageDialog(addVisitView, "Please use whole numbers (0-999) for height and weight.",
						"Error", JOptionPane.ERROR_MESSAGE, null);

			} else if (!ValidationService.isMenstrualCycleDateValid(radioIsPregnant, thisPatient.getDateOfBirth().getYear(), yearDropdown2)) {
				JOptionPane.showMessageDialog(addVisitView, "Invalid Menstrual Cycle Date.", "Error",
						JOptionPane.ERROR_MESSAGE, null);
			}else if (badCycle) {
				JOptionPane.showMessageDialog(addVisitView, "Invalid cycle date given.", "Error", JOptionPane.ERROR_MESSAGE,
						null);
			}

			/*
			 * New visit added
			 */
			else{ 
				// passes validation tests
				visits.addVisit(now, thisPatient, selectedPregnancy, lastCycle, Double.parseDouble(weight.getText()), visitGestation, visitRemarks,
						ailments, 0L);
				EndOfVisitView end = new EndOfVisitView(ailments);
				JOptionPane.showMessageDialog(addVisitView, "Successfully added a new visit");
				addVisitView.dispose();
				new PatientDetailsView(thisPatient);
				
			}

		});

				/* No visit is added */
				cancelButton.addActionListener((e) ->

				{
					addVisitView.dispose();
					new PatientDetailsView(thisPatient);
				});

				panel.add(addVisitButton);
				addVisitButton.setAlignmentX(CENTER_ALIGNMENT);
				panel.add(cancelButton);
				cancelButton.setAlignmentX(CENTER_ALIGNMENT);
				addVisitView.setSize(WIDTH, HEIGHT);
				addVisitView.setDefaultCloseOperation(EXIT_ON_CLOSE);
				addVisitView.setVisible(true);
				addVisitView.add(panel);
				addVisitView.setLocationRelativeTo(null);
	}

	private static void init() {

	}

	private static String[] populateYearArray(int stopYear) {
		int currentYear = LocalDate.now().getYear();
		int length = currentYear - stopYear + 1;

		String[] years = new String[length];
		int i = 0;

		while (currentYear >= stopYear) {
			years[i] = Integer.toString(currentYear);
			currentYear--;
			i++;
		}

		return years;
	}

	private static int getYearIndex(String[] years) {
		int targetYear = thisPatient.getLastCycle().getYear();
		String targetString = Integer.toString(targetYear);
		int index = 0;

		if(thisPatient.getStatusPregnant().equalsIgnoreCase("No")) {
			return 0;
		}
		while (index < years.length) {
			if(years[index].equalsIgnoreCase(targetString)) {
				return index;
			}
			index++;
		}

		return 0;
	}
}
