package edu.usm.cos420.antenatal.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import dao.PatientDao;
import domain.GenericPatient;
import edu.usm.cos420.antenatal.controller.AntenatalPatientController;
import edu.usm.cos420.antenatal.dao.domain.AntenatalPatientDao;
import edu.usm.cos420.antenatal.domain.AntenatalPatient;
import services.PatientSearch;

public class ValidationService {
	static List<JTextField> emptyFields = new ArrayList<JTextField>();

	/**
	 * Date format should be year-month-day where year is 20** or 19**, month is 1 thru 12, day is 1 thru 31
	 * @param date
	 * @return
	 */
	public static Boolean isDateValid(JTextField date) {
		
		if (date.getText().matches("^(19|20)\\d\\d[-](0[1-9]|1[012])[-](0[1-9]|[12][0-9]|3[01])$")) {
			return true;
		}
		return false;
	}

	/*
	 * it returns a list because eventually I want to highlight the empty textfields
	 */
	/**
	 * Checks if any fields are empty
	 * 
	 * @param firstName the first name of the patient
	 * @param lastName  the last name of the patient
	 * @param addr      the address of the patient
	 * @param village   the village of the patient
	 * @param height    the height of the patient
	 * @param weight    the weight of the patient
	 * @param cycle     the date of the last known menstrual cycle of the patient
	 * @return true if the fields are false if they are not
	 * 
	 */
	public static Boolean areFieldsEmpty(JTextField firstName, JTextField lastName, JTextField addr, JTextField village,
			JTextField height, JTextField weight) {
		if (firstName.getText().isEmpty() || lastName.getText().isEmpty() || addr.getText().isEmpty()
				|| village.getText().isEmpty() || height.getText().isEmpty() || weight.getText().isEmpty()) {

			return true;

		}
		return false;
	}

	/**
	 * @param visitGestation the visit gestation date
	 * @param patientName    the name of the patient
	 * @param ramButton      the rambutton
	 * @return true if the visitView fields are filled in false if they are empty
	 */
	public static Boolean areVisitViewFieldsEmpty(String visitGestation, String patientName, JCheckBox ramButton) {
		if (visitGestation.isEmpty() || patientName.isEmpty() || !ramButton.isSelected()) {
			return false;
		}
		return true;
	}

	// these are separate because we may want to change them in the future
	/**
	 * Validates the age of a patient
	 * 
	 * @param age the age of the patient
	 * @return true if the age is, false if its not
	 */
	public static Boolean isAgeValid(JTextField age) {
		if (age.getText().matches("^[0-9]{1,3}$")) {
			return true;
		}
		return false;
	}

	/**
	 * Validates an email address
	 * 
	 * @param email the email to check
	 * @return true if the emailAddress is a valid or not otherwise false
	 */
	public static Boolean isEmailValid(String email) {
		if (email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
			return true;
		}
		return false;
	}

	/*
	 * } else if (radioIsPregnant.isSelected() &&
	 * Integer.parseInt(yearDropdown2.getSelectedItem().toString()) < Integer
	 * .parseInt(yearDropdown.getSelectedItem().toString())
	 * 
	 * ) { JOptionPane.showMessageDialog(addPatientView,
	 * "Invalid Menstrual Cycle Date.", "Error", JOptionPane.ERROR_MESSAGE, null); }
	 */

	/**
	 * Validates a userID
	 * 
	 * @param userID the userid to check
	 * @return false if the userid is not valid otherwise true
	 */
	public static Boolean isUserIDValid(JTextField userIDField) {
		String input = userIDField.getText();
	
		try {
		Integer.parseInt(input);
		}catch(NumberFormatException e) {
			return false;
		}
		if(userIDField.getText().isEmpty()) {
			return false;
		}
		AntenatalPatientController controller = new AntenatalPatientController();
		if (userIDField.getText().matches("[0-9]*")) {
		AntenatalPatientDao apDao = new AntenatalPatientDao();
		PatientDao pDao = new PatientDao();
		PatientSearch apsearch = new PatientSearch((PatientDao)apDao);
		PatientSearch gpsearch = new PatientSearch(new PatientDao());
		ArrayList<GenericPatient> ap = apsearch.idSearch(Long.parseLong(input));
		ArrayList<GenericPatient> gp = gpsearch.idSearch(Long.parseLong(input));
			if(ap.isEmpty() && gp.isEmpty()) {
				return false;
			}
			return true;
		}
		

		return false;
	}

	public static Boolean isFirstNameValid(JTextField firstName) {
		if (firstName.getText().matches("^[A-Za-z ]*[A-Za-z][A-Za-z]*$")) {
			return true;
		}
		return false;
	}

	public static Boolean isLastNameValid(JTextField lastName) {
		if (lastName.getText().matches("^[A-Za-z ]*[A-Za-z][A-Za-z ]*$")) {
			return true;
		}
		return false;
	}

	public static Boolean isAddrValid(JTextField addr) {
		if (addr.getText().matches("^[A-Za-z0-9 ]*[A-Za-z0-9][A-Za-z0-9 ]*$")) {
			return true;
		}
		return false;
	}

	public static Boolean isVillageValid(JTextField village) {
		if (village.getText().matches("^[A-Za-z0-9 ]*[A-Za-z0-9][A-Za-z0-9 ]*$")) {
			return true;
		}
		return false;
	}

	/**
	 * Validates the weight of a patient
	 * 
	 * @param weight the weight of the patient
	 * @return true if the weight is valid, false if its not
	 */
	public static Boolean isWeightValid(JTextField weight) {
		if (weight.getText().matches("^[0-9]{1,3}$")) {
			return true;
		}
		return false;
	}

	/**
	 * Validates the height of a patient
	 * 
	 * @param height the height of a patient
	 * @return true if the height is valid false if its not
	 */
	public static Boolean isHeightValid(JTextField height) {
		if (height.getText().matches("^[0-9]{1,3}$")) {
			return true;
		}
		return false;
	}

	/*
	 * Checks for valid cycle date
	 */
	public static Boolean isMenstrualCycleDateValid(JRadioButton radioIsPregnant, int birthYear ,JComboBox cycleYearDropdown) {
		if(radioIsPregnant.isSelected()
					&& Integer.parseInt(cycleYearDropdown.getSelectedItem().toString()) <= birthYear) {
			return false;
		}
		return true;
	}

	public static boolean areFieldsEmpty(JTextField height, JTextField weight) {
		if (height.getText().isEmpty() || weight.getText().isEmpty()) {

			return true;

		}
		return false;

	}

}
