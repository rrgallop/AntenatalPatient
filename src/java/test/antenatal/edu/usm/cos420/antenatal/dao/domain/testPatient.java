package edu.usm.cos420.antenatal.dao.domain;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.GenericPatient;
import edu.usm.cos420.antenatal.*;
import edu.usm.cos420.antenatal.dao.domain.VisitDao;
import edu.usm.cos420.antenatal.domain.AntenatalPatient;

public class testPatient {

	AntenatalPatientDao pDao;

	@Before
	public void setupData() {
		pDao = new AntenatalPatientDao("patient_test.json");
	}

	@Test
	/*
	 * Test adding an antenatal patient
	 */
	public void testaddAntenatalPatient() {
		AntenatalPatient testPatient = new AntenatalPatient(null, null, null, null, null, null, null, null, 0, 0, null, null, null, null, 1L);
		List<GenericPatient> pList;

		pDao.add(testPatient);
		pList = pDao.list();

		testPatient = pDao.find(pList.get(0).getPatientId());

		assertNotNull("Dao returns a null patient.", testPatient);
	}

	@Test
	/*
	 * Test removing an antenatal patient
	 */
	public void testremoveAntenatalPatient() {
		AntenatalPatient testPatient = new AntenatalPatient(null, null, null, null, null, null, null, null, 0, 0, null, null, null, null, 1L);
		List<GenericPatient> pList;

		pDao.add(testPatient);
		pList = pDao.list();

		testPatient = pDao.find(pList.get(0).getPatientId());

		pDao.remove(testPatient.getPatientId());
		testPatient = pDao.find(pList.get(0).getPatientId());

		assertNull("Dao does not accurately remove patient.", testPatient);
	}

	@Test
	/*
	 * Test to make sure patient ID numbers are generated randomly
	 */
	public void testantenatalPatientIDNumbers() {
		AntenatalPatient testPatientOne = new AntenatalPatient(null, null, null, null, null, null, null, null, 0, 0, null, null, null, null, 1L);
		AntenatalPatient testPatientTwo = new AntenatalPatient(null, null, null, null, null, null, null, null, 0, 0, null, null, null, null, 2L);
		List<GenericPatient> pList;

		pDao.add(testPatientOne);
		pDao.add(testPatientTwo);
		pList = pDao.list();

		testPatientOne = pDao.find(pList.get(0).getPatientId());
		testPatientTwo = pDao.find(pList.get(1).getPatientId());

		assertNotEquals("Patient ID numbers are equal", testPatientOne.getPatientId(), testPatientTwo.getPatientId());
	}

//	@Test
	/*
	 * 
	 */

	/**
	 * Need to delete the file for next test
	 */
	@After
	public void tearDown() {
		System.gc();
		Path path = FileSystems.getDefault().getPath(".", "patient_test.json");
		try {
			Files.delete(path);
		} catch (NoSuchFileException x) {
			System.err.format("%s: no such" + " file or directory%n", path);
		} catch (DirectoryNotEmptyException x) {
			System.err.format("%s not empty%n", path);
		} catch (IOException x) {
			// File permission problems are caught here.
			System.err.println(x);
		}
	}
}
