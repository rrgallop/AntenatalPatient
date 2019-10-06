package edu.usm.cos420.antenatal.dao.domain;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.Month;
import java.util.TreeMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.usm.cos420.antenatal.dao.domain.VisitDao;
import edu.usm.cos420.antenatal.domain.AntenatalPatient;
import edu.usm.cos420.antenatal.domain.Visit;

public class testVisit {

	VisitDao vDao;

	@Before
	public void setupData() {
		vDao = new VisitDao("_test_visit.json");
	}

	@Test
	public void testSaveAndFind1() {
		
		int size = vDao.list().size();
		Long visitID = (long) size++;
		
		TreeMap<String, Boolean> ailments = new TreeMap<String, Boolean>();
		ailments.put("hiv", true);
		ailments.put("anemia", false);
		ailments.put("syphilis", false);
		ailments.put("pe", true);
		
        AntenatalPatient testPatient = new AntenatalPatient("Billy", "Johnson", "123 Street", "Manhattan", "NYC", "Male",
                "34", LocalDate.of(1999, Month.DECEMBER, 31), 178, 111,
                "O+", "no", LocalDate.of(1899, Month.JANUARY, 1), ailments, 100000L);

		Visit newVisit = new Visit(LocalDate.of(2011, Month.JUNE, 15), testPatient.getPatientId(), "0 weeks", "Hey Billy", ailments, visitID);
		
		vDao.add(newVisit);
		
		Visit retrievedVisit = vDao.find(visitID);
		
		assertNotNull("Dao returns a null visit.", retrievedVisit);
		assertEquals("Stored visit and original visit are not equal ", retrievedVisit.getVisitID(), newVisit.getVisitID());
		assertEquals("Stored visit and original visit are not equal ", retrievedVisit.getDateOfVisit(), newVisit.getDateOfVisit());
		assertEquals("Stored visit and original visit are not equal ", retrievedVisit.getGestation(), newVisit.getGestation());
		assertEquals("Stored visit and original visit are not equal ", retrievedVisit.getPatientId(), newVisit.getPatientId());
	}
	
	@Test
	public void testSaveAndUpdate1() {
		
		int size = vDao.list().size();
		Long visitID1, visitID2, visitID3;
		
		TreeMap<String, Boolean> ailments1 = new TreeMap<String, Boolean>();
		ailments1.put("hiv", false);
		ailments1.put("anemia", false);
		ailments1.put("syphilis", false);
		ailments1.put("pe", false);
		
		TreeMap<String, Boolean> ailments2 = new TreeMap<String, Boolean>();
		ailments1.put("hiv", true);
		ailments1.put("anemia", false);
		ailments1.put("syphilis", false);
		ailments1.put("pe", false);
		
		TreeMap<String, Boolean> ailments3 = new TreeMap<String, Boolean>();
		ailments1.put("hiv", true);
		ailments1.put("anemia", true);
		ailments1.put("syphilis", true);
		ailments1.put("pe", true);
		
		AntenatalPatient patient = new AntenatalPatient("Billy", "Johnson", "123 Some Street", "Portland","Maine", "Male",
				"34", LocalDate.of(1999, Month.DECEMBER, 31), 178.0, 111.0,
				"O+", "no", LocalDate.of(1899, Month.JANUARY, 1), ailments3,1L);
		
		visitID1 = (long) size++;
		Visit visit1 = new Visit(LocalDate.of(2000, Month.JANUARY, 1), testPatient.getPatientId(), "0 weeks", "visit 1, no ailments", ailments1, visitID1);
		vDao.add(visit1);
		
		visitID2 = (long) size++;
		Visit visit2 = new Visit(LocalDate.of(2011, Month.FEBRUARY, 2), testPatient.getPatientId(), "1 week", "visit 2, 2 ailments", ailments2, visitID2);
		vDao.add(visit2);
		
		visitID3 = (long) size++;
		Visit visit3 = new Visit(LocalDate.of(2022, Month.MARCH, 3), testPatient.getPatientId(), "2 weeks", "visit 3, 4 ailments", ailments3, visitID3);
		vDao.add(visit3);
		
		// Check one of the 3 visits to ensure it was stored correctly
		Visit retrievedVisit = vDao.find(visitID1);
		assertNotNull("Dao returns a null visit.", retrievedVisit);
		assertEquals("Stored visit and original visit are not equal ", retrievedVisit.getVisitID(), visit1.getVisitID());
		
		// Modify a visit and ensure it was properly stored
		String originalRemarks = visit2.getRemarks();
		LocalDate originalDate = visit2.getDateOfVisit();
		
		visit2.setRemarks("Good job Billy");
		visit2.setDateOfVisit(originalDate.plusDays(1000L)); //add 1000 days to the original date
		
		vDao.update(visit2);
		
		retrievedVisit = vDao.find(visit2.getVisitID());
		assertNotNull("Dao returns a null visit.", retrievedVisit);
		assertEquals("Stored visit id and original visit id are not equal ", retrievedVisit.getVisitID(), visit2.getVisitID());
		assertEquals("Stored visit gestation and original visit gestation are not equal ", retrievedVisit.getGestation(), visit2.getGestation()); //gestation should remain the same
		assertNotEquals("Modified visit remarks and original visit remarks are equal ", retrievedVisit.getRemarks(), originalRemarks);
		assertNotEquals("Modified visit date and original visit date are equal ", retrievedVisit.getDateOfVisit(), originalDate);
		
		
		// Check the 3rd visit to make sure it was un-modified
		
		retrievedVisit = vDao.find(visitID3);
		
		assertNotNull("Dao returns a null visit.", retrievedVisit);
		assertEquals("Stored visit and original visit are not equal ", retrievedVisit.getVisitID(), visit3.getVisitID());
		assertEquals("Stored visit and original visit are not equal ", retrievedVisit.getDateOfVisit(), visit3.getDateOfVisit());
		assertEquals("Stored visit and original visit are not equal ", retrievedVisit.getGestation(), visit3.getGestation());
		assertEquals("Stored visit and original visit are not equal ", retrievedVisit.getPatientId(), visit3.getPatientId());
		assertEquals("Stored visit and original visit are not equal ", retrievedVisit.getRemarks(), visit3.getRemarks());
		
	}

	/**
	 * Need to delete the file for next test
	 */
	@After
	public void tearDown() {
		System.gc();
		Path path = FileSystems.getDefault().getPath(".", "_test_visit.json");
		try {
			Files.delete(path);
		} catch (NoSuchFileException x) {
			System.err.format("%s: no such file or directory%n", path);
		} catch (DirectoryNotEmptyException x) {
			System.err.format("%s not empty%n", path);
		} catch (IOException x) {
			// File permission problems are caught here.
			System.out.println("attempt to delete");
			System.err.println(x);
		}
	}
}
