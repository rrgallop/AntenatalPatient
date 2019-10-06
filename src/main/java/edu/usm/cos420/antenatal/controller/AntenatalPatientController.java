package edu.usm.cos420.antenatal.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.TreeMap;

import domain.GenericPatient;
import edu.usm.cos420.antenatal.dao.domain.AntenatalPatientDao;
import edu.usm.cos420.antenatal.domain.AntenatalPatient;
import edu.usm.cos420.antenatal.view.impl.PatientView;

/**
 *  *   A Controller class to execute user's menu choice.
 *     List of possible choices can be found at {@link edu.usm.cos420.example1.view.TextUI}
 *   
 */	 
public class AntenatalPatientController {
	public static final LocalDate BOGUSDATE = LocalDate.parse("1800-01-01");
	
	private AntenatalPatientDao pDao;

	/**
	 * Constructor : pass in a service class which can provide access to
	 * AntenatalPatient operations.
	 * 
	 * @param view
	 * @param service
	 */
	public AntenatalPatientController()
	{
		pDao = new AntenatalPatientDao();
	}
	
	public AntenatalPatientController(String file) {
	    pDao = new AntenatalPatientDao(file);
	}

	/**
	 * Adds an AntenatalPatient to the JSON file
	 * 
	 * @param name
	 * @param ID
	 * @return if successfully added, fails if id already exists
	 */
	public Boolean addPatient(GenericPatient pat, double height, double weight,
							  String bloodtype, String isPregnant, LocalDate lastCycle,
							  TreeMap<String,Boolean> ailments)
	{
		AntenatalPatient p = new AntenatalPatient(pat.getFirstName(),
												  pat.getLastName(), 
												  pat.getAddress(), 
												  pat.getTribe(), 
												  pat.getVillage(), 
												  pat.getGender(), 
												  pat.getAge(), 
												  pat.getDateOfBirth(),
												  height, 
												  weight, 
												  bloodtype, 
												  isPregnant, 
												  lastCycle, 
												  ailments,
												  pat.getPatientId());
		pDao.add(p);
		return true;
	}

	/**
	 * Retrieve's a AntenatalPatient from the DAO
	 * 
	 * @param inputID
	 * @return the matching AntenatalPatient object if the patient exists, null
	 *         otherwise
	 */
	public AntenatalPatient getPatient(String inputID) {
		AntenatalPatient p = (AntenatalPatient) pDao.find(Long.parseLong(inputID));
		return p;
	}

}
