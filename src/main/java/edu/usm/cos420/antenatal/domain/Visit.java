package edu.usm.cos420.antenatal.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.TreeMap;

import edu.usm.cos420.antenatal.controller.VisitController;
import edu.usm.cos420.antenatal.dao.domain.AntenatalPatientDao;
import edu.usm.cos420.antenatal.dao.domain.VisitDao;

public class Visit implements Serializable {
	/**
		 * 
		 */
	private static final long serialVersionUID = -3009676401032558654L;
	/*
	 ** Date of Visit
	 * 
	 * Patient Name / Patient ID
	 * 
	 * Gestastion (# of weeks into the pregnancy)
	 * 
	 * Complaints / Remarks
	 */

	private Long visitID = 0L;
	private LocalDate dateOfVisit;
	private Long patientId;
	private String pregnantStatus;
	private LocalDate lastCycle;
	private double weight;
	private String gestation;
	private String remarks;
	private TreeMap<String, Boolean> ailments;

	/**
	 * Default constructor, takes no arguments but generates a default visit ID
	 */
	public Visit() {
		this.setVisitID(generateVisitID());
	}

	/**
	 * Four field constructor
	 * 
	 * @param date      date of visit
	 * @param name      name of patient
	 * @param gestation gestation period in weeks
	 * @param remarks   optional remarks/complaints
	 */
	public Visit(LocalDate date, Long patientId, String isPregnant, LocalDate lastCycle, double weight, String gestation, String remarks, Long id) {
		this.dateOfVisit = date;
		this.patientId = patientId;
		this.pregnantStatus = isPregnant;
		this.lastCycle = lastCycle;
		this.weight = weight;
		this.gestation = gestation;
		this.remarks = remarks;
		this.visitID = id;
	}

	/**
	 * Constructor for Visit taking ailments representing various health statuses
	 * 
	 * @param date      date of visit
	 * @param name      name of patient
	 * @param gestation gestation period in weeks
	 * @param remarks   optional remarks/complaints
	 */
	public Visit(LocalDate date, Long patientId, String pregnantStatus, LocalDate lastCycle, double weight, String gestation, String remarks, TreeMap<String, Boolean> ailments,
			Long id) {
		this.dateOfVisit = date;
		this.patientId = patientId;
		this.pregnantStatus = pregnantStatus;
		this.lastCycle = lastCycle;
		this.weight = weight;
		this.gestation = gestation;
		this.remarks = remarks;
		this.ailments = ailments;
		this.visitID = id;
	}

	public Long generateVisitID() {
		return visitID++;
	}

	/**
	 * @return the dateOfVisit
	 */
	public LocalDate getDateOfVisit() {
		return dateOfVisit;
	}

	/**
	 * @param dateOfVisit the dateOfVisit to set
	 */
	public void setDateOfVisit(LocalDate dateOfVisit) {
		this.dateOfVisit = dateOfVisit;
	}

	/**
	 * @return the name
	 */
	public Long getPatientId() {
		return patientId;
	}

	/**
	 * @param name the name to set
	 */
//	public void setName(String name) {
//		this.patient.setName(name);
//	}
	
	/**
	 * @return the pregnancy status
	 */
	public String getPregnantStatus() {
		return pregnantStatus;
	}

	/**
	 * @param the pregnancy status to set
	 */
	public void setPregnantStatus(String status) {
		this.pregnantStatus = status;
	}
	
	/**
	 * @return the date of last cycle
	 */
	public LocalDate getLastCycle() {
		return lastCycle;
	}

	/**
	 * @param the date to set
	 */
	public void setLastCycle(LocalDate localDate) {
		this.lastCycle = localDate;
	}
	
	/**
	 * @return the date of last cycle
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * @param the weight to set
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	/**
	 * @return the gestation
	 */
	public String getGestation() {
		return gestation;
	}

	/**
	 * @param gestation the gestation to set
	 */
	public void setGestation(String gestation) {
		this.gestation = gestation;
	}

	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * @return the visitID
	 */
	public Long getVisitID() {
		return visitID;
	}

	/**
	 * @param visitID the visitID to set
	 */
	public void setVisitID(Long visitID) {
		this.visitID = visitID;
	}
}
