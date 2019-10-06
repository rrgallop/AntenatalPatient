package edu.usm.cos420.antenatal.controller;

import java.time.LocalDate;
import java.util.TreeMap;

import edu.usm.cos420.antenatal.dao.domain.AntenatalPatientDao;
import edu.usm.cos420.antenatal.dao.domain.VisitDao;
import edu.usm.cos420.antenatal.domain.AntenatalPatient;
import edu.usm.cos420.antenatal.domain.Visit;

public class VisitController {
	private VisitDao visitDao;
	private AntenatalPatientDao antenatalPatientDao;
	public static final LocalDate BOGUSDATE = LocalDate.parse("1900-01-01");

	/**
	 * Constructor : pass in a service class which can provide access to
	 * AntenatalPatient operations.
	 * 
	 * @param view
	 * @param service
	 */
	public VisitController() {
		visitDao = new VisitDao();
		antenatalPatientDao = new AntenatalPatientDao();
	}

	/**
	 * Adds a visit to the JSON file
	 * 
	 * @param date      the date of the visit
	 * @param name      the name of the patient
	 * @param gestation the number of weeks into pregnancy
	 * @param remarks   anything of note about the visit
	 */

	public void addVisit(LocalDate date, AntenatalPatient patient, String isPregnant, LocalDate lastCycle, double weight, String gestation, String remarks,
			TreeMap<String, Boolean> ailments, Long id) {
		int size = visitDao.list().size();
		Long visitID = (long) size++;
		Visit newVisit = new Visit(date, patient.getPatientId(), isPregnant, lastCycle, weight, gestation, remarks, ailments, visitID);

		visitDao.add(newVisit);
		patient.addVisitHistory(visitID, date);
		patient.setStatusPregnant(isPregnant);
		if(isPregnant.equalsIgnoreCase("yes")) {
			patient.setLastCycle(lastCycle);
		}else {
			patient.setLastCycle(BOGUSDATE);
		}
		patient.setWeight(weight);
		//TODO: set patient ailments
		antenatalPatientDao.update(patient);

	}
	
	public Visit getVisit(Long visitID) {
		
		return visitDao.find(visitID);
	}

}
