package edu.usm.cos420.antenatal.dao.domain;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.List;
import com.google.gson.reflect.TypeToken;

import edu.usm.cos420.antenatal.dao.GenericDao;
import edu.usm.cos420.antenatal.dao.JsonDao;
import edu.usm.cos420.antenatal.domain.Visit;

public class VisitDao {
	private GenericDao<Long, Visit> visitDao;

	/**
	 * Default constructor creates an Json file called citem.json TypeToken allows
	 * the GSON parser to map to/from JSON to objects
	 */
	public VisitDao() {
		Type t = new TypeToken<Map<Long, Visit>>() {
		}.getType();
		visitDao = new JsonDao<>("Visits.json", t);
	}

	/**
	 * Constructor where the filename is provided
	 */
	public VisitDao(String fileName) {
		Type t = new TypeToken<Map<Long, Visit>>() {
		}.getType();
		visitDao = new JsonDao<>(fileName, t);
		System.out.println("Created visit dao with given filename: \"" + fileName + "\"");
	}

	/**
	 * Support for other DAOs is provided
	 * 
	 * @param dao a Data Access Object class that implements GenericDao<Long,CItem>
	 */
	public VisitDao(GenericDao<Long, Visit> dao) {
		visitDao = dao;
	}

	/**
	 * Returns the DAO used in the class
	 * 
	 * @return a DAO that implements GenericDao<Long,CItem>
	 */
	public GenericDao<Long, Visit> getCItemDao() {
		return visitDao;
	}

	/**
	 * Add a CItem to the DAO repository
	 * 
	 * @param entity any CItem object
	 */
	public void add(Visit entity) {
		visitDao.add(entity.getVisitID(), entity);
	}

	/**
	 * Update a CItem in the DAO repository
	 * 
	 * @param entity any CItem object
	 */
	public void update(Visit entity) {
		visitDao.update(entity.getVisitID(), entity);
	}

	/**
	 * Remove a CItem in the DAO repository
	 * 
	 * @param id of the CItem object to remove
	 */

	public void remove(Long id) {
		visitDao.remove(id);
	}

	/**
	 * Find a CItem in the DAO repository
	 * 
	 * @param id of the CItem object to locate
	 * @return the CItem with id field equal to key
	 */
	public Visit find(Long key) {
		return visitDao.find(key);
	}

	/**
	 * Generate a list of CItems in the DAO repository
	 * 
	 * @return List of CItems
	 */

	public List<Visit> list() {
		return visitDao.list();
	}

}
