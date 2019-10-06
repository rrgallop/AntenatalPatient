package edu.usm.cos420.antenatal.dao.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.reflect.TypeToken;

import dao.PatientDao;
import domain.GenericPatient;

import java.lang.reflect.Type;

import edu.usm.cos420.antenatal.dao.GenericDao;
import edu.usm.cos420.antenatal.dao.JsonDao;
import edu.usm.cos420.antenatal.domain.AntenatalPatient;

/**
 * 
 * A Data Access Object specifically for Patient entities
 * 
 */
public class AntenatalPatientDao extends PatientDao
{
	private GenericDao<Long,AntenatalPatient> PatientDao;

	/**
	 * Default constructor creates an Json file called Patient.json TypeToken allows
	 * the GSON parser to map to/from JSON to objects
	 */
public AntenatalPatientDao()
	{
        Type t = new TypeToken<Map<Long, AntenatalPatient>>(){}.getType(); 
		PatientDao = new JsonDao<>("AntenatalPatient.json",t);
	}

	/**
	 * Constructor where the filename is provided
	 */
	public AntenatalPatientDao(String fileName)
	{
         Type t = new TypeToken<Map<Long, AntenatalPatient>>(){}.getType(); 
		 PatientDao = new JsonDao<>(fileName,t); 
	}

	/**
	 * Support for other DAOs is provided
	 * 
	 * @param dao a Data Access Object class that implements
	 *            GenericDao<Long,Patient>
	 */
	public AntenatalPatientDao(GenericDao<Long,AntenatalPatient> dao)
	{
		PatientDao = dao;
	}

	/**
	 * Returns the DAO used in the class
	 * 
	 * @return a DAO that implements GenericDao<Long,Patient>
	 */
	public GenericDao<Long, AntenatalPatient> getPatientDao() {
		return PatientDao;
	}

	/**
	 * Add a Patient to the DAO repository
	 * 
	 * @param entity any Patient object
	 */
	public void add(AntenatalPatient entity) {
		PatientDao.add(entity.getPatientId(), entity);
	}

	/**
	 * Update a Patient in the DAO repository
	 * 
	 * @param entity any Patient object
	 */
	public void update(AntenatalPatient entity) {
		PatientDao.update(entity.getPatientId(), entity);
	}

	/**
	 * Remove a Patient in the DAO repository
	 * 
	 * @param id of the Patient object to remove
	 */

	public void remove(Long id) {
		PatientDao.remove(id);
	}

	/**
	 * Find a Patient in the DAO repository
	 * 
	 * @param id of the Patient object to locate
	 * @return the Patient with id field equal to key
	 */
	public AntenatalPatient find(Long key) {
		return PatientDao.find(key);
	}

	/**
	 * Generate a list of Patients in the DAO repository
	 * 
	 * @return List of Patients
	 */

	public List<GenericPatient> list() {
		ArrayList<GenericPatient> mylist = new ArrayList<GenericPatient>();
		for(AntenatalPatient p: PatientDao.list()) {
			mylist.add(p);
		}
		return mylist;
	}
	
	   /**
     * Generate a list of Patients in the DAO repository
     * 
     * @return List of Patients
     */

    public List<AntenatalPatient> aplist() {
        ArrayList<AntenatalPatient> mylist = new ArrayList<AntenatalPatient>();
        for(AntenatalPatient p: PatientDao.list()) {
            mylist.add(p);
        }
        return mylist;
    }

}
