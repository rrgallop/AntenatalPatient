/*
 * AntenatalPatient.java
 * 
 * Building off of Ryan G's original code for the Patient class to implement inheritance, 
 * using the consulting register's GenericPatient class as a superclass.
 * 
 */

package edu.usm.cos420.antenatal.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.TreeMap;

/**
 * AntenatalPatient is a placeholder for the time being until we can see the
 * implementation of the register's patient class
 * 
 */
public class AntenatalPatient extends domain.GenericPatient implements Serializable {
    
	private static final long serialVersionUID = 7526472295622776147L;
	
    private double height;
    private double weight;
    private String bloodType;
    private String isPregnant;
    private LocalDate lastCycle;
    private TreeMap<Long, LocalDate> visitHistory;  //Key: visitID  Value: Visit Date
    private TreeMap<String, Boolean> ailments; //Key is an ailment. Value is if they have it

	/** 
	 * Constructor for a new AntenatalPatient, specifying patient details
	 */
    public AntenatalPatient(String firstName,
    						String lastName,
    						String address,
    						String tribe,
  						  	String village,
  						  	String gender,
  						  	String age,
  						  	LocalDate dateOfBirth,
  						  	double height,
  						  	double weight,
  						  	String bloodType,
  						  	String isPregnant,
  						  	LocalDate lastCycle,
  						  	TreeMap<String,Boolean> ailments,
  						  	Long ID)
    {
    	super(firstName, lastName, address, tribe, village, gender, age, dateOfBirth, ID);
    	this.height = height;
        this.weight = weight;
        this.bloodType = bloodType;
        this.isPregnant = isPregnant;
        this.lastCycle = lastCycle;
        this.visitHistory = new TreeMap<Long, LocalDate>();
        this.ailments = ailments;
    }
    
    /**

	 * @return Returns height.
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * @param height The patient's height.
	 */
	public void setHeight(double height) {
		this.height = height;
	}

	/**
	 * @return Returns weight.
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * @param weight The patient's weight.
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	/**
	 * @return Returns bloodType.
	 */
	public String getBloodType() {
		return bloodType;
	}

	/**
	 * @param bloodtype The bloodtype to set.
	 */
	public void setBloodType(String bloodtype) {
		this.bloodType = bloodtype;
	}

	/**
	 * @return Returns isPregnant status.
	 */
	public String getStatusPregnant() {
		return isPregnant;
	}

	/**
	 * @param status Set the isPregnant status.
	 */
	public void setStatusPregnant(String status) {
		this.isPregnant = status;
	}

	/**
	 * @return Returns the date of patient's last menstrual cycle
	 */
	public LocalDate getLastCycle() {
		return this.lastCycle;
	}

	/**
	 * @param lastCycle Set the date of patient's last menstrual cycle
	 */
	public void setLastCycle(LocalDate lastCycle) {
		this.lastCycle = lastCycle;
	}

	/**
	 * Get the visitHistory of the patient
	 * 
	 * @return visitHistory a collection of the patient's past visits
	 */
	public TreeMap<Long, LocalDate> getVisitHistory() {
		return visitHistory;
	}

	/**
	 * Get the ailments of the patient
	 * 
	 * @return ailments
	 */
	public TreeMap<String, Boolean> getAilments() {
		return ailments;
	}

	/**
	 * Add a visit to the patient's visit history
	 * 
	 * @param visitID   the id of the patient visit
	 * @param visitDate the date the patient visit
	 */
	public void addVisitHistory(Long visitID, LocalDate visitDate) {
		this.visitHistory.put(visitID, visitDate);
	}

	/**
	 * Returns the String representation of this patient. Not required, it just
	 * pleases reading logs.
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Patient ID: " + this.getPatientId() + "\nFirst Name: " + this.getFirstName() + "\nLast Name: "
				+ this.getLastName() + "\nDOB: " + this.getDateOfBirth() + "\nAge: " + this.getAge() + "\nGender: "
				+ this.getGender() + "\nAddress:" + this.getAddress() + "\nVillage: " + this.getVillage() + "\nHeight: "
				+ this.getHeight() + "\nWeight: " + this.getWeight() + "\nBlood Type: " + this.getBloodType()
				+ "\nCurrently Pregnant: " + this.getStatusPregnant() + "\nLast menstrual cycle: " + this.getLastCycle()
				+ "\nVisit History: " + this.getVisitHistory();
	}
}
