package edu.usm.cos420.antenatal.service.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.swing.JTextField;

import org.junit.Test;



public class TestValidationService {
	
	
	@Test
	/*
	 * Test 
	 */
	public void testIdVerification() {
		
		JTextField userIDField = new JTextField(1);
		
		userIDField.setText("Hello my friend");
		Boolean result = ValidationService.isUserIDValid(userIDField);
		assertFalse("Failed to detect ID Input was not a number!", result);
		
		userIDField.setText("");
		result = ValidationService.isUserIDValid(userIDField);
		assertFalse("Failed to detect an empty string!", result);
		
		userIDField.setText("100000");
		result = ValidationService.isUserIDValid(userIDField);
		assertFalse("Failed to recognize ID as valid", result);
		
	}
	
	@Test
	/*
	 * 
	 */
	public void testDateValidation() {
		JTextField dateField = new JTextField(1);
		
		dateField.setText("Hello my friend");
		Boolean result = ValidationService.isDateValid(dateField);
		assertFalse("Failed to detect input was not a date! (yyyy-mm-dd)", result);
		
		dateField.setText("2111-12-12");
		result = ValidationService.isDateValid(dateField);
		assertFalse("Failed to detect invalid input for year!", result);
		
		dateField.setText("2000-13-12");
		result = ValidationService.isDateValid(dateField);
		assertFalse("Failed to detect invalid input for month!", result);
		
		dateField.setText("2000-12-32");
		assertFalse("Failed to detect invalid input for day!", result);
		
		dateField.setText("1942-12-31");
		result = ValidationService.isDateValid(dateField);
		assertTrue("Failed to recognize date as valid!", result);
		
	}

	@Test
	/*
	 * 
	 */
	public void testEmailValidation() {
		String airquotesEmail = "Hello this is my email address thank you";
		Boolean result = ValidationService.isEmailValid(airquotesEmail);
		assertFalse("Failed to detect invalid input for email address!", result);
		
		airquotesEmail = "mrSandman@yourdreams.net";
		result = ValidationService.isEmailValid(airquotesEmail);
		assertTrue("Failed to recognize email address as valid", result);
	}
	
	@Test
	/*
	 * 
	 */
	public void testAgeValidation() {
		JTextField ageField = new JTextField(1);
		
		ageField.setText("Hello I am 14 years old");
		Boolean result = ValidationService.isAgeValid(ageField);
		assertFalse("Failed to detect invalid input for age!",result);
		
		ageField.setText("25");
		result = ValidationService.isAgeValid(ageField);
		assertTrue("Failed to recognize age as valid!", result);
	}
}
