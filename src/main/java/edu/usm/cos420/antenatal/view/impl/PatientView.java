package edu.usm.cos420.antenatal.view.impl;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import edu.usm.cos420.antenatal.service.impl.ValidationService;
import java.util.TreeMap;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import domain.GenericPatient;
import edu.usm.cos420.antenatal.controller.AntenatalPatientController;

public class PatientView extends JFrame {
	/**
	 * View for adding a patient
	 */
	private static int WIDTH = 600;
	private static int HEIGHT = 700;
	private static int maxColumns = 1;
	private static int TEXTFIELD_WIDTH = 300;
	private static int TEXTFIELD_HEIGHT = 30;
	private static final long serialVersionUID = 2840735252655654044L;
	private static GenericPatient pat;

	public PatientView(GenericPatient pat) {
		createPatientView(pat);
		this.pat = pat;
	}

	/**
	 * Creates the view to add a patient
	 */
	public static void createPatientView(GenericPatient pat) {
		int fontSize = 16;
		JFrame addPatientView = new JFrame("Add a new antenatal patient");

		Dimension maxSize = new Dimension(TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		JPanel panel = new JPanel();
		JScrollPane scrollPane = new JScrollPane(panel); // For scrolling
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

		/* Patient Header */
		JLabel welcomeLabel = new JLabel(pat.getFirstName() + " " + pat.getLastName());
		welcomeLabel.setFont((new Font("Serif", Font.PLAIN, fontSize*2)));
		welcomeLabel.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(welcomeLabel);
		
		/** Existing details for patient **/
		/* DOB */
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		JLabel dobLabel = new JLabel("DOB: " + pat.getDateOfBirth().format(formatter));
		dobLabel.setFont((new Font("Serif", Font.PLAIN, fontSize)));
		dobLabel.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(dobLabel);
		
		/* Age */
		JLabel ageLabel = new JLabel("Age: " + pat.getAge());
		ageLabel.setFont((new Font("Serif", Font.PLAIN, fontSize)));
		ageLabel.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(ageLabel);
		
		/* Addr */
		JLabel addrLabel = new JLabel("Address: " + pat.getAddress());
		addrLabel.setFont((new Font("Serif", Font.PLAIN, fontSize)));
		addrLabel.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(addrLabel);
		
		/* Tribe */
		JLabel tribeLabel = new JLabel("Tribe: " + pat.getTribe());
		tribeLabel.setFont((new Font("Serif", Font.PLAIN, fontSize)));
		tribeLabel.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(tribeLabel);
		
		/* Village */
		JLabel villageLabel = new JLabel("Village: " + pat.getVillage());
		villageLabel.setFont((new Font("Serif", Font.PLAIN, fontSize)));
		villageLabel.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(villageLabel);
		
		/* Gender */
		JLabel genderLabel = new JLabel("Gender: " + pat.getGender());
		genderLabel.setFont((new Font("Serif", Font.PLAIN, fontSize)));
		genderLabel.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(genderLabel);
		
		/* Add some white space */
		JLabel gapLabel = new JLabel("         ");
		gapLabel.setFont((new Font("Serif", Font.PLAIN, 22)));
		gapLabel.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(gapLabel);
		
		/* Patient Header */
		JLabel actionLabel = new JLabel("Add Antenatal Patient Info");
		actionLabel.setFont((new Font("Serif", Font.PLAIN, 22)));
		actionLabel.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(actionLabel);
		
		/* Month 
		String[] months = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };

		JLabel monthLabel = new JLabel("Month");
		JComboBox monthDropdown = new JComboBox(months);
		monthDropdown.setMaximumSize(monthDropdown.getPreferredSize());
		monthDropdown.setSelectedIndex(0);
		monthLabel.setAlignmentX(CENTER_ALIGNMENT);
		monthDropdown.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(monthLabel);
		panel.add(monthDropdown);
		*/

		/* Day
		String[] days = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15",
				"16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };

		JLabel dayLabel = new JLabel("Day");
		JComboBox dayDropdown = new JComboBox(days);
		dayDropdown.setMaximumSize(dayDropdown.getPreferredSize());
		dayDropdown.setSelectedIndex(0);
		dayLabel.setAlignmentX(CENTER_ALIGNMENT);
		dayDropdown.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(dayLabel);
		panel.add(dayDropdown);
		*/
	
		/* Year
		int stopYear = LocalDate.now().getYear() - 120;
		String[] years = populateYearArray(stopYear);

		JLabel yearLabel = new JLabel("Year");
		JComboBox yearDropdown = new JComboBox(years);
		yearDropdown.setMaximumSize(yearDropdown.getPreferredSize());
		yearDropdown.setSelectedIndex(0);
		yearLabel.setAlignmentX(CENTER_ALIGNMENT);
		yearDropdown.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(yearLabel);
		panel.add(yearDropdown);
		*/
		
		/* Height */
		JLabel heightLabel = new JLabel("Height (cm)");
		JTextField height = new JTextField(maxColumns);
		height.setMaximumSize(maxSize);
		height.setAlignmentX(CENTER_ALIGNMENT);
		heightLabel.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(heightLabel);
		panel.add(height);

		/* Weight */
		JLabel weightLabel = new JLabel("Weight (kg)");
		JTextField weight = new JTextField(maxColumns);
		weight.setMaximumSize(maxSize);
		weight.setAlignmentX(CENTER_ALIGNMENT);
		weightLabel.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(weightLabel);
		panel.add(weight);

		/* Blood Type */
		String[] bloodTypes = { "Unknown", "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-" };

		JLabel btypeLabel = new JLabel("Blood Type");

		JRadioButton radioUnknownBT = new JRadioButton("Unknown", true);
		// radioUnknownBT.setBounds(120, 30, 50, 50);
		JRadioButton radioOpos = new JRadioButton("O+");
		// radioOpos.setBounds(300, 30, 50, 50);
		JRadioButton radioOneg = new JRadioButton("O-");
		// radioOneg.setBounds(300, 30, 50, 50);
		JRadioButton radioApos = new JRadioButton("A+");
		// radioApos.setBounds(300, 30, 50, 50);
		JRadioButton radioAneg = new JRadioButton("A-");
		// radioAneg.setBounds(300, 30, 50, 50);
		JRadioButton radioBpos = new JRadioButton("B+");
		// radioBpos.setBounds(300, 30, 50, 50);
		JRadioButton radioBneg = new JRadioButton("B-");
		// radioBneg.setBounds(300, 30, 50, 50);
		JRadioButton radioABpos = new JRadioButton("AB+");
		// radioABpos.setBounds(300, 30, 50, 50);
		JRadioButton radioABneg = new JRadioButton("AB-");
		// radioABneg.setBounds(300, 30, 50, 50);
		ButtonGroup btypeGroup = new ButtonGroup();
		btypeGroup.add(radioUnknownBT);
		btypeGroup.add(radioOpos);
		btypeGroup.add(radioOneg);
		btypeGroup.add(radioApos);
		btypeGroup.add(radioAneg);
		btypeGroup.add(radioBpos);
		btypeGroup.add(radioBneg);
		btypeGroup.add(radioABpos);
		btypeGroup.add(radioABneg);

		JComboBox btypeDropdown = new JComboBox(bloodTypes);
		btypeDropdown.setMaximumSize(btypeDropdown.getPreferredSize());
		btypeDropdown.setSelectedIndex(0);
		btypeLabel.setAlignmentX(CENTER_ALIGNMENT);
		btypeDropdown.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(btypeLabel);
		panel.add(btypeDropdown);

		/* Gender 
		JLabel genderLabel = new JLabel("Gender");
		JRadioButton radioMale = new JRadioButton("Male", true);
		JRadioButton radioFemale = new JRadioButton("Female");
		ButtonGroup genderGroup = new ButtonGroup();
		genderGroup.add(radioFemale);
		genderGroup.add(radioMale);
		genderLabel.setAlignmentX(CENTER_ALIGNMENT);
		radioFemale.setAlignmentX(CENTER_ALIGNMENT);
		radioMale.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(genderLabel);
		panel.add(radioMale);
		panel.add(radioFemale);
		*/

		/* Is Pregnant */
		JLabel pregnantLabel = new JLabel("Currently Pregnant");
		JRadioButton radioIsPregnant = new JRadioButton("Yes");
		JRadioButton radioNotPregnant = new JRadioButton("No", true);
		ButtonGroup pregnantGroup = new ButtonGroup();
		pregnantGroup.add(radioNotPregnant);
		pregnantGroup.add(radioIsPregnant);
		pregnantLabel.setAlignmentX(CENTER_ALIGNMENT);
		radioNotPregnant.setAlignmentX(CENTER_ALIGNMENT);
		radioIsPregnant.setAlignmentX(CENTER_ALIGNMENT);
		pregnantLabel.setVisible(false);
		radioIsPregnant.setVisible(false);
		radioNotPregnant.setVisible(false);

		panel.add(pregnantLabel);
		panel.add(radioNotPregnant);
		panel.add(radioIsPregnant);

		/**** Last Cycle ****/
		JLabel cycleLabel = new JLabel("Date of Last Menstrual Cycle");

		cycleLabel.setAlignmentX(CENTER_ALIGNMENT);
		cycleLabel.setVisible(false);
		panel.add(cycleLabel);

		/* Month */
		String[] months = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
		JLabel monthLabel = new JLabel("Month");
		
		JComboBox monthDropdown2 = new JComboBox(months);
		monthDropdown2.setMaximumSize(monthDropdown2.getPreferredSize());
		monthDropdown2.setSelectedIndex(0);
		monthDropdown2.setAlignmentX(CENTER_ALIGNMENT);
		monthLabel.setVisible(false);
		monthDropdown2.setVisible(false);
		panel.add(monthLabel);
		panel.add(monthDropdown2);

		/* Day */
		String[] days = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15",
				"16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
		JLabel dayLabel = new JLabel("Day");
		
		JComboBox dayDropdown2 = new JComboBox(days);
		dayDropdown2.setMaximumSize(dayDropdown2.getPreferredSize());
		dayDropdown2.setSelectedIndex(0);
		dayDropdown2.setAlignmentX(CENTER_ALIGNMENT);
		dayLabel.setVisible(false);
		dayDropdown2.setVisible(false);
		panel.add(dayLabel);
		panel.add(dayDropdown2);

		/* Year */
		int stopYear = LocalDate.now().getYear() - 120;
		String[] years = populateYearArray(stopYear);
		JLabel yearLabel = new JLabel("Year");
		JComboBox yearDropdown2 = new JComboBox(years);
		yearDropdown2.setMaximumSize(yearDropdown2.getPreferredSize());
		yearDropdown2.setSelectedIndex(years.length - 1);
		yearDropdown2.setAlignmentX(CENTER_ALIGNMENT);
		yearLabel.setVisible(false);
		yearDropdown2.setVisible(false);
		panel.add(yearLabel);
		panel.add(yearDropdown2);

		// If the generic patient is male, hide all pregnancy fields
		if(pat.getGender().equalsIgnoreCase("male")) {
				pregnantLabel.setVisible(false);
				radioIsPregnant.setVisible(false);
				radioNotPregnant.setVisible(false);
				radioNotPregnant.setSelected(true);
				cycleLabel.setVisible(false);
				monthLabel.setVisible(false);
				monthDropdown2.setVisible(false);
				dayLabel.setVisible(false);
				dayDropdown2.setVisible(false);
				yearLabel.setVisible(false);
				yearDropdown2.setVisible(false);
				yearDropdown2.setSelectedIndex(years.length - 1);
		}
		// if female, show pregnancy field
		else {
			pregnantLabel.setVisible(true);
			radioIsPregnant.setVisible(true);
			radioNotPregnant.setVisible(true);
		}

		/** Action Listener **/
		// If "is Pregnant" radio button is selected, show date picker
		radioIsPregnant.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cycleLabel.setVisible(true);
				monthLabel.setVisible(true);
				monthDropdown2.setVisible(true);
				dayLabel.setVisible(true);
				dayDropdown2.setVisible(true);
				yearDropdown2.setSelectedIndex(0);
				yearLabel.setVisible(true);
				yearDropdown2.setVisible(true);
			}
		});

		// If "not Pregnant" radio button is selected, hide date picker & select bogus date
		radioNotPregnant.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cycleLabel.setVisible(false);
				monthLabel.setVisible(false);
				monthDropdown2.setVisible(false);
				dayLabel.setVisible(false);
				dayDropdown2.setVisible(false);
				yearLabel.setVisible(false);
				yearDropdown2.setVisible(false);
				yearDropdown2.setSelectedIndex(years.length - 1);

			}
		});

		/* HIV */
		JLabel hivLabel = new JLabel("HIV (Yes/No)");
		JRadioButton radioUnknownHIV = new JRadioButton("Unknown", true);

		// radioUnknownHIV.setBounds(120, 30, 50, 50);
		JRadioButton radioYesHIV = new JRadioButton("Yes");
		// radioYesHIV.setBounds(120, 30, 50, 50);
		JRadioButton radioNoHIV = new JRadioButton("No");
		// radioNoHIV.setBounds(300, 30, 50, 50);
		ButtonGroup hivGroup = new ButtonGroup();
		hivGroup.add(radioUnknownHIV);
		hivGroup.add(radioYesHIV);
		hivGroup.add(radioNoHIV);

		hivLabel.setAlignmentX(CENTER_ALIGNMENT);
		radioUnknownHIV.setAlignmentX(CENTER_ALIGNMENT);
		radioYesHIV.setAlignmentX(CENTER_ALIGNMENT);
		radioNoHIV.setAlignmentX(CENTER_ALIGNMENT);

		panel.add(hivLabel);
		panel.add(radioUnknownHIV);
		panel.add(radioYesHIV);
		panel.add(radioNoHIV);

		/* Preeclampsia */
		JLabel preLabel = new JLabel("Preeclampsia");
		JRadioButton radioUnknownPre = new JRadioButton("Unknown", true);

		// radioUnknownPre.setBounds(120, 30, 50, 50);
		JRadioButton radioYesPre = new JRadioButton("Yes");
		// radioYesPre.setBounds(120, 30, 50, 50);
		JRadioButton radioNoPre = new JRadioButton("No");
		// radioNoPre.setBounds(300, 30, 50, 50);
		ButtonGroup preGroup = new ButtonGroup();
		preGroup.add(radioUnknownPre);
		preGroup.add(radioYesPre);
		preGroup.add(radioNoPre);

		preLabel.setAlignmentX(CENTER_ALIGNMENT);
		radioUnknownPre.setAlignmentX(CENTER_ALIGNMENT);
		radioYesPre.setAlignmentX(CENTER_ALIGNMENT);
		radioNoPre.setAlignmentX(CENTER_ALIGNMENT);

		panel.add(preLabel);
		panel.add(radioUnknownPre);
		panel.add(radioYesPre);
		panel.add(radioNoPre);

		/* Syphilis */
		JLabel syphilisLabel = new JLabel("Syphilis");
		JRadioButton radioUnknownSyphilis = new JRadioButton("Unknown", true);

		// radioUnknownSyphilis.setBounds(120, 30, 50, 50);
		JRadioButton radioYesSyphilis = new JRadioButton("Yes");
		// radioYesSyphilis.setBounds(120, 30, 50, 50);
		JRadioButton radioNoSyphilis = new JRadioButton("No");
		// radioNoSyphilis.setBounds(300, 30, 50, 50);

		ButtonGroup syphilisGroup = new ButtonGroup();
		syphilisGroup.add(radioUnknownSyphilis);
		syphilisGroup.add(radioYesSyphilis);
		syphilisGroup.add(radioNoSyphilis);

		syphilisLabel.setAlignmentX(CENTER_ALIGNMENT);
		radioUnknownSyphilis.setAlignmentX(CENTER_ALIGNMENT);
		radioYesSyphilis.setAlignmentX(CENTER_ALIGNMENT);
		radioNoSyphilis.setAlignmentX(CENTER_ALIGNMENT);

		panel.add(syphilisLabel);
		panel.add(radioUnknownSyphilis);
		panel.add(radioYesSyphilis);
		panel.add(radioNoSyphilis);

		/* Anemia */
		JLabel anemiaLabel = new JLabel("Anemia");
		JRadioButton radioUnknownAnemia = new JRadioButton("Unknown", true);

		// radioUnknownAnemia.setBounds(120, 30, 50, 50);
		JRadioButton radioYesAnemia = new JRadioButton("Yes");
		// radioYesAnemia.setBounds(120, 30, 50, 50);
		JRadioButton radioNoAnemia = new JRadioButton("No");
		// radioNoAnemia.setBounds(300, 30, 50, 50);

		ButtonGroup anemiaGroup = new ButtonGroup();
		anemiaGroup.add(radioUnknownAnemia);
		anemiaGroup.add(radioYesAnemia);
		anemiaGroup.add(radioNoAnemia);

		anemiaLabel.setAlignmentX(CENTER_ALIGNMENT);
		radioUnknownAnemia.setAlignmentX(CENTER_ALIGNMENT);
		radioYesAnemia.setAlignmentX(CENTER_ALIGNMENT);
		radioNoAnemia.setAlignmentX(CENTER_ALIGNMENT);

		panel.add(anemiaLabel);
		panel.add(radioUnknownAnemia);
		panel.add(radioYesAnemia);
		panel.add(radioNoAnemia);

		/* Add Patient Button */
		JButton addPatientButton = new JButton("Add");
		JButton cancelButton = new JButton("Cancel");
		addPatientButton.setAlignmentX(CENTER_ALIGNMENT);
		cancelButton.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(addPatientButton);
		panel.add(cancelButton);

		/* On "add" button press, adds a new patient */
		addPatientButton.addActionListener((e) -> {

			// Determine Date of Birth
			//String selectedMonth = monthDropdown.getSelectedItem().toString();
			//String selectedDay = dayDropdown.getSelectedItem().toString();
			//String selectedYear = yearDropdown.getSelectedItem().toString();
			//LocalDate dob = LocalDate.now();
			//boolean badDob = false;

//			try {
//				dob = LocalDate.parse(selectedYear + "-" + selectedMonth + "-" + selectedDay);
//			} catch (Exception badDate) {
//				badDob = true;
//			}

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

			/* Perform some sort of input validation */
			// Check for empty fields
			if (ValidationService.areFieldsEmpty(height, weight)) {
				JOptionPane.showMessageDialog(addPatientView, "Please leave no fields blank", "Error",
						JOptionPane.ERROR_MESSAGE, null);
			} else if (badCycle) {
				JOptionPane.showMessageDialog(addPatientView, "Invalid date given.", "Error", JOptionPane.ERROR_MESSAGE,
						null);
			}
			// Regex for height & weight must be an integer between 0-999
			else if (!ValidationService.isHeightValid(height) || !ValidationService.isWeightValid(weight)) {
				JOptionPane.showMessageDialog(addPatientView, "Please use whole numbers (0-999) for height and weight.",
						"Error", JOptionPane.ERROR_MESSAGE, null);

			} else if (!ValidationService.isMenstrualCycleDateValid(radioIsPregnant, pat.getDateOfBirth().getYear(), yearDropdown2)) {
				JOptionPane.showMessageDialog(addPatientView, "Invalid Menstrual Cycle Date.", "Error",
						JOptionPane.ERROR_MESSAGE, null);
			} else {
				AntenatalPatientController control = new AntenatalPatientController();

				// Determine which gender radio button is selected
//				String selectedGender = "Female";
//				if (radioMale.isSelected()) {
//					selectedGender = "Male";
//				}

				// Determine which blood type radio button is selected

				String selectedBT = "Unknown";
				if (radioOpos.isSelected()) {
					selectedBT = "O+";
				} else if (radioOneg.isSelected()) {
					selectedBT = "O-";
				} else if (radioApos.isSelected()) {
					selectedBT = "A+";
				} else if (radioAneg.isSelected()) {
					selectedBT = "A-";
				} else if (radioBpos.isSelected()) {
					selectedBT = "B+";
				} else if (radioBneg.isSelected()) {
					selectedBT = "B-";
				} else if (radioABpos.isSelected()) {
					selectedBT = "AB+";
				} else if (radioABneg.isSelected()) {
					selectedBT = "AB-";
				}

				// Determine Age based on DOB
//				LocalDate today = LocalDate.now();
//				Long age = Long.valueOf(today.getYear() - dob.getYear());

				// Determine which pregnancy radio button is selected
				String selectedPregnancy = "Unknown";
				if (radioIsPregnant.isSelected()) {
					selectedPregnancy = "Yes";
				} else if (radioNotPregnant.isSelected()) {
					selectedPregnancy = "No";
				}

				// Determine which HIV radio button is selected
				String selectedHIV = "Unknown";
				if (radioYesHIV.isSelected()) {
					selectedHIV = "Yes";
				} else if (radioNoHIV.isSelected()) {
					selectedHIV = "No";
				}

				// Determine which Preeclampsia radio button is selected
				String selectedPre = "Unknown";
				if (radioYesPre.isSelected()) {
					selectedPre = "Yes";
				} else if (radioNoPre.isSelected()) {
					selectedPre = "No";
				}

				// Determine which Syphilis radio button is selected
				String selectedSyphilis = "Unknown";
				if (radioYesSyphilis.isSelected()) {
					selectedSyphilis = "Yes";
				} else if (radioNoSyphilis.isSelected()) {
					selectedSyphilis = "No";
				}

				// Determine which Anemia radio button is selected
				String selectedAnemia = "Unknown";
				if (radioYesAnemia.isSelected()) {
					selectedAnemia = "Yes";
				} else if (radioNoAnemia.isSelected()) {
					selectedAnemia = "No";
				}

				TreeMap<String, Boolean> ailments = new TreeMap<String, Boolean>();
				if (!selectedHIV.equals("Unknown"))
					ailments.put("hiv", radioYesHIV.isSelected());
				if (!selectedAnemia.equals("Unknown"))
					ailments.put("anemia", radioYesAnemia.isSelected());
				if (!selectedSyphilis.equals("Unknown"))
					ailments.put("syphilis", radioYesSyphilis.isSelected());
				if (!selectedPre.equals("Unknown"))
					ailments.put("pe", radioYesPre.isSelected());

				/*
				 * if unknown is selected put false instead of null maybe in the future it could
				 * changed to a string instead of a boolean to handle the unknown case better
				 * but for now this is fine I just didn't want null to be there
				 */
				if (selectedHIV.equals("Unknown"))
					ailments.put("hiv", false);
				if (selectedAnemia.equals("Unknown"))
					ailments.put("anemia", false);
				if (selectedSyphilis.equals("Unknown"))
					ailments.put("syphilis", false);
				if (selectedPre.equals("Unknown"))
					ailments.put("pe", false);
				
				// Attempt to add the patient to the Dao
				if (control.addPatient(pat, Double.parseDouble(height.getText()), Double.parseDouble(weight.getText()),
						selectedBT, selectedPregnancy, lastCycle, ailments))

					JOptionPane.showMessageDialog(addPatientView, "Successfully added a new patient");
				else
					JOptionPane.showMessageDialog(addPatientView, "Patient already exists");
				addPatientView.dispose();
				PatientDetailsView pdv = new PatientDetailsView(control.getPatient(pat.getPatientId().toString()));

			}
		});
		/* On "cancel" button press, no Patient is added */
		cancelButton.addActionListener((e) -> {
			addPatientView.dispose();
		});

		addPatientView.setSize(WIDTH, HEIGHT);
		addPatientView.setDefaultCloseOperation(EXIT_ON_CLOSE);
		addPatientView.setVisible(true);
		addPatientView.add(scrollPane);
		addPatientView.setLocationRelativeTo(null);
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

}
